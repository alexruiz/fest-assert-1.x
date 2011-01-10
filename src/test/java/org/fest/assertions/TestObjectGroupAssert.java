/*
 * Created on Jan 9, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions;

import java.util.*;

/**
 * @author Alex Ruiz
 */
public class TestObjectGroupAssert extends ObjectGroupAssert<TestObjectGroupAssert, List<?>> {

  public TestObjectGroupAssert(List<?> actual) {
    super(TestObjectGroupAssert.class, actual);
  }

  @Override protected int actualGroupSize() {
    return actual.size();
  }

  @Override protected TestObjectGroupAssert onProperty(String propertyName) {
    // TODO Auto-generated method stub
    return null;
  }

  /** {@inheritDoc} */
  @Override protected Set<Object> actualAsSet() {
    return new LinkedHashSet<Object>(actual);
  }

  /** {@inheritDoc} */
  @Override protected List<Object> actualAsList() {
    return new ArrayList<Object>(actual);
  }
}
