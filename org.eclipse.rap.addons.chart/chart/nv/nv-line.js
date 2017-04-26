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

rwt.chart.register( "nv-line", function( widget ) {
  var chart = nv.models.lineChart();
  chart.xAxis.tickFormat( d3.format( "d" ) );
  chart.yAxis.tickFormat( d3.format( "d" ) );
  chart.lines.dispatch.on( "elementClick", function( item ) {
    widget.notifySelection( item.seriesIndex, item.pointIndex );
  });
  chart.xAxisFormat = function( value ) {
    return chart.xAxis.tickFormat( d3.format( value ) );
  };
  chart.yAxisFormat = function( value ) {
    return chart.yAxis.tickFormat( d3.format( value ) );
  };
  chart.colorScale = function( value ) {
    return chart.color( d3.scale.ordinal().range( value ).range() );
  };
  return chart;
});
