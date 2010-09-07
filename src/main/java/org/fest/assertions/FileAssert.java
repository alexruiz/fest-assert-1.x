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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatting.*;
import static org.fest.util.Arrays.isEmpty;
import static org.fest.util.Systems.LINE_SEPARATOR;

import java.io.*;

import org.fest.assertions.FileContentComparator.LineDiff;

/**
 * Assertions for <code>{@link File}</code>.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(File)}</code>.
 * </p>
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert extends GenericAssert<File> {

  private final FileContentComparator comparator;

  /**
   * Creates a new <code>FileAssert</code>.
   * @param actual the target to verify.
   */
  protected FileAssert(File actual) {
    this(actual, new FileContentComparator());
  }

  /* for testing only */
  FileAssert(File actual, FileContentComparator comparator) {
    super(actual);
    this.comparator = comparator;
  }

  /** {@inheritDoc} */
  @Override public FileAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override public FileAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override public FileAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  @Override public FileAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code File} does not exist.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} exists.
   */
  public FileAssert doesNotExist() {
    isNotNull();
    if (!actual.exists()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should not exist", actual));
  }

  /**
   * Verifies that the actual {@code File} does exist.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} does not exist.
   */
  public FileAssert exists() {
    isNotNull();
    assertExists(actual);
    return this;
  }

  /**
   * Verifies that the size of the actual {@code File} is equal to the given one.
   * @param expected the expected size of the actual {@code File}.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the size of the actual {@code File} is not equal to the given one.
   */
  public FileAssert hasSize(long expected) {
    isNotNull();
    long size = actual.length();
    if (size == expected) return this;
    failIfCustomMessageIsSet();
    throw failure(format("size of file:<%s> expected:<%s> but was:<%s>", actual, expected, size));
  }

  /**
   * Verifies that the actual {@code File} is a directory.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} is not a directory.
   */
  public FileAssert isDirectory() {
    isNotNull();
    if (actual.isDirectory()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a directory", actual));
  }

  /**
   * Verifies that the actual {@code File} is equal to the given one. To verify that the actual {@code File}
   * has the same content as another {@code File}, use <code>{@link #hasSameContentAs(File)}</code>.
   * @param expected the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not equal to the given one.
   */
  @Override public FileAssert isEqualTo(File expected) {
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code File} is a regular file.
   * @return this assertion object.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the actual {@code File} is not a regular file.
   */
  public FileAssert isFile() {
    isNotNull();
    if (actual.isFile()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a file", actual));
  }

  /**
   * Verifies that the actual {@code File} is not equal to the given one.
   * @param other the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is equal to the given one.
   */
  @Override public FileAssert isNotEqualTo(File other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code File} is not {@code null}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is {@code null}.
   */
  @Override public FileAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code File} is not the same as the given one.
   * @param other the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is the same as the given one.
   */
  @Override public FileAssert isNotSameAs(File other) {
    assertNotSameAs(other);
    return this;
  }

  /**
   * Verifies that the actual {@code File} is the same as the given one.
   * @param expected the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not the same as the given one.
   */
  @Override public FileAssert isSameAs(File expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code File} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code File} does not satisfy the given condition.
   * @see #is(Condition)
   */
  @Override public FileAssert satisfies(Condition<File> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code File} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} satisfies the given condition.
   * @throws NullPointerException if the given condition is {@code null}.
   * @see #isNot(Condition)
   */
  @Override public FileAssert doesNotSatisfy(Condition<File> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }


  /**
   * Verifies that the actual {@code File} satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual {@code File} does not satisfy the given condition.
   */
  @Override public FileAssert is(Condition<File> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code File} does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} satisfies the given condition.
   * @throws NullPointerException if the given condition is {@code null}.
   */
  @Override public FileAssert isNot(Condition<File> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the content of the actual {@code File} is equal to the content of the given one. Adapted from
   * <a href="http://junit-addons.sourceforge.net/junitx/framework/FileAssert.html" target="_blank">FileAssert</a> (from
   * <a href="http://sourceforge.net/projects/junit-addons">JUnit-addons</a>.)
   * @param expected the given {@code File} to compare the actual {@code File} to.
   * @return this assertion object.
   * @throws NullPointerException if the file to compare to is {@code null}.
   * @throws AssertionError if the the actual {@code File} is {@code null}.
   * @throws AssertionError if the content of the actual {@code File} is not equal to the content of the given one.
   */
  public FileAssert hasSameContentAs(File expected) {
    if (expected == null) throw new NullPointerException(formattedErrorMessage("File to compare to should not be null"));
    isNotNull();
    assertExists(actual).assertExists(expected);
    try {
      LineDiff[] diffs = comparator.compareContents(actual, expected);
      if (!isEmpty(diffs)) fail(expected, diffs);
    } catch (IOException e) {
      cannotCompareToExpectedFile(expected, e);
    }
    return this;
  }

  private void fail(File expected, LineDiff[] diffs) {
    failIfCustomMessageIsSet();
    StringBuilder b = new StringBuilder();
    b.append("file:").append(inBrackets(actual)).append(" and file:").append(inBrackets(expected))
      .append(" do not have same contents:");
    for (LineDiff diff : diffs) {
      b.append(LINE_SEPARATOR).append("line:").append(inBrackets(diff.lineNumber))
        .append(", expected:").append(inBrackets(diff.expected)).append(" but was:").append(inBrackets(diff.actual));
    }
    fail(b.toString());
  }

  private void cannotCompareToExpectedFile(File expected, Exception e) {
    failIfCustomMessageIsSet(e);
    String message = format("unable to compare contents of files:<%s> and <%s>", actual, expected);
    fail(message, e);
  }

  private FileAssert assertExists(File file) {
    if (file.exists()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should exist", file));
  }

  /**
   * Verifies that the actual {@code File} is a relative path.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not a relative path.
   */
  public FileAssert isRelative() {
    isNotNull();
    if (!actual.isAbsolute()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be a relative path", actual));
  }

  /**
   * Verifies that the actual {@code File} is an absolute path.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code File} is not an absolute path.
   */
  public FileAssert isAbsolute() {
    isNotNull();
    if (actual.isAbsolute()) return this;
    failIfCustomMessageIsSet();
    throw failure(format("file:<%s> should be an absolute path", actual));
  }

  /** {@inheritDoc} */
  @Override public FileAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
