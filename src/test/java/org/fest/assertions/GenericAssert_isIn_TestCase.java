/*
 * Created on Apr 23, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Arrays.copyOf;

import static org.fest.assertions.CommonFailures.expectNullPointerException;
import static org.fest.assertions.FailureMessages.unexpectedNotIn;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.lang.reflect.Array;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import org.fest.test.CodeToTest;

/**
 * Base class for testing <code>{@link GenericAssert#isIn(T... values)}</code> and
 * <code>{@link GenericAssert#isIn(Collection values)}.</code>
 * <p>
 * The idea is to tests against an array and a collection of T supplied by subclasses,
 * 
 * Subclasses must supply :
 * <ul>
 * <li>actual value by implementing {@link #notNullValue()}</li>
 * <li>a concrete instance of the subclass of GenericAssert under test by implementing
 * {@link GenericAssert_TestCase#assertionsFor(Object)}.</li>
 * <li>an array of values containing actual by implementing {@link #setUpValuesContainingActual()}</li>
 * </ul>
 * @param <T> The type of the {@code GenericAssert} to test.
 * 
 * @author Joel Costigliola
 */
public abstract class GenericAssert_isIn_TestCase<T> extends GenericAssert_TestCase<T> implements Assert_isIn_TestCase {

  private T actual;
  private GenericAssert<T> assertions;
  private GenericAssert<T> assertionsForNull;
  private T[] valuesContainingActual;
  private T[] valuesNotContainingActual;
  private T[] valuesContainingNull;
  private Collection<T> collectionContainingActual;
  private Collection<T> collectionNotContainingActual;
  private Collection<T> collectionContainingNull;

  @Before
  public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
    assertionsForNull = assertionsFor(null);
    // array initialization for vararg parameter based tests
    setUpValuesContainingActual();
    setUpValuesNotContainingActual();
    setUpValuesContainingNull();
    // collection initialization for collection parameter based test
    collectionContainingActual = list(valuesContainingActual);
    collectionNotContainingActual = list(valuesNotContainingActual);
    collectionContainingNull = list(valuesContainingActual);
    collectionContainingNull.add(null);
  }

  /**
   * implements this method by calling {@link #initValuesContainingActual(T ... values)} with non null values including
   * the actual value returned by {@link #notNullValue()}
   */
  protected abstract void setUpValuesContainingActual();

  /**
   * used to init the array valuesContainingActual, don't put null values as all test cases involving null are handled
   * automatically.
   * @param values values used in tests (null elements are not permitted)
   * @throws NullPointerException if one ot the given values is null
   */
  protected void initValuesContainingActual(T... values) {
    for (T value : values) {
      if (value == null) { throw new NullPointerException(
          "null elements are not permitted when initializing valuesContainingActual (all test cases involving null are handled automatically, don't worry !)"); }
    }
    this.valuesContainingActual = values;
  }

  /**
   * build valuesContainingNull = valuesContainingActual + null
   */
  private void setUpValuesContainingNull() {
    valuesContainingNull = copyOf(valuesContainingActual, valuesContainingActual.length + 1);
    valuesContainingNull[valuesContainingActual.length] = null;
  }

  /**
   * build valuesNotContainingActual = valuesContainingActual - actual value
   */
  @SuppressWarnings("unchecked")
  private void setUpValuesNotContainingActual() {
    valuesNotContainingActual = (T[]) Array.newInstance(valuesContainingActual.getClass().getComponentType(),
        valuesContainingActual.length - 1);
    int j = 0;
    for (int i = 0; i < valuesContainingActual.length; i++) {
      if (!areEqual(valuesContainingActual[i], actual)) {
        valuesNotContainingActual[j] = valuesContainingActual[i];
        j++;
      }
    }
  }

  @Test
  public final void should_pass_if_actual_is_in_given_values() {
    assertions.isIn(valuesContainingActual);
    assertions.isIn(collectionContainingActual);
  }

  @Test
  public final void should_pass_if_actual_is_null_and_is_in_given_values() {
    assertionsForNull.isIn(valuesContainingNull);
    assertionsForNull.isIn(collectionContainingNull);
  }

  @Test
  public final void should_fail_if_actual_is_not_in_given_values() {
    expectAssertionError(unexpectedNotIn(null, actual, valuesNotContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.isIn(valuesNotContainingActual);
      }
    });
    expectAssertionError(unexpectedNotIn(null, actual, collectionNotContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.isIn(collectionNotContainingActual);
      }
    });
  }

  @Test
  public final void should_fail_if_actual_is_null_and_is_not_in_given_values() {
    expectAssertionError(unexpectedNotIn(null, null, valuesContainingActual)).on(new CodeToTest() {
      public void run() {
        assertionsForNull.isIn(valuesContainingActual);
      }
    });
    expectAssertionError(unexpectedNotIn(null, null, collectionContainingActual)).on(new CodeToTest() {
      public void run() {
        assertionsForNull.isIn(collectionContainingActual);
      }
    });
  }

  @Test
  public final void should_fail_if_given_values_parameter_is_null() {
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertionsForNull.isIn(nullArray);
      }
    });
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertionsForNull.isIn(nullCollection);
      }
    });
  }

  @Test
  public final void should_throw_error_if_given_values_parameter_is_null() {
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertions.isIn(nullArray);
      }
    });
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertions.isIn(nullCollection);
      }
    });
  }

  @Test
  public final void should_throw_error_and_display_description_of_assertion_if_given_values_parameter_is_null() {
    expectNullPointerException("[A Test] expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertions.as("A Test").isIn(nullArray);
      }
    });
    expectNullPointerException("[A Test] expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertions.as("A Test").isIn(nullCollection);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_not_in_given_values() {
    expectAssertionError(unexpectedNotIn("A Test", actual, valuesNotContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").isIn(valuesNotContainingActual);
      }
    });
    expectAssertionError(unexpectedNotIn("A Test", actual, collectionNotContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").isIn(collectionNotContainingActual);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_is_not_in_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message").isIn(valuesNotContainingActual);
      }
    });
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message").isIn(collectionNotContainingActual);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_in_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").overridingErrorMessage("My custom message").isIn(valuesNotContainingActual);
      }
    });
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").overridingErrorMessage("My custom message").isIn(collectionNotContainingActual);
      }
    });
  }
}
