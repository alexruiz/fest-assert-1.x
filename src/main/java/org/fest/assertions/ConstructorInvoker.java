/*
 * Created on Feb 15, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions;

import javax.annotation.Nonnull;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Preconditions.checkNotNullOrEmpty;

/**
 * Constructor access through Java reflection.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class ConstructorInvoker {
  private static Logger logger = Logger.getLogger(ConstructorInvoker.class.getCanonicalName());

  @Nonnull Object newInstance(
      @Nonnull String className, @Nonnull Class<?>[] parameterTypes, @Nonnull Object[] parameterValues)
      throws Exception {
    checkNotNullOrEmpty(className);
    checkNotNull(parameterTypes);
    checkNotNull(parameterValues);
    Class<?> targetType = Class.forName(className);
    Constructor<?> constructor = targetType.getConstructor(parameterTypes);
    boolean accessible = constructor.isAccessible();
    try {
      setAccessible(constructor, true);
      return constructor.newInstance(parameterValues);
    } finally {
      try {
        setAccessible(constructor, accessible);
      } catch (RuntimeException e) {
        logger.log(Level.SEVERE, "Failed to set 'accessible' flag back to " + accessible, e);
      }
    }
  }

  private void setAccessible(@Nonnull AccessibleObject accessible, boolean value) {
    AccessController.doPrivileged(new SetAccessibleValueAction(accessible, value));
  }

  private static class SetAccessibleValueAction implements PrivilegedAction<Void> {
    private final AccessibleObject accessible;
    private final boolean value;

    private SetAccessibleValueAction(@Nonnull AccessibleObject accessible, boolean value) {
      this.accessible = checkNotNull(accessible);
      this.value = value;
    }

    @Override
    public Void run() {
      accessible.setAccessible(value);
      return null;
    }
  }
}
