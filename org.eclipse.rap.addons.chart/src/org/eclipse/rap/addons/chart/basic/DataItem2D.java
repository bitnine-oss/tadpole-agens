/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.addons.chart.basic;

import org.eclipse.swt.graphics.RGB;


/**
 * Represents a two-dimensional data item in a chart.
 */
public class DataItem2D extends DataItem {

  protected final Object x;

  /**
   * Create a new data item with the given values.
   *
   * @param x the x value of the item
   * @param y the y value of the item
   */
  public DataItem2D( Object x, double y ) {
    this( x, y, null );
  }

  /**
   * Create a new data item with the given values and text.
   *
   * @param x the x value of the item
   * @param y the y value of the item
   * @param text the label text for the item, or <code>null</code> to omit the label
   */
  public DataItem2D( Object x, double y, String text ) {
    this( x, y, text, null );
  }

  /**
   * Create a new data item with the given values, text, and color.
   *
   * @param x the x value of the item
   * @param y the y value of the item
   * @param text the label text for the item, or <code>null</code> to omit the label
   * @param color the color of this item, or <code>null</code> to use the default color
   */
  public DataItem2D( Object x, double y, String text, RGB color ) {
    super( y, text, color );
    this.x = x;
  }

  /**
   * Returns the x value of this data item.
   *
   * @return the item's x value
   */
  public Object getX() {
    return x;
  }

  /**
   * Returns the y value of this data item.
   *
   * @return the item's y value
   */
  public double getY() {
    return value;
  }

}
