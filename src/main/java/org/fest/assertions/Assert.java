/*
 * Created on Oct 10, 2007
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

import static org.fest.assertions.Fail.failWithMessage;
import static org.fest.assertions.Formatting.format;
import static org.fest.assertions.Formatting.valueOf;

/**
 * Base class for all assertion classes.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Assert {
  private Description description;
  private String errorMessage;

  /**
   * @return the description of the actual value in this assertion.
   */
  public final @Nullable String description() {
    return valueOf(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any {@code AssertionError} thrown when an
   * assertion fails.
   *
   * @param d the new description.
   */
  protected final void description(@Nullable String d) {
    description(d == null ? null : new BasicDescription(d));
  }

  /**
   * Sets the description of the actual value, to be used in as message of any {@code AssertionError} thrown when an
   * assertion fails.
   *
   * @param d the new description.
   */
  protected final void description(@Nullable Description d) {
    this.description = d;
  }

  /**
   * Returns the description of the actual value in this assertion.
   *
   * @return the description of the actual value in this assertion.
   * @since 1.2
   */
  protected final @Nullable Description rawDescription() {
    return description;
  }

  /**
   * Returns the given message formatted as follows:
   * <p/>
   * <pre>
   * [assertion description] given message.
   * </pre>
   *
   * @param message the message to format.
   * @return the formatted message.
   * @throws NullPointerException if the given message is {@code null}.
   */
  protected final @NotNull String formattedErrorMessage(@NotNull String message) {
    return format(description, message);
  }

  /**
   * Specifies the message to use in case of a failure, replacing the default one.
   *
   * @param message the new error message.
   */
  protected final void replaceDefaultErrorMessagesWith(@Nullable String message) {
    errorMessage = message;
  }

  /**
   * Returns the message to use when a failure occurs, if one has been specified.
   *
   * @return the message to use when a failure occurs, or {@code null} if none has been specified.
   */
  protected final @Nullable String customErrorMessage() {
    return errorMessage;
  }

  /**
   * Throws an {@code AssertionError} only if the the custom message in this assertion object is not {@code null}.
   *
   * @throws AssertionError only if the custom error message in this assertion object is not {@code null}.
   * @since 1.2
   */
  protected final void failIfCustomMessageIsSet() {
    failWithMessage(customErrorMessage());
  }

  /**
   * Throws an {@code AssertionError} only if the the custom message in this assertion object is not {@code null}.
   *
   * @param realCause cause of the error.
   * @throws AssertionError only if the custom error message in this assertion object is not {@code null}.
   * @since 1.2
   */
  protected final void failIfCustomMessageIsSet(@Nullable Throwable realCause) {
    failWithMessage(customErrorMessage(), realCause);
  }

  /**
   * Fails by throwing an {@code AssertionError}.
   *
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @param cause  the root cause of the failure, included in the thrown exception.
   * @throws NullPointerException if the given reason is {@code null}.
   */
  protected final void fail(@NotNull String reason, @Nullable Throwable cause) {
    Fail.fail(formattedErrorMessage(reason), cause);
  }

  /**
   * Fails by throwing an {@code AssertionError}.
   * <p/>
   * <strong>Note:</strong> This method appears to return {@code AssertionError}, but it is really not the case, since
   * the exception is thrown and not returned. In version 2.0 the return type of this method will change to {@code void}
   * . Since we cannot create an overloaded version with return type {@code void}, we cannot deprecate this method.
   * Please pretend this method does not return anything :)
   *
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @return the thrown {@code AssertionError}.
   * @throws AssertionError using the given reason as the message.
   * @see #failure(String)
   */
  protected final @NotNull AssertionError fail(@NotNull String reason) {
    // TODO in 2.0: change return type to 'void'
    throw failure(reason);
  }

  /**
   * Creates an {@code AssertionError}, adding the description of the actual value to the given message.
   *
   * @param reason the reason for the failure, used as the message for the thrown exception.
   * @return the created exception.
   */
  protected final @NotNull AssertionError failure(@NotNull String reason) {
    return Fail.failure(formattedErrorMessage(reason));
  }

  /**
   * Throws {@code UnsupportedOperationException} if called. It is easy to accidentally call {@link #equals(Object)}
   * instead of {@code isEqualTo}.
   *
   * @throws UnsupportedOperationException if this method is called.
   */
  @Override
  public final boolean equals(Object obj) {
    throw new UnsupportedOperationException("'equals' is not supported...maybe you intended to call 'isEqualTo'");
  }

  /**
   * Always returns 1.
   *
   * @return 1.
   */
  @Override
  public final int hashCode() {
    return 1;
  }
}
