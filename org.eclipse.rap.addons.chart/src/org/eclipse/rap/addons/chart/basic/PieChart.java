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
 * A basic pie or donut chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class PieChart extends NvChart {

  private boolean showLabels = true;
  private boolean labelsOutside;
  private boolean growOnHover = true;
  private boolean labelSunbeamLayout;
  private double labelThreshold = 0.02d;
  private String labelType = "key";
  private String legendPosition = "top";

  /**
   * Creates a new empty Pie chart.
   */
  public PieChart( Composite parent, int style ) {
    super( parent, style, "nv-pie" );
    requireJs( registerResource( "chart/nv/nv-pie.js" ) );
  }

  /**
   * Set whether labels should be displayed for each segment. The default is <code>true</code>.
   *
   * @param show <code>true</code> to display labels for each segment
   */
  public void setShowLabels( boolean show ) {
    checkWidget();
    if( show != showLabels ) {
      showLabels = show;
      setOption( "showLabels", show );
    }
  }

  /**
   * Returns whether labels are displayed for each segment.
   *
   * @return <code>true</code> if labels are displayed for each segment.
   */
  public boolean getShowLabels() {
    checkWidget();
    return showLabels;
  }

  /**
   * Whether chart labels should be outside the slices instead of inside them. Default is
   * <code>true</code>.
   *
   * @param labelsOutside <code>true</code> to show labels outside of slices
   */
  public void setLabelsOutside( boolean labelsOutside ) {
    checkWidget();
    if( this.labelsOutside != labelsOutside ) {
      this.labelsOutside = labelsOutside;
      setOption( "labelsOutside", labelsOutside );
    }
  }

  /**
   * Returns whether labels are shown outside of slices.
   *
   * @return <code>true</code> if labels are shown outside of slices
   */
  public boolean getLabelsOutside() {
    checkWidget();
    return labelsOutside;
  }

  /**
   * Whether to increase slice radius on hover or not. Default is <code>true</code>.
   *
   * @param growOnHover <code>true</code> if slices should increase on hover
   */
  public void setGrowOnHover( boolean growOnHover ) {
    checkWidget();
    if( this.growOnHover != growOnHover ) {
      this.growOnHover = growOnHover;
      setOption( "growOnHover", growOnHover );
    }
  }

  /**
   * Returns whether to increase slice radius on hover.
   *
   * @return <code>true</code> if slices should increase on hover
   */
  public boolean getGrowOnHover() {
    checkWidget();
    return growOnHover;
  }

  /**
   * Whether item labels are drawn in sunbeam layout instead of horizontally. Default is
   * <code>false</code>.
   *
   * @param labelSunbeamLayout <code>true</code> if labels are drawn in sunbeam layout
   */
  public void setLabelSunbeamLayout( boolean labelSunbeamLayout ) {
    checkWidget();
    if( this.labelSunbeamLayout != labelSunbeamLayout ) {
      this.labelSunbeamLayout = labelSunbeamLayout;
      setOption( "labelSunbeamLayout", labelSunbeamLayout );
    }
  }

  /**
   * Returns whether labels are drawn in sunbeam layout or not.
   *
   * @return <code>true</code> if labels are drawn in sunbeam layout
   */
  public boolean getLabelSunbeamLayout() {
    checkWidget();
    return labelSunbeamLayout;
  }

  /**
   * The slice threshold size (as percentage of the chart) to not display the label because it would
   * be too small of a space. Default is 0.02, i.e. 2% of chart size.
   *
   * @param labelThreshold the threshold size
   */
  public void setLabelThreshold( double labelThreshold ) {
    checkWidget();
    if( this.labelThreshold != labelThreshold ) {
      this.labelThreshold = labelThreshold;
      setOption( "labelThreshold", labelThreshold );
    }
  }

  /**
   * Returns the slice threshold size for labels to be displayed.
   *
   * @return the label threshold size
   */
  public double getLabelThreshold() {
    checkWidget();
    return labelThreshold;
  }

  /**
   * What kind of data to display for the slice labels. Valid options are <code>key</code>,
   * <code>value</code>, or <code>percent</code>. Default is <code>key</code>.
   *
   * @param labelType the kind of data to be displayed as labels
   */
  public void setLabelType( String labelType ) {
    checkWidget();
    if( labelType != null && !labelType.equals( this.labelType ) ) {
      this.labelType = labelType;
      setOption( "labelType", labelType );
    }
  }

  /**
   * Returns the kind of data to be displayed as labels.
   *
   * @return the labelType the kind of data to be displayed as labels
   */
  public String getLabelType() {
    checkWidget();
    return labelType;
  }

  /**
   * Position of the chart legend. Valid options are <code>top</code> or <code>right</code>. Default
   * is <code>top</code>.
   *
   * @param legendPosition the position of the legend
   */
  public void setLegendPosition( String legendPosition ) {
    checkWidget();
    if( legendPosition != null && !legendPosition.equals( this.legendPosition ) ) {
      this.legendPosition = legendPosition;
      setOption( "legendPosition", legendPosition );
    }
  }

  /**
   * Returns the position of the chart legend.
   *
   * @return the position of the legend.
   */
  public String getLegendPosition() {
    checkWidget();
    return legendPosition;
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
    setItems( values );
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
