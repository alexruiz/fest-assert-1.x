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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Fail#failIfNull(String, Description, Object)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_failIfNull_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_fail_if_value_is_null() {
    thrown.expectAssertionError("[A message] expecting a non-null object, but it was null");
    Fail.failIfNull(null, new BasicDescription("A message"), null);
  }

  @Test public void should_fail_with_custom_message_if_value_is_null() {
    thrown.expectAssertionError("My custom message");
    Fail.failIfNull("My custom message", null, null);
  }

  @Test public void should_pass_if_value_is_not_null() {
    Fail.failIfNull(null, null, "Luke");
  }
}
