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
import static org.fest.assertions.FailureMessages.notIn;
import static org.fest.util.Arrays.array;

import org.junit.*;

/**
 * Tests for <code>{@link GenericAssert#isIn(Object... values)}</code>.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public final class GenericAssert_isIn_withArray_Test implements Assert_isIn_TestCase {

  @Rule public ExpectedException thrown = none();

  private String actual;
  private TestAssert assertions;
  private TestAssert assertionsForNull;
  private Object[] containingActual;
  private Object[] notContainingActual;
  private Object[] containingNull;

  @Before public void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
    assertionsForNull = new TestAssert(null);
    containingActual = array("Hello", "Bye");
    notContainingActual = array("Bye");
    containingNull = array("Hello", "Bye", null);
  }

  @Test public final void should_pass_if_actual_is_in_given_values() {
    assertions.isIn(containingActual);
  }

  @Test public final void should_pass_if_actual_is_null_and_is_in_given_values() {
    assertionsForNull.isIn(containingNull);
  }

  @Test public final void should_fail_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError(notIn(null, actual, notContainingActual));
    assertions.isIn(notContainingActual);
  }

  @Test public final void isIn_should_fail_if_actual_is_null_and_is_not_in_given_values() {
    thrown.expectAssertionError(notIn(null, null, containingActual));
    assertionsForNull.isIn(containingActual);
  }

  @Test public final void isIn_should_fail_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Object[] nullArray = null;
    assertionsForNull.isIn(nullArray);
  }

  @Test public final void should_throw_error_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("expecting values parameter not to be null");
    Object[] nullArray = null;
    assertions.isIn(nullArray);
  }

  @Test public final void should_throw_error_and_display_description_if_given_values_parameter_is_null() {
    thrown.expectNullPointerException("[A Test] expecting values parameter not to be null");
    Object[] nullArray = null;
    assertions.as("A Test")
              .isIn(nullArray);
  }

  @Test public final void should_fail_and_display_description_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError(notIn("A Test", actual, notContainingActual));
    assertions.as("A Test")
              .isIn(notContainingActual);
  }

  @Test public final void should_fail_with_custom_message_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isIn(notContainingActual);
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_is_not_in_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isIn(notContainingActual);
  }

  @Test public void should_fail_if_values_is_empty() {
    Object[] values = new Object[0];
    thrown.expectAssertionError(notIn(null, actual, values));
    assertions.isIn(values);
  }
}
