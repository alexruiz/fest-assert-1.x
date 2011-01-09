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

import static java.awt.Color.*;
import static org.fest.assertions.ExpectedException.none;
import static org.fest.assertions.Images.*;

import java.awt.image.BufferedImage;

import org.junit.*;

/**
 * Tests for <code>{@link ImageAssert#isEqualTo(BufferedImage)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_isEqualTo_Test {

  @Rule public ExpectedException thrown = none();

  private static BufferedImage actual;

  @BeforeClass public static void setUpOnce() {
    actual = fivePixelBlueImage();
  }

  @Test public void should_pass_if_actual_and_expected_are_equal() {
    new ImageAssert(fivePixelBlueImage()).isEqualTo(fivePixelBlueImage());
  }

  @Test public void should_pass_if_both_actual_and_expected_are_null() {
    new ImageAssert(null).isEqualTo(null);
  }

  @Test public void should_fail_if_expected_is_null() {
    thrown.expectAssertionError(String.format("expected:<null> but was:<%s>", actual));
    new ImageAssert(actual).isEqualTo(null);
  }

  @Test public void should_fail_and_display_description_if_expected_is_null() {
    thrown.expectAssertionError(String.format("[A Test] expected:<null> but was:<%s>", actual));
    new ImageAssert(actual).as("A Test")
                           .isEqualTo(null);
  }

  @Test public void should_fail_if_width_is_not_equal() {
    thrown.expectAssertionError("image size: expected:<(w=3, h=5)> but was:<(w=5, h=5)>");
    BufferedImage expected = image(3, 5, BLUE);
    new ImageAssert(actual).isEqualTo(expected);
  }

  @Test public void should_fail_and_display_description_if_width_is_not_equal() {
    thrown.expectAssertionError("[A Test] image size: expected:<(w=3, h=5)> but was:<(w=5, h=5)>");
    BufferedImage expected = image(3, 5, BLUE);
    new ImageAssert(actual).as("A Test")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_if_height_is_not_equal() {
    thrown.expectAssertionError("image size: expected:<(w=5, h=2)> but was:<(w=5, h=5)>");
    BufferedImage expected = image(5, 2, BLUE);
    new ImageAssert(actual).isEqualTo(expected);
  }

  @Test public void should_fail_and_display_description_if_height_is_not_equal() {
    thrown.expectAssertionError("[A Test] image size: expected:<(w=5, h=2)> but was:<(w=5, h=5)>");
    BufferedImage expected = image(5, 2, BLUE);
    new ImageAssert(actual).as("A Test")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_if_color_is_not_equal() {
    thrown.expectAssertionError("expected:<color[r=0,g=0,b=255]> but was:<color[r=255,g=255,b=0]> at pixel [0,0]");
    new ImageAssert(actual).isEqualTo(fivePixelYellowImage());
  }

  @Test public void should_fail_and_display_description_if_color_is_not_equal() {
    thrown.expectAssertionError("[A Test] expected:<color[r=0,g=0,b=255]> but was:<color[r=255,g=255,b=0]> at pixel [0,0]");
    BufferedImage expected = image(5, 5, YELLOW);
    new ImageAssert(actual).as("A Test")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_if_width_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(3, 5, BLUE);
    new ImageAssert(actual).overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_width_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(3, 5, BLUE);
    new ImageAssert(actual).as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_if_height_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(5, 2, BLUE);
    new ImageAssert(actual).overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_height_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(5, 2, BLUE);
    new ImageAssert(actual).as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_if_color_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(5, 5, YELLOW);
    new ImageAssert(actual).overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }

  @Test public void should_fail_with_custom_message_ignoring_description_if_color_is_not_equal() {
    thrown.expectAssertionError("My custom message");
    BufferedImage expected = image(5, 5, YELLOW);
    new ImageAssert(actual).as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isEqualTo(expected);
  }
}
