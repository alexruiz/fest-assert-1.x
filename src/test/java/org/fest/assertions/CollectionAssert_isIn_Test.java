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

import static org.fest.util.Collections.set;

import java.util.Collection;

/**
 * Tests for <code>{@link CollectionAssert#isIn(Collection)}</code> and
 * <code>{@link CollectionAssert#isIn(java.util.Collection)}</code>.
 * 
 * @author Joel Costigliola
 */
public class CollectionAssert_isIn_Test extends GenericAssert_isIn_TestCase<Collection<?>> {

  @Override
  protected GenericAssert<Collection<?>> assertionsFor(Collection<?> actual) {
    return new CollectionAssert(actual);
  }

  @Override
  protected Collection<?> notNullValue() {
    return set("a1", "a2", "a3");
  }

  @Override
  public void setUpValuesContainingActual() {
    initValuesContainingActual(notNullValue(), set("b1", "b2"), set("c1", "c2"));
  }

}
