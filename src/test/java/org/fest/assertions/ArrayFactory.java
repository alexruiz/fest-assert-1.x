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

import org.jetbrains.annotations.NotNull;

/**
 * @author Alex Ruiz
 */
final class ArrayFactory {
  static @NotNull boolean[] booleanArray(@NotNull boolean... values) {
    return values;
  }

  static @NotNull byte[] byteArray(@NotNull int... values) {
    int arraySize = values.length;
    byte[] array = new byte[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = (byte) values[i];
    }
    return array;
  }

  static @NotNull char[] charArray(@NotNull char... values) {
    return values;
  }

  static @NotNull double[] doubleArray(@NotNull double... values) {
    return values;
  }

  static @NotNull float[] floatArray(@NotNull float... values) {
    return values;
  }

  static @NotNull int[] intArray(@NotNull int... values) {
    return values;
  }

  static @NotNull long[] longArray(@NotNull long... values) {
    return values;
  }

  static @NotNull short[] shortArray(@NotNull int... values) {
    int arraySize = values.length;
    short[] array = new short[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = (short) values[i];
    }
    return array;
  }

  static @NotNull Object[] objectArray(@NotNull Object... values) {
    return values;
  }

  private ArrayFactory() {
  }
}
