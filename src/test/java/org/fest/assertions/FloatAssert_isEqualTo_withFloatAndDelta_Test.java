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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Delta.delta;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.ComparisonFailure;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test for {@link FloatAssert#isEqualTo(Float, Delta)}.
 * 
 * @author Alex Ruiz
 */
public class FloatAssert_isEqualTo_withFloatAndDelta_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta_of_zero() {
    new FloatAssert(8.0f).isEqualTo(new Float(8.0), delta(0.0));
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta() {
    new FloatAssert(8.688f).isEqualTo(new Float(8.68), delta(0.009));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "expected:<8.888f> but was:<8.688f> using delta:<0.0090f>");
    new FloatAssert(8.688f).isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test
  public void should_fail_and_display_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "[A Test] expected:<8.888f> but was:<8.688f> using delta:<0.0090f>");
    new FloatAssert(8.688f).as("A Test").isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatAssert(8.688f).overridingErrorMessage("My custom message").isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatAssert(8.688f).as("A Test").overridingErrorMessage("My custom message")
    .isEqualTo(new Float(8.888), delta(0.009));
  }

  @Test
  public void should_fail_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(ComparisonFailure.class, "expected:<8.0f> but was:<null>");
    new FloatAssert(null).isEqualTo(new Float(8), delta(0.1));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatAssert(null).overridingErrorMessage("My custom message").isEqualTo(new Float(8), delta(0.1));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatAssert(null).as("A Test").overridingErrorMessage("My custom message").isEqualTo(new Float(8), delta(0.1));
  }
}
