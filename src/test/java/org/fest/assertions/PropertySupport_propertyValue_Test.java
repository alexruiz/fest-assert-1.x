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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Fail.fail;
import static org.junit.Assert.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.fest.mocks.EasyMockTemplate;
import org.fest.util.IntrospectionError;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link PropertySupport#propertyValue(PropertyDescriptor, String, Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class PropertySupport_propertyValue_Test {

  private JavaBeanDescriptor descriptor;
  private PropertySupport propertySupport;

  @Before public void setUp() {
    descriptor = createMock(JavaBeanDescriptor.class);
    propertySupport = new PropertySupport(descriptor);
  }

  @Test public void should_throw_error_if_getter_cannot_be_invoked() throws IntrospectionException {
    final Name name = new Name("Leia", "Organa");
    final PropertyDescriptor realDescriptor = new PropertyDescriptor("firstName", Name.class);
    final RuntimeException cause = new RuntimeException("Failed on purpose");
    new EasyMockTemplate(descriptor) {
      @Override protected void expectations() throws Throwable {
        expect(descriptor.invokeReadMethod(realDescriptor, name)).andThrow(cause);
      }

      @Override protected void codeToTest() throws Throwable {
        try {
          propertySupport.propertyValue(realDescriptor, "firstName", name);
          fail();
        } catch (IntrospectionError e) {
          assertEquals("Unable to obtain the value in property 'firstName'", e.getMessage());
          assertSame(cause, e.getCause());
        }
      }
    }.run();
  }
}
