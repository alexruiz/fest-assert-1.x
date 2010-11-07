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

import static org.fest.assertions.ArrayFactory.longArray;

/**
 * Tests for <code>{@link LongArrayAssert#isIn(long[])}</code> and
 * <code>{@link LongArrayAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class LongArrayAssert_isIn_isNotIn_Test extends GenericAssert_isIn_isNotIn_TestCase<long[]> {

  @Override
  protected GenericAssert<long[]> assertionsFor(long[] actual) {
    return new LongArrayAssert(actual);
  }

  @Override
  protected long[] notNullValue() {
    return longArray(1, 2, 3);
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), longArray(4, 5, 6), longArray(7, 8));
  }

}
