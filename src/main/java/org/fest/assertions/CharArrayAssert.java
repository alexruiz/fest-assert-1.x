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
 * <p>
 * Assertions for arrays of {@code char}.
 * </p>
 *
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(char[])}.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert extends ArrayAssert<CharArrayAssert, char[]> {
  /**
   * Creates a new {@link CharArrayAssert}.
   * 
   * @param actual the target to verify.
   */
  protected CharArrayAssert(@Nullable char... actual) {
    super(CharArrayAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code char} array contains the given values.
   * 
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code char} array is {@code null}.
   * @throws NullPointerException if the given {@code char} array is {@code null}.
   * @throws AssertionError if the actual {@code char} array does not contain the given values.
   */
  public @Nonnull CharArrayAssert contains(@Nonnull char... values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code char} array contains the given values <strong>only</strong>.
   * 
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code char} array is {@code null}.
   * @throws NullPointerException if the given {@code char} array is {@code null}.
   * @throws AssertionError if the actual {@code char} array does not contain the given objects, or if the actual
   *           {@code char} array contains elements other than the ones specified.
   */
  public @Nonnull CharArrayAssert containsOnly(@Nonnull char... values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code char} array does not contain the given values.
   * 
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code char} array is {@code null}.
   * @throws NullPointerException if the given {@code char} array is {@code null}.
   * @throws AssertionError if the actual {@code char} array contains any of the given values.
   */
  public @Nonnull CharArrayAssert excludes(@Nonnull char... values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code char} array is equal to the given array. Array equality is checked by
   * {@link Arrays#equals(char[], char[])}.
   * 
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code char} array is not equal to the given one.
   */
  @Override
  public @Nonnull CharArrayAssert isEqualTo(@Nullable char[] expected) {
    if (Arrays.equals(actual, expected)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code char} array is not equal to the given array. Array equality is checked by
   * {@link Arrays#equals(char[], char[])}.
   * 
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code char} array is equal to the given one.
   */
  @Override
  public @Nonnull CharArrayAssert isNotEqualTo(@Nullable char[] array) {
    if (!Arrays.equals(actual, array)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }
}
