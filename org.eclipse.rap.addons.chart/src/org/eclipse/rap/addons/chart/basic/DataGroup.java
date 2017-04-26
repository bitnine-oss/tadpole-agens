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

import static org.eclipse.rap.addons.chart.internal.ColorUtil.toHtmlString;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;


/**
 * Represents a series of data items in a chart.
 */
public class DataGroup {

  protected final DataItem[] items;
  protected final String text;
  protected final RGB color;

  /**
   * Create a new group of data items with the given text.
   *
   * @param items the items of the group
   * @param text the label text for the group, or <code>null</code> to omit the label
   */
  public DataGroup( DataItem[] items, String text ) {
    this( items, text, null );
  }

  /**
   * Create a new group of data items with the given text and color.
   *
   * @param items the items of the group
   * @param text the label text for the group, or <code>null</code> to omit the label
   * @param color the color of this group, or <code>null</code> to use the default color
   */
  public DataGroup( DataItem[] items, String text, RGB color ) {
    this.items = items;
    this.text = text;
    this.color = color;
  }
  
  public JsonObject toJson(  ) {
	    JsonArray values = new JsonArray();
	    for( int i = 0; i < this.items.length; i++ ) {
	      DataItem item = this.items[ i ];
	      if( item instanceof DataItem2D ) {
	        DataItem2D item2d = ( DataItem2D )item;
	        values.add( new JsonObject().add( "x", ""+item2d.getX() ).add( "y", item2d.getY() ) );
	      } else {
	        values.add( new JsonObject().add( "x", i ).add( "y", item.getValue() ) );
	      }
	    }
	    JsonObject json = new JsonObject().add( "values", values );
	    if( this.text != null ) {
	      json.add( "key", this.text );
	    }
	    if( this.color != null ) {
	      json.add( "color", toHtmlString( this.color ) );
	    }
	    
	    return json;
	  }

}
