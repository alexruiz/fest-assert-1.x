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

/**
 * Tests for <code>{@link DoubleAssert#isIn(Double... values)}</code> and
 * <code>{@link DoubleAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class DoubleAssert_isIn_Test extends GenericAssert_isIn_TestCase<Double> {

  @Override
  protected GenericAssert<Double> assertionsFor(Double actual) {
    return new DoubleAssert(actual);
  }

  @Override
  protected Double notNullValue() {
    return 1.0;
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), 2.0, 3.0);
  }

}
