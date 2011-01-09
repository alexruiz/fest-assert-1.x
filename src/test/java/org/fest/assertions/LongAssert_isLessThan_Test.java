/*
 * Created on Jun 18, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link LongAssert#isLessThan(long)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class LongAssert_isLessThan_Test implements Assert_isLessThan_TestCase {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_actual_is_less_than_expected() {
    new LongAssert(2).isLessThan(6);
  }

  @Test public void should_fail_if_actual_is_equal_to_expected() {
    thrown.expectAssertionError("actual value:<6> should be less than:<6>");
    new LongAssert(6).isLessThan(6);
  }

  @Test public void should_fail_and_display_description_if_actual_is_equal_to_expected() {
    thrown.expectAssertionError("[A Test] actual value:<6> should be less than:<6>");
    new LongAssert(6).as("A Test")
                     .isLessThan(6);
  }

  @Test public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    thrown.expectAssertionError("My custom message");
    new LongAssert(6).overridingErrorMessage("My custom message")
                     .isLessThan(6);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_equal_to_expected() {
    thrown.expectAssertionError("My custom message");
    new LongAssert(6).as("A Test")
                     .overridingErrorMessage("My custom message")
                     .isLessThan(6);
  }

  @Test public void should_fail_if_actual_is_greater_than_expected() {
    thrown.expectAssertionError("actual value:<10> should be less than:<6>");
    new LongAssert(10).isLessThan(6);
  }

  @Test public void should_fail_and_display_description_if_actual_is_greater_than_expected() {
    thrown.expectAssertionError("[A Test] actual value:<10> should be less than:<6>");
    new LongAssert(10).as("A Test")
                      .isLessThan(6);
  }

  @Test public void should_fail_with_custom_message_if_actual_is_greater_than_expected() {
    thrown.expectAssertionError("My custom message");
    new LongAssert(10).overridingErrorMessage("My custom message")
                      .isLessThan(6);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_greater_than_expected() {
    thrown.expectAssertionError("My custom message");
    new LongAssert(10).as("A Test")
                      .overridingErrorMessage("My custom message")
                      .isLessThan(6);
  }
}