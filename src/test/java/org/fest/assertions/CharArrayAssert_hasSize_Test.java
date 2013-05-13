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

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.ArrayFactory.charArray;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link CharArrayAssert#hasSize(int)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert_hasSize_Test implements Assert_hasSize_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static char[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = charArray('a', 'b', 'c');
  }

  @Override
  @Test
  public void should_pass_if_actual_has_expected_size() {
    new CharArrayAssert(array).hasSize(3);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new CharArrayAssert(null).hasSize(1);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new CharArrayAssert(null).as("A Test").hasSize(1);
  }

  @Override
  @Test
  public void should_fail_if_actual_does_not_have_expected_size() {
    thrown.expect(AssertionError.class, "expected size:<2> but was:<3> for <[a, b, c]>");
    new CharArrayAssert(array).hasSize(2);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_does_not_have_expected_size() {
    thrown.expect(AssertionError.class, "[A Test] expected size:<2> but was:<3> for <[a, b, c]>");
    new CharArrayAssert(array).as("A Test").hasSize(2);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_does_not_have_expected_size() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharArrayAssert(array).overridingErrorMessage("My custom message").hasSize(2);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_have_expected_size() {
    thrown.expect(AssertionError.class, "My custom message");
    new CharArrayAssert(array).as("A Test").overridingErrorMessage("My custom message").hasSize(2);
  }
}
