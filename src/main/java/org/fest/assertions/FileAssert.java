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

import static org.fest.assertions.Formatting.format;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Arrays.isNullOrEmpty;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.SystemProperties.LINE_SEPARATOR;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.assertions.FileContentComparator.LineDiff;
import org.fest.util.VisibleForTesting;

/**
 * <p>
 * Assertions for {@link File}.
 * </p>
 *
 * <p>
 * To create a new instance of this class invoke {@link Assertions#assertThat(File)}.
 * </p>
 * 
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert extends GenericAssert<FileAssert, File> {
  private final FileContentComparator comparator;

  /**
   * Creates a new {@link FileAssert}.
   * 
   * @param actual the target to verify.
   */
  protected FileAssert(@Nullable File actual) {
    this(actual, new FileContentComparator());
  }

  @VisibleForTesting
  FileAssert(@Nullable File actual, @Nonnull FileContentComparator comparator) {
    super(FileAssert.class, actual);
    this.comparator = comparator;
  }

  /**
   * Verifies that the actual {@code File} does not exist.
   * 
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} exists.
   */
  public @Nonnull FileAssert doesNotExist() {
    isNotNull();
    if (!actual.exists()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should not exist", actual));
  }

  /**
   * Verifies that the actual {@code File} does exist.
   * 
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} does not exist.
   */
  public @Nonnull FileAssert exists() {
    isNotNull();
    assertExists(actual);
    return this;
  }

  /**
   * Verifies that the size of the actual {@code File} is equal to the given one.
   * 
   * @param expected the expected size of the actual {@code File}.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the size of the actual {@code File} is not equal to the given one.
   */
  public @Nonnull FileAssert hasSize(long expected) {
    isNotNull();
    long size = actual.length();
    if (size == expected) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("size of file:<%s> expected:<%s> but was:<%s>", actual, expected, size));
  }

  /**
   * Verifies that the actual {@code File} is a directory.
   * 
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} is not a directory.
   */
  public @Nonnull FileAssert isDirectory() {
    isNotNull();
    if (actual.isDirectory()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a directory", actual));
  }

  /**
   * Verifies that the actual {@code File} is a regular file.
   * 
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} is not a regular file.
   */
  public @Nonnull FileAssert isFile() {
    isNotNull();
    if (actual.isFile()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a file", actual));
  }

  /**
   * Verifies that the content of the actual {@code File} is equal to the content of the given one. Adapted from <a
   * href="http://junit-addons.sourceforge.net/junitx/framework/FileAssert.html" target="_blank">FileAssert</a> (from <a
   * href="http://sourceforge.net/projects/junit-addons">JUnit-addons</a>.)
   * 
   * @param expected the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws NullPointerException if the file to compare to is {@code null}.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the content of the actual {@code File} is not equal to the content of the given one.
   */
  public @Nonnull FileAssert hasSameContentAs(@Nonnull File expected) {
    checkNotNull(expected);
    isNotNull();
    assertExists(actual).assertExists(expected);
    try {
      LineDiff[] diffs = comparator.compareContents(actual, expected);
      if (!isNullOrEmpty(diffs)) {
        fail(expected, diffs);
      }
    } catch (IOException e) {
      cannotCompareToExpectedFile(expected, e);
    }
    return this;
  }

  private void fail(@Nonnull File expected, @Nonnull LineDiff[] diffs) {
    failIfCustomMessageIsSet();
    StringBuilder b = new StringBuilder();
    b.append("file:").append(inBrackets(actual)).append(" and file:").append(inBrackets(expected))
    .append(" do not have same contents:");
    for (LineDiff diff : diffs) {
      b.append(LINE_SEPARATOR)
      .append("line:").append(inBrackets(diff.lineNumber)).append(", expected:").append(inBrackets(diff.expected))
      .append(" but was:").append(inBrackets(diff.actual));
    }
    fail(b.toString());
  }

  private void cannotCompareToExpectedFile(@Nonnull File expected, @Nonnull Exception e) {
    failIfCustomMessageIsSet(e);
    String message = format("unable to compare contents of files:<%s> and <%s>", actual, expected);
    fail(message, e);
  }

  private FileAssert assertExists(@Nonnull File file) {
    if (file.exists()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should exist", file));
  }

  /**
   * Verifies that the actual {@code File} is a relative path.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not a relative path.
   */
  public @Nonnull FileAssert isRelative() {
    isNotNull();
    if (!actual.isAbsolute()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a relative path", actual));
  }

  /**
   * Verifies that the actual {@code File} is an absolute path.
   * 
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not an absolute path.
   */
  public @Nonnull FileAssert isAbsolute() {
    isNotNull();
    if (actual.isAbsolute()) {
      return this;
    }
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be an absolute path", actual));
  }
}
