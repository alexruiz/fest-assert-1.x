/*
 * Created on Sep 27, 2009
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

import static org.fest.assertions.Formatting.*;
import static org.fest.util.Arrays.array;

/**
 * Common error messages.
 *
 * @author Alex Ruiz
 */
final class ErrorMessages {
  private ErrorMessages() {
  }

  static @NotNull String unexpectedNotEqual(@Nullable Object actual, @Nullable Object expected) {
    return format("expected:<%s> but was:<%s>", expected, actual);
  }

  static @NotNull String unexpectedEqual(@Nullable Object actual, @Nullable Object o) {
    return assertionFailed(actual, " should not be equal to:", o);
  }

  static @NotNull String unexpectedLessThanOrEqualTo(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be greater than:", value);
  }

  static @NotNull String unexpectedLessThan(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be greater than or equal to:", value);
  }

  static @NotNull String unexpectedGreaterThanOrEqualTo(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be less than:", value);
  }

  static @NotNull String unexpectedGreaterThan(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be less than or equal to:", value);
  }

  static @NotNull String unexpectedNotIn(@Nullable Object actual, @NotNull Object other) {
    return assertionFailed(actual, " should be in:", other);
  }

  static @NotNull String unexpectedIn(@Nullable Object actual, @NotNull Object other) {
    return assertionFailed(actual, " should not be in:", other);
  }

  private static @NotNull String assertionFailed(
      @Nullable Object actual, @NotNull String reason, @Nullable Object expected) {
    return assertionFailed(null, actual, reason, expected);
  }

  private static @NotNull String assertionFailed(
      @Nullable Description description, @Nullable Object actual, @NotNull String reason, @Nullable Object expected) {
    return createMessageFrom(description, array("actual value:", inBrackets(actual), reason, inBrackets(expected)));
  }
}
