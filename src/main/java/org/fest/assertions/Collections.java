/*
 * Created on Mar 31, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.List;

import static org.fest.util.Lists.emptyList;
import static org.fest.util.Lists.newArrayList;

/**
 * Utility methods for {@code Collection}s.
 *
 * @author Alex Ruiz
 */
public final class Collections {
  private Collections() {
  }

  /**
   * Attempts to find the given objects in the given {@code Collection}, returning the ones that were not found.
   *
   * @param target  the given {@code Collection}.
   * @param objects the elements to find in the given {@code Collection}.
   * @return the elements that were not found in the given {@code Collection}.
   */
  public static @NotNull Collection<Object> notFound(@Nullable Collection<?> target, @NotNull Object... objects) {
    if (target == null) {
      return emptyList();
    }
    List<Object> notFound = newArrayList();
    for (Object o : objects) {
      if (!target.contains(o)) {
        notFound.add(o);
      }
    }
    return notFound;
  }

  /**
   * Attempts to find the given objects in the given {@code Collection}, returning the ones that were found.
   *
   * @param target  the given {@code Collection}.
   * @param objects the elements to find in the given {@code Collection}.
   * @return the elements that were found in the given {@code Collection}.
   */
  public static @NotNull Collection<Object> found(@Nullable Collection<?> target, @NotNull Object... objects) {
    if (target == null) {
      return emptyList();
    }
    List<Object> found = newArrayList();
    for (Object o : objects) {
      if (target.contains(o)) {
        found.add(o);
      }
    }
    return found;
  }
}
