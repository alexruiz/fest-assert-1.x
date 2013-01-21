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

import static org.fest.assertions.MapFactory.map;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.fest.assertions.MapAssert.Entry;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link MapAssert#actualGroupSize()}.
 * 
 * @author Alex Ruiz
 */
public class MapAssert_actualGroupSize_Test {
  private static MapAssert assertions;

  @BeforeClass
  public static void setUpOnce() {
    Map<Object, Object> actual = map(new Entry("name", "Yoda"), new Entry("job", "Jedi"));
    assertions = new MapAssert(actual);
  }

  @Test
  public void should_return_size_of_actual() {
    assertEquals(2, assertions.actualGroupSize());
  }
}
