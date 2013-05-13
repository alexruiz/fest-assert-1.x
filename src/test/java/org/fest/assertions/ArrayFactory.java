/*
 * Created on Sep 14, 2009
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

import javax.annotation.Nonnull;

/**
 * @author Alex Ruiz
 */
final class ArrayFactory {
  static @Nonnull boolean[] booleanArray(@Nonnull boolean... values) {
    return values;
  }

  static @Nonnull byte[] byteArray(@Nonnull int... values) {
    int arraySize = values.length;
    byte[] array = new byte[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = (byte) values[i];
    }
    return array;
  }

  static @Nonnull char[] charArray(@Nonnull char... values) {
    return values;
  }

  static @Nonnull double[] doubleArray(@Nonnull double... values) {
    return values;
  }

  static @Nonnull float[] floatArray(@Nonnull float... values) {
    return values;
  }

  static @Nonnull int[] intArray(@Nonnull int... values) {
    return values;
  }

  static @Nonnull long[] longArray(@Nonnull long... values) {
    return values;
  }

  static @Nonnull short[] shortArray(@Nonnull int... values) {
    int arraySize = values.length;
    short[] array = new short[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = (short) values[i];
    }
    return array;
  }

  static @Nonnull Object[] objectArray(@Nonnull Object... values) {
    return values;
  }

  private ArrayFactory() {
  }
}
