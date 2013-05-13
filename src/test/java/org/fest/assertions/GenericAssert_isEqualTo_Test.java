/*
 * Created on Apr 23, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.FailureMessages.notEqual;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link GenericAssert#isEqualTo(Object)}.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_isEqualTo_Test implements Assert_isEqualTo_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private Object actual;
  private TestAssert assertions;

  @Before
  public final void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
  }

  @Override
  @Test
  public final void should_pass_if_actual_and_expected_are_equal() {
    assertions.isEqualTo(actual);
  }

  @Test
  public final void should_pass_if_both_actual_and_expected_are_null() {
    new TestAssert(null).isEqualTo(null);
  }

  @Override
  @Test
  public final void should_fail_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, notEqual(actual, "Bye"));
    assertions.isEqualTo("Bye");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, notEqual("A Test", actual, "Bye"));
    assertions.as("A Test").isEqualTo("Bye");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isEqualTo("Bye");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isEqualTo("Bye");
  }
}