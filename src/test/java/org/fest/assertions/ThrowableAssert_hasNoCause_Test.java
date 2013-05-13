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
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;

/**
 * Tests for {@link ThrowableAssert#hasNoCause()}.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_hasNoCause_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_does_not_have_cause() {
    new ThrowableAssert(new Exception()).hasNoCause();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ThrowableAssert(null).hasNoCause();
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ThrowableAssert(null).as("A Test").hasNoCause();
  }

  @Test
  public void should_fail_if_actual_has_cause() {
    thrown.expect(AssertionError.class, "expected exception without cause, but cause was:<java.io.IOException>");
    new ThrowableAssert(new Exception(new IOException())).hasNoCause();
  }

  @Test
  public void should_fail_and_display_description_if_actual_has_cause() {
    String message = "[A Test] expected exception without cause, but cause was:<java.io.IOException>";
    thrown.expect(AssertionError.class, message);
    Exception e = new Exception(new IOException());
    new ThrowableAssert(e).as("A Test").hasNoCause();
  }

  @Test
  public void should_fail_with_custom_message_if_actual_has_cause() {
    thrown.expect(AssertionError.class, "My custom message");
    Exception e = new Exception(new IOException());
    new ThrowableAssert(e).overridingErrorMessage("My custom message").hasNoCause();
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_has_cause() {
    thrown.expect(AssertionError.class, "My custom message");
    Exception e = new Exception(new IOException());
    new ThrowableAssert(e).as("A Test").overridingErrorMessage("My custom message").hasNoCause();
  }
}
