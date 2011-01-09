/*
 * Created on Oct 1, 2009
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
import static org.fest.assertions.FailureMessages.*;

import org.junit.*;

/**
 * Test case for implementations of <code>{@link NumberAssert#isZero()}</code>.
 * @param <T> The type supported by the implementation of the {@code NumberAssert} to test.
 *
 * @author Alex Ruiz
 */
public abstract class NumberAssert_isZero_TestCase<T extends Number> {

  // TODO make NumberAssert a subclass of ComparableAssert

  @Rule public ExpectedException thrown = none();

  private T notZero;
  private T zero;
  private NumberAssert assertions;

  @Before public final void setUp() {
    notZero = notZero();
    zero = zero();
    assertions = assertionsFor(notZero);
  }

  protected abstract T notZero();

  protected abstract T zero();

  protected abstract NumberAssert assertionsFor(T actual);

  @Test public final void should_pass_if_actual_is_zero() {
    assertionsFor(zero).isZero();
  }

  @Test public final void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
        new BigDecimalAssert(null).isZero();
  }

//  @Test
//  public final void should_fail_and_display_description_if_actual_is_null() {
//    thrown.expectAssertionError(actualIsNull("A Test"));
//      public void run() {
//        assertionsFor(null).as("A Test")
//                           .isZero();
//      }
//    });
//  }

  @Test public final void should_fail_if_actual_is_not_zero() {
    thrown.expectAssertionError(notEqual(notZero, zero));
        assertions.isZero();
  }
//
//  @Test
//  public final void should_fail_and_display_description_if_actual_is_not_zero() {
//    thrown.expectAssertionError("[A Test] expected:<0> but was:<8.0>");
//      public void run() {
//        assertions.as("A Test")
//                  .isZero();
//      }
//    });
//  }
//
//  @Test
//  public final void should_fail_with_custom_message_if_actual_is_not_zero() {
//    thrown.expectAssertionError("My custom message");
//      public void run() {
//        assertions.overridingErrorMessage("My custom message")
//                  .isZero();
//      }
//    });
//  }
//
//  @Test
//  public final void should_fail_with_custom_message_ignoring_description_if_actual_is_not_zero() {
//    thrown.expectAssertionError("My custom message");
//      public void run() {
//        assertions.as("A Test")
//                  .overridingErrorMessage("My custom message")
//                  .isZero();
//      }
//    });
//  }
}