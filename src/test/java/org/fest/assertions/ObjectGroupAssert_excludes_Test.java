/*
 * Created on Jun 30, 2010
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

import java.util.List;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;

/**
 * Tests for {@link ObjectGroupAssert#excludes(Object...)}.
 *
 * @author Yvonne Wang
 */
public final class ObjectGroupAssert_excludes_Test implements GroupAssert_excludes_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private List<?> actual;
  private TestObjectGroupAssert assertions;

  @Before
  public void setUp() {
    actual = newArrayList("Luke", "Leia");
    assertions = new TestObjectGroupAssert(actual);
  }

  @Override
  @Test
  public final void should_pass_if_actual_excludes_given_value() {
    assertions.excludes("Anakin");
  }

  @Override
  @Test
  public final void should_pass_if_actual_excludes_given_values() {
    assertions.excludes("Han", "Yoda");
  }

  @Override
  @Test
  public final void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new TestObjectGroupAssert(null).excludes("Luke");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new TestObjectGroupAssert(null).as("A Test").excludes("Luke");
  }

  @Override
  @Test
  public final void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] objects = null;
    assertions.excludes(objects);
  }

  @Override
  @Test
  public final void should_fail_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "<['Luke', 'Leia']> does not exclude element(s):<['Luke']>");
    assertions.excludes("Luke");
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_contains_given_values() {
    String message = "[A Test] <['Luke', 'Leia']> does not exclude element(s):<['Luke']>";
    thrown.expect(AssertionError.class, message);
    assertions.as("A Test").excludes("Luke");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").excludes("Luke");
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_contains_given_values() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").excludes("Luke");
  }
}