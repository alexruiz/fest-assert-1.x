/*
 * Created on Mar 29, 2009
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

import static java.util.Collections.emptyList;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.*;

/**
 * Tests for <code>{@link ListAssert#endsWith(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_endsWith_Test {

  @Rule public ExpectedException thrown = none();

  private static Object[] EMPTY_SEQUENCE = new Object[0];

  private static List<String> list;

  @BeforeClass public static void setUpOnce() {
    list = list("Anakin", "Leia");
  }

  @Test public void should_pass_if_actual_ends_with_sequence() {
    new ListAssert(list).endsWith("Leia");
  }

  @Test public void should_pass_if_actual_and_expected_are_equal() {
    new ListAssert(list).endsWith("Anakin", "Leia");
  }

  @Test public void should_pass_if_both_actual_and_sequence_are_empty() {
    new ListAssert(emptyList()).endsWith(EMPTY_SEQUENCE);
  }

  @Test public void should_fail_if_actual_is_smaller_than_sequence() {
    String message = "list:<['Anakin', 'Leia']> does not end with the sequence:<['Han', 'Anakin', 'Leia']>";
    thrown.expectAssertionError(message);
    new ListAssert(list).endsWith("Han", "Anakin", "Leia");
  }

  @Test public void should_fail_and_display_description_if_actual_is_smaller_than_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not end with the sequence:<['Han', 'Anakin', 'Leia']>";
    thrown.expectAssertionError(message);
    new ListAssert(list).as("A Test")
                        .endsWith("Han", "Anakin", "Leia");
  }

  @Test public void should_fail_with_custom_message_if_actual_is_smaller_than_sequence() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message")
                        .endsWith("Han", "Anakin", "Leia");
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_smaller_than_sequence() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).as("A Test")
                        .overridingErrorMessage("My custom message")
                        .endsWith("Han", "Anakin", "Leia");
  }

  @Test public void should_fail_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expectAssertionError("list:<['Anakin', 'Leia']> does not end with the sequence:<[]>");
    new ListAssert(list).endsWith(EMPTY_SEQUENCE);
  }

  @Test public void should_fail_and_display_description_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expectAssertionError("[A Test] list:<['Anakin', 'Leia']> does not end with the sequence:<[]>");
    new ListAssert(list).as("A Test")
                        .endsWith(EMPTY_SEQUENCE);
  }

  @Test public void should_fail_with_custom_message_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message")
                        .endsWith(EMPTY_SEQUENCE);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).as("A Test")
                        .overridingErrorMessage("My custom message")
                        .endsWith(EMPTY_SEQUENCE);
  }

  @Test public void should_fail_if_actual_does_not_end_with_sequence() {
    String message = "list:<['Anakin', 'Leia']> does not end with the sequence:<['Leia', 'Anakin']>";
    thrown.expectAssertionError(message);
    new ListAssert(list).endsWith("Leia", "Anakin");
  }

  @Test public void should_fail_and_display_description_if_actual_does_not_end_with_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not end with the sequence:<['Leia', 'Anakin']>";
    thrown.expectAssertionError(message);
    new ListAssert(list).as("A Test")
                        .endsWith("Leia", "Anakin");
  }

  @Test public void should_fail_with_custom_message_if_actual_does_not_end_with_sequence() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message")
                        .endsWith("Leia", "Anakin");
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_end_with_sequence() {
    thrown.expectAssertionError("My custom message");
    new ListAssert(list).as("A Test")
                        .overridingErrorMessage("My custom message")
                        .endsWith("Leia", "Anakin");
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new ListAssert(null).endsWith("Gandalf", "Frodo", "Sam");
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new ListAssert(null).as("A Test")
                        .endsWith("Gandalf", "Frodo", "Sam");
  }

  @Test public void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException("The given array should not be null");
    Object[] objects = null;
    new ListAssert(emptyList()).endsWith(objects);
  }

  @Test public void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException("[A Test] The given array should not be null");
    Object[] objects = null;
    new ListAssert(emptyList()).as("A Test")
                               .endsWith(objects);
  }
}