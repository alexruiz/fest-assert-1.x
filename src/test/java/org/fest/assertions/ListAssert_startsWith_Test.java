/*
 * Created on Mar 29, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;

import java.util.List;

import org.fest.test.ExpectedException;
import org.fest.util.Lists;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ListAssert#startsWith(Object...)}.
 * 
 * @author Alex Ruiz
 */
public class ListAssert_startsWith_Test {
  @Rule
  public ExpectedException thrown = none();

  private static final Object[] EMPTY_SEQUENCE = new Object[0];
  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = Lists.newArrayList("Anakin", "Leia");
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    new ListAssert(list).startsWith("Anakin");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new ListAssert(list).startsWith("Anakin", "Leia");
  }

  @Test
  public void should_pass_if_both_actual_and_sequence_are_empty() {
    new ListAssert(newArrayList()).startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_if_actual_is_smaller_than_sequence() {
    String message = "list:<['Anakin', 'Leia']> does not start with the sequence:<['Anakin', 'Leia', 'Han']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).startsWith("Anakin", "Leia", "Han");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_smaller_than_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<['Anakin', 'Leia', 'Han']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).as("A Test").startsWith("Anakin", "Leia", "Han");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_smaller_than_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").startsWith("Anakin", "Leia", "Han");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_smaller_than_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").startsWith("Anakin", "Leia", "Han");
  }

  @Test
  public void should_fail_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expect(AssertionError.class, "list:<['Anakin', 'Leia']> does not start with the sequence:<[]>");
    new ListAssert(list).startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_not_empty_and_sequence_is_empty() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<[]>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).as("A Test").startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_not_empty_and_sequence_is_empty() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    thrown.expect(AssertionError.class, "list:<['Anakin', 'Leia']> does not start with the sequence:<['Leia', 'Anakin']>");
    new ListAssert(list).startsWith("Leia", "Anakin");
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_start_with_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<['Leia', 'Anakin']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).as("A Test").startsWith("Leia", "Anakin");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_start_with_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").startsWith("Leia", "Anakin");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_start_with_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").startsWith("Leia", "Anakin");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ListAssert(null).startsWith("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ListAssert(null).as("A Test").startsWith("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] objects = null;
    new ListAssert(emptyList()).startsWith(objects);
  }
}
