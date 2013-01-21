/*
 * Created on Apr 19, 2010
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

import static org.fest.assertions.Formatter.format;
import static org.fest.assertions.NotNull.notNull;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link GenericAssert#isNot(Condition)}.
 * 
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_isNot_Test implements GenericAssert_doesNotSatisfy_orAlias_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private Object actual;
  private TestAssert assertions;
  private Condition<Object> notNull;

  @Before
  public void setUp() {
    actual = "Hello";
    assertions = new TestAssert(actual);
    notNull = notNull();
  }

  @Override
  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    new TestAssert(null).isNot(notNull);
  }

  @Override
  @Test
  public void should_throw_error_if_condition_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.isNot(null);
  }

  @Override
  @Test
  public void should_fail_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, String.format("actual value:<%s> should not be:<NotNull>", format(actual)));
    assertions.isNot(notNull);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not be:<NotNull>", format(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.as("A Test").isNot(notNull);
  }

  @Override
  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, String.format("actual value:<%s> should not be:<Not Null>", format(actual)));
    assertions.isNot(notNull.as("Not Null"));
  }

  @Override
  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not be:<Not Null>", format(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.as("A Test").isNot(notNull.as("Not Null"));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isNot(notNull);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isNot(notNull);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isNot(notNull.as("Not Null"));
  }
}