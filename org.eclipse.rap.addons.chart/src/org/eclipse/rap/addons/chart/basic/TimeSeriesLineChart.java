/*******************************************************************************
 * Copyright (c) 2015, 2016 EclipseSource and others.
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

import org.eclipse.rap.addons.chart.NvChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic line chart widget that supports multiple lines.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class TimeSeriesLineChart extends NvChart {

  private String xAxisLabel = "";
  private String yAxisLabel = "";
  private String xAxisFormat = "d";
  private String yAxisFormat = "d";

  private boolean interactive = true;
  private String interpolate = "linear";
  private boolean area;
  private boolean useInteractiveGuideline;
  private boolean useVoronoi = true;
  private boolean padData = true;

  /**
   * Creates a new empty LineChart.
   */
  public TimeSeriesLineChart( Composite parent, int style ) {
   super( parent, style, "nv-line" );
    requireJs( registerResource( "chart/nv/nv-line.js" ) );
  }

  /**
   * Sets the label to display on the x-axis. Default is empty.
   *
   * @param label the label for the x-axis
   */
  public void setXAxisLabel( String label ) {
    checkWidget();
    if( label != null && !label.equals( xAxisLabel ) ) {
      xAxisLabel = label;
      setOption( "xAxis.axisLabel", label );
    }
  }

  /**
   * Returns the label for the x-axis.
   *
   * @return the label for the x-axis
   */
  public String getXAxisLabel() {
    checkWidget();
    return xAxisLabel;
  }

  /**
   * Sets the label to display on the y-axis. Default is empty.
   *
   * @param label the label for the y-axis
   */
  public void setYAxisLabel( String label ) {
    checkWidget();
    if( label != null && !label.equals( yAxisLabel ) ) {
      yAxisLabel = label;
      setOption( "yAxis.axisLabel", label );
    }
  }

  /**
   * Returns the label for the y-axis.
   *
   * @return the label for the y-axis
   */
  public String getYAxisLabel() {
    checkWidget();
    return yAxisLabel;
  }

  /**
   * Sets the format for the labels on the x-axis. The format string must be recognizable by the
   * <a href="https://github.com/mbostock/d3/wiki/Formatting#d3_format">d3.format()</a> function.
   * Default is <code>d</code>.
   *
   * @see "https://github.com/mbostock/d3/wiki/Formatting#d3_format"
   * @param format a d3 format string for the labels of the x-axis
   */
  public void setXAxisFormat( String format ) {
    checkWidget();
    if( format != null && !format.equals( xAxisFormat ) ) {
      xAxisFormat = format;
      setOption( "xAxisFormat", format );
    }
  }
 
  /**
   * tick values
   * 
   * @param format
   */
  public void tickValues(String format) {
	  checkWidget();
	    if( format != null) {
	      setOption( "xAxis.tickValues", format );
	    }
  }

  /**
   * ticket format
   * 
   * @param format
   * @author hangum 
   */
  public void tickFormat(String format) {
	  checkWidget();
	    if( format != null ) {
	      setOption( "xAxis.tickFormat", format );
	    } 
  }

  /**
   * Returns the format used for labels on the x-axis.
   *
   * @return the format for the labels on the x-axis
   */
  public String getXAxisFormat() {
    checkWidget();
    return xAxisFormat;
  }

  /**
   * Sets the format for the labels on the y-axis. The format string must be recognizable by the
   * <a href="https://github.com/mbostock/d3/wiki/Formatting#d3_format">d3.format()</a> function.
   * Default is <code>d</code>.
   *
   * @see "https://github.com/mbostock/d3/wiki/Formatting#d3_format"
   * @param format a d3 format string for the labels of the y-axis
   */
  public void setYAxisFormat( String format ) {
    checkWidget();
    if( format != null && !format.equals( yAxisFormat ) ) {
      yAxisFormat = format;
      setOption( "yAxisFormat", format );
    }
  }

  /**
   * Returns the format used for labels on the y-axis.
   *
   * @return the format for the labels on the y-axis
   */
  public String getYAxisFormat() {
    checkWidget();
    return yAxisFormat;
  }

  /**
   * A master flag for turning chart interaction on and off. This overrides all tooltip, voronoi,
   * and guideline options. Default is <code>true</code>.
   *
   * @param interactive whether chart interaction is turned on
   */
  public void setInteractive( boolean interactive ) {
    checkWidget();
    if( this.interactive != interactive ) {
      this.interactive = interactive;
      setOption( "interactive", interactive );
    }
  }

  /**
   * Returns whether chart interaction is turned on.
   *
   * @return <code>true</code> if chart interaction is turned on
   */
  public boolean isInteractive() {
    checkWidget();
    return this.interactive;
  }

  /**
   * Controls the line interpolation between points, see the
   * <a href="https://github.com/mbostock/d3/wiki/SVG-Shapes#line_interpolate">D3 reference</a> for
   * valid options. Default is <code>linear</code>.
   *
   * @see "https://github.com/mbostock/d3/wiki/SVG-Shapes#line_interpolate"
   * @param interpolate the line interpolation function
   */
  public void setInterpolate( String interpolate ) {
    checkWidget();
    if( interpolate != null && !interpolate.equals( this.interpolate ) ) {
      this.interpolate = interpolate;
      setOption( "interpolate", interpolate );
    }
  }

  /**
   * Returns the line interpolation function.
   *
   * @return the line interpolation function
   */
  public String getInterpolate() {
    checkWidget();
    return interpolate;
  }

  /**
   * Function to define if a line is a normal line or if it fills in the area. Notice the default
   * gets the value from the line's definition in data. If a non-function is given, it the value is
   * used for all lines. Default is <code>false</code>.
   *
   * @param area whether area below lines is filled.
   */
  public void setArea( boolean area ) {
    checkWidget();
    if( this.area != area ) {
      this.area = area;
      setOption( "isArea", area );
    }
  }

  /**
   * Returns whether area below lines is filled.
   *
   * @return <code>true</code> if area is filled below lines.
   */
  public boolean getArea() {
    checkWidget();
    return area;
  }

  /**
   * Sets the chart to use a guideline and floating tooltip instead of requiring the user to hover
   * over specific hotspots. Turning this on will set the 'interactive' and 'useVoronoi' options to
   * false to avoid conflicting. Default is <code>false</code>.
   *
   * @param useInteractiveGuideline whether to use interactive guidelines
   */
  public void setUseInteractiveGuideline( boolean useInteractiveGuideline ) {
    checkWidget();
    if( this.useInteractiveGuideline != useInteractiveGuideline ) {
      this.useInteractiveGuideline = useInteractiveGuideline;
      setOption( "useInteractiveGuideline", useInteractiveGuideline );
    }
  }

  /**
   * Returns whether guidelines are interactive.
   *
   * @return <code>true</code> if interactive guidelines are used
   */
  public boolean getUseInteractiveGuideline() {
    checkWidget();
    return useInteractiveGuideline;
  }

  /**
   * Use voronoi diagram to select nearest point to display tooltip instead of requiring a hover
   * over the specific point itself. Setting this to <code>false</code> will also set clipVoronoi to
   * <code>false</code>. Default is <code>true</code>.
   *
   * @param useVoronoi whether to determine nearest point for tooltips using voronoi diagram
   */
  public void setUseVoronoi( boolean useVoronoi ) {
    checkWidget();
    if( this.useVoronoi != useVoronoi ) {
      this.useVoronoi = useVoronoi;
      setOption( "useVoronoi", useVoronoi );
    }
  }

  /**
   * Returns whether to determine nearest point for tooltips using voronoi diagram.
   *
   * @return <code>true</code> if voronoi diagram is used to determine nearest point for tooltips
   */
  public boolean getUseVoronoi() {
    checkWidget();
    return useVoronoi;
  }

  /**
   * Whether to pad the data. Default is <code>true</code>.
   *
   * @param padData <code>true</code> if data is padded.
   */
  public void setPadData( boolean padData ) {
    checkWidget();
    if( this.padData != padData ) {
      this.padData = padData;
      setOption( "padData", padData );
    }
  }

  /**
   * Returns whether to pad the data.
   *
   * @return <code>true</code> if data is padded
   */
  public boolean getPadData() {
    checkWidget();
    return padData;
  }

  /**
   * Sets the data items to display. Every item represents a separate line. Later changes to this
   * list won't be reflected. To change the chart data, call this method with a new list of items.
   *
   * @param items the data items to display
   */
  public void setItems( TimeDataGroup... items ) {
    JsonArray values = new JsonArray();
    for( TimeDataGroup item : items ) {
      values.add( toJson( item ) );
    }
    
    setItems( values );
  }

  private static JsonObject toJson( TimeDataGroup group ) {
    JsonArray values = new JsonArray();
    for( int i = 0; i < group.items.length; i++ ) {
    	TimeDataItem2D item = group.items[ i ];
      if( item instanceof TimeDataItem2D ) {
    	  TimeDataItem2D item2d = ( TimeDataItem2D )item;
        values.add( new JsonObject().add( "x", ""+item2d.getX() ).add( "y", item2d.getY() ) );
//      } else {
//        values.add( new JsonObject().add( "x", i ).add( "y", item.getValue() ) );
      }
    }
    JsonObject json = new JsonObject().add( "values", values );
    if( group.text != null ) {
      json.add( "key", group.text );
    }
    if( group.color != null ) {
      json.add( "color", toHtmlString( group.color ) );
    }
    
    return json;
  }

}
