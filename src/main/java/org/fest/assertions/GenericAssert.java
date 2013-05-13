/*
 * Created on May 21, 2007
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

import static org.fest.assertions.ErrorMessages.unexpectedIn;
import static org.fest.assertions.ErrorMessages.unexpectedNotIn;
import static org.fest.assertions.Fail.*;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Preconditions.checkNotNull;

/**
 * Template for assertions.
 *
 * @param <S> used to simulate "self types." For more information please read &quot;<a href="http://goo.gl/fjgOM"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API
 *            implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GenericAssert<S, A> extends Assert {
  protected final A actual;
  protected final S myself;

  /**
   * Creates a new {@link GenericAssert}.
   *
   * @param selfType the "self type."
   * @param actual   the actual value to verify.
   */
  protected GenericAssert(@Nonnull Class<S> selfType, @Nullable A actual) {
    this.actual = actual;
    myself = selfType.cast(this);
  }

  /**
   * Asserts that the actual value (specified in the constructor of this class) is {@code null}.
   *
   * @throws AssertionError if the actual value is not {@code null}.
   */
  public final void isNull() {
    failIfNotNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value does not satisfy the given condition.
   * @see #is(Condition)
   */
  public final @Nonnull S satisfies(@Nonnull Condition<A> condition) {
    if (matches(condition)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfConditionNotSatisfied(condition));
  }

  private @Nonnull String errorMessageIfConditionNotSatisfied(@Nonnull Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should satisfy condition", actual));
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value satisfies the given condition.
   * @see #isNot(Condition)
   */
  public final @Nonnull S doesNotSatisfy(@Nonnull Condition<A> condition) {
    if (!matches(condition)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfConditionSatisfied(condition));
  }

  private @Nonnull String errorMessageIfConditionSatisfied(@Nonnull Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not satisfy condition", actual));
  }

  /**
   * Alias for {@link #satisfies(Condition)}.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value does not satisfy the given condition.
   * @since 1.2
   */
  public final @Nonnull S is(@Nonnull Condition<A> condition) {
    if (matches(condition)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfIsNot(condition));
  }

  private @Nonnull String errorMessageIfIsNot(@Nonnull Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should be", actual));
  }

  /**
   * Alias for {@link #doesNotSatisfy(Condition)}.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError       if the actual value satisfies the given condition.
   * @since 1.2
   */
  public final @Nonnull S isNot(@Nonnull Condition<A> condition) {
    if (!matches(condition)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfIs(condition));
  }

  private boolean matches(@Nonnull Condition<A> condition) {
    checkNotNull(condition);
    return condition.matches(actual);
  }

  private @Nonnull String errorMessageIfIs(@Nonnull Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not be", actual));
  }

  /**
   * Sets the description of the actual value, to be used in as message of any {@code AssertionError} thrown when an
   * assertion fails. This method should be called before any assertion method, otherwise any assertion failure will not
   * show the provided description.
   * <p/>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public @Nonnull S as(@Nullable String description) {
    description(description);
    return myself();
  }

  /**
   * Alias for {@link #as(String)}, since "as" is a keyword in <a href="http://groovy.codehaus.org/"
   * target="_blank">Groovy</a>. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p/>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public @Nonnull S describedAs(@Nullable String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any {@code AssertionError} thrown when an
   * assertion fails. This method should be called before any assertion method, otherwise any assertion failure will not
   * show the provided description.
   * <p/>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public @Nonnull S as(@Nullable Description description) {
    description(description);
    return myself();
  }

  /**
   * Alias for {@link #as(Description)}, since "as" is a keyword in <a href="http://groovy.codehaus.org/"
   * target="_blank">Groovy</a>. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p/>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   *
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public @Nonnull S describedAs(@Nullable Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual value is equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  public @Nonnull S isEqualTo(@Nullable A expected) {
    failIfNotEqual(customErrorMessage(), rawDescription(), actual, expected);
    return myself();
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  public @Nonnull S isNotEqualTo(@Nullable A other) {
    failIfEqual(customErrorMessage(), rawDescription(), actual, other);
    return myself();
  }

  /**
   * Verifies that the actual value is not {@code null}.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   */
  public final @Nonnull S isNotNull() {
    failIfActualIsNull(customErrorMessage(), rawDescription(), actual);
    return myself();
  }

  /**
   * Verifies that the actual value is the same as the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  public final @Nonnull S isSameAs(@Nullable A expected) {
    failIfNotSame(customErrorMessage(), rawDescription(), actual, expected);
    return myself();
  }

  /**
   * Verifies that the actual value is not the same as the given one.
   *
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  public final @Nonnull S isNotSameAs(@Nullable A other) {
    failIfSame(customErrorMessage(), rawDescription(), actual, other);
    return myself();
  }

  /**
   * Verifies that the actual value is in the given values.
   *
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError       if the actual value is not in the given values.
   * @throws NullPointerException if the given parameter is null.
   */
  public final @Nonnull S isIn(@Nonnull Object... values) {
    return isIn(newArrayList(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   *
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError       if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  public final @Nonnull S isIn(@Nonnull Collection<?> values) {
    checkNotNull(values);
    if (isActualIn(values)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotIn(actual, values));
  }

  /**
   * Verifies that the actual value is in the given values.
   *
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError       if the actual value is not in the given values.
   * @throws NullPointerException if the given parameter is null.
   */
  public final @Nonnull S isNotIn(@Nonnull Object... values) {
    return isNotIn(newArrayList(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   *
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError       if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  public final @Nonnull S isNotIn(@Nonnull Collection<?> values) {
    checkNotNull(values);
    if (!isActualIn(values)) {
      return myself();
    }
    failIfCustomMessageIsSet();
    throw failure(unexpectedIn(actual, values));
  }

  private boolean isActualIn(@Nonnull Collection<?> values) {
    if (values.isEmpty()) {
      return false;
    }
    for (Object value : values) {
      if (areEqual(actual, value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Replaces the default message displayed in case of a failure with the given one.
   * <p/>
   * For example, the following assertion:
   * <pre>
   * assertThat("Hello").isEqualTo("Bye");
   * </pre>
   * will fail with the default message "<em>expected:<'[Bye]'> but was:<'[Hello]'></em>."
   * <p/>
   * We can replace this message with our own:
   * <pre>
   * assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
   * </pre>
   * in this case, the assertion will fail showing the message "<em>'Hello' should be equal to 'Bye'</em>".
   *
   * @param message the given error message, which will replace the default one.
   * @return this assertion.
   * @since 1.2
   */
  public @Nonnull S overridingErrorMessage(@Nullable String message) {
    replaceDefaultErrorMessagesWith(message);
    return myself();
  }

  protected final @Nonnull S myself() {
    return checkNotNull(myself);
  }
}
