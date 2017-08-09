/*
 * Created on Oct 2, 2009
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
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Sets.newLinkedHashSet;

/**
 * Utility methods for arrays.
 *
 * @author Alex Ruiz
 * @since 1.2
 */
public final class ArrayInspection {
  private ArrayInspection() {
  }

  /**
   * Copies the contents of the given array into an array of objects.
   *
   * @param array the array to copy.
   * @return an array of objects containing the contents of the array.
   * @throws NullPointerException     if the given object is {@code null}.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static @NotNull Object[] copy(@NotNull Object array) {
    return copyToList(checkNotNull(array)).toArray();
  }

  /**
   * Copies the contents of the given array into a list.
   *
   * @param array the array to copy.
   * @return a list containing the contents of the array.
   * @throws IllegalArgumentException if the given object is not an array.
   * @since 1.3.
   */
  public static @NotNull List<Object> copyToList(@NotNull Object array) {
    return copy(checkNotNull(array), newArrayList());
  }

  /**
   * Copies the contents of the given array into a list.
   *
   * @param array the array to copy.
   * @return a list containing the contents of the array.
   * @throws IllegalArgumentException if the given object is not an array.
   * @since 1.3.
   */
  public static @NotNull Set<Object> copyToSet(@NotNull Object array) {
    return copy(checkNotNull(array), newLinkedHashSet());
  }

  private static @NotNull <T extends Collection<Object>> T copy(@NotNull Object array, @NotNull T destination) {
    int length = sizeOf(array);
    for (int i = 0; i < length; i++) {
      destination.add(get(array, i));
    }
    return destination;
  }

  /**
   * Returns the size of the given array.
   *
   * @param array the array.
   * @return the size of the given array.
   * @throws NullPointerException     if the given array is {@code null}.
   * @throws IllegalArgumentException if the given object is not an array.
   */
  public static int sizeOf(@NotNull Object array) {
    checkNotNull(array);
    validateIsArray(array);
    return getLength(array);
  }

  private static void validateIsArray(@NotNull Object array) {
    if (!array.getClass().isArray()) {
      throw new IllegalArgumentException("The given object is not an array");
    }
  }
}
