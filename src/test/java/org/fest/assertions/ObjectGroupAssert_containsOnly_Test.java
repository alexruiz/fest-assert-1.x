/*
 * Created on Jun 29, 2010
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

import java.util.List;

import static java.util.Collections.emptyList;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;

/**
 * Tests for {@link ObjectGroupAssert#containsOnly(Object...)}.
 *
 * @author Yvonne Wang
 */
public final class ObjectGroupAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private List<?> actual;
  private TestObjectGroupAssert assertions;

  @Before
  public void setUp() {
    actual = newArrayList("Gandalf", "Frodo", "Sam");
    assertions = new TestObjectGroupAssert(actual);
  }

  @Override
  @Test
  public final void should_pass_if_actual_contains_only_given_values() {
    assertions.containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Override
  @Test
  public final void should_pass_if_actual_contains_only_given_values_in_different_order() {
    assertions.containsOnly("Sam", "Frodo", "Gandalf");
  }

  @Override
  @Test
  public final void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new TestObjectGroupAssert(null).containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new TestObjectGroupAssert(null).as("A Test").containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Override
  @Test
  public final void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] objects = null;
    assertions.containsOnly(objects);
  }

  @Override
  @Test
  public final void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "<[]> does not contain element(s):<['Sam']>");
    new TestObjectGroupAssert(emptyList()).containsOnly("Sam");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "[A Test] <[]> does not contain element(s):<['Sam']>");
    new TestObjectGroupAssert(emptyList()).as("A Test").containsOnly("Sam");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").containsOnly("Sam");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").containsOnly("Sam");
  }

  @Override
  @Test
  public final void should_fail_if_actual_contains_unexpected_values() {
    String message = "unexpected element(s):<['Sam']> in <['Gandalf', 'Frodo', 'Sam']>";
    thrown.expect(AssertionError.class, message);
    assertions.containsOnly("Gandalf", "Frodo");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_contains_unexpected_values() {
    String message = "[A Test] unexpected element(s):<['Sam']> in <['Gandalf', 'Frodo', 'Sam']>";
    thrown.expect(AssertionError.class, message);
    assertions.as("A Test").containsOnly("Gandalf", "Frodo");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").containsOnly("Gandalf", "Frodo");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_contains_unexpected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").containsOnly("Gandalf", "Frodo");
  }

  @Override
  @Test
  public final void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    String message = "<['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    thrown.expect(AssertionError.class, message);
    assertions.containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_does_not_contain_all_the_expected_values() {
    String message = "[A Test] <['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    thrown.expect(AssertionError.class, message);
    assertions.as("A Test").containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_all_the_expected_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message")
        .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
  }
}