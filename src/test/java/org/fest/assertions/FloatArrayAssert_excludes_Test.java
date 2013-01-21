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

import static org.fest.assertions.ArrayFactory.floatArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FloatArrayAssert#excludes(float...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FloatArrayAssert_excludes_Test implements GroupAssert_excludes_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static float[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = floatArray(8f);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_value() {
    new FloatArrayAssert(array).excludes(10f);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_values() {
    new FloatArrayAssert(array).excludes(6f, 10f);

  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new FloatArrayAssert(null).excludes(8f);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new FloatArrayAssert(null).as("A Test").excludes(8f);
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new FloatArrayAssert(array).excludes(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "<[8.0f]> does not exclude element(s):<[8.0f]>");
    new FloatArrayAssert(array).excludes(8f);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "[A Test] <[8.0f]> does not exclude element(s):<[8.0f]>");
    new FloatArrayAssert(array).as("A Test").excludes(8f);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatArrayAssert(array).overridingErrorMessage("My custom message").excludes(8f);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new FloatArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").excludes(8f);
  }
}