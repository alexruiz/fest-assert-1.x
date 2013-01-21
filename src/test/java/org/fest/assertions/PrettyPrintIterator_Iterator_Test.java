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

import static org.fest.test.ExpectedException.none;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.ToString.toStringOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fest.assertions.IteratorAssert.PrettyPrintIterator;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link PrettyPrintIterator}.
 * 
 * @author Alex Ruiz
 */
public class PrettyPrintIterator_Iterator_Test {
  @Rule
  public ExpectedException thrown = none();

  private static List<String> list;

  private PrettyPrintIterator iterator;

  @BeforeClass
  public static void setUpOnce() {
    list = newArrayList("Yoda", "Luke");
  }

  @Before
  public void setUp() {
    iterator = new PrettyPrintIterator(list.iterator());
  }

  @Test
  public void should_delegate_to_Iterator() {
    assertTrue(iterator.hasNext());
    assertEquals("Yoda", iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals("Luke", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  public void should_throw_error_on_remove() {
    thrown.expect(UnsupportedOperationException.class);
    iterator.remove();
  }

  @Test
  public void should_show_contents_in_toString() {
    assertEquals(iterator.toString(), toStringOf(list));
  }
}
