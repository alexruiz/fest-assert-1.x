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

import static org.fest.assertions.CommonFailures.expectNullPointerException;
import static org.fest.assertions.FailureMessages.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Arrays.copyOf;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.lang.reflect.Array;
import java.util.Collection;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Base class for testing <b>isIn</b> assertions :
 * <ul>
 * <li><code>{@link GenericAssert#isIn(T... values)}</code></li>
 * <li><code>{@link GenericAssert#isIn(Collection values)}.</code></li>
 * </ul>
 * and <b>isNotIn</b> assertions :<br>
 * <ul><br>
 * <li><code>{@link GenericAssert#isNotIn(T... values)}</code></li>
 * <li><code>{@link GenericAssert#isNotIn(Collection values)}.</code></li>
 * </ul>
 * <p>
 * Tests are made against an array and a collection of T supplied by concrete subclasses like {@link StringAssert_isIn_isNotIn_Test}.
 * <p>
 * Subclasses must supply :
 * <ul>
 * <li>The <i>actual value under assertion</i> by implementing {@link #notNullValue()}</li>
 * <li>The concrete instance of Assert to test by implementing {@link #assertionsFor(Object)}.</li>
 * <li>An array of values containing the <i>actual value under assertion</i> by implementing {@link #setUpValuesContainingActual()}</li>
 * </ul>
 * @param <T> The type of the {@code GenericAssert} to test.
 *
 * @author Joel Costigliola
 */
public abstract class GenericAssert_isIn_isNotIn_TestCase<T> extends GenericAssert_TestCase<T> implements Assert_isIn_TestCase, Assert_isNotIn_TestCase {

  private T actual;
  private GenericAssert<T> assertions;
  private GenericAssert<T> assertionsForNull;
  private T[] valuesContainingActual;
  private T[] valuesNotContainingActual;
  private T[] valuesContainingNull;
  private T[] valuesNotContainingNull;
  private Collection<T> collectionContainingActual;
  private Collection<T> collectionNotContainingActual;
  private Collection<T> collectionContainingNull;
  private Collection<T> collectionNotContainingNull;

  @Before
  public void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
    assertionsForNull = assertionsFor(null);
    // array initialization for vararg parameter based tests
    setUpValuesContainingActual();
    setUpValuesNotContainingActual();
    setUpValuesContainingNull();
    valuesNotContainingNull = valuesContainingActual; // a check is done on valuesContainingActual to avoid null elements
    // collection initialization for collection parameter based test
    collectionContainingActual = list(valuesContainingActual);
    collectionNotContainingActual = list(valuesNotContainingActual);
    collectionNotContainingNull = list(valuesContainingActual);
    collectionContainingNull = list(valuesContainingActual); // a check is done on valuesContainingActual to avoid null elements
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
  public final void isIn_should_pass_if_actual_is_in_given_values() {
    assertions.isIn(valuesContainingActual);
    assertions.isIn(collectionContainingActual);
  }

  @Test
  public final void isIn_should_pass_if_actual_is_null_and_is_in_given_values() {
    assertionsForNull.isIn(valuesContainingNull);
    assertionsForNull.isIn(collectionContainingNull);
  }

  @Test
  public final void isIn_should_fail_if_actual_is_not_in_given_values() {
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
  public final void isIn_should_fail_if_actual_is_null_and_is_not_in_given_values() {
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
  public final void isIn_should_fail_if_given_values_parameter_is_null() {
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
  public final void isIn_should_throw_error_if_given_values_parameter_is_null() {
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
  public final void isIn_should_throw_error_and_display_description_of_assertion_if_given_values_parameter_is_null() {
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
  public final void isIn_should_fail_and_display_description_of_assertion_if_actual_is_not_in_given_values() {
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
  public final void isIn_should_fail_with_custom_message_if_actual_is_not_in_given_values() {
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
  public final void isIn_should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_in_given_values() {
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

  // ----------------------------------------------------------------------------------------------
  // isNotIn tests
  // ----------------------------------------------------------------------------------------------

  @Test
  public final void isNotIn_should_pass_if_actual_is_not_in_given_values() {
    assertions.isNotIn(valuesNotContainingActual);
    assertions.isNotIn(collectionNotContainingActual);
  }

  @Test
  public final void isNotIn_should_pass_if_actual_is_null_and_is_not_in_given_values() {
    assertionsForNull.isNotIn(valuesNotContainingNull);
    assertionsForNull.isNotIn(collectionNotContainingNull);
  }

  @Test
  public final void isNotIn_should_fail_if_actual_is_in_given_values() {
    expectAssertionError(unexpectedIn(null, actual, valuesContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.isNotIn(valuesContainingActual);
      }
    });
    expectAssertionError(unexpectedIn(null, actual, collectionContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.isNotIn(collectionContainingActual);
      }
    });
  }

  @Test
  public final void isNotIn_should_fail_if_actual_is_null_and_is_in_given_values() {
    expectAssertionError(unexpectedIn(null, null, valuesContainingNull)).on(new CodeToTest() {
      public void run() {
        assertionsForNull.isNotIn(valuesContainingNull);
      }
    });
    expectAssertionError(unexpectedIn(null, null, collectionContainingNull)).on(new CodeToTest() {
      public void run() {
        assertionsForNull.isNotIn(collectionContainingNull);
      }
    });
  }

  @Test
  public final void isNotIn_should_fail_if_given_values_parameter_is_null() {
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertionsForNull.isNotIn(nullArray);
      }
    });
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertionsForNull.isNotIn(nullCollection);
      }
    });
  }

  @Test
  public final void isNotIn_should_throw_error_if_given_values_parameter_is_null() {
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertions.isNotIn(nullArray);
      }
    });
    expectNullPointerException("expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertions.isNotIn(nullCollection);
      }
    });
  }

  @Test
  public final void isNotIn_should_throw_error_and_display_description_of_assertion_if_given_values_parameter_is_null() {
    expectNullPointerException("[A Test] expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        T[] nullArray = null;
        assertions.as("A Test").isNotIn(nullArray);
      }
    });
    expectNullPointerException("[A Test] expecting values parameter not to be null").on(new CodeToTest() {
      public void run() {
        Collection<T> nullCollection = null;
        assertions.as("A Test").isNotIn(nullCollection);
      }
    });
  }

  @Test
  public final void isNotIn_should_fail_and_display_description_of_assertion_if_actual_is_in_given_values() {
    expectAssertionError(unexpectedIn("A Test", actual, valuesContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").isNotIn(valuesContainingActual);
      }
    });
    expectAssertionError(unexpectedIn("A Test", actual, collectionContainingActual)).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").isNotIn(collectionContainingActual);
      }
    });
  }

  @Test
  public final void isNotIn_should_fail_with_custom_message_if_actual_is_in_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message").isNotIn(valuesContainingActual);
      }
    });
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message").isNotIn(collectionContainingActual);
      }
    });
  }

  @Test
  public final void isNotIn_should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_in_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").overridingErrorMessage("My custom message").isNotIn(valuesContainingActual);
      }
    });
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").overridingErrorMessage("My custom message").isNotIn(collectionContainingActual);
      }
    });
  }

}
