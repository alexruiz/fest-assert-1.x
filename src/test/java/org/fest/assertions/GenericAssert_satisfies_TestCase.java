/*
 * Created on Apr 16, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.NotNull.notNull;

import org.junit.*;

/**
 * Base class for testing <code>{@link GenericAssert#satisfies(Condition)}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_satisfies_TestCase<S extends GenericAssert<S, A>, A> extends
    GenericAssert_TestCase<S, A> implements GenericAssert_satisfies_orAlias_TestCase {

  @Rule public ExpectedException thrown = none();

  private GenericAssert<S, A> assertions;
  private Condition<A> notNull;

  @Before public final void setUp() {
    assertions = assertionsFor(null);
    notNull = notNull();
  }

  @Test public final void should_pass_if_condition_is_satisfied() {
    assertionsFor(notNullValue()).satisfies(notNull);
  }

  @Test public final void should_throw_error_if_condition_is_null() {
    thrown.expectAssertionError(actualIsNull());
    assertions.satisfies(null);
  }

  @Test public final void should_fail_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("actual value:<null> should satisfy condition:<NotNull>");
    assertions.satisfies(notNull);
  }

  @Test public final void should_fail_and_display_description_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("[A Test] actual value:<null> should satisfy condition:<NotNull>");
    assertions.as("A Test")
              .satisfies(notNull);
  }

  @Test public final void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("actual value:<null> should satisfy condition:<non-null>");
    assertions.satisfies(notNull.as("non-null"));
  }

  @Test public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("[A Test] actual value:<null> should satisfy condition:<non-null>");
    assertions.as("A Test")
              .satisfies(notNull.as("non-null"));
  }

  @Test public final void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .satisfies(notNull);
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .satisfies(notNull.as("non-null"));
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_condition_is_not_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .satisfies(notNull);
  }
}