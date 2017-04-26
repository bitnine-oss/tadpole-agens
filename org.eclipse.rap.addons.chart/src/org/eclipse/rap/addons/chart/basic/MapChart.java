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
import static org.eclipse.rap.rwt.internal.util.ParamCheck.notNullOrEmpty;

import org.eclipse.rap.addons.chart.Chart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic world map chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
@SuppressWarnings( "restriction" )
public class MapChart extends Chart {

  private static final String PROP_TOPOJSON_JS_URL = "org.eclipse.rap.addons.chart.topojsonJsUrl";
  private static final String DEF_TOPOJSON_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/topojson/1.6.20/topojson.min.js";

  private boolean showGraticule;
  private double scaleFactor = 1d;
  private double longitude;
  private double latitude;

  /**
   * Creates a new empty WorldMap chart.
   *
   * @param parent a composite control which will be the parent of the new instance (cannot be null)
   * @param style the style of control to construct
   * @param path a resource path to registered topology geo data file (cannot be null or empty)
   *
   */
  public MapChart( Composite parent, int style, String path ) {
    super( parent, style, "topojson-world" );
    notNullOrEmpty( path, "path" );
    requireJs( System.getProperty( PROP_TOPOJSON_JS_URL, DEF_TOPOJSON_JS_URL ) );
    requireJs( registerResource( "chart/topojson/topojson-world.js" ) );
    requireCss( registerResource( "resources/topojson-world.css" ) );
    setOption( "dataPath", path );
  }

  /**
   * Set array of colors, which will be used to fill the countries with.
   *
   * @param colors The array with country fill colors.
   */
  public void setColors( RGB[] colors ) {
    checkWidget();
    JsonArray json = new JsonArray();
    for( RGB color : colors ) {
      json.add( toHtmlString( color ) );
    }
    setOption( "colors", json );
  }

  /**
   * Set whether graticule should be displayed or not. The default is <code>false</code>.
   *
   * @param show <code>true</code> to display graticule.
   */
  public void setShowGraticule( boolean show ) {
    checkWidget();
    if( show != showGraticule ) {
      showGraticule = show;
      setOption( "showGraticule", show );
    }
  }

  /**
   * Returns whether graticule is displayed.
   *
   * @return <code>true</code> if graticule is displayed.
   */
  public boolean getShowGraticule() {
    checkWidget();
    return showGraticule;
  }

  /**
   * Set map scale factor. The default is 1.
   *
   * @param scaleFactor map scale factor.
   */
  public void setScaleFactor( double scaleFactor ) {
    checkWidget();
    if( scaleFactor <= 0 ) {
      SWT.error( SWT.ERROR_INVALID_ARGUMENT );
    }
    if( scaleFactor != this.scaleFactor ) {
      this.scaleFactor = scaleFactor;
      setOption( "scaleFactor", scaleFactor );
    }
  }

  /**
   * Returns map scale factor.
   *
   * @return the scale factor.
   */
  public double getScaleFactor() {
    checkWidget();
    return scaleFactor;
  }

  /**
   * Set map projection's center to the specified location.
   *
   * @param longitude location longitude in degrees.
   * @param latitude location latitude in degrees.
   */
  public void setCenter( double longitude, double latitude ) {
    checkWidget();
    if( longitude != this.longitude || latitude != this.latitude ) {
      this.longitude = longitude;
      this.latitude = latitude;
      setOption( "center", new JsonArray().add( longitude ).add( latitude ) );
    }
  }

  /**
   * Returns map projection's center as two-element array of longitude and latitude in degrees.
   *
   * @return the map projection's center.
   */
  public double[] getCenter() {
    checkWidget();
    return new double[] { longitude, latitude };
  }

  /**
   * Sets the map data items to display. Later changes to this list won't be reflected. To change
   * the chart data, call this method with a new list of items.
   *
   * @param items the map data items to display
   */
  public void setItems( MapDataItem... items ) {
    JsonObject values = new JsonObject();
    for( MapDataItem item : items ) {
      values.add( item.getCountry(), toJson( item ) );
    }
    setOption( "countries", values );
  }

  private static JsonObject toJson( MapDataItem item ) {
    JsonObject json = new JsonObject();
    if( item.text != null ) {
      json.add( "label", item.text );
    }
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }

}
