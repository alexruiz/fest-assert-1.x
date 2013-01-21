/*
 * Created on Sep 16, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Fail#failIfNotNull(String, Description, Object)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_failIfNotNull_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_fail_if_value_is_not_null() {
    thrown.expect(AssertionError.class, "[A message] <'Leia'> should be null");
    Fail.failIfNotNull(null, new BasicDescription("A message"), "Leia");
  }

  @Test
  public void should_fail_with_custom_message_if_value_is_not_null() {
    thrown.expect(AssertionError.class, "My custom message");
    Fail.failIfNotNull("My custom message", null, "Leia");
  }

  @Test
  public void should_pass_if_value_is_not_null() {
    Fail.failIfNotNull(null, null, null);
  }
}
