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
 * Tests for <code>{@link LongAssert#isIn(Long... values)}</code> and
 * <code>{@link LongAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class LongAssert_isIn_isNotIn_Test extends GenericAssert_isIn_isNotIn_TestCase<Long> {

  @Override
  protected GenericAssert<Long> assertionsFor(Long actual) {
    return new LongAssert(actual);
  }

  @Override
  protected Long notNullValue() {
    return 1L;
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), 2L, 3L);
  }

}
