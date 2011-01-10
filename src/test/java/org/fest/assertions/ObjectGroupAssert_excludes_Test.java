/*
 * Created on Jun 30, 2010
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
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.*;

/**
 * Tests for <code>{@link ObjectGroupAssert#excludes(Object...)}</code>.
 *
 * @author Yvonne Wang
 */
public final class ObjectGroupAssert_excludes_Test implements GroupAssert_excludes_TestCase {

  @Rule public ExpectedException thrown = none();

  private List<?> actual;
  private TestObjectGroupAssert assertions;

  @Before public void setUp() {
    actual = list("Luke", "Leia");
    assertions = new TestObjectGroupAssert(actual);
  }

  @Test public final void should_pass_if_actual_excludes_given_value() {
    assertions.excludes("Anakin");
  }

  @Test public final void should_pass_if_actual_excludes_given_values() {
    assertions.excludes("Han", "Yoda");
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new TestObjectGroupAssert(null).excludes("Luke");
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new TestObjectGroupAssert(null).as("A Test")
                                   .excludes("Luke");
  }

  @Test public final void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException("The given array should not be null");
    Object[] objects = null;
    assertions.excludes(objects);
  }

  @Test public final void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException("[A Test] The given array should not be null");
    Object[] objects = null;
    assertions.as("A Test")
              .excludes(objects);
  }

  @Test public final void should_fail_if_actual_contains_given_values() {
    thrown.expectAssertionError("<['Luke', 'Leia']> does not exclude element(s):<['Luke']>");
    assertions.excludes("Luke");
  }

  @Test public final void should_fail_and_display_description_if_actual_contains_given_values() {
    String message = "[A Test] <['Luke', 'Leia']> does not exclude element(s):<['Luke']>";
    thrown.expectAssertionError(message);
    assertions.as("A Test")
              .excludes("Luke");
  }

  @Test public final void should_fail_with_custom_message_if_actual_contains_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .excludes("Luke");
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_contains_given_values() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .excludes("Luke");
  }
}