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
 * Tests for {@link CharAssert#isGreaterThanOrEqualTo(char)}.
 * 
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class CharAssert_isGreaterThanOrEqualTo_Test implements Assert_isGreaterThanOrEqualTo_TestCase {
  @Rule
  public ExpectedException thrown = none();

  @Override
  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    new CharAssert('a').isGreaterThanOrEqualTo('A');
  }

  @Override
  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    new CharAssert('a').isGreaterThanOrEqualTo('a');
  }

  @Override
  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class, "actual value:<A> should be greater than or equal to:<a>");
    new CharAssert('A').isGreaterThanOrEqualTo('a');
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<A> should be greater than or equal to:<a>");
    new CharAssert('A').as("A Test").isGreaterThanOrEqualTo('a');
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharAssert('A').overridingErrorMessage("My custom message").isGreaterThanOrEqualTo('a');
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_less_than_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharAssert('A').as("A Test").overridingErrorMessage("My custom message").isGreaterThanOrEqualTo('a');
  }
}
