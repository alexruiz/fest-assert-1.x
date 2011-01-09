/*
 * Created on Jun 2, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.MapAssert.entry;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.*;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-329" target="_blank">FEST-329</a>.
 *
 * @author Yvonne Wang
 */
public class FEST329_containsEntryFails_Test {

  @Rule public ExpectedException thrown = none();

  private Map<Integer, String> map;
  private MapAssert mapAssert;

  @Before public void setUp() {
    map = new LinkedHashMap<Integer, String>();
    map.put(1, "foo");
    map.put(2, "bar");
    mapAssert = new MapAssert(map);
  }

  @Test public void should_return_false_if_key_and_value_exist_in_map_but_not_as_entry() {
    thrown.expectAssertionError("the map:<{1='foo', 2='bar'}> does not contain the entry:<[1='bar']>");
    mapAssert.includes(entry(1, "bar"));
  }
}
