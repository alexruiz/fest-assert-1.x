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
 * Test for {@link DoubleAssert#isEqualTo(Double, Delta)}.
 * 
 * @author Alex Ruiz
 */
public class DoubleAssert_isEqualTo_withDoubleAndDelta_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta_of_zero() {
    new DoubleAssert(8.0).isEqualTo(new Double(8.0), delta(0.0));
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal_and_using_delta() {
    new DoubleAssert(8.688).isEqualTo(new Double(8.68), delta(0.009));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "expected:<8.888> but was:<8.688> using delta:<0.0090>");
    new DoubleAssert(8.688).isEqualTo(new Double(8.888), delta(0.009));
  }

  @Test
  public void should_fail_and_display_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "[A Test] expected:<8.888> but was:<8.688> using delta:<0.0090>");
    new DoubleAssert(8.688).as("A Test").isEqualTo(new Double(8.888), delta(0.009));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(8.688).overridingErrorMessage("My custom message").isEqualTo(new Double(8.888), delta(0.009));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(8.688).as("A Test").overridingErrorMessage("My custom message")
    .isEqualTo(new Double(8.888), delta(0.009));
  }

  @Test
  public void should_fail_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(ComparisonFailure.class, "expected:<8.0> but was:<null>");
    new DoubleAssert(null).isEqualTo(new Double(8), delta(0.1));
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(ComparisonFailure.class, "[A Test] expected:<8.0> but was:<null>");
    new DoubleAssert(null).as("A Test").isEqualTo(new Double(8), delta(0.1));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(null).overridingErrorMessage("My custom message").isEqualTo(new Double(8), delta(0.1));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_null_and_expected_is_not_null() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(null).as("A Test").overridingErrorMessage("My custom message")
    .isEqualTo(new Double(8), delta(0.1));
  }
}
