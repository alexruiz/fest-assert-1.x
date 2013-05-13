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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

import static org.fest.assertions.Formatting.format;
import static org.fest.util.Objects.namesOf;
import static org.fest.util.Preconditions.checkNotNull;

/**
 * Assertions for {@code Object}s.
 * <p/>
 * To create a new instance of this class use the method {@link Assertions#assertThat(Object)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert extends GenericAssert<ObjectAssert, Object> {
  /**
   * Creates a new {@link ObjectAssert}.
   *
   * @param actual the target to verify.
   */
  protected ObjectAssert(@Nullable Object actual) {
    super(ObjectAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Object} is an instance of the given type.
   *
   * @param type the type to check the actual {@code Object} against.
   * @return this assertion object.
   * @throws AssertionError       if the actual {@code Object} is {@code null}.
   * @throws AssertionError       if the actual {@code Object} is not an instance of the given type.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public @Nonnull ObjectAssert isInstanceOf(@Nonnull Class<?> type) {
    isNotNull();
    checkNotNull(type);
    Class<?> current = actual.getClass();
    if (type.isAssignableFrom(current)) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("expected instance of:<%s> but was instance of:<%s>", type, current));
  }

  /**
   * Verifies that the actual {@code Object} is an instance of any of the given types.
   *
   * @param types the types to check the actual {@code Object} against.
   * @return this assertion object.
   * @throws AssertionError       if the actual {@code Object} is {@code null}.
   * @throws AssertionError       if the actual {@code Object} is not an instance of any of the given types.
   * @throws NullPointerException if the given array of types is {@code null}.
   * @throws NullPointerException if the given array of types contains {@code null}s.
   */
  public @Nonnull ObjectAssert isInstanceOfAny(@Nonnull Class<?>... types) {
    isNotNull();
    checkNotNull(types);
    if (!foundInstanceOfAny(types)) {
      String format = "expected instance of any:<%s> but was instance of:<%s>";
      fail(String.format(format, typeNames(types), actual.getClass().getName()));
    }
    return this;
  }

  private boolean foundInstanceOfAny(@Nonnull Class<?>... types) {
    Class<?> current = actual.getClass();
    for (Class<?> type : types) {
      checkNotNull(type);
      if (type.isAssignableFrom(current)) {
        return true;
      }
    }
    return false;
  }

  private @Nonnull String typeNames(@Nonnull Class<?>... types) {
    return Arrays.toString(namesOf(types));
  }
}
