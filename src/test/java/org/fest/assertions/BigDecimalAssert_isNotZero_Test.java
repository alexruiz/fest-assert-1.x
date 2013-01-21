/*
 * Created on Jan 10, 2007
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

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link org.fest.assertions.BigDecimalAssert#isNotZero()}.
 * 
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert_isNotZero_Test implements NumberAssert_isNotZero_TestCase {
  @Rule
  public ExpectedException thrown = none();

  @Override
  @Test
  public void should_pass_if_actual_is_not_zero() {
    new BigDecimalAssert(ONE).isNotZero();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new BigDecimalAssert(null).isNotZero();
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new BigDecimalAssert(null).as("A Test").isNotZero();
  }

  @Override
  @Test
  public void should_fail_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "actual value:<0> should not be equal to:<0>");
    new BigDecimalAssert(ZERO).isNotZero();
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<0> should not be equal to:<0>");
    new BigDecimalAssert(ZERO).as("A Test").isNotZero();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "My custom message");
    new BigDecimalAssert(ZERO).overridingErrorMessage("My custom message").isNotZero();
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_zero() {
    thrown.expect(AssertionError.class, "My custom message");
    new BigDecimalAssert(ZERO).as("A Test").overridingErrorMessage("My custom message").isNotZero();
  }
}