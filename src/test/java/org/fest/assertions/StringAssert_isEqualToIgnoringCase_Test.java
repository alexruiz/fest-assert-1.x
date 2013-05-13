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
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link StringAssert#isEqualToIgnoringCase(String)}.
 *
 * @author Joel Costigliola
 */
public class StringAssert_isEqualToIgnoringCase_Test {
  @Rule
  public ExpectedException thrown = none();

  private static String jedi;

  @BeforeClass
  public static void setUpOnce() {
    jedi = "Luke";
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new StringAssert(jedi).isEqualToIgnoringCase("Luke");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal_ignoring_case() {
    new StringAssert(jedi).isEqualToIgnoringCase("luke");
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    new StringAssert(null).isEqualToIgnoringCase(null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new StringAssert(null).isEqualToIgnoringCase("Yoda");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new StringAssert(null).as("A Test").isEqualToIgnoringCase("Yoda");
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal_ignoring_case() {
    thrown.expect(AssertionError.class, "<'Luke'> should be equal to :<'Yoda'> ignoring case");
    new StringAssert(jedi).isEqualToIgnoringCase("Yoda");
  }

  @Test
  public void should_fail_and_display_description_if_actual_and_expected_are_not_equal_ignoring_case() {
    thrown.expect(AssertionError.class, "[A Test] <'Luke'> should be equal to :<'Yoda'> ignoring case");
    new StringAssert(jedi).as("A Test").isEqualToIgnoringCase("Yoda");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal_ignoring_case() {
    thrown.expect(AssertionError.class, "My custom message");
    new StringAssert(jedi).overridingErrorMessage("My custom message").isEqualToIgnoringCase("Yoda");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal_ignoring_case() {
    thrown.expect(AssertionError.class, "My custom message");
    new StringAssert(jedi).as("A Test").overridingErrorMessage("My custom message").isEqualToIgnoringCase("Yoda");
  }
}
