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

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.ArrayFactory.doubleArray;
import static org.fest.assertions.EmptyArrays.emptyDoubleArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link DoubleArrayAssert#containsOnly(double...)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DoubleArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static double[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = doubleArray(98.6, 8.66);
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new DoubleArrayAssert(array).containsOnly(98.6, 8.66);
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new DoubleArrayAssert(array).containsOnly(8.66, 98.6);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new DoubleArrayAssert(null).containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new DoubleArrayAssert(null).as("A Test").containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new DoubleArrayAssert(emptyDoubleArray()).containsOnly(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "<[]> does not contain element(s):<[5323.2]>");
    new DoubleArrayAssert(emptyDoubleArray()).containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "[A Test] <[]> does not contain element(s):<[5323.2]>");
    new DoubleArrayAssert(emptyDoubleArray()).as("A Test").containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(emptyDoubleArray()).overridingErrorMessage("My custom message").containsOnly(
        doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(emptyDoubleArray()).as("A Test").overridingErrorMessage("My custom message")
        .containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "unexpected element(s):<[98.6]> in <[98.6, 8.66]>");
    new DoubleArrayAssert(array).containsOnly(doubleArray(8.66));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "[A Test] unexpected element(s):<[98.6]> in <[98.6, 8.66]>");
    new DoubleArrayAssert(array).as("A Test").containsOnly(doubleArray(8.66));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).overridingErrorMessage("My custom message").containsOnly(doubleArray(8.66));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).as("A Test").overridingErrorMessage("My custom message")
        .containsOnly(doubleArray(8.66));
  }

  @Override
  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "<[98.6, 8.66]> does not contain element(s):<[5323.2]>");
    new DoubleArrayAssert(array).containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "[A Test] <[98.6, 8.66]> does not contain element(s):<[5323.2]>");
    new DoubleArrayAssert(array).as("A Test").containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).overridingErrorMessage("My custom message").containsOnly(doubleArray(5323.2));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).as("A Test").overridingErrorMessage("My custom message")
        .containsOnly(doubleArray(5323.2));
  }
}
