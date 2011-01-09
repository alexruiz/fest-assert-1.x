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
 * Copyright @2008-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.charArray;
import static org.fest.assertions.EmptyArrays.emptyCharArray;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link CharArrayAssert#containsOnly(char...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_actual_contains_only_given_values() {
    new CharArrayAssert('a', 'b').containsOnly('a', 'b');
  }

  @Test public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new CharArrayAssert('a', 'b').containsOnly('b', 'a');
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new CharArrayAssert(null).containsOnly(charArray('c', 'd'));
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new CharArrayAssert(null).as("A Test")
                             .containsOnly(charArray('c', 'd'));
  }

  @Test public void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException("The given array should not be null");
    new CharArrayAssert(emptyCharArray()).containsOnly(null);
  }

  @Test public void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException("[A Test] The given array should not be null");
    new CharArrayAssert(emptyCharArray()).as("A Test")
                                         .containsOnly(null);
  }

  @Test public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("<[]> does not contain element(s):<[c, d]>");
    new CharArrayAssert(emptyCharArray()).containsOnly(charArray('c', 'd'));
  }

  @Test public void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("[A Test] <[]> does not contain element(s):<[c, d]>");
    new CharArrayAssert(emptyCharArray()).as("A Test")
                                         .containsOnly(charArray('c', 'd'));
  }

  @Test public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert(emptyCharArray()).overridingErrorMessage("My custom message")
                                         .containsOnly(charArray('c', 'd'));
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert(emptyCharArray()).as("A Test")
                                         .overridingErrorMessage("My custom message")
                                         .containsOnly(charArray('c', 'd'));
  }

  @Test public void should_fail_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("unexpected element(s):<[b]> in <[a, b]>");
    new CharArrayAssert('a', 'b').containsOnly(charArray('a'));
  }

  @Test public void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("[A Test] unexpected element(s):<[b]> in <[a, b]>");
    new CharArrayAssert('a', 'b').as("A Test")
                                 .containsOnly(charArray('a'));
  }

  @Test public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert('a', 'b').overridingErrorMessage("My custom message")
                                 .containsOnly(charArray('a'));
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert('a', 'b').as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .containsOnly(charArray('a'));
  }

  @Test public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("<[a, b]> does not contain element(s):<[c]>");
    new CharArrayAssert('a', 'b').containsOnly(charArray('c'));
  }

  @Test public void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("[A Test] <[a, b]> does not contain element(s):<[c]>");
    new CharArrayAssert('a', 'b').as("A Test")
                                 .containsOnly(charArray('c'));
  }

  @Test public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert('a', 'b').overridingErrorMessage("My custom message")
                                 .containsOnly(charArray('c'));
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("My custom message");
    new CharArrayAssert('a', 'b').as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .containsOnly(charArray('c'));
  }
}
