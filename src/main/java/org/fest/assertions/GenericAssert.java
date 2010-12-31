/*
 * Created on May 21, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Fail.*;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.util.Collection;

import org.fest.util.Arrays;

/**
 * Template for assertions.
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GenericAssert<T> extends Assert {

  protected final T actual;

  /**
   * Creates a new <code>{@link GenericAssert}</code>.
   * @param actual the actual target to verify.
   */
  protected GenericAssert(T actual) {
    this.actual = actual;
  }

  /**
   * Asserts that the actual value (specified in the constructor of this class) is {@code null}.
   * @throws AssertionError if the actual value is not {@code null}.
   */
  public final void isNull() {
    failIfNotNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @see #is(Condition)
   */
  protected abstract GenericAssert<T> satisfies(Condition<T> condition);

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   * @see #isNot(Condition)
   */
  protected abstract GenericAssert<T> doesNotSatisfy(Condition<T> condition);

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @since 1.2
   */
  protected abstract GenericAssert<T> is(Condition<T> condition);

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   * @since 1.2
   */
  protected abstract GenericAssert<T> isNot(Condition<T> condition);

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> as(String description);

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> describedAs(String description);

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> as(Description description);

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract GenericAssert<T> describedAs(Description description);

  /**
   * Verifies that the actual value is equal to the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  protected abstract GenericAssert<T> isEqualTo(T expected);

  /**
   * Verifies that the actual value is not equal to the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  protected abstract GenericAssert<T> isNotEqualTo(T other);

  /**
   * Verifies that the actual value is not {@code null}.
   * @return this assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   */
  protected abstract GenericAssert<T> isNotNull();

  /**
   * Verifies that the actual value is the same as the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  protected abstract GenericAssert<T> isSameAs(T expected);

  /**
   * Verifies that the actual value is not the same as the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  protected abstract GenericAssert<T> isNotSameAs(T other);

  /**
   * Verifies that the actual value is in the given values.
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given values.
   * @throws NullPointerException if the given vararg parameter is null.
   */
  protected GenericAssert<T> isIn(T... values) {
    return isIn(list(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  protected GenericAssert<T> isIn(Collection<T> values) {
    if (values == null) { throw new NullPointerException( formattedErrorMessage("expecting values parameter not to be null")); }
    if (assertThatActualIsIn(values)) { return this; }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotIn(customErrorMessage(), actual, values));
  }

  /**
   * Return true if values collection contains actual, false otherwise.</br>
   * Works also when T is an array by comparing values elements (arrays !) with actual.
   * @param values the collection to search actual in
   * @return true if values collection contains actual, false otherwise.
   */
  protected boolean assertThatActualIsIn(Collection<T> values) {
    if (values.isEmpty()) { return false; }
    if (Arrays.isArray(values.iterator().next())) {
      // Using values.contains(actual) doesn't work when T is an array since contains relies on equals and array equals only compare references. 
      // We solve that with Objects.areEquals that compare array elements
      for (T value : values) {
        if (areEqual(actual, value)) { return true; }
      }
    }
    // T is not an array, using contains works
    return values.contains(actual);
  }

  /**
   * Verifies that the actual value is in the given values.
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given values.
   * @throws NullPointerException if the given vararg parameter is null.
   */
  protected GenericAssert<T> isNotIn(T... values) {
    return isNotIn(list(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  protected GenericAssert<T> isNotIn(Collection<T> values) {
    if (values == null) { throw new NullPointerException( formattedErrorMessage("expecting values parameter not to be null")); }
    if (assertThatActualIsNotIn(values)) { return this; }
    failIfCustomMessageIsSet();
    throw failure(unexpectedIn(customErrorMessage(), actual, values));
  }

  /**
   * Return true if values collection does not contain actual, false otherwise.</br>
   * Works also when T is an array by comparing values elements (arrays !) with actual.
   * @param values the collection to search actual in
   * @return true if values collection does not contain actual, false otherwise.
   */
  protected boolean assertThatActualIsNotIn(Collection<T> values) {
    if (values.isEmpty()) { return false; }
    if (Arrays.isArray(values.iterator().next())) {
      // Using values.contains(actual) doesn't work when T is an array since contains relies on equals and array equals only compare references. 
      // We solve that with Objects.areEquals that compare array elements
      for (T value : values) {
        if (areEqual(actual, value)) { return false; }
      }
    }
    // T is not an array, using contains works
    return !values.contains(actual);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   */
  protected final void assertSatisfies(Condition<T> condition) {
    if (matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfConditionNotSatisfied(condition));
  }

  private String errorMessageIfConditionNotSatisfied(Condition<T> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should satisfy condition", actual));
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   */
  protected final void assertIs(Condition<T> condition) {
    if (matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfIsNot(condition));
  }

  private String errorMessageIfIsNot(Condition<T> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should be", actual));
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   */
  protected final void assertDoesNotSatisfy(Condition<T> condition) {
    if (!matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfConditionSatisfied(condition));
  }

  private String errorMessageIfConditionSatisfied(Condition<T> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not satisfy condition", actual));
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the condition to check.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   */
  protected final void assertIsNot(Condition<T> condition) {
    if (!matches(condition)) return;
    failIfCustomMessageIsSet();
    fail(errorMessageIfIs(condition));
  }

  private boolean matches(Condition<T> condition) {
    validateIsNotNull(condition);
    return condition.matches(actual);
  }

  private void validateIsNotNull(Condition<T> condition) {
    if (condition == null) throw new NullPointerException("Condition to check should not be null");
  }

  private String errorMessageIfIs(Condition<T> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not be", actual));
  }

  /**
   * Verifies that the actual value is equal to the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  protected final void assertEqualTo(T expected) {
    failIfNotEqual(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   * @param other the value to compare the actual value to.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  protected final void assertNotEqualTo(T other) {
    failIfEqual(customErrorMessage(), rawDescription(), actual, other);
  }

  /**
   * Verifies that the actual value is not {@code null}.
   * @throws AssertionError if the actual value is {@code null}.
   */
  protected final void assertNotNull() {
    failIfActualIsNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value is the same as the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  protected final void assertSameAs(T expected) {
    failIfNotSame(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Verifies that the actual value is not the same as the given one.
   * @param expected the value to compare the actual value to.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  protected final void assertNotSameAs(T expected) {
    failIfSame(customErrorMessage(), rawDescription(), actual, expected);
  }

  /**
   * Replaces the default message displayed in case of a failure with the given one.
   * <p>
   * For example, the following assertion:
   * <pre>
   * assertThat("Hello").isEqualTo("Bye");
   * </pre>
   * will fail with the default message "<em>expected:<'[Bye]'> but was:<'[Hello]'></em>."
   * </p>
   * <p>
   * We can replace this message with our own:
   * <pre>
   * assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
   * </pre>
   * in this case, the assertion will fail showing the message "<em>'Hello' should be equal to 'Bye'</em>".
   * </p>
   * @param message the given error message, which will replace the default one.
   * @return this assertion.
   * @since 1.2
   */
  protected abstract GenericAssert<T> overridingErrorMessage(String message);
}
