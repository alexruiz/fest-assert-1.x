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
 * Tests for <code>{@link FloatAssert#isIn(Float... values)}</code> and
 * <code>{@link FloatAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class FloatAssert_isIn_isNotIn_Test extends GenericAssert_isIn_isNotIn_TestCase<Float> {

  @Override
  protected GenericAssert<Float> assertionsFor(Float actual) {
    return new FloatAssert(actual);
  }

  @Override
  protected Float notNullValue() {
    return 1.0f;
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), 2.0f, 3.0f);
  }

}
