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

import static org.fest.assertions.ArrayFactory.charArray;


/**
 * Tests for <code>{@link CharArrayAssert#isIn(char[])}</code> and
 * <code>{@link CharArrayAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class CharArrayAssert_isIn_Test extends GenericAssert_isIn_TestCase<char[]> {

  @Override
  protected GenericAssert<char[]> assertionsFor(char[] actual) {
    return new CharArrayAssert(actual);
  }

  @Override
  protected char[] notNullValue() {
    return charArray('a', 'b');
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), charArray('c', 'd'), charArray('e', 'f'));
  }

}
