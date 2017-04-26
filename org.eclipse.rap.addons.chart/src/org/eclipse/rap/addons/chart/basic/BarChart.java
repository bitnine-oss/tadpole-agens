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

import org.eclipse.rap.addons.chart.NvChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic bar chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class BarChart extends NvChart {

  private boolean showValues = true;
  private boolean showXAxis = true;
  private boolean showYAxis = true;
  private String xAxisLabel = "";
  private String yAxisLabel = "";
  private boolean staggerLabels;

  /**
   * Creates a new empty BarChart.
   */
  public BarChart( Composite parent, int style ) {
    super( parent, style, "nv-bar" );
    requireJs( registerResource( "chart/nv/nv-bar.js" ) );
  }

  /**
   * Whether the y values should be displayed in the chart. Only recommended to use
   * if there aren't many bars. The default is <code>true</code>.
   *
   * @param show <code>true</code> to display y values
   */
  public void setShowValues( boolean show ) {
    checkWidget();
    if( show != showValues ) {
      showValues = show;
      setOption( "showValues", show );
    }
  }

  /**
   * Returns whether y values are being displayed in the chart.
   *
   * @return <code>true</code> if y values are displayed
   */
  public boolean getShowValues() {
    checkWidget();
    return showValues;
  }

  /**
   * Sets the X axis label. Default is empty.
   *
   * @param label the label text
   */
  public void setXAxisLabel( String label ) {
    checkWidget();
    if( label != null && !label.equals( xAxisLabel ) ) {
      xAxisLabel = label;
      setOption( "xAxis.axisLabel", label );
    }
  }

  /**
   * Returns the X axis label.
   *
   * @return the label for the X axis.
   */
  public String getXAxisLabel() {
    checkWidget();
    return xAxisLabel;
  }

  /**
   * Sets the Y axis label. Default is empty.
   *
   * @param label the label text
   */
  public void setYAxisLabel( String label ) {
    checkWidget();
    if( label != null && !label.equals( yAxisLabel ) ) {
      yAxisLabel = label;
      setOption( "yAxis.axisLabel", label );
    }
  }

  /**
   * Returns the X axis label.
   *
   * @return the label for the Y axis
   */
  public String getYAxisLabel() {
    checkWidget();
    return yAxisLabel;
  }

  /**
   * Display or hide the X axis. Default is <code>true</code>.
   *
   * @param show <code>true</code> to display X axis
   */
  public void setShowXAxis( boolean show ) {
    checkWidget();
    if( showXAxis != show ) {
      showXAxis = show;
      setOption( "showXAxis", show );
    }
  }

  /**
   * Returns whether X axis is displayed in chart.
   *
   * @return <code>true</code> if X axis is displayed.
   */
  public boolean getShowXAxis() {
    checkWidget();
    return showXAxis;
  }

  /**
   * Display or hide the Y axis. Default is <code>true</code>.
   *
   * @param show <code>true</code> to display Y axis
   */
  public void setShowYAxis( boolean show ) {
    checkWidget();
    if( showYAxis != show ) {
      showYAxis = show;
      setOption( "showYAxis", show );
    }
  }

  /**
   * Returns whether Y axis is displayed in chart.
   *
   * @return <code>true</code> if Y axis is displayed.
   */
  public boolean getShowYAxis() {
    checkWidget();
    return showYAxis;
  }

  /**
   * Makes the X labels stagger at different distances from the axis so they are less likely to
   * overlap. Default is <code>false</code>.
   *
   * @param staggerLabels <code>true</code> to display labels in staggered layout.
   */
  public void setStaggerLabels( boolean staggerLabels ) {
    checkWidget();
    if( this.staggerLabels != staggerLabels ) {
      this.staggerLabels = staggerLabels;
      setOption( "staggerLabels", staggerLabels );
    }
  }

  /**
   * Returns whether labels are displayed in staggered layout.
   *
   * @return <code>true</code> if labels are displayed in staggered layout.
   */
  public boolean getStaggerLabels() {
    checkWidget();
    return staggerLabels;
  }

  /**
   * Sets the data items to display. Later changes to this list won't be reflected. To change the
   * chart data, call this method with a new list of items.
   *
   * @param items the data items to display
   */
  public void setItems( DataItem... items ) {
    JsonArray values = new JsonArray();
    for( DataItem item : items ) {
      values.add( toJson( item ) );
    }
    setItems( new JsonArray().add( new JsonObject().add( "values", values ) ) );
  }

  private static JsonObject toJson( DataItem item ) {
    JsonObject json = new JsonObject().add( "value", item.value );
    if( item.text != null ) {
      json.add( "label", item.text );
    }
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }

}
