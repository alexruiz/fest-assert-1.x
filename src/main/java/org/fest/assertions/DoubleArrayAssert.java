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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Arrays;

import static java.lang.Math.abs;
import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.unexpectedEqual;
import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Preconditions.checkNotNull;

/**
 * Assertions for arrays of {@code double}.
 * <p/>
 * To create a new instance of this class invoke {@link Assertions#assertThat(double[])}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DoubleArrayAssert extends ArrayAssert<DoubleArrayAssert, double[]> {
  /**
   * Creates a new {@link DoubleArrayAssert}.
   *
   * @param actual the target to verify.
   */
  protected DoubleArrayAssert(@Nullable double... actual) {
    super(DoubleArrayAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code double} array contains the given values.
   *
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError       if the actual {@code double} array is {@code null}.
   * @throws NullPointerException if the given {@code double} array is {@code null}.
   * @throws AssertionError       if the actual {@code double} array does not contain the given values.
   */
  public @NotNull DoubleArrayAssert contains(@NotNull double... values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code double} array contains the given values <strong>only</strong>.
   *
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError       if the actual {@code double} array is {@code null}.
   * @throws NullPointerException if the given {@code double} array is {@code null}.
   * @throws AssertionError       if the actual {@code double} array does not contain the given objects, or if the actual
   *                              {@code double} array contains elements other than the ones specified.
   */
  public @NotNull DoubleArrayAssert containsOnly(@NotNull double... values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code double} array does not contain the given values.
   *
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError       if the actual {@code double} array is {@code null}.
   * @throws NullPointerException if the given {@code double} array is {@code null}.
   * @throws AssertionError       if the actual {@code double} array contains any of the given values.
   */
  public @NotNull DoubleArrayAssert excludes(@NotNull double... values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code double} array is equal to the given array. Array equality is checked by
   * {@link Arrays#equals(double[], double[])}.
   *
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code double} array is not equal to the given one.
   */
  @Override
  public @NotNull DoubleArrayAssert isEqualTo(@Nullable double[] expected) {
    if (Arrays.equals(actual, expected)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code double} array is equal to the given array, within a positive delta.
   *
   * @param expected the given array to compare the actual array to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws NullPointerException if {@code delta} is {@code null}.
   * @throws AssertionError       if the actual {@code double} array is not equal to the given one.
   * @since 1.1
   */
  public @NotNull DoubleArrayAssert isEqualTo(@Nullable double[] expected, @NotNull Delta delta) {
    checkNotNull(delta);
    if (actual == expected) {
      return this;
    }
    if (actual == null || expected == null) {
      throw failureWhenNotEqual(expected, delta);
    }
    int length = expected.length;
    if (actual.length != length) {
      failureWhenNotEqual(expected, delta);
    }
    for (int i = 0; i < length; i++) {
      if (!equals(expected[i], actual[i], delta)) {
        failureWhenNotEqual(expected, delta);
      }
    }
    return this;
  }

  private @NotNull AssertionError failureWhenNotEqual(@Nullable double[] expected, @NotNull Delta delta) {
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected) + format(" using delta:<%s>", delta.doubleValue()));
  }

  private boolean equals(double e, double a, @NotNull Delta delta) {
    if (Double.compare(e, a) == 0) {
      return true;
    }
    return abs(e - a) <= delta.doubleValue();
  }

  /**
   * Verifies that the actual {@code double} array is not equal to the given array. Array equality is checked by
   * {@link Arrays#equals(double[], double[])}.
   *
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code double} array is equal to the given one.
   */
  @Override
  public @NotNull DoubleArrayAssert isNotEqualTo(@Nullable double[] array) {
    if (!Arrays.equals(actual, array)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }
}
