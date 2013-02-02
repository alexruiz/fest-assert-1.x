/*
 * Created on Jan 23, 2008
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

import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Strings.quote;
import static org.fest.util.ToString.toStringOf;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * Assertions for {@code Map}s.
 * </p>
 *
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(Map)}.
 * </p>
 * 
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert extends GroupAssert<MapAssert, Map<?, ?>> {
  private static final String ENTRY = "entry";
  private static final String ENTRIES = "entries";

  /**
   * Creates a new {@link MapAssert}.
   * 
   * @param actual the target to verify.
   */
  protected MapAssert(@Nullable Map<?, ?> actual) {
    super(MapAssert.class, actual);
  }

  /**
   * <p>
   * Verifies that the actual {@code Map} contains the given entries.
   * </p>
   * 
   * <p>
   * Example:
   * <pre>
   * // static import org.fest.assertions.Assertions.*;
   * // static import org.fest.assertions.MapAssert.*;
   * 
   * assertThat(myMap).{@link #includes(org.fest.assertions.MapAssert.Entry...) includes}({@link #entry(Object, Object) entry}(&quot;jedi&quot;, yoda), {@link #entry(Object, Object) entry}(&quot;sith&quot;, anakin));
   * </pre>
   * </p>
   * 
   * @param entries the given entries.
   * @return this assertion error.
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual {@code Map} does not contain any of the given entries.
   * @throws NullPointerException if the given array of entries is {@code null}.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   */
  public @Nonnull MapAssert includes(@Nonnull Entry... entries) {
    isNotNull();
    checkNotNull(entries);
    List<Entry> notFound = newArrayList();
    for (Entry e : entries) {
      if (!containsEntry(checkNotNull(e))) {
        notFound.add(e);
      }
    }
    if (!notFound.isEmpty()) {
      failIfNotFound(entryOrEntries(notFound), notFound);
    }
    return this;
  }

  /**
   * <p>
   * Verifies that the actual {@code Map} does not contain the given entries.
   * </p>
   * 
   * <p>
   * Example:
   * <pre>
   * // static import org.fest.assertions.Assertions.*;
   * // static import org.fest.assertions.MapAssert.*;
   * 
   * assertThat(myMap).{@link #excludes(org.fest.assertions.MapAssert.Entry...) excludes}({@link #entry(Object, Object) entry}(&quot;jedi&quot;, yoda), {@link #entry(Object, Object) entry}(&quot;sith&quot;, anakin));
   * </pre>
   * </p>
   * 
   * @param entries the given entries.
   * @return this assertion error.
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual {@code Map} contains any of the given entries.
   * @throws NullPointerException if the given array of entries is {@code null}.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   */
  public @Nonnull MapAssert excludes(@Nonnull Entry... entries) {
    isNotNull();
    checkNotNull(entries);
    List<Entry> found = newArrayList();
    for (Entry e : entries) {
      if (containsEntry(checkNotNull(e))) {
        found.add(e);
      }
    }
    if (!found.isEmpty()) {
      failIfFound(entryOrEntries(found), found);
    }
    return this;
  }

  private boolean containsEntry(@Nonnull Entry e) {
    if (!actual.containsKey(e.key)) {
      return false;
    }
    return actual.get(e.key).equals(e.value);
  }

  private @Nonnull String entryOrEntries(@Nonnull List<Entry> found) {
    return found.size() == 1 ? ENTRY : ENTRIES;
  }

  /**
   * Creates a new map entry.
   * 
   * @param key the key of the entry.
   * @param value the value of the entry.
   * @return the created entry.
   * @see #includes(org.fest.assertions.MapAssert.Entry...)
   */
  public static @Nonnull Entry entry(@Nullable Object key, @Nullable Object value) {
    return new Entry(key, value);
  }

  /**
   * An entry in a {@code Map}.
   * 
   * @author Yvonne Wang
   */
  public static class Entry {
    final Object key;
    final Object value;

    Entry(@Nullable Object key, @Nullable Object value) {
      this.key = key;
      this.value = value;
    }

    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
      return String.format("%s=%s", quote(key), quote(value));
    }
  }

  private void failIfNotFound(@Nonnull String description, @Nonnull Collection<?> notFound) {
    failIfCustomMessageIsSet();
    String format = "the map:<%s> does not contain the %s:<%s>";
    fail(String.format(format, formattedActual(), description, toStringOf(notFound)));
  }

  private void failIfFound(@Nonnull String description, @Nonnull Collection<?> found) {
    failIfCustomMessageIsSet();
    fail(String.format("the map:<%s> contains the %s:<%s>", formattedActual(), description, toStringOf(found)));
  }

  private @Nonnull String formattedActual() {
    return checkNotNull(toStringOf(actual));
  }

  /**
   * @return the number of elements in the actual {@code Map}.
   */
  @Override
  protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }
}