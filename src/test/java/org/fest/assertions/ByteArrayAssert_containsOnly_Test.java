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

import static org.fest.assertions.ArrayFactory.byteArray;
import static org.fest.assertions.EmptyArrays.emptyByteArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ByteArrayAssert#containsOnly(byte...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {
  @Rule
  public ExpectedException thrown = none();

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new ByteArrayAssert(byteArray(8)).containsOnly(byteArray(8));
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(6, 8));
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ByteArrayAssert(null).containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ByteArrayAssert(null).as("A Test").containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new ByteArrayAssert(emptyByteArray()).containsOnly(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "<[]> does not contain element(s):<[7]>");
    new ByteArrayAssert(emptyByteArray()).containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "[A Test] <[]> does not contain element(s):<[7]>");
    new ByteArrayAssert(emptyByteArray()).as("A Test").containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(emptyByteArray()).overridingErrorMessage("My custom message").containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(emptyByteArray()).as("A Test").overridingErrorMessage("My custom message")
    .containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "unexpected element(s):<[6]> in <[8, 6]>");
    new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(8));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "[A Test] unexpected element(s):<[6]> in <[8, 6]>");
    new ByteArrayAssert(byteArray(8, 6)).as("A Test").containsOnly(byteArray(8));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(byteArray(8, 6)).overridingErrorMessage("My custom message").containsOnly(byteArray(8));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(byteArray(8, 6)).as("A Test").overridingErrorMessage("My custom message")
    .containsOnly(byteArray(8));
  }

  @Override
  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "<[8, 6]> does not contain element(s):<[7]>");
    new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "[A Test] <[8, 6]> does not contain element(s):<[7]>");
    new ByteArrayAssert(byteArray(8, 6)).as("A Test").containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(byteArray(8, 6)).overridingErrorMessage("My custom message").containsOnly(byteArray(7));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteArrayAssert(byteArray(8, 6)).as("A Test").overridingErrorMessage("My custom message")
    .containsOnly(byteArray(7));
  }
}
