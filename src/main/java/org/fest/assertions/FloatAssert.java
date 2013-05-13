/*
 * Created on Jun 18, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.lang.Float.compare;
import static java.lang.Float.valueOf;
import static java.lang.Math.abs;
import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.format;

/**
 * Assertions for {@code Float}s and {@code float}s.
 * <p/>
 * To create a new instance of this class invoke either {@link Assertions#assertThat(Float)} or
 * {@link Assertions#assertThat(float)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class FloatAssert extends GenericAssert<FloatAssert, Float> implements NumberAssert {
  private static final float ZERO = 0f;

  /**
   * Creates a new {@link FloatAssert}.
   *
   * @param actual the actual value to verify.
   */
  protected FloatAssert(float actual) {
    super(FloatAssert.class, actual);
  }

  /**
   * Creates a new {@link FloatAssert}.
   *
   * @param actual the actual value to verify.
   */
  protected FloatAssert(@Nullable Float actual) {
    super(FloatAssert.class, actual);
  }

  /**
   * Creates a new holder for a delta value to be used in
   * {@link FloatAssert#isEqualTo(float, org.fest.assertions.FloatAssert.Delta)}.
   *
   * @param d the delta value.
   * @return a new delta value holder.
   * @deprecated use method {@link org.fest.assertions.Delta#delta(double)} instead. This method will be removed in
   *             version 2.0.
   */
  @Deprecated
  public static @Nonnull Delta delta(float d) {
    return new Delta(d);
  }

  /**
   * Verifies that the actual {@code Float} is equal to the given one.
   *
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to the given one.
   */
  public @Nonnull FloatAssert isEqualTo(float expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Float} is equal to the given one, within a positive delta.
   *
   * @param expected the value to compare the actual one to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to the given one.
   * @deprecated use method {@link #isEqualTo(float, org.fest.assertions.Delta)} instead. This method will be removed in
   *             version 2.0.
   */
  @Deprecated
  public @Nonnull FloatAssert isEqualTo(float expected, @Nonnull Delta delta) {
    return isEqualTo(expected, delta.value);
  }

  /**
   * Verifies that the actual {@code Float} is equal to the given one, within a positive delta.
   *
   * @param expected the value to compare the actual one to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to the given one.
   * @since 1.2
   */
  public @Nonnull FloatAssert isEqualTo(float expected, @Nonnull org.fest.assertions.Delta delta) {
    return isEqualTo(expected, delta.floatValue());
  }

  private @Nonnull FloatAssert isEqualTo(float expected, float deltaValue) {
    return isEqualTo(valueOf(expected), deltaValue);
  }

  /**
   * Verifies that the actual {@code Float} is equal to the given one, within a positive delta.
   *
   * @param expected the value to compare the actual one to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to the given one.
   * @since 1.3
   */
  public @Nonnull FloatAssert isEqualTo(@Nullable Float expected, @Nonnull org.fest.assertions.Delta delta) {
    return isEqualTo(expected, delta.floatValue());
  }

  private @Nonnull FloatAssert isEqualTo(@Nullable Float expected, float deltaValue) {
    if (actual == null || expected == null) {
      return isEqualTo(expected);
    }
    if (actual.compareTo(expected) == 0) {
      return this;
    }
    if (abs(expected - actual) <= deltaValue) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected) + format(" using delta:<%s>", deltaValue));
  }

  /**
   * Verifies that the actual {@code Float} is not equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is equal to the given one.
   */
  public @Nonnull FloatAssert isNotEqualTo(float other) {
    if (compareTo(other) != 0) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, other));
  }

  /**
   * Verifies that the actual {@code Float} is greater than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not greater than the given one.
   */
  public @Nonnull FloatAssert isGreaterThan(float other) {
    if (compareTo(other) > 0) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Float} is less than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not less than the given one.
   */
  public @Nonnull FloatAssert isLessThan(float other) {
    if (compareTo(other) < 0) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Float} is greater or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not greater than or equal to the given one.
   */
  public @Nonnull FloatAssert isGreaterThanOrEqualTo(float other) {
    if (compareTo(other) >= 0) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Float} is less or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not less than or equal to the given one.
   */
  public @Nonnull FloatAssert isLessThanOrEqualTo(float other) {
    if (compareTo(other) <= 0) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  private int compareTo(float other) {
    return compare(actual, other);
  }

  /**
   * Verifies that the actual {@code Float} is equal to {@link Float#NaN}.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to {@code NaN}.
   */
  public @Nonnull FloatAssert isNaN() {
    return isEqualTo(Float.NaN);
  }

  /**
   * Verifies that the actual {@code Float} is equal to zero.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not equal to zero.
   */
  @Override
  public @Nonnull FloatAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Float} is positive.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not positive.
   */
  @Override
  public @Nonnull FloatAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Float} is negative.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Float} is not negative.
   */
  @Override
  public @Nonnull FloatAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Holds a delta value to be used in {@link FloatAssert#isEqualTo(float, org.fest.assertions.FloatAssert.Delta)}.
   *
   * @deprecated use top-level class {@link org.fest.assertions.Delta} instead. This class will be removed in version
   *             2.0.
   */
  @Deprecated
  public static class Delta {
    final float value;

    private Delta(float value) {
      this.value = value;
    }
  }
}
