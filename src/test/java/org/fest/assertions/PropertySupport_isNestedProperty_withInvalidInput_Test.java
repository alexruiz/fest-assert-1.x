/*
 * Created on Jun 2, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link PropertySupport#isNestedProperty(String)}.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
public class PropertySupport_isNestedProperty_withInvalidInput_Test {
  private static PropertySupport propertySupport;

  @BeforeClass
  public static void setUpOnce() {
    propertySupport = PropertySupport.instance();
  }

  @Test
  public void should_return_true_if_property_is_nested() {
    assertTrue(propertySupport.isNestedProperty("person.name"));
    assertTrue(propertySupport.isNestedProperty("adress.street.name"));
  }

  @Test
  public void should_return_false_if_property_name_does_not_contain_separator() {
    assertFalse(propertySupport.isNestedProperty("person"));
  }

  @Test
  public void should_return_false_if_property_name_starts_with_separator() {
    assertFalse(propertySupport.isNestedProperty(".name"));
  }

  @Test
  public void should_return_false_if_property_name_ends_with_separator() {
    assertFalse(propertySupport.isNestedProperty("person."));
  }

  @Test
  public void should_return_false_if_property_name_contains_separator_only() {
    assertFalse(propertySupport.isNestedProperty("."));
  }

  @Test
  public void should_return_false_if_property_name_is_empty() {
    assertFalse(propertySupport.isNestedProperty(""));
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_property_is_null() {
    propertySupport.isNestedProperty(null);
  }
}
