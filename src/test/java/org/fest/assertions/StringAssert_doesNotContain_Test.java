/*
 * Created on Jun 7, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link StringAssert#doesNotContain(String)}</code>.
 *
 * @author Yvonne Wang
 */
public class StringAssert_doesNotContain_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_fail_if_actual_String_contains_given_text() {
    thrown.expectAssertionError("<'hello'> should not contain <'he'>");
    new StringAssert("hello").doesNotContain("he");
  }

  @Test public void should_fail_and_display_description_if_actual_String_contains_given_text() {
    thrown.expectAssertionError("[A Test] <'hello'> should not contain <'he'>");
    new StringAssert("hello").as("A Test").doesNotContain("he");
  }

  @Test public void should_fail_with_custom_message_if_actual_String_contains_given_text() {
    thrown.expectAssertionError("My custom message");
    new StringAssert("hello").overridingErrorMessage("My custom message")
                             .doesNotContain("he");
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_actual_String_contains_given_text() {
    thrown.expectAssertionError("My custom message");
    new StringAssert("hello").as("A Test")
                             .overridingErrorMessage("My custom message")
                             .doesNotContain("he");
  }

  @Test public void should_throw_error_if_given_text_is_null() {
    thrown.expectNullPointerException("The given String should not be null");
    new StringAssert("hello").doesNotContain(null);
  }

  @Test public void should_pass_if_actual_String_does_not_contain_given_text() {
    new StringAssert("hello").doesNotContain("world");
  }
}