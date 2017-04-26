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
package org.eclipse.rap.addons.chart;

import static org.eclipse.rap.addons.chart.internal.ColorUtil.toJson;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.theme.BoxDimensions;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;


/**
 * A chart widget based on <a href="http://nvd3.org/">nvd3.js</a>.
 * <p>
 * Be default, the nvd3 JS library and CSS (<code>nv.d3.min.js</code> and
 * <code>nv.d3.min.css</code>) is loaded from a CDN. To change the URLs, you can set the system
 * properties <em>org.eclipse.rap.addons.chart.nvd3JsUrl</em> and
 * <em>org.eclipse.rap.addons.chart.nvd3CssUrl</em> to custom URLs.
 * </p>
 * <p>
 * Subclasses must provide a client implementation and refer to it using a renderer id in the
 * constructor. Have a look at the existing implementations of this class.
 * </p>
 *
 * @see "http://nvd3.org/"
 */
public abstract class NvChart extends Chart {

  private static final String PROP_NVD3_JS_URL = "org.eclipse.rap.addons.chart.nvd3JsUrl";
  private static final String PROP_NVD3_CSS_URL = "org.eclipse.rap.addons.chart.nvd3CssUrl";
  private static final String DEF_NVD3_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.js";
  private static final String DEF_NVD3_CSS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.css";

  private String noData;
  private boolean showLegend = true;
  private boolean tooltipsEnabled = true;
  private RGB[] colors;
  private BoxDimensions margin;

  /**
   * Create a chart instance that is implemented by the given <code>renderer</code>.
   */
  protected NvChart( Composite parent, int style, String renderer ) {
    super( parent, style, renderer );
    requireJs( System.getProperty( PROP_NVD3_JS_URL, DEF_NVD3_JS_URL ) );
    requireCss( System.getProperty( PROP_NVD3_CSS_URL, DEF_NVD3_CSS_URL ) );
    requireCss( registerResource( "resources/nv-chart.css" ) );
  }

  /**
   * Message to display if no data items are provided. Default text is
   * <code>No Data Available.</code>.
   *
   * @param text the text to display when no data is available
   */
  public void setNoDataMessage( String text ) {
    checkWidget();
    if( text != null && !text.equals( noData ) ) {
      noData = text;
      setOption( "noData", text );
    }
  }

  /**
   * Returns the text to display when no data is available.
   *
   * @return the text to display when no data is available
   */
  public String getNoDataMessage() {
    checkWidget();
    return noData;
  }

  /**
   * Whether to display the chart legend or not. Default is <code>true</code>.
   *
   * @param show <code>true</code> if legend should be shown
   */
  public void setShowLegend( boolean show ) {
    checkWidget();
    if( showLegend != show ) {
      showLegend = show;
      setOption( "showLegend", show );
    }
  }

  /**
   * Returns whether legend is shown for chart.
   *
   * @return <code>true</code> if legend is shown
   */
  public boolean getShowLegend() {
    checkWidget();
    return showLegend;
  }

  /**
   * Whether tooltips are enabled or not. Default is <code>true</code>.
   *
   * @param enabled <code>true</code> if tooltips are enabled
   */
  public void setTooltipsEnabled( boolean enabled ) {
    checkWidget();
    if( tooltipsEnabled != enabled ) {
      tooltipsEnabled = enabled;
      setOption( "tooltip.enabled", enabled );
    }
  }

  /**
   * Returns whether tooltips are enabled for the chart.
   *
   * @return <code>true</code> if tooltips are enabled
   */
  public boolean isTooltipsEnabled() {
    checkWidget();
    return tooltipsEnabled;
  }

  /**
   * Sets the colors to be used by the chart. This can be used alternatively to specifying the
   * colors individually for every <code>DataItem</code>. Default colors used by nvd3 is
   * category20.
   *
   * @param colors the colors as array of <code>RGB</code> values
   *
   * @see "https://github.com/mbostock/d3/wiki/Ordinal-Scales#categorical-colors"
   */
  public void setColors( RGB[] colors ) {
    checkWidget();
    if( colors != null && !colors.equals( this.colors ) ) {
      this.colors = colors;
      setOption( "colorScale", toJson( colors ) );
    }
  }

  /**
   * Returns the colors used by the chart.
   *
   * @return the colors, or null if nvd3 defaults applies
   */
  public RGB[] getColors() {
    checkWidget();
    return colors;
  }

  /**
   * Defines the margins for the chart component. Default is <code>0</code> for all margins.
   *
   * @param margin object with margins of chart
   */
  public void setMargin( BoxDimensions margin ) {
    checkWidget();
    if( margin != null && !margin.equals( this.margin ) ) {
      this.margin = margin;
      JsonObject marginJson = new JsonObject()
        .add( "top", margin.top )
        .add( "right", margin.right)
        .add( "bottom", margin.bottom )
        .add( "left", margin.left);
      setOption( "margin", marginJson );
    }
  }

  /**
   * Returns the margins of the chart.
   *
   * @return the margins of the chart
   */
  public BoxDimensions getMargin() {
    checkWidget();
    return margin;
  }

}
