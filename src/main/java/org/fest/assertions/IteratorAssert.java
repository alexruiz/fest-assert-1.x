/*
 * Created on Jan 11, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Sets.newLinkedHashSet;
import static org.fest.util.ToString.toStringOf;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.util.VisibleForTesting;

/**
 * Assertions for {@link Iterator}s.
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(Iterator)}.
 * </p>
 * 
 * @author Alex Ruiz
 * 
 * @since 1.4
 */
public class IteratorAssert extends ObjectGroupAssert<IteratorAssert, Iterator<?>> {
  /**
   * Creates a new {@link IteratorAssert}.
   * 
   * @param actual the target to verify.
   */
  protected IteratorAssert(@Nullable Iterator<?> actual) {
    super(IteratorAssert.class, wrap(actual));
  }

  private static @Nullable Iterator<?> wrap(@Nullable Iterator<?> actual) {
    if (actual == null) {
      return null;
    }
    return new PrettyPrintIterator(actual);
  }

  /** {@inheritDoc} */
  @Override
  protected @Nonnull IteratorAssert onProperty(@Nonnull String propertyName) {
    checkNotNull(propertyName);
    isNotNull();
    List<Object> subset = PropertySupport.instance().propertyValues(propertyName, newArrayList(contentOfActual()));
    return new IteratorAssert(subset.iterator());
  }

  /** {@inheritDoc} */
  @Override
  protected @Nullable Set<Object> actualAsSet() {
    if (actual == null) {
      return null;
    }
    return newLinkedHashSet(contentOfActual());
  }

  /** {@inheritDoc} */
  @Override
  protected @Nullable List<Object> actualAsList() {
    if (actual == null) {
      return null;
    }
    return newArrayList(contentOfActual());
  }

  /** {@inheritDoc} */
  @Override
  protected int actualGroupSize() {
    isNotNull();
    return contentOfActual().length;
  }

  private @Nonnull Object[] contentOfActual() {
    PrettyPrintIterator wrapped = (PrettyPrintIterator) actual;
    return wrapped.contents().toArray();
  }

  @VisibleForTesting
  static class PrettyPrintIterator implements Iterator<Object> {
    private final Iterator<?> wrapped;

    boolean wrappedWasConsumed;
    List<Object> wrappedContents;
    Iterator<Object> iterator;

    PrettyPrintIterator(@Nonnull Iterator<?> wrapped) {
      this.wrapped = wrapped;
    }

    @Nonnull List<Object> contents() {
      consumeIterator();
      return wrappedContents;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasNext() {
      consumeIterator();
      return iterator.hasNext();
    }

    /** {@inheritDoc} */
    @Override
    public @Nullable Object next() {
      consumeIterator();
      return iterator.next();
    }

    private synchronized void consumeIterator() {
      if (wrappedWasConsumed) {
        return;
      }
      wrappedContents = newArrayList(wrapped);
      wrappedWasConsumed = true;
      iterator = wrappedContents.iterator();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
      consumeIterator();
      return toStringOf(wrappedContents);
    }
  }
}