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

import static org.fest.assertions.ArrayFactory.doubleArray;

/**
 * Tests for <code>{@link DoubleArrayAssert#isIn(double[])}</code> and
 * <code>{@link DoubleArrayAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class DoubleArrayAssert_isIn_isNotIn_Test extends GenericAssert_isIn_isNotIn_TestCase<double[]> {

  @Override
  protected GenericAssert<double[]> assertionsFor(double[] actual) {
    return new DoubleArrayAssert(actual);
  }

  @Override
  protected double[] notNullValue() {
    return doubleArray(1.0, 2.0, 3.0);
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), doubleArray(4.0, 5.0, 6.0), doubleArray(7.0, 8.0));
  }

}
