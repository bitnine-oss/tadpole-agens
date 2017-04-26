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
 * Represents a data item in a chart.
 */
public class DataItem {

  protected final double value;
  protected final String text;
  protected final RGB color;

  /**
   * Create a new data item with the given value.
   *
   * @param value the value of the item
   */
  public DataItem( double value ) {
    this( value, null );
  }

  /**
   * Create a new data item with the given value and text.
   *
   * @param value the value of the item
   * @param text the label text for the item, or <code>null</code> to omit the label
   */
  public DataItem( double value, String text ) {
    this( value, text, null );
  }

  /**
   * Create a new data item with the given value, text, and color.
   *
   * @param value the value of the item
   * @param text the label text for the item, or <code>null</code> to omit the label
   * @param color the color of this item, or <code>null</code> to use the default color
   */
  public DataItem( double value, String text, RGB color ) {
    this.value = value;
    this.text = text;
    this.color = color;
  }

  /**
   * Returns the value of this data item.
   *
   * @return the item value
   */
  public double getValue() {
    return value;
  }

  /**
   * Returns the text for this data item.
   *
   * @return the item text, can be <code>null</code>
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the color for this data item.
   *
   * @return the item color, can be <code>null</code>
   */
  public RGB getColor() {
    return color;
  }

}
