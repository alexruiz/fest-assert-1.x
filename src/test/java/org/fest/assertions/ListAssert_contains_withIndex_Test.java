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

import static java.util.Collections.emptyList;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.Index.atIndex;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;

/**
 * Tests for {@link ListAssert#contains(Object, Index)}.
 *
 * @author Alex Ruiz
 */
public class ListAssert_contains_withIndex_Test {
  @Rule
  public ExpectedException thrown = none();

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = newArrayList("Anakin", "Leia");
  }

  @Test
  public void should_pass_if_actual_contains_Object_at_index() {
    new ListAssert(list).contains("Anakin", atIndex(0)).contains("Leia", atIndex(1));
  }

  @Test
  public void should_throw_error_if_expected_Index_is_null() {
    thrown.expect(NullPointerException.class);
    Index index = null;
    new ListAssert(list).contains("Anakin", index);
  }

  @Test
  public void should_throw_error_if_index_is_negative() {
    thrown.expect(IndexOutOfBoundsException.class, "The index <-1> should be greater than or equal to zero and less than 2");
    new ListAssert(list).contains("Anakin", atIndex(-1));
  }

  @Test
  public void should_throw_error_and_display_description_if_index_is_negative() {
    thrown.expect(IndexOutOfBoundsException.class,
        "[A Test] The index <-1> should be greater than or equal to zero and less than 2");
    new ListAssert(list).as("A Test").contains("Anakin", atIndex(-1));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ListAssert(null).contains("Anakin", atIndex(0));
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ListAssert(null).as("A Test").contains("Anakin", atIndex(0));
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    thrown.expect(AssertionError.class, "expecting non-empty, but it was empty");
    new ListAssert(emptyList()).contains("Anakin", atIndex(3));
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_empty() {
    thrown.expect(AssertionError.class, "[A Test] expecting non-empty, but it was empty");
    new ListAssert(emptyList()).as("A Test").contains("Anakin", atIndex(3));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_empty() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(emptyList()).overridingErrorMessage("My custom message").contains("Anakin", atIndex(3));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_empty() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(emptyList()).as("A Test").overridingErrorMessage("My custom message").contains("Anakin", atIndex(3));
  }

  @Test
  public void should_throw_error_if_index_value_is_equal_to_size_of_actual() {
    thrown.expect(IndexOutOfBoundsException.class,
        "The index <2> should be greater than or equal to zero and less than 2");
    new ListAssert(list).contains("Anakin", atIndex(2));
  }

  @Test
  public void should_throw_error_and_display_description_if_index_value_is_equal_to_size_of_actual() {
    String message = "[A Test] The index <2> should be greater than or equal to zero and less than 2";
    thrown.expect(IndexOutOfBoundsException.class, message);
    new ListAssert(list).as("A Test").contains("Anakin", atIndex(2));
  }

  @Test
  public void should_throw_error_if_index_value_is_greater_than_size_of_actual() {
    thrown.expect(IndexOutOfBoundsException.class,
        "The index <3> should be greater than or equal to zero and less than 2");
    new ListAssert(list).contains("Anakin", atIndex(3));
  }

  @Test
  public void should_throw_error_and_display_description_if_index_value_is_greater_than_size_of_actual() {
    thrown.expect(IndexOutOfBoundsException.class,
        "[A Test] The index <3> should be greater than or equal to zero and less than 2");
    new ListAssert(list).as("A Test").contains("Anakin", atIndex(3));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_Object_at_index() {
    thrown.expect(AssertionError.class, "expecting <'Han'> at index <1> but found <'Leia'>");
    new ListAssert(list).contains("Han", atIndex(1));
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_contain_Object_at_index() {
    thrown.expect(AssertionError.class, "[A Test] expecting <'Han'> at index <1> but found <'Leia'>");
    new ListAssert(list).as("A Test").contains("Han", atIndex(1));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_Object_at_index() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).overridingErrorMessage("My custom message").contains("Han", atIndex(1));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_contain_Object_at_index() {
    thrown.expect(AssertionError.class, "My custom message");
    new ListAssert(list).as("A Test").overridingErrorMessage("My custom message").contains("Han", atIndex(1));
  }
}