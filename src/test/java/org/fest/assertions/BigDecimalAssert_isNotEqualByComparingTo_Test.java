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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.BigDecimals.*;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link BigDecimalAssert#isNotEqualByComparingTo(BigDecimal)}</code>.
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert_isNotEqualByComparingTo_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_values_are_not_equal() {
    new BigDecimalAssert(eight()).isNotEqualByComparingTo(seven());
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new BigDecimalAssert(null).isNotEqualByComparingTo(eight());
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new BigDecimalAssert(null).as("A Test")
                              .isNotEqualByComparingTo(eight());
  }

  @Test public void should_fail_if_values_are_not_equal() {
    thrown.expectAssertionError("actual value:<8.0> should not be equal to:<8.0>");
    new BigDecimalAssert(eight()).isNotEqualByComparingTo(eight());
  }

  @Test public void should_fail_and_display_description_if_values_are_not_equal() {
    thrown.expectAssertionError("[A Test] actual value:<8.0> should not be equal to:<8.0>");
    new BigDecimalAssert(eight()).as("A Test")
                                 .isNotEqualByComparingTo(eight());
  }
}
