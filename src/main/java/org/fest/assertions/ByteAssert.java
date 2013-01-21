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

import static java.lang.Byte.valueOf;
import static org.fest.assertions.ErrorMessages.unexpectedGreaterThan;
import static org.fest.assertions.ErrorMessages.unexpectedGreaterThanOrEqualTo;
import static org.fest.assertions.ErrorMessages.unexpectedLessThan;
import static org.fest.assertions.ErrorMessages.unexpectedLessThanOrEqualTo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.util.VisibleForTesting;

/**
 * <p>
 * Assertions for {@code Byte}s and {@code byte}s.
 * </p>
 *
 * <p>
 * To create a new instance of this class invoke either {@link Assertions#assertThat(Byte)} or
 * {@link Assertions#assertThat(byte)}.
 * </p>
 * 
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 * 
 * @since 1.2
 */
public class ByteAssert extends GenericAssert<ByteAssert, Byte> implements NumberAssert {
  private static final byte ZERO = (byte) 0;

  @VisibleForTesting
  ByteAssert(int actual) {
    this((byte) actual);
  }

  /**
   * Creates a new {@link ByteAssert}.
   * 
   * @param actual the actual value to verify.
   */
  protected ByteAssert(byte actual) {
    super(ByteAssert.class, actual);
  }

  /**
   * Creates a new {@link ByteAssert}.
   * 
   * @param actual the actual value to verify.
   */
  protected ByteAssert(@Nullable Byte actual) {
    super(ByteAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to the given one.
   * 
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to the given one.
   */
  public @Nonnull ByteAssert isEqualTo(byte expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Byte} value is not equal to the given one.
   * 
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is equal to the given one.
   */
  public @Nonnull ByteAssert isNotEqualTo(byte other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Byte} value is greater than the given one.
   * 
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than the given one.
   */
  public @Nonnull ByteAssert isGreaterThan(byte other) {
    // TODO check for NPE
    if (actual > other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less than the given one.
   * 
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than the given one.
   */
  public @Nonnull ByteAssert isLessThan(byte other) {
    if (actual < other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is greater or equal to the given one.
   * 
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than or equal to the given one.
   */
  public @Nonnull ByteAssert isGreaterThanOrEqualTo(byte other) {
    if (actual >= other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less or equal to the given one.
   * 
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than or equal to the given one.
   */
  public @Nonnull ByteAssert isLessThanOrEqualTo(byte other) {
    if (actual <= other) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to zero.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to zero.
   */
  @Override
  public @Nonnull ByteAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is positive.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not positive.
   */
  @Override
  public @Nonnull ByteAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is negative.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not negative.
   */
  @Override
  public @Nonnull ByteAssert isNegative() {
    return isLessThan(ZERO);
  }
}
