/*
 * Created on Apr 26, 2010
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

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link GenericAssert#isNotNull()}.
 * 
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isNotNull_Test {
  @Rule
  public ExpectedException thrown = none();

  private TestAssert assertions;

  @Before
  public final void setUp() {
    assertions = new TestAssert(null);
  }

  @Test
  public final void should_pass_if_actual_is_not_null() {
    new TestAssert("Hello").isNotNull();
  }

  @Test
  public final void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    assertions.isNotNull();
  }

  @Test
  public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    assertions.as("A Test").isNotNull();
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_is_null() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isNotNull();
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isNotNull();
  }
}