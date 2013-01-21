/*
 * Created on Jan 24, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.MapAssert.entry;
import static org.fest.assertions.MapFactory.map;
import static org.fest.test.ExpectedException.none;

import java.util.Map;

import org.fest.assertions.MapAssert.Entry;
import org.fest.test.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link MapAssert#includes(Entry...)}.
 * 
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert_includes_Test {
  @Rule
  public ExpectedException thrown = none();

  private static Map<Object, Object> map;

  @BeforeClass
  public static void setUpOnce() {
    map = map(entry("key1", 1), entry("key2", 2));
  }

  @Test
  public void should_pass_if_actual_contains_entry() {
    new MapAssert(map).includes(entry("key1", 1));
  }

  @Test
  public void should_pass_if_actual_contains_entries() {
    new MapAssert(map).includes(entry("key1", 1), entry("key2", 2));
  }

  @Test
  public void should_throw_error_if_entry_is_null() {
    thrown.expect(NullPointerException.class);
    Entry[] entries = { entry("key6", 6), null };
    new MapAssert(map).includes(entries);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new MapAssert(null).includes(entry("key6", 6));
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new MapAssert(null).as("A Test").includes(entry("key6", 6));
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    Entry[] entry = null;
    new MapAssert(map).includes(entry);
  }

  @Test
  public void should_fail_if_actual_does_not_include_entry() {
    thrown.expect(AssertionError.class, "the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key6'=6]>");
    new MapAssert(map).includes(entry("key6", 6));
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_include_entry() {
    String message = "[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key6'=6]>";
    thrown.expect(AssertionError.class, message);
    new MapAssert(map).as("A Test").includes(entry("key6", 6));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_include_entry() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).overridingErrorMessage("My custom message").includes(entry("key6", 6));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_include_entry() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).as("A Test").overridingErrorMessage("My custom message").includes(entry("key6", 6));
  }

  @Test
  public void should_fail_if_actual_does_not_include_entries() {
    String message = "the map:<{'key1'=1, 'key2'=2}> does not contain the entries:<['key6'=6, 'key8'=8]>";
    thrown.expect(AssertionError.class, message);
    new MapAssert(map).includes(entry("key6", 6), entry("key8", 8));
  }

  @Test
  public void should_fail_and_display_description_if_actual_does_not_include_entries() {
    String message = "[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the entries:<['key6'=6, 'key8'=8]>";
    thrown.expect(AssertionError.class, message);
    new MapAssert(map).as("A Test").includes(entry("key6", 6), entry("key8", 8));
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_include_entries() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).overridingErrorMessage("My custom message").includes(entry("key6", 6), entry("key8", 8));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_does_not_include_entries() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).as("A Test").overridingErrorMessage("My custom message")
    .includes(entry("key6", 6), entry("key8", 8));
  }

  @Test
  public void should_fail_if_entry_contains_existing_value_but_not_existing_key() {
    thrown.expect(AssertionError.class, "the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key1'=6]>");
    new MapAssert(map).includes(entry("key1", 6));
  }

  @Test
  public void should_fail_and_display_description_if_entry_contains_existing_value_but_not_existing_key() {
    String message = "[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key1'=6]>";
    thrown.expect(AssertionError.class, message);
    new MapAssert(map).as("A Test").includes(entry("key1", 6));
  }

  @Test
  public void should_fail_with_custom_message_if_entry_contains_existing_value_but_not_existing_key() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).overridingErrorMessage("My custom message").includes(entry("key1", 6));
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_entry_contains_existing_value_but_not_existing_key() {
    thrown.expect(AssertionError.class, "My custom message");
    new MapAssert(map).as("A Test").overridingErrorMessage("My custom message").includes(entry("key1", 6));
  }
}
