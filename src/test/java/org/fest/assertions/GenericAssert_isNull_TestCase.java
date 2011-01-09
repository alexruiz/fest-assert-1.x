/*
 * Created on Sep 4, 2009
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
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.Formatter.format;

import org.junit.*;

/**
 * Base class for testing <code>{@link GenericAssert#isNull()}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isNull_TestCase<S extends GenericAssert<S, A>, A> extends
    GenericAssert_TestCase<S, A> {

  @Rule public ExpectedException thrown = none();

  private A actual;
  private GenericAssert<S, A> assertions;

  @Before public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
  }

  @Test public final void should_pass_if_actual_is_null() {
    assertionsFor(null).isNull();
  }

  @Test public final void should_fail_if_actual_is_not_null() {
    thrown.expectAssertionError(String.format("<%s> should be null", format(actual)));
    assertions.isNull();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_not_null() {
    thrown.expectAssertionError(String.format("[A Test] <%s> should be null", format(actual)));
    assertions.as("A Test")
              .isNull();
  }

  @Test public final void should_fail_with_custom_message_if_actual_is_not_null() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNull();
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_is_not_null() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .isNull();
  }
}