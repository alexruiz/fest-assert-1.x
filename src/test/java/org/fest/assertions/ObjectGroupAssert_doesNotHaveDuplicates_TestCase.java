/*
 * Created on Jul 1, 2010
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

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;

import org.junit.*;

/**
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#doesNotHaveDuplicates()}</code>.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert_doesNotHaveDuplicates_TestCase<S extends ObjectGroupAssert<S, A>, A> extends
    ObjectGroupAssert_TestCase<S, A> implements GroupAssert_doesNotHaveDuplicates_TestCase {

  @Rule public ExpectedException thrown = none();

  private static Object[] actualValues;

  @BeforeClass public static void setUpOnce() {
    actualValues = objectArray("Luke", "Yoda", "Luke");
  }

  private A actual;
  private ObjectGroupAssert<S, A> assertions;

  @Before public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
  }

  @Test public final void should_pass_if_actual_does_not_contain_duplicates() {
    assertionsFor(actualFrom("Luke", "Yoda")).doesNotHaveDuplicates();
  }

  @Test public final void should_pass_if_actual_is_empty() {
    Object[] empty = new Object[0];
    assertionsFor(actualFrom(empty)).doesNotHaveDuplicates();
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new CollectionAssert(null).doesNotHaveDuplicates();
  }

  @Test public final void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new CollectionAssert(null).as("A Test")
                              .doesNotHaveDuplicates();
  }

  @Test public final void should_fail_if_actual_has_duplicates() {
    thrown.expectAssertionError("<['Luke', 'Yoda', 'Luke']> contains duplicate(s):<['Luke']>");
    assertions.doesNotHaveDuplicates();
  }

  @Test public final void should_fail_and_display_description_if_actual_has_duplicates() {
    String message = "[A Test] <['Luke', 'Yoda', 'Luke']> contains duplicate(s):<['Luke']>";
    thrown.expectAssertionError(message);
    assertions.as("A Test")
              .doesNotHaveDuplicates();
  }

  @Test public final void should_fail_with_custom_message_if_actual_has_duplicates() {
    thrown.expectAssertionError("My custom message");
    assertions.overridingErrorMessage("My custom message")
              .doesNotHaveDuplicates();
  }

  @Test public final void should_fail_with_custom_message_ignoring_description_if_actual_has_duplicates() {
    thrown.expectAssertionError("My custom message");
    assertions.as("A Test")
              .overridingErrorMessage("My custom message")
              .doesNotHaveDuplicates();
  }
}
