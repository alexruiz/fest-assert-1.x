/*
 * Created on Jan 12, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link ObjectArrayAssert#actualAsList()}.
 * 
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_actualAsList_Test {
  private static ObjectArrayAssert assertions;

  @BeforeClass
  public static void setUpOnce() {
    Object[] actual = { "Luke", "Yoda", "Leia" };
    assertions = new ObjectArrayAssert(actual);
  }

  @Test
  public void should_return_List_with_contents_in_actual() {
    List<Object> list = assertions.actualAsList();
    assertEquals(newArrayList(assertions.actual), list);
  }
}
