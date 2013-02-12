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

import static org.fest.util.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link PropertySupport#removeFirstPropertyIfNested(String)}.
 * 
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class PropertySupport_removeFirstPropertyIfNested_withNestedProperties_Test {
  @Parameters
  public static Collection<Object[]> propertyNames() {
    return newArrayList(new Object[][] { { "person.name", "name" }, { "address.street.name", "street.name" } });
  }

  private final String propertyName;
  private final String withoutFirstProperty;

  public PropertySupport_removeFirstPropertyIfNested_withNestedProperties_Test(String propertyName,
      String withoutFirstProperty) {
    this.propertyName = propertyName;
    this.withoutFirstProperty = withoutFirstProperty;
  }

  @Test
  public void should_remove_first_property() {
    assertEquals(withoutFirstProperty, PropertySupport.instance().removeFirstPropertyIfNested(propertyName));
  }
}
