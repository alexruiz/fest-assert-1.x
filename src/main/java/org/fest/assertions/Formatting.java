/*
 * Created on Sep 14, 2007
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
import java.util.IllegalFormatException;

import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isNullOrEmpty;
import static org.fest.util.ToString.toStringOf;

/**
 * Utility methods for formatting values.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class Formatting {
  private static final String EMPTY_MESSAGE = "";

  private Formatting() {
  }

  static @NotNull String createMessageFrom(@Nullable Description description, @NotNull Object[] message) {
    return format(description, concat(message));
  }

  /**
   * Returns the given message formatted as follows:
   * <pre>
   * [description] message.
   * </pre>
   *
   * @param description the description of the actual value in the failed assertion. It can be {@code null}.
   * @param message     the message to format.
   * @return the formatted message.
   * @throws NullPointerException if the given message is {@code null}.
   * @since 1.2
   */
  public static @NotNull String format(@Nullable Description description, @NotNull String message) {
    checkNotNull(message);
    String s = valueOf(description);
    return format(s) + message;
  }

  /**
   * Returns the value of the given {@link Description}.
   *
   * @param description the given {@code Description}.
   * @return the value of the given {@code Description}, or {@code null} if the given {@code Description} is
   *         {@code null}.
   */
  public static @Nullable String valueOf(@Nullable Description description) {
    return description == null ? null : description.value();
  }

  /**
   * Formats the given message: <li>if it is {@code null} or empty, an empty {@code String} is returned, otherwise uses
   * the following format:
   * <pre>
   * [message]{whitespace}
   * </pre>
   *
   * @param message the message to format.
   * @return the formatted message.
   */
  public static @NotNull String format(@Nullable String message) {
    if (isNullOrEmpty(message)) {
      return EMPTY_MESSAGE;
    }
    return String.format("[%s] ", message);
  }

  /**
   * Returns the {@code String} representation of the given object in between brackets ("<" and ">"). This method has
   * special support for arrays, {@code Class<?>}, {@code Collection}s, {@code Map}s, {@code File}s and
   * {@code Dimension}s. For any other types, this method simply calls its {@code toString} implementation.
   *
   * @param o the given object.
   * @return the {@code String} representation of the given object in between brackets.
   */
  public static @NotNull String inBrackets(@Nullable Object o) {
    return doBracketAround(toStringOf(o));
  }

  private static @NotNull String doBracketAround(@Nullable String s) {
    return String.format("<%s>", s);
  }

  /**
   * Returns a formatted string using the specified format {@code String} and arguments.
   *
   * @param format a format {@code String}.
   * @param args   Arguments referenced by the format specifiers in the format {@code String}. If there are more
   *               arguments than format specifiers, the extra arguments are ignored. The number of arguments is
   *               variable and may be zero.
   * @return A formatted {@code String}.
   * @throws IllegalFormatException If a format string contains an illegal syntax, a format specifier that is
   *                                incompatible with the given arguments, insufficient arguments given the format
   *                                {@code String}, or other illegal conditions.
   * @throws NullPointerException   if the given format is {@code null}.
   * @since 1.3.1
   */
  public static @NotNull String format(@NotNull String format, @NotNull Object... args) {
    Object[] argsAsText = new String[args.length];
    for (int i = 0; i < args.length; i++) {
      argsAsText[i] = toStringOf(args[i]);
    }
    return String.format(format, argsAsText);
  }
}
