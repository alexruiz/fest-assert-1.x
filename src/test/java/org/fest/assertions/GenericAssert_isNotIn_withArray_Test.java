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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.FailureMessages.in;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Arrays.array;

/**
 * Tests for code>{@link GenericAssert#isNotIn(Object...)}.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public final class GenericAssert_isNotIn_withArray_Test implements Assert_isNotIn_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private String actual;
  private TestAssert assertions;
  private TestAssert assertionsForNull;
  private Object[] containingActual;
  private Object[] notContainingActual;
  private Object[] containingNull;

  @Before
  public void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
    assertionsForNull = new TestAssert(null);
    containingActual = array("Hello", "Bye");
    notContainingActual = array("Bye");
    containingNull = array("Hello", "Bye", null);
  }

  @Override
  @Test
  public final void should_pass_if_actual_is_not_in_given_values() {
    assertions.isNotIn(notContainingActual);
  }

  @Override
  @Test
  public final void should_pass_if_actual_is_null_and_is_not_in_given_values() {
    assertionsForNull.isNotIn(containingActual);
  }

  @Override
  @Test
  public final void should_fail_if_actual_is_in_given_values() {
    thrown.expect(AssertionError.class, in(null, actual, containingActual));
    assertions.isNotIn(containingActual);
  }

  @Test
  public final void isNotIn_should_fail_if_actual_is_null_and_is_in_given_values() {
    thrown.expect(AssertionError.class, in(null, null, containingNull));
    assertionsForNull.isNotIn(containingNull);
  }

  @Test
  public final void isNotIn_should_fail_if_given_values_parameter_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] nullArray = null;
    assertionsForNull.isNotIn(nullArray);
  }

  @Override
  @Test
  public final void should_throw_error_if_given_values_parameter_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] nullArray = null;
    assertions.isNotIn(nullArray);
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_is_in_given_values() {
    thrown.expect(AssertionError.class, in("A Test", actual, containingActual));
    assertions.as("A Test").isNotIn(containingActual);
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_is_in_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isNotIn(containingActual);
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_is_in_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isNotIn(containingActual);
  }
}
