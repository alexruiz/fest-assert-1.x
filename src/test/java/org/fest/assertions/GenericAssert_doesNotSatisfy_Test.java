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

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.NotNull.notNull;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.ToString.toStringOf;

/**
 * Tests for {@link GenericAssert#doesNotSatisfy(Condition)}.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_doesNotSatisfy_Test implements GenericAssert_doesNotSatisfy_orAlias_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private TestAssert assertions;
  private Object actual;
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
    new TestAssert(null).doesNotSatisfy(notNull);
  }

  @Override
  @Test
  public void should_throw_error_if_condition_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.doesNotSatisfy(null);
  }

  @Override
  @Test
  public void should_fail_if_condition_is_satisfied() {
    String msg = String.format("actual value:<%s> should not satisfy condition:<NotNull>", toStringOf(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.doesNotSatisfy(notNull);
  }

  @Override
  @Test
  public void should_fail_and_display_description_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not satisfy condition:<NotNull>", toStringOf(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.as("A Test").doesNotSatisfy(notNull);
  }

  @Override
  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    String msg = String.format("actual value:<%s> should not satisfy condition:<Not Null>", toStringOf(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.doesNotSatisfy(notNull.as("Not Null"));
  }

  @Override
  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg =
        String.format("[A Test] actual value:<%s> should not satisfy condition:<Not Null>", toStringOf(actual));
    thrown.expect(AssertionError.class, msg);
    assertions.as("A Test").doesNotSatisfy(notNull.as("Not Null"));
  }

  @Override
  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").doesNotSatisfy(notNull);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").doesNotSatisfy(notNull);
  }

  @Override
  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").doesNotSatisfy(notNull.as("Not Null"));
  }
}