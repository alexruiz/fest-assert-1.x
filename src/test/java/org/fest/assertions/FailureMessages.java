/*
 * Created on Jun 12, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatter.format;
import static org.fest.util.Strings.*;

import org.junit.ComparisonFailure;

/**
 * @author Alex Ruiz
 */
final class FailureMessages {

  static String actualIsNull() {
    return "expecting actual value not to be null";
  }

  static String actualIsNull(String description) {
    return String.format("[%s] expecting actual value not to be null", description);
  }

  static String conditionIsNull() {
    return "Condition to check should not be null";
  }

  static String typeIsNull() {
    return "expected type should not be null";
  }

  static String typeIsNull(String description) {
    return String.format("[%s] expected type should not be null", description);
  }

  static String notEqual(Object actual, Object expected) {
    return notEqual(null, actual, expected);
  }

  static String notEqual(String description, Object actual, Object expected) {
    String d = inBrackets(description);
    String a = format(actual);
    String e = format(expected);
    boolean isArray = isArray(actual) || isArray(expected);
    if (!isArray) return new ComparisonFailure(d, e, a).getMessage();
    d = addSpaceIfNotEmpty(d);
    return concat(d, "expected:<", e, "> but was:<", a, ">");
  }

  static String equal(Object actual, Object other) {
    return equal(null, actual, other);
  }

  static String equal(String description, Object actual, Object other) {
    String d = addSpaceIfNotEmpty(inBrackets(description));
    return concat(d, "actual value:<", format(actual), "> should not be equal to:<", format(other), ">");
  }

  static String equalOrLess(Object actual, Object other) {
    return equalOrLess(null, actual, other);
  }

  static String equalOrLess(String description, Object actual, Object other) {
    String d = addSpaceIfNotEmpty(inBrackets(description));
    return concat(d, "actual value:<", format(actual), "> should be greater than:<", format(other), ">");
  }

  static String notIn(String description, Object actual, Object other) {
    String d = addSpaceIfNotEmpty(inBrackets(description));
    return concat(d, "actual value:<", format(actual), "> should be in:<", format(other), ">");
  }

  static String in(String description, Object actual, Object other) {
    String d = addSpaceIfNotEmpty(inBrackets(description));
    return concat(d, "actual value:<", format(actual), "> should not be in:<", format(other), ">");
  }

  private static String inBrackets(String s) {
    if (isEmpty(s)) return "";
    return concat("[", s, "]");
  }

  private static String addSpaceIfNotEmpty(String s) {
    if (isEmpty(s)) return s;
    return concat(s, " ");
  }

  private static boolean isArray(Object o) {
    return o != null && o.getClass().isArray();
  }
}
