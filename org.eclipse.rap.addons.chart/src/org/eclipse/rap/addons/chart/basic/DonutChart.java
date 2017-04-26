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

import org.eclipse.swt.widgets.Composite;

/**
 * Donut chart control based on nv.models.pieChart of nvd3 library.
 */
public class DonutChart extends PieChart {

  private double cornerRadius = 0d;
  private double donutRatio = 0.5d;
  private double padAngle = 0d;
  private String title = "";

  /**
   * Constructor for a donut chart.
   *
   * @param parent a composite control which will be the parent of the new instance (cannot be null)
   * @param style the style of control to construct
   */
  public DonutChart( Composite parent, int style ) {
    super( parent, style );
    setOption( "donut", true );
  }

  /**
   * The corner radius of the slices. Typically used together with padAngle. Default is 0.
   *
   * @param cornerRadius the corner radius
   */
  public void setCornerRadius( double cornerRadius ) {
    checkWidget();
    if( this.cornerRadius != cornerRadius ) {
      this.cornerRadius = cornerRadius;
      setOption( "cornerRadius", cornerRadius );
    }
  }

  /**
   * Returns the cornerRadius of the donut slices.
   *
   * @return the cornerRadius
   */
  public double getCornerRadius() {
    checkWidget();
    return cornerRadius;
  }

  /**
   * Percent of pie radius to cut out of the middle to make the donut. It is multiplied by the outer
   * radius to calculate the inner radius, thus it should be between 0 and 1. Default is 0.5.
   *
   * @param donutRatio the ratio of the cut-out middle
   */
  public void setDonutRatio( double donutRatio ) {
    checkWidget();
    if( this.donutRatio != donutRatio ) {
      this.donutRatio = donutRatio;
      setOption( "donutRatio", donutRatio );
    }
  }

  /**
   * Returns the donut ratio.
   *
   * @return the donut ratio
   */
  public double getDonutRatio() {
    checkWidget();
    return donutRatio;
  }

  /**
   * Defines the spacing between slices as percentage of the chart. Default is 0.
   *
   * @param padAngle the padding angle between slices
   */
  public void setPadAngle( double padAngle ) {
    checkWidget();
    if( this.padAngle != padAngle ) {
      this.padAngle = padAngle;
      setOption( "padAngle", padAngle );
    }
  }

  /**
   * Returns the padding angle between slices.
   *
   * @return the padding angle between slices
   */
  public double getPadAngle() {
    checkWidget();
    return padAngle;
  }

  /**
   * Text to include within the middle of the donut chart. Default is empty.
   *
   * @param title the donut title
   */
  public void setTitle( String title ) {
    checkWidget();
    if( title != null && !title.equals( this.title ) ) {
      this.title = title;
      setOption( "title", title );
    }
  }

  /**
   * Returns the donut title.
   *
   * @return the title
   */
  public String getTitle() {
    checkWidget();
    return title;
  }

}
