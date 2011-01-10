/*
 * Created on Jun 29, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.*;

/**
 * Tests for <code>{@link ObjectGroupAssert#containsOnly(Object...)}</code>.
 *
 * @author Yvonne Wang
 */
public final class ObjectGroupAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {

  @Rule public ExpectedException thrown = none();

  private List<?> actual;
  private TestObjectGroupAssert assertions;

  @Before public void setUp() {
    actual = list("Gandalf", "Frodo", "Sam");
    assertions = new TestObjectGroupAssert(actual);
  }

  @Test public final void should_pass_if_actual_contains_only_given_values() {
    assertions.containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test public final void should_pass_if_actual_contains_only_given_values_in_different_order() {
    assertions.containsOnly("Sam", "Frodo", "Gandalf");
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new TestObjectGroupAssert(null).containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new TestObjectGroupAssert(null).as("A Test")
                                   .containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test public final void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException("The given array should not be null");
    Object[] objects = null;
    assertions.containsOnly(objects);
  }

  @Test public final void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException("[A Test] The given array should not be null");
    Object[] objects = null;
    assertions.as("A Test")
              .containsOnly(objects);
  }

  @Test public final void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("<[]> does not contain element(s):<['Sam']>");
    new TestObjectGroupAssert(emptyList()).containsOnly("Sam");
  }

  @Test public final void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("[A Test] <[]> does not contain element(s):<['Sam']>");
    new TestObjectGroupAssert(emptyList()).as("A Test")
                                          .containsOnly("Sam");
  }

  @Test public final void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .containsOnly("Sam");
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .containsOnly("Sam");
  }

  @Test public final void should_fail_if_actual_contains_unexpected_values() {
    String message = "unexpected element(s):<['Sam']> in <['Gandalf', 'Frodo', 'Sam']>";
    thrown.expectAssertionError(message);
    assertions.containsOnly("Gandalf", "Frodo");
  }

  @Test public final void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    String message = "[A Test] unexpected element(s):<['Sam']> in <['Gandalf', 'Frodo', 'Sam']>";
    thrown.expectAssertionError(message);
    assertions.as("A Test")
              .containsOnly("Gandalf", "Frodo");
  }

  @Test public final void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .containsOnly("Gandalf", "Frodo");
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .containsOnly("Gandalf", "Frodo");
  }

  @Test public final void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    String message = "<['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    thrown.expectAssertionError(message);
    assertions.containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Test public final void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    String message = "[A Test] <['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    thrown.expectAssertionError(message);
    assertions.as("A Test")
              .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Test public final void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }
}