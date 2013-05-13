/*
 * Created on Mar 29, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.assertions;

import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;

/**
 * Tests for {@link ListAssert#containsExactly(Object...)}.
 *
 * @author Alex Ruiz
 */
public class ListAssert_containsExactly_Test {
  @Rule
  public ExpectedException thrown = none();

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = newArrayList("Luke", "Leia");
  }

  @Test
  public void should_pass_if_actual_contains_exactly_the_expected_Objects() {
    new ListAssert(list).containsExactly("Luke", "Leia");
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Object[] objects = null;
    new ListAssert(list).containsExactly(objects);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ListAssert(null).containsExactly("Anakin");
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ListAssert(null).as("A Test").containsExactly("Anakin");
  }

  @Test
  public void should_fail_if_actual_does_not_contain_exactly_the_expected_Objects() {
    thrown.expect(AssertionError.class, "expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>");
    new ListAssert(list).containsExactly("Anakin");
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_exactly_the_expected_Objects() {
    thrown.expect(AssertionError.class, "[A Test] expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>");
    new ListAssert(list).as("A Test").containsExactly("Anakin");
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_exactly_the_expected_Objects() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").containsExactly("Anakin");
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_exactly_the_expected_Objects() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").containsExactly("Anakin");
  }
}