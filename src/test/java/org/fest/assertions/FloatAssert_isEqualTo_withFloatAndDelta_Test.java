/*
 * Created on Jan 12, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Delta.delta;
import static org.fest.assertions.ExpectedException.none;

import org.junit.Rule;
import org.junit.Test;

/**
 * Test for <code>{@link FloatAssert#isEqualTo(Float, Delta)}</code>.
 *
 * @author Alex Ruiz
 */
public class FloatAssert_isEqualTo_withFloatAndDelta_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_actual_and_expected_are_equal_and_using_delta_of_zero() {
    new FloatAssert(8.0f).isEqualTo(new Float(8.0), delta(0.0));
  }

  @Test public void should_pass_if_actual_and_expected_are_equal_and_using_delta() {
    new FloatAssert(8.688f).isEqualTo(new Float(8.68), delta(0.009));
  }

  @Test public void should_fail_if_actual_and_expected_are_not_equal() {
    thrown.expectAssertionError("expected:<8.888> but was:<8.688> using delta:<0.0090>");
    new FloatAssert(8.688f).isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test public void should_fail_and_display_description_if_actual_and_expected_are_not_equal() {
    thrown.expectAssertionError("[A Test] expected:<8.888> but was:<8.688> using delta:<0.0090>");
    new FloatAssert(8.688f).as("A Test")
                           .isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    thrown.expectAssertionError("My custom message");
    new FloatAssert(8.688f).overridingErrorMessage("My custom message")
                           .isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal() {
    thrown.expectAssertionError("My custom message");
    new FloatAssert(8.688f).as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test public void should_fail_if_actual_is_null_and_expected_is_not_null() {
    thrown.expectComparisonFailure("expected:<8.0> but was:<null>");
    new FloatAssert(null).isEqualTo(new Float(8), delta(0.1));
  }

  @Test public void should_fail_and_display_description_if_actual_is_null_and_expected_is_not_null() {
    thrown.expectComparisonFailure("[A Test] expected:<8.0> but was:<null>");
    new FloatAssert(null).as("A Test")
                          .isEqualTo(new Float(8), delta(0.1));
  }

  @Test public void should_fail_with_custom_message_if_actual_is_null_and_expected_is_not_null() {
    thrown.expectAssertionError("My custom message");
    new FloatAssert(null).overridingErrorMessage("My custom message")
                          .isEqualTo(new Float(8), delta(0.1));
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_null_and_expected_is_not_null() {
    thrown.expectAssertionError("My custom message");
    new FloatAssert(null).as("A Test")
                          .overridingErrorMessage("My custom message")
                          .isEqualTo(new Float(8), delta(0.1));
  }
}
