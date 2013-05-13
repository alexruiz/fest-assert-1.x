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

import org.fest.util.IntrospectionError;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyDescriptor;

import static org.fest.assertions.Fail.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link PropertySupport#propertyValue(PropertyDescriptor, String, Object)}.
 *
 * @author Alex Ruiz
 */
public class PropertySupport_propertyValue_Test {
  private JavaBeanDescriptor descriptor;
  private PropertySupport propertySupport;

  @Before
  public void setUp() {
    descriptor = mock(JavaBeanDescriptor.class);
    propertySupport = new PropertySupport(descriptor);
  }

  @Test
  public void should_throw_error_if_getter_cannot_be_invoked() throws Exception {
    Name name = new Name("Leia", "Organa");
    PropertyDescriptor realDescriptor = new PropertyDescriptor("firstName", Name.class);
    RuntimeException cause = new RuntimeException("Failed on purpose");
    when(descriptor.invokeReadMethod(realDescriptor, name)).thenThrow(cause);
    try {
      propertySupport.propertyValue(realDescriptor, "firstName", name);
      fail();
    } catch (IntrospectionError e) {
      assertEquals("Unable to obtain the value in property 'firstName'", e.getMessage());
      assertSame(cause, e.getCause());
    }
  }
}
