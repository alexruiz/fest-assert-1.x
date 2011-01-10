/*
 * Created on Jul 1, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.util.Collections.list;

import java.util.List;

import org.junit.*;

/**
 * Tests for <code>{@link ObjectGroupAssert#isNullOrEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class ObjectGroupAssert_isNullOrEmpty_Test implements GroupAssert_isNullOrEmpty_TestCase {

  @Rule public ExpectedException thrown = none();

  private List<?> actual;
  private TestObjectGroupAssert assertions;

  @Before public void setUp() {
    actual = list(8);
    assertions = new TestObjectGroupAssert(actual);
  }

  @Test public final void should_pass_if_actual_is_null() {
    new TestObjectGroupAssert(null).isNullOrEmpty();
  }

  @Test public final void should_pass_if_actual_is_empty() {
    new TestObjectGroupAssert(emptyList()).isNullOrEmpty();
  }

  @Test public final void should_fail_if_actual_has_content() {
    thrown.expectAssertionError("expecting null or empty, but was:<[8]>");
    assertions.isNullOrEmpty();
  }

  @Test public final void should_fail_and_display_description_if_actual_has_content() {
    thrown.expectAssertionError("[A Test] expecting null or empty, but was:<[8]>");
    assertions.as("A Test")
              .isNullOrEmpty();
  }

  @Test public final void should_fail_with_custom_message_if_actual_has_content() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNullOrEmpty();
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_has_content() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isNullOrEmpty();
  }
}