/*
 * Created on Sep 27, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.assertions;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.BLUE;
import static java.awt.Color.YELLOW;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * @author Alex Ruiz
 */
final class Images {
  static BufferedImage fivePixelBlueImage() {
    return image(5, 5, BLUE);
  }

  static BufferedImage fivePixelYellowImage() {
    return image(5, 5, YELLOW);
  }

  static BufferedImage image(int width, int height, Color color) {
    BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);
    Graphics graphics = image.createGraphics();
    graphics.setColor(color);
    graphics.fillRect(0, 0, width, height);
    return image;
  }

  private Images() {
  }
}
