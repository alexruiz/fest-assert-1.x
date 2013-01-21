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
 * Tests for {@link Fail#failIfSame(String, Description, Object, Object)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_failIfSame_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_fail_if_values_are_same() {
    Object o = new Object();
    thrown.expect(AssertionError.class, String.format("[A message] given objects are same:<%s>", o));
    Fail.failIfSame(null, new BasicDescription("A message"), o, o);
  }

  @Test
  public void should_fail_with_custom_error_if_values_are_same() {
    Object o = new Object();
    thrown.expect(AssertionError.class, "My custom message");
    Fail.failIfSame("My custom message", null, o, o);
  }

  @Test
  public void should_pass_if_values_are_not_same() {
    Fail.failIfSame(null, null, "Luke", "Anakin");
  }
}
