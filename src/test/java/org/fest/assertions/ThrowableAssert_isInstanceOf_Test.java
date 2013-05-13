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
 * Tests for {@link ThrowableAssert#isInstanceOf(Class)}.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_isInstanceOf_Test implements Assert_isInstanceOf_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private static Exception actual;

  @BeforeClass
  public static void setUpOnce() {
    actual = new Exception();
  }

  @Override
  @Test
  public void should_pass_if_actual_is_instance_of_expected() {
    new ThrowableAssert(actual).isInstanceOf(Exception.class);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ThrowableAssert(null).isInstanceOf(NullPointerException.class);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ThrowableAssert(null).as("A Test").isInstanceOf(NullPointerException.class);
  }

  @Override
  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new ThrowableAssert(actual).isInstanceOf(null);
  }

  @Override
  @Test
  public void should_fail_if_actual_is_not_instance_of_expected() {
    String message = "expected instance of:<java.lang.NullPointerException> but was instance of:<java.lang.Exception>";
    thrown.expect(AssertionError.class, message);
    new ThrowableAssert(actual).isInstanceOf(NullPointerException.class);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_actual_is_not_instance_of_expected() {
    String message = "[A Test] expected instance of:<java.lang.NullPointerException> but was instance of:<java.lang.Exception>";
    thrown.expect(AssertionError.class, message);
    new ThrowableAssert(actual).as("A Test").isInstanceOf(NullPointerException.class);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_actual_is_not_instance_of_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ThrowableAssert(actual).overridingErrorMessage("My custom message").isInstanceOf(NullPointerException.class);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_not_instance_of_expected() {
    thrown.expect(AssertionError.class, "My custom message");
    new ThrowableAssert(actual).as("A Test").overridingErrorMessage("My custom message")
        .isInstanceOf(NullPointerException.class);
  }
}