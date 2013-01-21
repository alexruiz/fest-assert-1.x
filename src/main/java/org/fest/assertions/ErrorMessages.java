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

import static org.fest.assertions.Formatting.createMessageFrom;
import static org.fest.assertions.Formatting.format;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Arrays.array;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Common error messages.
 * 
 * @author Alex Ruiz
 */
final class ErrorMessages {
  static @Nonnull String unexpectedNotEqual(@Nullable Object actual, @Nullable Object expected) {
    return format("expected:<%s> but was:<%s>", expected, actual);
  }

  static @Nonnull String unexpectedEqual(@Nullable Object actual, @Nullable Object o) {
    return assertionFailed(actual, " should not be equal to:", o);
  }

  static @Nonnull String unexpectedLessThanOrEqualTo(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be greater than:", value);
  }

  static @Nonnull String unexpectedLessThan(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be greater than or equal to:", value);
  }

  static @Nonnull String unexpectedGreaterThanOrEqualTo(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be less than:", value);
  }

  static @Nonnull String unexpectedGreaterThan(@Nullable Object actual, @Nullable Object value) {
    return assertionFailed(actual, " should be less than or equal to:", value);
  }

  static @Nonnull String unexpectedNullType(@Nullable Description description) {
    return createMessageFrom(description, array("expected type should not be null"));
  }

  static @Nonnull String unexpectedNotIn(@Nullable Object actual, @Nonnull Object other) {
    return assertionFailed(actual, " should be in:", other);
  }

  static @Nonnull String unexpectedIn(@Nullable Object actual, @Nonnull Object other) {
    return assertionFailed(actual, " should not be in:", other);
  }

  private static @Nonnull String assertionFailed(
      @Nullable Object actual, @Nonnull String reason, @Nullable Object expected) {
    return assertionFailed(null, actual, reason, expected);
  }

  private static @Nonnull String assertionFailed(
      @Nullable Description description, @Nullable Object actual, @Nonnull String reason, @Nullable Object expected) {
    return createMessageFrom(description, array("actual value:", inBrackets(actual), reason, inBrackets(expected)));
  }

  private ErrorMessages() {}
}
