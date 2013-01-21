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

import static org.fest.assertions.ArrayFactory.doubleArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link DoubleArrayAssert#excludes(double...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DoubleArrayAssert_excludes_Test implements GroupAssert_excludes_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static double[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = doubleArray(55.03, 4345.91);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_value() {
    new DoubleArrayAssert(array).excludes(5323.2);
  }

  @Override
  @Test
  public void should_pass_if_actual_excludes_given_values() {
    new DoubleArrayAssert(array).excludes(8.6, 5323.2);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new DoubleArrayAssert(null).excludes(55.03, 4345.91);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new DoubleArrayAssert(null).as("A Test").excludes(55.03, 4345.91);
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new DoubleArrayAssert(array).excludes(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "<[55.03, 4345.91]> does not exclude element(s):<[55.03]>");
    new DoubleArrayAssert(array).excludes(55.03);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "[A Test] <[55.03, 4345.91]> does not exclude element(s):<[55.03]>");
    new DoubleArrayAssert(array).as("A Test").excludes(55.03);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).overridingErrorMessage("My custom message").excludes(55.03);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new DoubleArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").excludes(55.03);
  }
}
