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

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.*;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectAssert#isInstanceOf(Class)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert_isInstanceOf_Test implements Assert_isInstanceOf_TestCase {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_actual_is_instance_of_expected() {
    new ObjectAssert(6).isInstanceOf(Integer.class);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new ObjectAssert(null).isInstanceOf(String.class);
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new ObjectAssert(null).as("A Test")
                          .isInstanceOf(String.class);
  }

  @Test public void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException(typeIsNull());
    new ObjectAssert("Yoda").isInstanceOf(null);
  }

  @Test public void should_throw_error_and_display_description_if_expected_is_null() {
    thrown.expectNullPointerException(typeIsNull("A Test"));
    new ObjectAssert("Yoda").as("A Test")
                            .isInstanceOf(null);
  }

  @Test public void should_fail_if_actual_is_not_instance_of_expected() {
    String message = "expected instance of:<java.lang.String> but was instance of:<java.lang.Integer>";
    thrown.expectAssertionError(message);
    new ObjectAssert(2).isInstanceOf(String.class);
  }

  @Test public void should_fail_and_display_description_if_actual_is_not_instance_of_expected() {
    String message = "[A Test] expected instance of:<java.lang.String> but was instance of:<java.lang.Integer>";
    thrown.expectAssertionError(message);
    new ObjectAssert(2).as("A Test")
                       .isInstanceOf(String.class);
  }

  @Test public void should_fail_with_custom_message_if_actual_is_not_instance_of_expected() {
    thrown.expectAssertionError("My custom message");
    new ObjectAssert(2).overridingErrorMessage("My custom message")
                       .isInstanceOf(String.class);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_is_not_instance_of_expected() {
    thrown.expectAssertionError("My custom message");
    new ObjectAssert(2).as("A Test")
                       .overridingErrorMessage("My custom message")
                       .isInstanceOf(String.class);
  }
}