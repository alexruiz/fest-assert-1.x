/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.longArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link LongArrayAssert#excludes(long...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class LongArrayAssert_excludes_Test implements GroupAssert_excludes_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static long[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = longArray(6, 8);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_value() {
    new LongArrayAssert(array).excludes(10);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_values() {
    new LongArrayAssert(array).excludes(10, 2);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new LongArrayAssert(null).excludes(6, 8);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new LongArrayAssert(null).as("A Test").excludes(6, 8);
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new LongArrayAssert(array).excludes(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "<[6, 8]> does not exclude element(s):<[6]>");
    new LongArrayAssert(array).excludes(6);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "[A Test] <[6, 8]> does not exclude element(s):<[6]>");
    new LongArrayAssert(array).as("A Test").excludes(6);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new LongArrayAssert(array).overridingErrorMessage("My custom message").excludes(6);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new LongArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").excludes(6);
  }
}
