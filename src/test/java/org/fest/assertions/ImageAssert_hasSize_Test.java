/*
 * Created on Jun 9, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.Images.fivePixelBlueImage;

import java.awt.Dimension;

import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ImageAssert#hasSize(Dimension)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_hasSize_Test {

  @Rule public ExpectedException thrown = none();

  @Test public void should_pass_if_actual_has_expected_size() {
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 5));
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    new ImageAssert(null).hasSize(new Dimension(5, 5));
  }

  @Test public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull("A Test"));
    new ImageAssert(null).as("A Test")
                         .hasSize(new Dimension(5, 5));
  }

  @Test public void should_throw_error_if_given_size_is_null() {
    thrown.expectNullPointerException("The size to compare to should not be null");
    new ImageAssert(fivePixelBlueImage()).hasSize(null);
  }

  @Test public void should_throw_error_and_display_description_if_given_size_is_null() {
    thrown.expectNullPointerException("[A Test] The size to compare to should not be null");
    new ImageAssert(fivePixelBlueImage()).as("A Test")
                                         .hasSize(null);
  }

  @Test public void should_fail_if_width_is_not_equal() {
    thrown.expectAssertionError("expected:<(w=[3], h=5)> but was:<(w=[5], h=5)>");
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(3, 5));
  }

  @Test public void should_fail_and_display_description_if_width_is_not_equal() {
    thrown.expectAssertionError("[A Test] expected:<(w=[3], h=5)> but was:<(w=[5], h=5)>");
    new ImageAssert(fivePixelBlueImage()).as("A Test")
                                         .hasSize(new Dimension(3, 5));
  }

  @Test public void should_fail_if_height_is_not_equal() {
    thrown.expectAssertionError("expected:<(w=5, h=[3])> but was:<(w=5, h=[5])>");
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 3));
  }

  @Test public void should_fail_and_display_description_if_height_is_not_equal() {
    thrown.expectAssertionError("[A Test] expected:<(w=5, h=[3])> but was:<(w=5, h=[5])>");
    new ImageAssert(fivePixelBlueImage()).as("A Test")
                                         .hasSize(new Dimension(5, 3));
  }
}
