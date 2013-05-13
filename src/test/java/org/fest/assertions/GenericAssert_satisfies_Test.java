/*
 * Created on Apr 16, 2010
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

/**
 * Tests for {@link GenericAssert#satisfies(Condition)}.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class GenericAssert_satisfies_Test implements GenericAssert_satisfies_orAlias_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private TestAssert assertions;
  private Condition<Object> notNull;

  @Before
  public final void setUp() {
    assertions = new TestAssert(null);
    notNull = notNull();
  }

  @Override
  @Test
  public final void should_pass_if_condition_is_satisfied() {
    new TestAssert("Hello").satisfies(notNull);
  }

  @Override
  @Test
  public final void should_throw_error_if_condition_is_null() {
    thrown.expect(NullPointerException.class);
    assertions.satisfies(null);
  }

  @Override
  @Test
  public final void should_fail_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "actual value:<null> should satisfy condition:<NotNull>");
    assertions.satisfies(notNull);
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<null> should satisfy condition:<NotNull>");
    assertions.as("A Test").satisfies(notNull);
  }

  @Override
  @Test
  public final void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "actual value:<null> should satisfy condition:<non-null>");
    assertions.satisfies(notNull.as("non-null"));
  }

  @Override
  @Test
  public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "[A Test] actual value:<null> should satisfy condition:<non-null>");
    assertions.as("A Test").satisfies(notNull.as("non-null"));
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").satisfies(notNull);
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").satisfies(notNull.as("non-null"));
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_condition_is_not_satisfied() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").satisfies(notNull);
  }
}