/*
 * Created on Sep 8, 2009
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
import static org.fest.assertions.FailureMessages.actualIsNull;

import org.junit.Rule;
import org.junit.Test;

/**
 * Test case for implementations of <code>{@link GroupAssert#isNotEmpty()}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code GroupAssert} to test.
 *
 * @author Alex Ruiz
 */
public abstract class GroupAssert_isNotEmpty_TestCase<S extends GroupAssert<S, A>, A> {

  @Rule public ExpectedException thrown = none();

  @Test public final void should_pass_if_actual_is_not_empty() {
    assertionsFor(notEmptyGroup()).isNotEmpty();
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    assertionsFor(null).isNotEmpty();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    assertionsFor(null).as("A Test")
                       .isNotEmpty();
  }

  @Test public final void should_fail_if_actual_is_empty() {
    thrown.expectAssertionError("expecting non-empty, but it was empty");
    assertionsFor(emptyGroup()).isNotEmpty();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_empty() {
    thrown.expectAssertionError("[A Test] expecting non-empty, but it was empty");
    assertionsFor(emptyGroup()).as("A Test")
                               .isNotEmpty();
  }

  @Test public final void should_fail_with_custom_message_if_actual_is_empty() {
    thrown.expectAssertionError("My custom message");
    assertionsFor(emptyGroup()).overridingErrorMessage("My custom message")
                               .isNotEmpty();
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_is_empty() {
    thrown.expectAssertionError("My custom message");
    assertionsFor(emptyGroup()).as("A Test")
                               .overridingErrorMessage("My custom message")
                               .isNotEmpty();
  }

  protected abstract GroupAssert<S, A> assertionsFor(A actual);
  protected abstract A emptyGroup();
  protected abstract A notEmptyGroup();
}