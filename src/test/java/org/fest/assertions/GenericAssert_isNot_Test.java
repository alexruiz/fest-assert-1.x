/*
 * Created on Apr 19, 2010
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

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.conditionIsNull;
import static org.fest.assertions.Formatter.format;
import static org.fest.assertions.NotNull.notNull;

import org.junit.*;

/**
 * Tests for <code>{@link GenericAssert#isNot(Condition)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_isNot_Test implements GenericAssert_doesNotSatisfy_orAlias_TestCase {

  @Rule public ExpectedException thrown = none();

  private Object actual;
  private TestAssert assertions;
  private Condition<Object> notNull;

  @Before public void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
    notNull = notNull();
  }

  @Test public void should_pass_if_condition_is_not_satisfied() {
    new TestAssert(null).isNot(notNull);
  }

  @Test public void should_throw_error_if_condition_is_null() {
    thrown.expectNullPointerException(conditionIsNull());
    assertions.isNot(null);
  }

  @Test public void should_fail_if_condition_is_satisfied() {
    thrown.expectAssertionError(String.format("actual value:<%s> should not be:<NotNull>", format(actual)));
    assertions.isNot(notNull);
  }

  @Test public void should_fail_and_display_description_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not be:<NotNull>", format(actual));
    thrown.expectAssertionError(msg);
    assertions.as("A Test")
              .isNot(notNull);
  }

  @Test public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    thrown.expectAssertionError(String.format("actual value:<%s> should not be:<Not Null>", format(actual)));
    assertions.isNot(notNull.as("Not Null"));
  }

  @Test public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not be:<Not Null>", format(actual));
    thrown.expectAssertionError(msg);
    assertions.as("A Test")
              .isNot(notNull.as("Not Null"));
  }

  @Test public void should_fail_with_custom_message_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNot(notNull);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message").isNot(notNull);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNot(notNull.as("Not Null"));
  }
}