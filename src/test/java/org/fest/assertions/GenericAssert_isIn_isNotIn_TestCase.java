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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.*;
import static org.fest.util.Arrays.copyOf;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.lang.reflect.Array;
import java.util.Collection;

import org.junit.*;

/**
 * Base class for testing <b>isIn</b> assertions :
 * <ul>
 * <li><code>{@link GenericAssert#isIn(A... values)}</code></li>
 * <li><code>{@link GenericAssert#isIn(Collection values)}.</code></li>
 * </ul>
 * and <b>isNotIn</b> assertions :<br>
 * <ul><br>
 * <li><code>{@link GenericAssert#isNotIn(A... values)}</code></li>
 * <li><code>{@link GenericAssert#isNotIn(Collection values)}.</code></li>
 * </ul>
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type of the {@code GenericAssert} to test.
 *
 * TODO separate this test classes into 2: one for array and one for collection.
 *
 * @author Joel Costigliola
 */
public abstract class GenericAssert_isIn_isNotIn_TestCase<S extends GenericAssert<S, A>, A> extends
    GenericAssert_TestCase<S, A> implements Assert_isIn_TestCase, Assert_isNotIn_TestCase {

  @Rule public ExpectedException thrown = none();

  private A actual;
  private GenericAssert<S, A> assertions;
  private GenericAssert<S, A> assertionsForNull;
  private A[] valuesContainingActual;
  private A[] valuesNotContainingActual;
  private A[] valuesContainingNull;
  private A[] valuesNotContainingNull;
  private Collection<A> collectionContainingActual;
  private Collection<A> collectionNotContainingActual;
  private Collection<A> collectionContainingNull;
  private Collection<A> collectionNotContainingNull;

  @Before public void setUp() {
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
   * Implement this method by calling {@link #initValuesContainingActual(A ... values)} with non {@code null} values
   * including the actual value returned by {@link #notNullValue()}.
   */
  protected abstract void setUpValuesContainingActual();

  /**
   * Used to initialize the array {@code valuesContainingActual}. Don't put {@code null} values as all test cases
   * involving {@code null} are handled automatically.
   * @param values values used in tests ({@code null} elements are not permitted)
   * @throws NullPointerException if one to the given values is {@code null}
   */
  protected void initValuesContainingActual(A... values) {
    for (A value : values) {
      if (value == null) { throw new NullPointerException(
          "null elements are not permitted when initializing valuesContainingActual (all test cases involving null are handled automatically, don't worry !)"); }
    }
    this.valuesContainingActual = values;
  }

  private void setUpValuesContainingNull() {
    valuesContainingNull = copyOf(valuesContainingActual, valuesContainingActual.length + 1);
    valuesContainingNull[valuesContainingActual.length] = null;
  }

  @SuppressWarnings("unchecked")
  private void setUpValuesNotContainingActual() {
    Class<?> arrayType = valuesContainingActual.getClass().getComponentType();
    valuesNotContainingActual = (A[]) Array.newInstance(arrayType, valuesContainingActual.length - 1);
    int j = 0;
    for (int i = 0; i < valuesContainingActual.length; i++) {
      if (!areEqual(valuesContainingActual[i], actual)) {
        valuesNotContainingActual[j] = valuesContainingActual[i];
        j++;
      }
    }
  }

  @Test public final void isIn_should_pass_if_actual_is_in_given_values() {
    assertions.isIn(valuesContainingActual);
    assertions.isIn(collectionContainingActual);
  }

  @Test public final void isIn_should_pass_if_actual_is_null_and_is_in_given_values() {
    assertionsForNull.isIn(valuesContainingNull);
    assertionsForNull.isIn(collectionContainingNull);
  }

  @Test public final void isIn_should_fail_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError(notIn(null, actual, valuesNotContainingActual));
    assertions.isIn(valuesNotContainingActual);
    thrown.expectAssertionError(notIn(null, actual, collectionNotContainingActual));
    assertions.isIn(collectionNotContainingActual);
  }

  @Test public final void isIn_should_fail_if_actual_is_null_and_is_not_in_given_values() {
    thrown.expectAssertionError(notIn(null, null, valuesContainingActual));
    assertionsForNull.isIn(valuesContainingActual);
    thrown.expectAssertionError(notIn(null, null, collectionContainingActual));
    assertionsForNull.isIn(collectionContainingActual);
  }

  @Test public final void isIn_should_fail_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    A[] nullArray = null;
    assertionsForNull.isIn(nullArray);
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertionsForNull.isIn(nullCollection);
  }

  @Test public final void isIn_should_throw_error_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    A[] nullArray = null;
    assertions.isIn(nullArray);
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertions.isIn(nullCollection);
  }

  @Test public final void isIn_should_throw_error_and_display_description_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("[A Test] expecting values parameter not to be null");
    A[] nullArray = null;
    assertions.as("A Test").isIn(nullArray);
    thrown.expectNullPointerException("[A Test] expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertions.as("A Test").isIn(nullCollection);
  }

  @Test public final void isIn_should_fail_and_display_description_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError(notIn("A Test", actual, valuesNotContainingActual));
    assertions.as("A Test").isIn(valuesNotContainingActual);
    thrown.expectAssertionError(notIn("A Test", actual, collectionNotContainingActual));
    assertions.as("A Test").isIn(collectionNotContainingActual);
  }

  @Test public final void isIn_should_fail_with_custom_message_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isIn(valuesNotContainingActual);
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isIn(collectionNotContainingActual);
  }

  @Test public final void isIn_should_fail_with_custom_message_ignoring_description_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isIn(valuesNotContainingActual);
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isIn(collectionNotContainingActual);
  }

  // ----------------------------------------------------------------------------------------------
  // isNotIn tests
  // ----------------------------------------------------------------------------------------------

  @Test public final void isNotIn_should_pass_if_actual_is_not_in_given_values() {
    assertions.isNotIn(valuesNotContainingActual);
    assertions.isNotIn(collectionNotContainingActual);
  }

  @Test public final void isNotIn_should_pass_if_actual_is_null_and_is_not_in_given_values() {
    assertionsForNull.isNotIn(valuesNotContainingNull);
    assertionsForNull.isNotIn(collectionNotContainingNull);
  }

  @Test public final void isNotIn_should_fail_if_actual_is_in_given_values() {
    thrown.expectAssertionError(in(null, actual, valuesContainingActual));
    assertions.isNotIn(valuesContainingActual);
    thrown.expectAssertionError(in(null, actual, collectionContainingActual));
    assertions.isNotIn(collectionContainingActual);
  }

  @Test public final void isNotIn_should_fail_if_actual_is_null_and_is_in_given_values() {
    thrown.expectAssertionError(in(null, null, valuesContainingNull));
    assertionsForNull.isNotIn(valuesContainingNull);
    thrown.expectAssertionError(in(null, null, collectionContainingNull));
    assertionsForNull.isNotIn(collectionContainingNull);
  }

  @Test public final void isNotIn_should_fail_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    A[] nullArray = null;
    assertionsForNull.isNotIn(nullArray);
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertionsForNull.isNotIn(nullCollection);
  }

  @Test public final void isNotIn_should_throw_error_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    A[] nullArray = null;
    assertions.isNotIn(nullArray);
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertions.isNotIn(nullCollection);
  }

  @Test public final void isNotIn_should_throw_error_and_display_description_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("[A Test] expecting values parameter not to be null");
    A[] nullArray = null;
    assertions.as("A Test")
              .isNotIn(nullArray);
    thrown.expectNullPointerException("[A Test] expecting values parameter not to be null");
    Collection<A> nullCollection = null;
    assertions.as("A Test")
              .isNotIn(nullCollection);
  }

  @Test public final void isNotIn_should_fail_and_display_description_if_actual_is_in_given_values() {
    thrown.expectAssertionError(in("A Test", actual, valuesContainingActual));
    assertions.as("A Test")
              .isNotIn(valuesContainingActual);
    thrown.expectAssertionError(in("A Test", actual, collectionContainingActual));
    assertions.as("A Test")
              .isNotIn(collectionContainingActual);
  }

  @Test public final void isNotIn_should_fail_with_custom_message_if_actual_is_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNotIn(valuesContainingActual);
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNotIn(collectionContainingActual);
  }

  @Test public final void isNotIn_should_fail_with_custom_message_ignoring_description_if_actual_is_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isNotIn(valuesContainingActual);
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isNotIn(collectionContainingActual);
  }
}
