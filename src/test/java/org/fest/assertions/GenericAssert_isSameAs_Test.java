/*
 * Created on Apr 27, 2010
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

import static org.fest.test.ExpectedException.none;
import static org.fest.util.ToString.toStringOf;

/**
 * Tests for {@link GenericAssert#isSameAs(Object)}.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_isSameAs_Test {
  @Rule
  public ExpectedException thrown = none();

  private Object actual;
  private TestAssert assertions;

  @Before
  public void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
  }

  @Test
  public final void should_pass_if_actual_and_expected_are_same() {
    assertions.isSameAs(actual);
  }

  @Test
  public final void should_fail_if_actual_and_expected_are_not_same() {
    String msg = String.format("expected same instance but found:<%s> and:<%s>", toStringOf(actual), toStringOf("Bye"));
    thrown.expect(AssertionError.class, msg);
    assertions.isSameAs("Bye");
  }

  @Test
  public final void should_fail_and_display_description_if_actual_and_expected_are_not_same() {
    String msg = String.format("[A Test] expected same instance but found:<%s> and:<%s>", toStringOf(actual),
        toStringOf("Bye"));
    thrown.expect(AssertionError.class, msg);
    assertions.as("A Test").isSameAs("Bye");
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_and_expected_are_not_same() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isSameAs("Bye");
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_same() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isSameAs("Bye");
  }
}
