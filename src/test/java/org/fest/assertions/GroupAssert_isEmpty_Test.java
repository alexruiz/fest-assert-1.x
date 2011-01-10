/*
 * Created on Sep 8, 2009
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
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.Formatter.format;
import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.*;

/**
 * Tests for <code>{@link GroupAssert#isEmpty()}</code>.
 *
 * @author Alex Ruiz
 */
public final class GroupAssert_isEmpty_Test {

  @Rule public ExpectedException thrown = none();

  private static List<String> actual;
  private static TestObjectGroupAssert assertions;

  @BeforeClass public static void setUpOnce() {
    actual = list("Hello");
    assertions = new TestObjectGroupAssert(actual);
  }

  @Test public final void should_pass_if_actual_is_empty() {
    new TestObjectGroupAssert(emptyList()).isEmpty();
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new TestObjectGroupAssert(null).isEmpty();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new TestObjectGroupAssert(null).as("A Test")
                             .isEmpty();
  }

  @Test public final void should_fail_if_actual_is_not_empty() {
    thrown.expectAssertionError(String.format("expecting empty, but was:<%s>", format(actual)));
    assertions.isEmpty();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_not_empty() {
    thrown.expectAssertionError(String.format("[A Test] expecting empty, but was:<%s>", format(actual)));
    assertions.as("A Test")
              .isEmpty();
  }

  @Test public final void should_fail_with_custom_message_if_actual_is_not_empty() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isEmpty();
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_is_not_empty() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isEmpty();
  }
}