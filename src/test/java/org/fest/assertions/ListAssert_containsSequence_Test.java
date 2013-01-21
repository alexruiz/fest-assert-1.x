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
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ListAssert#containsSequence(Object...)}.
 * 
 * @author Alex Ruiz
 */
public class ListAssert_containsSequence_Test {
  @Rule
  public ExpectedException thrown = none();

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = newArrayList("Anakin", "Leia", "Han");
  }

  @Test
  public void should_pass_if_actual_contains_sequence() {
    new ListAssert(list).containsSequence("Anakin", "Leia").containsSequence("Leia", "Han");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new ListAssert(list).containsSequence("Anakin", "Leia", "Han");
  }

  @Test
  public void should_pass_if_both_actual_and_sequence_are_empty() {
    Object[] sequence = new Object[0];
    new ListAssert(emptyList()).containsSequence(sequence);
  }

  @Test
  public void should_pass_if_actual_is_not_empty_and_expected_is_empty() {
    Object[] sequence = new Object[0];
    new ListAssert(list).containsSequence(sequence);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_sequence() {
    String message = "list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Ben']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).containsSequence("Ben");
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Ben']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).as("A Test").containsSequence("Ben");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").containsSequence("Ben");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").containsSequence("Ben");
  }

  @Test
  public void should_fail_if_actual_does_not_contain_some_elements_in_the_sequence() {
    String message = "list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Anakin', 'Ben']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).containsSequence("Anakin", "Ben");
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_some_elements_in_the_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Anakin', 'Ben']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).as("A Test").containsSequence("Anakin", "Ben");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_some_elements_in_the_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").containsSequence("Anakin", "Ben");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_some_elements_in_the_sequence() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").containsSequence("Anakin", "Ben");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ListAssert(null).containsSequence("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ListAssert(null).as("A Test").containsSequence("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] objects = null;
    new ListAssert(emptyList()).containsSequence(objects);
  }

  @Test
  public void should_fail_if_actual_contains_first_elements_of_sequence_and_sequence_is_longer_than_actual() {
    String message = "list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Anakin', 'Leia', 'Han', 'Ben', 'R2-D2']>";
    thrown.expect(AssertionError.class, message);
    new ListAssert(list).containsSequence("Anakin", "Leia", "Han", "Ben", "R2-D2");
  }
}
