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

import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Strings.isNullOrEmpty;
import static org.fest.util.Strings.quote;
import static org.fest.util.ToString.toStringOf;

/**
 * Creates instances of JUnit's {@code ComparisonFailure}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class ComparisonFailureFactory {
  private static final String EMPTY_MESSAGE = "";
  private static ConstructorInvoker constructorInvoker = new ConstructorInvoker();

  private ComparisonFailureFactory() {
  }

  static void constructorInvoker(@NotNull ConstructorInvoker newConstructorInvoker) {
    constructorInvoker = checkNotNull(newConstructorInvoker);
  }

  /**
   * Creates a new instance of JUnit's {@code ComparisonFailure} only if JUnit 4+ is in the classpath.
   *
   * @param message  the identifying message or {@code null}.
   * @param expected the expected value.
   * @param actual   the actual value.
   * @return the created {@code ComparisonFailure}, or {@code null} if JUnit 4+ is not in the classpath.
   */
  public static @Nullable AssertionError comparisonFailure(
      @Nullable String message, @Nullable Object expected, @Nullable Object actual) {
    try {
      return newComparisonFailure(clean(message), expected, actual);
    } catch (Exception e) {
      return null;
    }
  }

  private static @NotNull String clean(@Nullable String message) {
    return message == null ? "" : message;
  }

  private static @Nullable AssertionError newComparisonFailure(
      @Nullable String message, @Nullable Object expected, @Nullable Object actual) throws Exception {
    String className = "org.junit.ComparisonFailure";
    Class<?>[] parameterTypes = new Class<?>[]{String.class, String.class, String.class};
    Object[] parameterValues = new Object[]{format(message), asString(expected), asString(actual)};
    Object o = constructorInvoker.newInstance(className, parameterTypes, parameterValues);
    if (o instanceof AssertionError) {
      return (AssertionError) o;
    }
    return null;
  }

  private static @Nullable String asString(@Nullable Object o) {
    if (o instanceof String) {
      return quote((String) o);
    }
    if (o == null) {
      return null;
    }
    return toStringOf(o);
  }

  private static @NotNull String format(@Nullable String message) {
    if (isNullOrEmpty(message)) {
      return EMPTY_MESSAGE;
    }
    return String.format("[%s]", message);
  }
}
