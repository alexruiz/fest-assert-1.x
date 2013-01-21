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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FailureMessages.actualIsNull;
import static org.fest.assertions.Images.fivePixelBlueImage;
import static org.fest.test.ExpectedException.none;

import java.awt.Dimension;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link ImageAssert#hasSize(Dimension)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_hasSize_Test {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_has_expected_size() {
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 5));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new ImageAssert(null).hasSize(new Dimension(5, 5));
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new ImageAssert(null).as("A Test").hasSize(new Dimension(5, 5));
  }

  @Test
  public void should_throw_error_if_given_size_is_null() {
    thrown.expect(NullPointerException.class);
    new ImageAssert(fivePixelBlueImage()).hasSize(null);
  }

  @Test
  public void should_fail_if_width_is_not_equal() {
    thrown.expect(AssertionError.class, "expected:<(w=[3], h=5)> but was:<(w=[5], h=5)>");
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(3, 5));
  }

  @Test
  public void should_fail_and_display_description_if_width_is_not_equal() {
    thrown.expect(AssertionError.class, "[A Test] expected:<(w=[3], h=5)> but was:<(w=[5], h=5)>");
    new ImageAssert(fivePixelBlueImage()).as("A Test").hasSize(new Dimension(3, 5));
  }

  @Test
  public void should_fail_if_height_is_not_equal() {
    thrown.expect(AssertionError.class, "expected:<(w=5, h=[3])> but was:<(w=5, h=[5])>");
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 3));
  }

  @Test
  public void should_fail_and_display_description_if_height_is_not_equal() {
    thrown.expect(AssertionError.class, "[A Test] expected:<(w=5, h=[3])> but was:<(w=5, h=[5])>");
    new ImageAssert(fivePixelBlueImage()).as("A Test").hasSize(new Dimension(5, 3));
  }
}
