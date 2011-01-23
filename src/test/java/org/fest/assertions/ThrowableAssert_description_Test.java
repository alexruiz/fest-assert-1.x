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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for:
 * <ul>
 * <li><code>{@link ThrowableAssert#as(Description)}</code></li>
 * <li><code>{@link ThrowableAssert#as(String)}</code></li>
 * <li><code>{@link ThrowableAssert#describedAs(Description)}</code></li>
 * <li><code>{@link ThrowableAssert#describedAs(String)}</code></li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class ThrowableAssert_description_Test {

  private ThrowableAssert assertion;

  @Before public final void setUp() {
    assertion = new ThrowableAssert(new Exception());
    assertNull(assertion.description());
  }

  @Test public void should_set_description_as_text() {
    assertion.as("A Test");
    assertEquals("A Test", assertion.description());
    assertEquals("A Test", assertion.objectAssert.description());
  }

  @Test public void should_set_description_as_text_safely_for_Groovy() {
    assertion.describedAs("A Test");
    assertEquals("A Test", assertion.description());
    assertEquals("A Test", assertion.objectAssert.description());
  }

  @Test public void should_set_description() {
    assertion.as(new BasicDescription("A Test"));
    assertEquals("A Test", assertion.description());
    assertEquals("A Test", assertion.objectAssert.description());
  }

  @Test public void should_set_description_safely_for_Groovy() {
    assertion.describedAs(new BasicDescription("A Test"));
    assertEquals("A Test", assertion.description());
    assertEquals("A Test", assertion.objectAssert.description());
  }
}
