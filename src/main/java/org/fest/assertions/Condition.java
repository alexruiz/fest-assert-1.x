/*
 * Created on Sep 17, 2007
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

/**
 * Condition to be met by an {@code Object}.
 *
 * @param <T> the type of {@code Object} this condition accepts.
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Condition<T> {
  private String description;

  /**
   * Creates a new {@link Condition}.
   */
  public Condition() {
  }

  /**
   * Creates a new {@link Condition}.
   *
   * @param description the description of this condition.
   */
  public Condition(@Nullable String description) {
    as(description);
  }

  /**
   * Sets the description of this condition.
   *
   * @param newDescription the description to set.
   * @return this condition.
   */
  public final @NotNull Condition<T> as(@Nullable String newDescription) {
    description = newDescription;
    return this;
  }

  final @NotNull String addDescriptionTo(@NotNull String s) {
    checkNotNull(s);
    String descriptionToAdd = description();
    if (isNullOrEmpty(descriptionToAdd)) {
      descriptionToAdd = getClass().getSimpleName();
    }
    return String.format("%s:<%s>", s, descriptionToAdd);
  }

  /**
   * @return the description of this condition.
   */
  public final @Nullable String description() {
    return description;
  }

  /**
   * Verifies that the given value satisfies this condition.
   *
   * @param value the value to verify.
   * @return {@code true} if the given value satisfies this condition, {@code false} otherwise.
   */
  public abstract boolean matches(@Nullable T value);
}
