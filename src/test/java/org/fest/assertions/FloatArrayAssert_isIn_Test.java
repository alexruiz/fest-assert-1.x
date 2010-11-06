/*
 * Created on Oct 7, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.floatArray;

/**
 * Tests for <code>{@link FloatArrayAssert#isIn(float[])}</code> and
 * <code>{@link FloatArrayAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class FloatArrayAssert_isIn_Test extends GenericAssert_isIn_TestCase<float[]> {

  @Override
  protected GenericAssert<float[]> assertionsFor(float[] actual) {
    return new FloatArrayAssert(actual);
  }

  @Override
  protected float[] notNullValue() {
    return floatArray(1.0f, 2.0f, 3.0f);
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), floatArray(4.0f, 5.0f, 6.0f), floatArray(7.0f, 8.0f));
  }

}
