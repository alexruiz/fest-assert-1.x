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

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link CharArrayAssert#contains(char...)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert_contains_Test implements GroupAssert_contains_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static char[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = ArrayFactory.charArray('a', 'b');
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_given_value() {
    new CharArrayAssert(array).contains('a');
  }

  @Override
  @Test
  public void should_pass_if_actual_contains_given_values() {
    new CharArrayAssert(array).contains('a', 'b');
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new CharArrayAssert(null).contains('a', 'b');
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new CharArrayAssert(null).as("A Test").contains('a', 'b');
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new CharArrayAssert(array).contains(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_does_not_contain_given_values() {
    thrown.expect(AssertionError.class, "<[a, b]> does not contain element(s):<[c, d]>");
    new CharArrayAssert(array).contains('c', 'd');
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_given_values() {
    thrown.expect(AssertionError.class, "[A Test] <[a, b]> does not contain element(s):<[c, d]>");
    new CharArrayAssert(array).as("A Test").contains('c', 'd');
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharArrayAssert(array).overridingErrorMessage("My custom message").contains('c', 'd');
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").contains('c', 'd');
  }
}
