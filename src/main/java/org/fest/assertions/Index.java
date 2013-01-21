/*
 * Created on Mar 30, 2009
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

/**
 * An index.
 * 
 * @author Alex Ruiz
 */
public final class Index {
  private final int value;

  /**
   * Creates a new {@link Index}.
   * 
   * @param index the value of the index.
   * @return the created {@code Index}.
   */
  public static Index atIndex(int index) {
    return new Index(index);
  }

  private Index(int value) {
    this.value = value;
  }

  /**
   * @return the value of this index.
   */
  public int value() {
    return value;
  }
}
