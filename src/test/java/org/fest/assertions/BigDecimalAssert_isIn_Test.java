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

import java.math.BigDecimal;

/**
 * Tests for <code>{@link BigDecimalAssert#isIn(BigDecimal... values)}</code> and
 * <code>{@link BigDecimalAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class BigDecimalAssert_isIn_Test extends GenericAssert_isIn_TestCase<BigDecimal> {

  @Override
  protected GenericAssert<BigDecimal> assertionsFor(BigDecimal actual) {
    return new BigDecimalAssert(actual);
  }

  @Override
  protected BigDecimal notNullValue() {
    return BigDecimals.eight();
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), BigDecimals.seven(), BigDecimals.negativeEight());
  }

}
