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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.assertions;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link StringAssert#doesNotMatch(String)}.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class StringAssert_doesNotMatch_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_does_not_match_given_pattern() {
    new StringAssert("Luke 001").doesNotMatch("^\\d+.*$");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new StringAssert(null).doesNotMatch("^\\d+.*$");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new StringAssert(null).as("A Test").doesNotMatch("^\\d+.*$");
  }

  @Test
  public void should_fail_if_actual_matches_given_pattern() {
    thrown.expect(AssertionError.class, "<'Luke 001'> should not match the regular expression:<'^.*\\d+$'>");
    new StringAssert("Luke 001").doesNotMatch("^.*\\d+$");
  }

  @Test
  public void should_fail_and_display_description_if_actual_matches_given_pattern() {
    String message = "[A Test] <'Luke 001'> should not match the regular expression:<'^.*\\d+$'>";
    thrown.expect(AssertionError.class, message);
    new StringAssert("Luke 001").as("A Test").doesNotMatch("^.*\\d+$");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_matches_given_pattern() {
    thrown.expect(AssertionError.class, "My custom message");
    new StringAssert("Luke 001").overridingErrorMessage("My custom message").doesNotMatch("^.*\\d+$");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_matches_given_pattern() {
    thrown.expect(AssertionError.class, "My custom message");
    new StringAssert("Luke 001").as("A Test").overridingErrorMessage("My custom message").doesNotMatch("^.*\\d+$");
  }
}