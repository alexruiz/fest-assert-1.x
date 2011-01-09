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

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;

import org.junit.*;

/**
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#containsOnly(Object...)}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert_containsOnly_TestCase<S extends ObjectGroupAssert<S, A>, A> extends
    ObjectGroupAssert_TestCase<S, A> implements GroupAssert_containsOnly_TestCase {

  @Rule public ExpectedException thrown = none();

  private static Object[] actualValues;
  private static Object[] emptyValues;

  @BeforeClass public static void setUpOnce() {
    actualValues = objectArray("Gandalf", "Frodo", "Sam");
    emptyValues = new Object[0];
  }

  private A actual;
  private ObjectGroupAssert<S, A> assertions;
  private A empty;

  @Before public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
    empty = actualFrom(emptyValues);
  }

  @Test public final void should_pass_if_actual_contains_only_given_values() {
    assertions.containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test public final void should_pass_if_actual_contains_only_given_values_in_different_order() {
    assertions.containsOnly("Sam", "Frodo", "Gandalf");
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    assertionsFor(null).containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    assertionsFor(null).as("A Test")
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
    assertionsFor(empty).containsOnly("Sam");
  }

  @Test public final void should_fail_and_display_description_if_actual_is_empty_and_expecting_at_least_one_element() {
    thrown.expectAssertionError("[A Test] <[]> does not contain element(s):<['Sam']>");
    assertionsFor(empty).as("A Test")
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