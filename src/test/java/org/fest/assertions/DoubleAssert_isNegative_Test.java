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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test for {@link DoubleAssert#isNegative()}.
 * 
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class DoubleAssert_isNegative_Test implements NumberAssert_isNegative_TestCase {
  @Rule
  public ExpectedException thrown = none();

  @Override
  @Test
  public void should_pass_if_actual_is_negative() {
    new DoubleAssert(-6.68).isNegative();
  }

  @Override
  @Test
  public void should_fail_if_actual_is_positive() {
    thrown.expect(AssertionError.class, "actual value:<6.68> should be less than:<0.0>");
    new DoubleAssert(6.68).isNegative();
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_positive() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<6.68> should be less than:<0.0>");
    new DoubleAssert(6.68).as("A Test").isNegative();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_positive() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(6.68).overridingErrorMessage("My custom message").isNegative();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_positive() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(6.68).as("A Test").overridingErrorMessage("My custom message").isNegative();
  }

  @Override
  @Test
  public void should_fail_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "actual value:<0.0> should be less than:<0.0>");
    new DoubleAssert(0.0).isNegative();
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<0.0> should be less than:<0.0>");
    new DoubleAssert(0.0).as("A Test").isNegative();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(0.0).overridingErrorMessage("My custom message").isNegative();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleAssert(0.0).as("A Test").overridingErrorMessage("My custom message").isNegative();
  }
}