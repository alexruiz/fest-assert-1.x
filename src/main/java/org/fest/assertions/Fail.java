/*
 * Created on Mar 19, 2007
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.fest.assertions.ComparisonFailureFactory.comparisonFailure;
import static org.fest.assertions.ErrorMessages.unexpectedEqual;
import static org.fest.assertions.ErrorMessages.unexpectedNotEqual;
import static org.fest.assertions.Formatting.*;
import static org.fest.util.Arrays.array;
import static org.fest.util.Objects.areEqual;

/**
 * Common failures.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class Fail {
  /**
   * This constructor is protected to make it possible to subclass this class. Since all its methods are static, there
   * is no point on creating a new instance of it.
   */
  protected Fail() {
  }

  /**
   * Fails with no message.
   *
   * @throws AssertionError without any message.
   */
  public static void fail() {
    fail(null);
  }

  /**
   * Throws an {@code AssertionError} if the given objects are equal.
   *
   * @param customErrorMessage  any custom error message. This message will replace the default one only if it (the
   *                            custom message) is not {@code null}.
   * @param descriptionOfActual the description of the actual value.
   * @param actual              the actual object.
   * @param other               the object to compare to.
   * @throws AssertionError if the given objects are equal.
   * @since 1.2
   */
  protected static void failIfEqual(@Nullable String customErrorMessage, @Nullable Description descriptionOfActual,
                                    @Nullable Object actual, @Nullable Object other) {
    if (!areEqual(actual, other)) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(format(descriptionOfActual, unexpectedEqual(actual, other)));
  }

  /**
   * Throws an {@code AssertionError} if 'actual' is not equal to 'expected'. If JUnit 4 (or greater) is in the
   * classpath, this method will throw a {@code ComparisonFailure} instead. More details about this feature can be
   * found <a href="http://docs.codehaus.org/display/FEST/JUnit-Specific+Features">here</a>.
   *
   * @param customErrorMessage  any custom error message. This message will replace the default one only if it (the
   *                            custom message) is not {@code null}.
   * @param descriptionOfActual the description of the actual value.
   * @param actual              the actual object.
   * @param expected            the expected object.
   * @throws AssertionError if the given objects are not equal.
   * @since 1.2
   */
  protected static void failIfNotEqual(@Nullable String customErrorMessage, @Nullable Description descriptionOfActual,
                                       @Nullable Object actual, @Nullable Object expected) {
    if (areEqual(actual, expected)) {
      return;
    }
    failWithMessage(customErrorMessage);
    throw comparisonFailed(descriptionOfActual, actual, expected);
  }

  /**
   * Returns a {@code AssertionError} describing a comparison failure.
   *
   * @param description the description of the comparison.
   * @param actual      the actual value.
   * @param expected    the expected value.
   * @return a {@code AssertionError} describing the comparison failure.
   * @since 1.3
   */
  protected static @NotNull AssertionError comparisonFailed(
      @Nullable Description description, @Nullable Object actual, @Nullable Object expected) {
    AssertionError comparisonFailure = comparisonFailure(valueOf(description), expected, actual);
    if (comparisonFailure != null) {
      return comparisonFailure;
    }
    return failure(format(description, unexpectedNotEqual(actual, expected)));
  }

  /**
   * Throws an {@code AssertionError} if the actual value is {@code null}.
   *
   * @param customErrorMessage any custom error message. This message will replace the default one only if it (the
   *                           custom message) is not {@code null}.
   * @param description        the description of the actual value.
   * @param actual             the actual value.
   * @throws AssertionError if the actual value is {@code null}.
   * @since 1.3
   */
  protected static void failIfActualIsNull(@Nullable String customErrorMessage, @Nullable Description description,
                                           @Nullable Object actual) {
    if (actual != null) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(description, array("expecting actual value not to be null"));
  }

  /**
   * Throws an {@code AssertionError} if the given object is {@code null}.
   *
   * @param customErrorMessage any custom error message. This message will replace the default one only if it (the
   *                           custom message) is not {@code null}.
   * @param description        the description of the given object.
   * @param o                  the given object.
   * @throws AssertionError if the given object is {@code null}.
   * @since 1.2
   */
  protected static void failIfNull(@Nullable String customErrorMessage, @Nullable Description description,
                                   @Nullable Object o) {
    if (o != null) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(description, array("expecting a non-null object, but it was null"));
  }

  /**
   * Throws an {@code AssertionError} if the given object is not {@code null}.
   *
   * @param customErrorMessage any custom error message. This message will replace the default one only if it (the
   *                           custom message) is not {@code null}.
   * @param description        the description of the given object.
   * @param o                  the given object.
   * @throws AssertionError if the given object is not {@code null}.
   * @since 1.2
   */
  protected static void failIfNotNull(@Nullable String customErrorMessage, @Nullable Description description,
                                      @Nullable Object o) {
    if (o == null) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(description, array(inBrackets(o), " should be null"));
  }

  /**
   * Throws an {@code AssertionError} if the given objects are the same.
   *
   * @param customErrorMessage  any custom error message. This message will replace the default one only if it (the
   *                            custom message) is not {@code null}.
   * @param descriptionOfActual the description of the actual value.
   * @param actual              the actual object.
   * @param other               the object to compare to.
   * @throws AssertionError if the given objects are the same.
   * @since 1.2
   */
  protected static void failIfSame(@Nullable String customErrorMessage, @Nullable Description descriptionOfActual,
                                   @Nullable Object actual, @Nullable Object other) {
    if (actual != other) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(descriptionOfActual, array("given objects are same:", inBrackets(actual)));
  }

  /**
   * Throws an {@code AssertionError} if the given objects are not the same.
   *
   * @param customErrorMessage  any custom error message. This message will replace the default one only if it (the
   *                            custom message) is not {@code null}.
   * @param descriptionOfActual the description of the actual value.
   * @param actual              the actual object.
   * @param other               the object to compare to.
   * @throws AssertionError if the given objects are not the same.
   * @since 1.2
   */
  protected static void failIfNotSame(@Nullable String customErrorMessage, @Nullable Description descriptionOfActual,
                                      @Nullable Object actual, @Nullable Object other) {
    if (actual == other) {
      return;
    }
    failWithMessage(customErrorMessage);
    fail(descriptionOfActual,
        array("expected same instance but found:", inBrackets(actual), " and:", inBrackets(other)));
  }

  private static void fail(@Nullable Description description, @NotNull Object[] message) {
    throw failure(createMessageFrom(description, message));
  }

  /**
   * Throws an {@code AssertionError} only if the given custom message is not {@code null}.
   *
   * @param customErrorMessage the custom error message.
   * @throws AssertionError only if the custom error message is not {@code null}.
   * @since 1.2
   */
  protected static void failWithMessage(@Nullable String customErrorMessage) {
    if (customErrorMessage != null) {
      fail(customErrorMessage);
    }
  }

  /**
   * Throws an {@code AssertionError} only if the given custom message is not {@code null}.
   *
   * @param customErrorMessage the custom error message.
   * @param realCause          cause of the error.
   * @throws AssertionError only if the custom error message is not {@code null}.
   * @since 1.2
   */
  protected static void failWithMessage(@Nullable String customErrorMessage, Throwable realCause) {
    if (customErrorMessage != null) {
      fail(customErrorMessage, realCause);
    }
  }

  /**
   * Throws an {@code AssertionError} with the given message and with the {@code Throwable} that caused the failure.
   *
   * @param description the description of the failed assertion. It can be {@code null}.
   * @param realCause   cause of the error.
   */
  public static void fail(String description, @Nullable Throwable realCause) {
    AssertionError error = failure(description);
    error.initCause(realCause);
    throw error;
  }

  /**
   * Fails with the given message.
   * <p/>
   * <strong>Note:</strong> This method appears to return {@code AssertionError}, but it is really not the case, since
   * the exception is thrown and not returned. In version 2.0 the return type of this method will change to
   * {@code void}. Since we cannot create an overloaded version with return type {@code void}, we cannot deprecate this
   * method. Please pretend this method does not return anything :)
   *
   * @param message error message.
   * @return the thrown {@code AssertionError}.
   * @throws AssertionError with the given message.
   * @see #failure(String)
   */
  public static @NotNull AssertionError fail(@Nullable String message) {
    // TODO in 2.0: change return type to 'void'
    throw failure(message);
  }

  /**
   * Creates a {@code AssertionError} with the given message.
   *
   * @param message the message of the exception to create.
   * @return the created exception.
   * @since 1.2
   */
  public static @NotNull AssertionError failure(@Nullable String message) {
    return new AssertionError(message);
  }
}
