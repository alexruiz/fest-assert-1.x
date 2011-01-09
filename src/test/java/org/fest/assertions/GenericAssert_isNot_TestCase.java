/*
 * Created on Apr 19, 2010
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
import static org.fest.assertions.FailureMessages.conditionIsNull;
import static org.fest.assertions.Formatter.format;
import static org.fest.assertions.NotNull.notNull;
import static org.fest.util.Strings.concat;

import org.junit.*;

/**
 * Base class for testing <code>{@link GenericAssert#isNot(Condition)}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isNot_TestCase<S extends GenericAssert<S, A>, A> extends
    GenericAssert_TestCase<S, A> implements GenericAssert_doesNotSatisfy_orAlias_TestCase {

  @Rule public ExpectedException thrown = none();

  private A actual;
  private GenericAssert<S, A> assertions;
  private Condition<A> notNull;

  @Before public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
    notNull = notNull();
  }

  @Test public final void should_pass_if_condition_is_not_satisfied() {
    assertionsFor(null).isNot(notNull);
  }

  @Test public final void should_throw_error_if_condition_is_null() {
    thrown.expectNullPointerException(conditionIsNull());
    assertions.isNot(null);
  }

  @Test public final void should_fail_if_condition_is_satisfied() {
    thrown.expectAssertionError(String.format("actual value:<%s> should not be:<NotNull>", format(actual)));
    assertions.isNot(notNull);
  }

  @Test public final void should_fail_and_display_description_if_condition_is_satisfied() {
    String msg = String.format("[A Test] actual value:<%s> should not be:<NotNull>", format(actual));
    thrown.expectAssertionError(msg);
    assertions.as("A Test")
              .isNot(notNull);
  }

  @Test public final void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    thrown.expectAssertionError(String.format("actual value:<%s> should not be:<Not Null>", format(actual)));
    assertions.isNot(notNull.as("Not Null"));
  }

  @Test public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg = concat("[A Test] actual value:<> should not be:<Not Null>", format(actual));
    thrown.expectAssertionError(msg);
    assertions.as("A Test")
              .isNot(notNull.as("Not Null"));
  }

  @Test public void should_fail_with_custom_message_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNot(notNull);
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message").isNot(notNull);
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .isNot(notNull.as("Not Null"));
  }
}