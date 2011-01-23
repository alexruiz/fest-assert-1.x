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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Collections.list;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrayAssert#actualAsSet()}</code>
 *
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_actualAsSet_Test {

  private static ObjectArrayAssert assertions;

  @BeforeClass public static void setUpOnce() {
    Object[] actual = { "Luke", "Yoda", "Leia" };
    assertions = new ObjectArrayAssert(actual);
  }

  @Test public void should_return_Set_with_contents_in_actual() {
    Set<Object> set = assertions.actualAsSet();
    assertEquals(list(assertions.actual), new ArrayList<Object>(set));
  }
}
