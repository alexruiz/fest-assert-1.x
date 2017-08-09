/*
 * Created on Oct 4, 2009
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Fail.comparisonFailed;

/**
 * Template for assertions applicable to {@link Comparable}s.
 *
 * @param <S> used to simulate "self types." For more information please read &quot;<a href="http://goo.gl/fjgOM"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <T> the type of the "actual" value.
 * @author Alex Ruiz
 * @author Ted M. Young
 */
public abstract class ComparableAssert<S, T extends Comparable<T>> extends GenericAssert<S, T> {
  /**
   * Creates a new {@link ComparableAssert}.
   *
   * @param selfType the "self type."
   * @param actual   the target to verify.
   */
  protected ComparableAssert(@NotNull Class<S> selfType, @Nullable T actual) {
    super(selfType, actual);
  }

  /**
   * Verifies that the actual {@link Comparable} is equal to the given one.
   *
   * @param expected the given {@code Comparable} to compare the actual {@code Comparable} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is not equal to the given one.
   */
  public final @NotNull S isEqualByComparingTo(T expected) {
    isNotNull();
    if (actual.compareTo(expected) == 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw comparisonFailed(rawDescription(), actual, expected);
  }

  /**
   * Verifies that the actual {@link Comparable} is <b>not</b> equal to the given one.
   *
   * @param expected the given {@code Comparable} to use to compare to the actual {@code Comparable}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is equal to the given one.
   */
  public final @NotNull S isNotEqualByComparingTo(T expected) {
    isNotNull();
    if (actual.compareTo(expected) != 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@link Comparable} is less than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is not less than the given one.
   */
  public final @NotNull S isLessThan(T other) {
    isNotNull();
    if (actual.compareTo(other) < 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@link Comparable} is greater than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is not greater than the given one.
   */
  public final @NotNull S isGreaterThan(@Nullable T other) {
    isNotNull();
    if (actual.compareTo(other) > 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@link Comparable} is less than or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is not less than or equal to the given one.
   */
  public final @NotNull S isLessThanOrEqualTo(@Nullable T other) {
    isNotNull();
    if (actual.compareTo(other) <= 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@link Comparable} is greater than or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Comparable} is {@code null}.
   * @throws AssertionError if the actual {@code Comparable} is not greater than or equal to the given one.
   */
  public final @NotNull S isGreaterThanOrEqualTo(@Nullable T other) {
    isNotNull();
    if (actual.compareTo(other) >= 0) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }
}