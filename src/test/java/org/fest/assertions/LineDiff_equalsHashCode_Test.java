/*
 * Created on Sep 27, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.EqualsHashCodeContractAssert.*;
import static org.junit.Assert.*;

import org.fest.assertions.FileContentComparator.LineDiff;
import org.fest.test.EqualsHashCodeContractTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link LineDiff#equals(Object)}</code> and <code>{@link LineDiff#hashCode()}</code>.
 *
 * @author Alex Ruiz
 */
public class LineDiff_equalsHashCode_Test implements EqualsHashCodeContractTestCase {

  private LineDiff diff;

  @Before public void setUp() {
    diff = LineDiff.lineDiff(1, "hello", "world");
  }

  @Test public void equals_should_be_consistent() {
    LineDiff other = LineDiff.lineDiff(1, "hello", "world");
    assertEquals(other, diff);
  }

  @Test public void equals_should_be_reflexive() {
    assertEqualsIsReflexive(diff);
  }

  @Test public void equals_should_be_symmetric() {
    LineDiff other = LineDiff.lineDiff(1, "hello", "world");
    assertEqualsIsSymmetric(diff, other);
  }

  @Test public void equals_should_be_transitive() {
    LineDiff other1 = LineDiff.lineDiff(1, "hello", "world");
    LineDiff other2 = LineDiff.lineDiff(1, "hello", "world");
    assertEqualsIsTransitive(diff, other1, other2);
  }

  @Test public void should_maintain_equals_and_hashCode_contract() {
    LineDiff other = LineDiff.lineDiff(1, "hello", "world");
    assertMaintainsEqualsAndHashCodeContract(diff, other);
  }

  @Test public void should_not_be_equal_to_Object_of_different_type() {
    assertFalse(diff.equals("hello"));
  }

  @Test public void should_not_be_equal_to_null() {
    assertIsNotEqualToNull(diff);
  }

  @Test public void should_not_be_equal_if_line_numbers_are_not_equal() {
    LineDiff other = LineDiff.lineDiff(2, "hello", "world");
    assertFalse(diff.equals(other));
  }

  @Test public void should_not_be_equal_if_actual_values_are_not_equal() {
    LineDiff other = LineDiff.lineDiff(1, "hi", "world");
    assertFalse(diff.equals(other));
  }

  @Test public void should_not_be_equal_if_expected_values_are_not_equal() {
    LineDiff other = LineDiff.lineDiff(1, "hello", "everybody");
    assertFalse(diff.equals(other));
  }
}
