/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.*;

import java.util.Arrays;

/**
 * Assertions for {@code byte} arrays.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(byte[])}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert extends ArrayAssert<byte[]> {

  /**
   * Creates a new </code>{@link ByteArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected ByteArrayAssert(byte... actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  @Override public ByteArrayAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override public ByteArrayAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override public ByteArrayAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override public ByteArrayAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code byte} array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not contain the given values.
   */
  public ByteArrayAssert contains(byte...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not contain the given objects, or if the actual
   * {@code byte} array contains elements other than the ones specified.
   */
  public ByteArrayAssert containsOnly(byte...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array contains any of the given values.
   */
  public ByteArrayAssert excludes(byte...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not satisfy the given condition.
   * @see #is(Condition)
   */
  @Override public ByteArrayAssert satisfies(Condition<byte[]> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code byte} array satisfies the given condition.
   * @see #isNot(Condition)
   */
  @Override public ByteArrayAssert doesNotSatisfy(Condition<byte[]> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not satisfy the given condition.
   * @since 1.2
   */
  @Override public ByteArrayAssert is(Condition<byte[]> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code byte} array satisfies the given condition.
   * @since 1.2
   */
  @Override public ByteArrayAssert isNot(Condition<byte[]> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array is not {@code null}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   */
  @Override public ByteArrayAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array contains at least on element.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array is empty.
   */
  @Override public ByteArrayAssert isNotEmpty() {
    assertIsNotEmpty();
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(byte[], byte[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is not equal to the given one.
   */
  @Override public ByteArrayAssert isEqualTo(byte[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code byte} array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(byte[], byte[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is equal to the given one.
   */
  @Override public ByteArrayAssert isNotEqualTo(byte[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }

  /**
   * Verifies that the number of elements in the actual {@code byte} array is equal to the given one.
   * @param expected the expected number of elements in the actual {@code byte} array.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws AssertionError if the number of elements in the actual {@code byte} array is not equal to the given
   * one.
   */
  @Override public ByteArrayAssert hasSize(int expected) {
    assertHasSize(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array is the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is not the same as the given one.
   */
  @Override public ByteArrayAssert isSameAs(byte[] expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array is not the same as the given array.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is the same as the given one.
   */
  @Override public ByteArrayAssert isNotSameAs(byte[] expected) {
    assertNotSameAs(expected);
    return this;
  }

  /** {@inheritDoc} */
  @Override public ByteArrayAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
