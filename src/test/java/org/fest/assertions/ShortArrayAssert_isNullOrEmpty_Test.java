/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.EmptyArrays.emptyShortArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ShortArrayAssert#isNullOrEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ShortArrayAssert_isNullOrEmpty_Test implements GroupAssert_isNullOrEmpty_TestCase {

  private static short[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = ArrayFactory.shortArray(6);
  }

  @Test
  public void should_pass_if_actual_is_null() {
    new ShortArrayAssert(null).isNullOrEmpty();
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    new ShortArrayAssert(emptyShortArray()).isNullOrEmpty();
  }

  @Test
  public void should_fail_if_actual_has_content() {
    expectAssertionError("expecting null or empty, but was:<[6]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).isNullOrEmpty();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_has_content() {
    expectAssertionError("[A Test] expecting null or empty, but was:<[6]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .isNullOrEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_has_content() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).overridingErrorMessage("My custom message")
                                   .isNullOrEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_has_content() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .isNullOrEmpty();
      }
    });
  }
}
