/*
 * Created on Feb 14, 2008
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

import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.unexpectedEqual;
import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Assertions for {@code byte} arrays.
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(byte[])}.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert extends ArrayAssert<ByteArrayAssert, byte[]> {
  /**
   * Creates a new {@link ByteArrayAssert}.
   * 
   * @param actual the target to verify.
   */
  protected ByteArrayAssert(@Nullable byte... actual) {
    super(ByteArrayAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code byte} array contains the given values.
   * 
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not contain the given values.
   */
  public @Nonnull ByteArrayAssert contains(@Nonnull byte... values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array contains the given values <strong>only</strong>.
   * 
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array does not contain the given objects, or if the actual
   *           {@code byte} array contains elements other than the ones specified.
   */
  public @Nonnull ByteArrayAssert containsOnly(@Nonnull byte... values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array does not contain the given values.
   * 
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is {@code null}.
   * @throws NullPointerException if the given {@code byte} array is {@code null}.
   * @throws AssertionError if the actual {@code byte} array contains any of the given values.
   */
  public @Nonnull ByteArrayAssert excludes(@Nonnull byte... values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code byte} array is equal to the given array. Array equality is checked by
   * {@link Arrays#equals(byte[], byte[])}.
   * 
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is not equal to the given one.
   */
  @Override
  public @Nonnull ByteArrayAssert isEqualTo(@Nullable byte[] expected) {
    if (Arrays.equals(actual, expected)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code byte} array is not equal to the given array. Array equality is checked by
   * {@link Arrays#equals(byte[], byte[])}.
   * 
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code byte} array is equal to the given one.
   */
  @Override
  public @Nonnull ByteArrayAssert isNotEqualTo(@Nullable byte[] array) {
    if (!Arrays.equals(actual, array)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }
}
