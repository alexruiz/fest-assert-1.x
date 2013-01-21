/*
 * Created on Dec 23, 2007
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
import static org.fest.test.ExpectedException.none;

import org.fest.test.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FileAssert#isDirectory()}.
 * 
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_isDirectory_Test extends FileAssert_TestCase {
  @Rule
  public ExpectedException thrown = none();

  @Test
  public void should_pass_if_actual_is_directory() {
    file.ensureIsDirectory();
    new FileAssert(file).isDirectory();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new FileAssert(null).isDirectory();
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new FileAssert(null).as("A Test").isDirectory();
  }

  @Test
  public void should_fail_if_actual_is_not_directory() {
    thrown.expect(AssertionError.class, "file:<c:\\f.txt> should be a directory");
    new FileAssert(file).isDirectory();
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_not_directory() {
    thrown.expect(AssertionError.class, "[A Test] file:<c:\\f.txt> should be a directory");
    new FileAssert(file).as("A Test").isDirectory();
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_directory() {
    thrown.expect(AssertionError.class, "My custom message");
    new FileAssert(file).overridingErrorMessage("My custom message").isDirectory();
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_is_not_directory() {
    thrown.expect(AssertionError.class, "My custom message");
    new FileAssert(file).as("A Test").overridingErrorMessage("My custom message").isDirectory();
  }
}