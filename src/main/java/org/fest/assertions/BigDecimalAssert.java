/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.assertions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * Assertions for {@code BigDecimal}s.
 * </p>
 * 
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(BigDecimal)}.
 * </p>
 * 
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert extends ComparableAssert<BigDecimalAssert, BigDecimal> implements NumberAssert {
  /**
   * Creates a new {@link BigDecimalAssert}.
   * 
   * @param actual the target to verify.
   */
  protected BigDecimalAssert(@Nullable BigDecimal actual) {
    super(BigDecimalAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code BigDecimal} is positive.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code BigDecimal} value is {@code null}.
   * @throws AssertionError if the actual {@code BigDecimal} value is not positive.
   */
  @Override
  public @Nonnull BigDecimalAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code BigDecimal} is negative.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code BigDecimal} value is {@code null}.
   * @throws AssertionError if the actual {@code BigDecimal} value is not negative.
   */
  @Override
  public @Nonnull BigDecimalAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Verifies that the actual {@code BigDecimal} is equal to zero, regardless of precision.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code BigDecimal} value is {@code null}.
   * @throws AssertionError if the actual {@code BigDecimal} value is not equal to zero.
   */
  @Override
  public @Nonnull BigDecimalAssert isZero() {
    return isEqualByComparingTo(ZERO);
  }

  /**
   * Verifies that the actual {@code BigDecimal} is not equal to zero, regardless of precision.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code BigDecimal} is {@code null}.
   * @throws AssertionError if the actual {@code BigDecimal} is equal to zero.
   */
  public @Nonnull BigDecimalAssert isNotZero() {
    return isNotEqualByComparingTo(ZERO);
  }
}
