/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Sets.newLinkedHashSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.util.IntrospectionError;

/**
 * <p>
 * Assertions for {@link Collection}s.
 * </p>
 * 
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(Collection)}.
 * </p>
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert extends ObjectGroupAssert<CollectionAssert, Collection<?>> {
  /**
   * Creates a new {@link CollectionAssert}.
   * 
   * @param actual the target to verify.
   */
  protected CollectionAssert(Collection<?> actual) {
    super(CollectionAssert.class, actual);
  }

  /**
   * Returns the number of elements in the actual collection.
   * 
   * @return the number of elements in the actual collection.
   * @throws AssertionError if the actual collection is {@code null}.
   */
  @Override
  protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }

  /**
   * <p>
   * Creates a new instance of {@link CollectionAssert} whose target collection contains the values of the given
   * property name from the elements of this {@code CollectionAssert}'s collection. Property access works with both
   * simple properties like {@code Person.age} and nested properties {@code Person.father.age}.
   * </p>
   * 
   * <p>
   * For example, let's say we have a collection of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty(&quot;age&quot;).containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </pre>
   * </p>
   * 
   * @param propertyName the name of the property to extract values from the actual collection to build a new
   *          {@code CollectionAssert}.
   * @return a new {@code CollectionAssert} containing the values of the given property name from the elements of this
   *         {@code CollectionAssert}'s collection.
   * @throws AssertionError if the actual collection is {@code null}.
   * @throws NullPointerException if the given property name is {@code null}.
   * @throws IntrospectionError if an element in the given collection does not have a matching property.
   * @since 1.3
   */
  @Override
  public @Nonnull CollectionAssert onProperty(@Nonnull String propertyName) {
    isNotNull();
    checkNotNull(propertyName);
    if (actual.isEmpty()) {
      return new CollectionAssert(emptyList());
    }
    return new CollectionAssert(PropertySupport.instance().propertyValues(propertyName, actual));
  }

  /** {@inheritDoc} */
  @Override
  protected @Nullable Set<Object> actualAsSet() {
    return actual == null ? null : newLinkedHashSet(actual.toArray());
  }

  /** {@inheritDoc} */
  @Override
  protected @Nullable List<Object> actualAsList() {
    return actual == null ? null : newArrayList(actual.toArray());
  }
}
