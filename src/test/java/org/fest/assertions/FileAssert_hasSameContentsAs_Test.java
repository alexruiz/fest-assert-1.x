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
import static org.fest.assertions.FileContentComparator.LineDiff.lineDiff;
import static org.fest.assertions.FileStub.newFile;
import static org.fest.test.ExpectedException.none;
import static org.fest.util.Strings.concat;
import static org.fest.util.SystemProperties.LINE_SEPARATOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FileAssert#hasSameContentAs(File)}.
 * 
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_hasSameContentsAs_Test extends FileAssert_TestCase {
  @Rule
  public ExpectedException thrown = none();

  private FileContentComparatorStub comparator;

  @Before
  public void onSetUp() {
    comparator = new FileContentComparatorStub();
  }

  @Test
  public void should_pass_is_actual_and_expected_have_same_content() {
    file.ensureExists();
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    new FileAssert(file, comparator).hasSameContentAs(expected);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    new FileAssert(null).hasSameContentAs(file);
  }

  @Test
  public void should_fail_and_display_description_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull("A Test"));
    new FileAssert(null).as("A Test").hasSameContentAs(file);
  }

  @Test
  public void should_fail_if_actual_and_expected_do_not_have_same_content() {
    file.ensureExists();
    comparator.expectedLineDiffs(lineDiff(6, "abc", "xyz"), lineDiff(8, "xyz", "abc"));
    final String message = concat("file:<c:\\f.txt> and file:<c:\\temp\\expected.txt> do not have same contents:",
        LINE_SEPARATOR, "line:<6>, expected:<'xyz'> but was:<'abc'>", LINE_SEPARATOR,
        "line:<8>, expected:<'abc'> but was:<'xyz'>");
    thrown.expect(AssertionError.class, message);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    new FileAssert(file, comparator).hasSameContentAs(expected);
  }

  @Test
  public void should_fail_and_display_description_if_actual_and_expected_do_not_have_same_content() {
    file.ensureExists();
    comparator.expectedLineDiffs(lineDiff(6, "abc", "xyz"), lineDiff(8, "xyz", "abc"));
    final String message = concat(
        "[A Test] file:<c:\\f.txt> and file:<c:\\temp\\expected.txt> do not have same contents:", LINE_SEPARATOR,
        "line:<6>, expected:<'xyz'> but was:<'abc'>", LINE_SEPARATOR, "line:<8>, expected:<'abc'> but was:<'xyz'>");
    thrown.expect(AssertionError.class, message);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    new FileAssert(file, comparator).as("A Test").hasSameContentAs(expected);
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_do_not_have_same_content() {
    file.ensureExists();
    comparator.expectedLineDiffs(lineDiff(6, "abc", "xyz"), lineDiff(8, "xyz", "abc"));
    thrown.expect(AssertionError.class, "My custom message");
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    new FileAssert(file, comparator).overridingErrorMessage("My custom message").hasSameContentAs(expected);
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_actual_and_expected_do_not_have_same_content() {
    file.ensureExists();
    comparator.expectedLineDiffs(lineDiff(6, "abc", "xyz"), lineDiff(8, "xyz", "abc"));
    thrown.expect(AssertionError.class, "My custom message");
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    new FileAssert(file, comparator).as("A Test").overridingErrorMessage("My custom message")
    .hasSameContentAs(expected);
  }

  @Test
  public void should_fail_if_IO_error_is_thrown() {
    file.ensureExists();
    IOException toThrow = new IOException();
    comparator.exceptionToThrow(toThrow);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    try {
      new FileAssert(file, comparator).hasSameContentAs(expected);
      fail();
    } catch (AssertionError e) {
      assertEquals("unable to compare contents of files:<c:\\f.txt> and <c:\\temp\\expected.txt>", e.getMessage());
      assertSame(toThrow, e.getCause());
    }
  }

  @Test
  public void should_fail_and_display_description_if_IO_error_is_thrown() {
    file.ensureExists();
    IOException toThrow = new IOException();
    comparator.exceptionToThrow(toThrow);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    try {
      new FileAssert(file, comparator).as("A Test").hasSameContentAs(expected);
      fail();
    } catch (AssertionError e) {
      String expectedMessage = "[A Test] unable to compare contents of files:<c:\\f.txt> and <c:\\temp\\expected.txt>";
      assertEquals(expectedMessage, e.getMessage());
      assertSame(e.getCause(), toThrow);
    }
  }

  @Test
  public void should_fail_with_custom_message_if_IO_error_is_thrown() {
    file.ensureExists();
    IOException toThrow = new IOException();
    comparator.exceptionToThrow(toThrow);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    try {
      new FileAssert(file, comparator).overridingErrorMessage("My custom message").hasSameContentAs(expected);
      fail();
    } catch (AssertionError e) {
      assertEquals("My custom message", e.getMessage());
      assertSame(e.getCause(), toThrow);
    }
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_if_IO_error_is_thrown() {
    file.ensureExists();
    IOException toThrow = new IOException();
    comparator.exceptionToThrow(toThrow);
    FileStub expected = newFile("c:\\temp\\expected.txt").ensureExists();
    try {
      new FileAssert(file, comparator).as("A Test").overridingErrorMessage("My custom message")
      .hasSameContentAs(expected);
      fail();
    } catch (AssertionError e) {
      assertEquals("My custom message", e.getMessage());
      assertSame(e.getCause(), toThrow);
    }
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    thrown.expect(NullPointerException.class);
    new FileAssert(file).hasSameContentAs(null);
  }

  static class FileContentComparatorStub extends FileContentComparator {
    private LineDiff[] diffs = new LineDiff[0];
    private IOException toThrow;

    void expectedLineDiffs(LineDiff... newDiffs) {
      diffs = newDiffs;
    }

    void exceptionToThrow(IOException e) {
      this.toThrow = e;
    }

    @Override
    @Nonnull LineDiff[] compareContents(@Nonnull File actual, @Nonnull File expected) throws IOException {
      if (toThrow != null) {
        throw toThrow;
      }
      return diffs;
    }
  }
}
