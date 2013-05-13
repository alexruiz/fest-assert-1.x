/*
 * Created on Feb 16, 2008
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.ArrayInspection.*;

/**
 * Assertions for arrays.
 *
 * @param <S> used to simulate "self types." For more information please read &quot;<a href="http://goo.gl/fjgOM"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 * @author Alex Ruiz
 */
public abstract class ArrayAssert<S, A> extends ItemGroupAssert<S, A> {
  /**
   * Creates a new {@link ArrayAssert}.
   *
   * @param selfType the "self type."
   * @param actual   the target to verify.
   */
  protected ArrayAssert(@Nonnull Class<S> selfType, @Nullable A actual) {
    super(selfType, actual);
  }

  /**
   * @return the size of the actual array.
   * @throws AssertionError if the actual array is {@code null}.
   */
  @Override
  protected final int actualGroupSize() {
    isNotNull();
    return sizeOf(actual);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected @Nullable Set<Object> actualAsSet() {
    isNotNull();
    return copyToSet(actual);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected @Nullable List<Object> actualAsList() {
    isNotNull();
    return copyToList(actual);
  }
}
