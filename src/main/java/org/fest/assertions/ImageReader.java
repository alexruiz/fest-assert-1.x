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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Reads an image from the file system.
 *
 * @author Yvonne Wang
 */
class ImageReader {
  @Nullable BufferedImage read(@NotNull File imageFile) throws IOException {
    return ImageIO.read(imageFile);
  }
}
