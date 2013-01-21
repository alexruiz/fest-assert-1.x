/*
 * Created on Apr 23, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FailureMessages.notEqual;
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Base class for testing {@link GenericAssert#isEqualTo(Object)}.
 * 
 * @param <S> used to simulate "self types." For more information please read &quot;<a href="http://goo.gl/fjgOM"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> The type supported by the implementation of the {@code GenericAssert} to test.
 * 
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isEqualTo_TestCase<S extends GenericAssert<S, A>, A> extends
GenericAssert_TestCase<S, A> implements Assert_isEqualTo_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private A actual;
  private GenericAssert<S, A> assertions;
  private A notEqualValue;

  @Before
  public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
    notEqualValue = notEqualValue();
  }

  protected abstract A notEqualValue();

  @Override
  @Test
  public final void should_pass_if_actual_and_expected_are_equal() {
    assertions.isEqualTo(actual);
  }

  @Test
  public final void should_pass_if_both_actual_and_expected_are_null() {
    assertionsFor(null).isEqualTo(null);
  }

  @Override
  @Test
  public final void should_fail_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, notEqual(actual, notEqualValue));
    assertions.isEqualTo(notEqualValue);
  }

  @Override
  @Test
  public final void should_fail_and_display_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, notEqual("A Test", actual, notEqualValue));
    assertions.as("A Test").isEqualTo(notEqualValue);
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.overridingErrorMessage("My custom message").isEqualTo(notEqualValue);
  }

  @Override
  @Test
  public final void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_are_not_equal() {
    thrown.expect(AssertionError.class, "My custom message");
    assertions.as("A Test").overridingErrorMessage("My custom message").isEqualTo(notEqualValue);
  }
}