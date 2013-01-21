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
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ByteAssert#isLessThan(byte)}.
 * 
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class ByteAssert_isLessThan_Test implements Assert_isLessThan_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static byte actual;
  private static byte lessThanActual;

  @BeforeClass
  public static void setUp() {
    actual = 8;
    lessThanActual = 6;
  }

  @Override
  @Test
  public void should_pass_if_actual_is_less_than_expected() {
    new ByteAssert(actual).isLessThan((byte) 10);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class, "actual value:<8> should be less than:<8>");
    new ByteAssert(actual).isLessThan(actual);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<8> should be less than:<8>");
    new ByteAssert(actual).as("A Test").isLessThan(actual);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteAssert(actual).as("A Test").overridingErrorMessage("My custom message").isLessThan(actual);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_equal_to_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteAssert(actual).overridingErrorMessage("My custom message").isLessThan(actual);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_greater_than_expected() {
    thrown.expect(AssertionError.class, "actual value:<8> should be less than:<6>");
    new ByteAssert(actual).isLessThan(lessThanActual);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_greater_than_expected() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<8> should be less than:<6>");
    new ByteAssert(actual).as("A Test").isLessThan(lessThanActual);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_greater_than_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteAssert(actual).overridingErrorMessage("My custom message").isLessThan(lessThanActual);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_greater_than_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ByteAssert(actual).as("A Test").overridingErrorMessage("My custom message").isLessThan(lessThanActual);
  }
}
