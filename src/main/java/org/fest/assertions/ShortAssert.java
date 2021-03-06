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

import org.fest.util.VisibleForTesting;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static java.lang.Short.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Assertions for {@code Short}s and {@code short}s.
 * <p/>
 * To create a new instance of this class invoke either {@link Assertions#assertThat(Short)} or
 * {@link Assertions#assertThat(short)}.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ShortAssert extends GenericAssert<ShortAssert, Short> implements NumberAssert {
  private static final short ZERO = (short) 0;

  @VisibleForTesting ShortAssert(int actual) {
    this((short) actual);
  }

  /**
   * Creates a new {@link ShortAssert}.
   *
   * @param actual the actual value to verify.
   */
  protected ShortAssert(short actual) {
    super(ShortAssert.class, actual);
  }

  /**
   * Creates a new {@link ShortAssert}.
   *
   * @param actual the actual value to verify.
   */
  protected ShortAssert(@Nullable Short actual) {
    super(ShortAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Short} is equal to the given one.
   *
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not equal to the given one.
   */
  public @NotNull ShortAssert isEqualTo(short expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Short} is not equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is equal to the given one.
   */
  public @NotNull ShortAssert isNotEqualTo(short other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Short} is greater than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not greater than the given one.
   */
  public @NotNull ShortAssert isGreaterThan(short other) {
    if (actual > other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is less than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not less than the given one.
   */
  public @NotNull ShortAssert isLessThan(short other) {
    if (actual < other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is greater or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not greater than or equal to the given one.
   */
  public @NotNull ShortAssert isGreaterThanOrEqualTo(short other) {
    if (actual >= other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is less or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not less than or equal to the given one.
   */
  public @NotNull ShortAssert isLessThanOrEqualTo(short other) {
    if (actual <= other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Short} is equal to zero.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not equal to zero.
   */
  @Override
  public @NotNull ShortAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Short} is positive.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not positive.
   */
  @Override
  public @NotNull ShortAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Short} is negative.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Short} is not negative.
   */
  @Override
  public @NotNull ShortAssert isNegative() {
    return isLessThan(ZERO);
  }
}
