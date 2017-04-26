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

public class MapDataItem extends DataItem {

  private final String country;

  /**
   * Create a new map data item with the given ISO 3166-1 country code.
   * All numeric/two-letter/three-letter country codes are supported.
   *
   * @param country the country of the item
   */
  public MapDataItem( String country ) {
    this( country, null, null );
  }

  /**
   * Create a new map data item with the given ISO 3166-1 country code and text.
   * All numeric/two-letter/three-letter country codes are supported.
   *
   * @param country the country of the item
   * @param text the tooltip text for the item, or <code>null</code> to omit the tooltip
   */
  public MapDataItem( String country, String text ) {
    this( country, text, null );
  }

  /**
   * Create a new map data item with the given ISO 3166-1 country code, text, and color.
   * All numeric/two-letter/three-letter country codes are supported.
   *
   * @param country the country of the item
   * @param text the tooltip text for the item, or <code>null</code> to omit the tooltip
   * @param color the color of this item, or <code>null</code> to use the default color
   */
  public MapDataItem( String country, String text, RGB color ) {
    super( 0, text, color );
    this.country = country;
  }

  /**
   * Returns the value of this data item.
   *
   * @return the item value
   */
  public String getCountry() {
    return country;
  }

}
