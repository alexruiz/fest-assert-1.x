/*
 * Created on Dec 23, 2007
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

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link ThrowableAssert#hasMessage(String)}.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_hasMessage_Test {
  @Rule
  public ExpectedException thrown = none();

  private static Exception actual;

  @BeforeClass
  public static void setUpOnce() {
    actual = new Exception("An exception");
  }

  @Test
  public void should_pass_if_message_in_actual_is_equal_to_expected() {
    new ThrowableAssert(actual).hasMessage("An exception");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ThrowableAssert(null).hasMessage("");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ThrowableAssert(null).as("A Test").hasMessage("");
  }

  @Test
  public void should_fail_if_message_in_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class, "expected:<'[Hi]'> but was:<'[An exception]'>");
    new ThrowableAssert(actual).hasMessage("Hi");
  }

  @Test
  public void should_fail_and_display_description_if_message_in_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class, "[A Test] expected:<'[Hi]'> but was:<'[An exception]'>");
    new ThrowableAssert(actual).as("A Test").hasMessage("Hi");
  }

  @Test
  public void should_fail_with_custom_message_if_message_in_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ThrowableAssert(actual).overridingErrorMessage("My custom message").hasMessage("Hi");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_message_in_actual_is_not_equal_to_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ThrowableAssert(actual).as("A Test").overridingErrorMessage("My custom message").hasMessage("Hi");
  }
}