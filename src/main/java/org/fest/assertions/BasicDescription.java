/*
 * Created on Oct 29, 2008
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

import static org.fest.util.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * Default implementation of {@link Description}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicDescription implements Description {
  private final String description;

  /**
   * Creates a new {@link BasicDescription}.
   * 
   * @param description the value of this description.
   * @throws NullPointerException if the given description is {@code null}.
   */
  public BasicDescription(@Nonnull String description) {
    this.description = checkNotNull(description);
  }

  /** @see org.fest.assertions.Description#value() */
  @Override
  public @Nonnull String value() {
    return checkNotNull(description);
  }
}
