/*
 * Created on Sep 6, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import org.junit.Test;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-378" target="_blank">FEST-378</a>.
 *
 * @author Alex Ruiz
 */
public class FEST378_npeWithNullIterable_Test {

  @Test public void should_not_throw_NPE_if_Iterable_is_null() {
    Iterable<Object> iterable = null;
    Assertions.assertThat(iterable).isNull();
  }
}
