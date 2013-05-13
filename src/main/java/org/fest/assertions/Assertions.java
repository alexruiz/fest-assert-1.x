/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Entry point for assertion methods for different data types. Each method in this class is a static factory for the
 * type-specific assertion objects. The purpose of this class is to make test code more readable.
 * <p/>
 * For example:
 * <pre>
 * int removed = employees.removeFired();
 * {@link org.fest.assertions.Assertions#assertThat(int) assertThat}(removed).{@link org.fest.assertions.IntAssert#isZero isZero}();
 *
 * List&lt;Employee&gt; newEmployees = employees.hired(TODAY);
 * {@link org.fest.assertions.Assertions#assertThat(java.util.Collection) assertThat}(newEmployees).{@link org.fest.assertions.CollectionAssert#hasSize(int) hasSize}(6);
 * </pre>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ted Young
 */
public class Assertions {
  /**
   * This constructor is protected to make it possible to subclass this class. Since all its methods are static, there
   * is no point on creating a new instance of it.
   */
  protected Assertions() {
  }

  /**
   * Creates a new instance of {@link BigDecimalAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull BigDecimalAssert assertThat(@Nullable BigDecimal actual) {
    return new BigDecimalAssert(actual);
  }

  /**
   * Creates a new instance of {@link BooleanAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull BooleanAssert assertThat(boolean actual) {
    return new BooleanAssert(actual);
  }

  /**
   * Creates a new instance of {@link BooleanAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull BooleanAssert assertThat(@Nullable Boolean actual) {
    return new BooleanAssert(actual);
  }

  /**
   * Creates a new instance of {@link BooleanArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull BooleanArrayAssert assertThat(@Nullable boolean[] actual) {
    return new BooleanArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link ImageAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ImageAssert assertThat(@Nullable BufferedImage actual) {
    return new ImageAssert(actual);
  }

  /**
   * Creates a new instance of {@link ByteAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ByteAssert assertThat(byte actual) {
    return new ByteAssert(actual);
  }

  /**
   * Creates a new instance of {@link ByteAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ByteAssert assertThat(@Nullable Byte actual) {
    return new ByteAssert(actual);
  }

  /**
   * Creates a new instance of {@link ByteArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ByteArrayAssert assertThat(@Nullable byte[] actual) {
    return new ByteArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link CharAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull CharAssert assertThat(char actual) {
    return new CharAssert(actual);
  }

  /**
   * Creates a new instance of {@link CharAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull CharAssert assertThat(@Nullable Character actual) {
    return new CharAssert(actual);
  }

  /**
   * Creates a new instance of {@link CharArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull CharArrayAssert assertThat(@Nullable char[] actual) {
    return new CharArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link CollectionAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull CollectionAssert assertThat(@Nullable Collection<?> actual) {
    return new CollectionAssert(actual);
  }

  /**
   * Creates a new instance of {@link ListAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   * @since 1.1
   */
  public static @Nonnull ListAssert assertThat(@Nullable List<?> actual) {
    return new ListAssert(actual);
  }

  /**
   * Creates a new instance of {@link DoubleAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull DoubleAssert assertThat(double actual) {
    return new DoubleAssert(actual);
  }

  /**
   * Creates a new instance of {@link DoubleAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull DoubleAssert assertThat(@Nullable Double actual) {
    return new DoubleAssert(actual);
  }

  /**
   * Creates a new instance of {@link DoubleArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull DoubleArrayAssert assertThat(@Nullable double[] actual) {
    return new DoubleArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link FileAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull FileAssert assertThat(@Nullable File actual) {
    return new FileAssert(actual);
  }

  /**
   * Creates a new instance of {@link FloatAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull FloatAssert assertThat(float actual) {
    return new FloatAssert(actual);
  }

  /**
   * Creates a new instance of {@link FloatAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull FloatAssert assertThat(@Nullable Float actual) {
    return new FloatAssert(actual);
  }

  /**
   * Creates a new instance of {@link FloatArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull FloatArrayAssert assertThat(@Nullable float[] actual) {
    return new FloatArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link IntAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull IntAssert assertThat(int actual) {
    return new IntAssert(actual);
  }

  /**
   * Creates a new instance of {@link IntAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull IntAssert assertThat(@Nullable Integer actual) {
    return new IntAssert(actual);
  }

  /**
   * Creates a new instance of {@link IntArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull IntArrayAssert assertThat(@Nullable int[] actual) {
    return new IntArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link IteratorAssert}.
   *
   * @param actual an {@code Iterable} whose contents will be added to a new {@code Collection}.
   * @return the created assertion object.
   */
  public static @Nonnull IteratorAssert assertThat(@Nullable Iterable<?> actual) {
    Iterator<?> iterator = actual == null ? null : actual.iterator();
    return assertThat(iterator);
  }

  /**
   * Creates a new instance of {@link IteratorAssert}.
   *
   * @param actual an {@code Iterator} whose contents will be added to a new {@code Collection}.
   * @return the created assertion object.
   */
  public static @Nonnull IteratorAssert assertThat(@Nullable Iterator<?> actual) {
    return new IteratorAssert(actual);
  }

  /**
   * Creates a new instance of {@link LongAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull LongAssert assertThat(long actual) {
    return new LongAssert(actual);
  }

  /**
   * Creates a new instance of {@link LongAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull LongAssert assertThat(@Nullable Long actual) {
    return new LongAssert(actual);
  }

  /**
   * Creates a new instance of {@link LongArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull LongArrayAssert assertThat(@Nullable long[] actual) {
    return new LongArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link MapAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull MapAssert assertThat(@Nullable Map<?, ?> actual) {
    return new MapAssert(actual);
  }

  /**
   * Creates a new instance of {@link ObjectAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ObjectAssert assertThat(@Nullable Object actual) {
    return new ObjectAssert(actual);
  }

  /**
   * Creates a new instance of {@link ObjectArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ObjectArrayAssert assertThat(@Nullable Object[] actual) {
    return new ObjectArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link ShortAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ShortAssert assertThat(short actual) {
    return new ShortAssert(actual);
  }

  /**
   * Creates a new instance of {@link ShortAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ShortAssert assertThat(@Nullable Short actual) {
    return new ShortAssert(actual);
  }

  /**
   * Creates a new instance of {@link ShortArrayAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ShortArrayAssert assertThat(@Nullable short[] actual) {
    return new ShortArrayAssert(actual);
  }

  /**
   * Creates a new instance of {@link StringAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull StringAssert assertThat(@Nullable String actual) {
    return new StringAssert(actual);
  }

  /**
   * Returns the given assertion. This method improves code readability by surrounding the given assertion with
   * "{@code assertThat}".
   * <p/>
   * For example, let's assume we have the following custom assertion class:
   * <pre>
   * public class ServerSocketAssertion implements AssertExtension {
   *   private final ServerSocket socket;
   *
   *   public ServerSocketAssertion(ServerSocket socket) {
   *     this.socket = socket;
   *   }
   *
   *   public ServerSocketAssert isConnectedTo(int port) {
   *     assertThat(socket.isBound()).isTrue();
   *     assertThat(socket.getLocalPort()).isEqualTo(port);
   *     assertThat(socket.isClosed()).isFalse();
   *     return this;
   *   }
   * }
   * </pre>
   * <p/>
   * We can wrap that assertion with "{@code assertThat}" to improve test code readability.
   * <pre>
   * ServerSocketAssertion socket = new ServerSocketAssertion(server.getSocket());
   * assertThat(socket).isConnectedTo(2000);
   * </pre>
   *
   * @param assertion the assertion to return.
   * @return the given assertion.
   * @throws NullPointerException if the given assertion is {@code null}.
   */
  public static @Nonnull <T extends AssertExtension> T assertThat(@Nonnull T assertion) {
    return assertion;
  }

  /**
   * Creates a new instance of {@link ThrowableAssert}.
   *
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static @Nonnull ThrowableAssert assertThat(@Nullable Throwable actual) {
    return new ThrowableAssert(actual);
  }
}
