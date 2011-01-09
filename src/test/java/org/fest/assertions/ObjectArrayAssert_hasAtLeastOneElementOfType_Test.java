/*
 * Created on Oct 27, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.*;

import org.junit.*;

/**
 * Tests for <code>{@link ObjectArrayAssert#hasAtLeastOneElementOfType(Class)}</code>.
 *
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_hasAtLeastOneElementOfType_Test {

  @Rule public ExpectedException thrown = none();

  private static Object[] array;

  @BeforeClass public static void setUpOnce() {
    array = objectArray(6, "Hello");
  }

  @Test public void should_pass_if_actual_has_all_elements_of_the_expected_type() {
    new ObjectArrayAssert(array).hasAtLeastOneElementOfType(Integer.class)
                                .hasAtLeastOneElementOfType(String.class)
                                .hasAtLeastOneElementOfType(Object.class);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    Object[] actual = null;
    new ObjectArrayAssert(actual).hasAtLeastOneElementOfType(Integer.class);
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    Object[] actual = null;
    new ObjectArrayAssert(actual).as("A Test")
                                 .hasAtLeastOneElementOfType(Integer.class);
  }

  @Test public void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException(typeIsNull());
    new ObjectArrayAssert(1, 2).hasAtLeastOneElementOfType(null);
  }

  @Test public void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException(typeIsNull("A Test"));
    new ObjectArrayAssert(1, 2).as("A Test")
                               .hasAtLeastOneElementOfType(null);
  }

  @Test public void should_fail_if_elements_in_actual_do_not_belong_to_expected_type() {
    String message = "array:<[6, 'Hello']> does not have any elements of type:<java.lang.Float>";
    thrown.expectAssertionError(message);
    new ObjectArrayAssert(array).hasAtLeastOneElementOfType(Float.class);
  }

  @Test public void should_fail_and_display_description_if_elements_in_actual_do_not_belong_to_expected_type() {
    String message = "[A Test] array:<[6, 'Hello']> does not have any elements of type:<java.lang.Float>";
    thrown.expectAssertionError(message);
    new ObjectArrayAssert(array).as("A Test")
                                .hasAtLeastOneElementOfType(Float.class);
  }

  @Test public void should_fail_with_custom_message_if_elements_in_actual_do_not_belong_to_expected_type() {
    thrown.expectAssertionError("My custom message");
    new ObjectArrayAssert(array).overridingErrorMessage("My custom message")
                                .hasAtLeastOneElementOfType(Float.class);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_elements_in_actual_do_not_belong_to_expected_type() {
    thrown.expectAssertionError("My custom message");
    new ObjectArrayAssert(array).as("A Test")
                                .overridingErrorMessage("My custom message")
                                .hasAtLeastOneElementOfType(Float.class);
  }
}