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
 * Tests for <code>{@link org.fest.assertions.ShortAssert#isIn(Short... values)} }</code> and
 * <code>{@link org.fest.assertions.ShortAssert#isIn(java.util.Collection)}</code>.
 *
 * @author Joel Costigliola
 */
public class ShortAssert_isIn_isNotIn_Test extends GenericAssert_isIn_isNotIn_TestCase<Short> {

  @Override
  protected GenericAssert<Short> assertionsFor(Short actual) {
    return new ShortAssert(actual);
  }

  @Override
  protected Short notNullValue() {
    return (short)1;           
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), (short)2, (short)3);
  }

}
