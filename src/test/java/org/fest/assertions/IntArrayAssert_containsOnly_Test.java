/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.intArray;
import static org.fest.assertions.EmptyArrays.emptyIntArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link IntArrayAssert#containsOnly(int...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static int[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = intArray(6, 8);
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new IntArrayAssert(array).containsOnly(6, 8);
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new IntArrayAssert(array).containsOnly(8, 6);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new IntArrayAssert(null).containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new IntArrayAssert(null).as("A Test").containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new IntArrayAssert(emptyIntArray()).containsOnly(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "<[]> does not contain element(s):<[10, 2]>");
    new IntArrayAssert(emptyIntArray()).containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "[A Test] <[]> does not contain element(s):<[10, 2]>");
    new IntArrayAssert(emptyIntArray()).as("A Test").containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(emptyIntArray()).overridingErrorMessage("My custom message").containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(emptyIntArray()).as("A Test").overridingErrorMessage("My custom message")
    .containsOnly(intArray(10, 2));
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "unexpected element(s):<[8]> in <[6, 8]>");
    new IntArrayAssert(array).containsOnly(intArray(6));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "[A Test] unexpected element(s):<[8]> in <[6, 8]>");
    new IntArrayAssert(array).as("A Test").containsOnly(intArray(6));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").containsOnly(intArray(6));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").containsOnly(intArray(6));
  }

  @Override
  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "<[6, 8]> does not contain element(s):<[10]>");
    new IntArrayAssert(array).containsOnly(intArray(10));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "[A Test] <[6, 8]> does not contain element(s):<[10]>");
    new IntArrayAssert(array).as("A Test").containsOnly(intArray(10));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(array).overridingErrorMessage("My custom message").containsOnly(intArray(10));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new IntArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").containsOnly(intArray(10));
  }
}