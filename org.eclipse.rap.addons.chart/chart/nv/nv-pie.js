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

rwt.chart.register( "nv-pie", function( widget ) {
  var chart = nv.models.pieChart()
    .x( function( d ) { return d.label; })
    .y( function( d ) { return d.value; })
    .showLabels( true );
  chart.pie.dispatch.on( "elementClick.rap", function( item ) {
    widget.notifySelection( item.index );
  });
  chart.colorScale = function( value ) {
    return chart.color( d3.scale.ordinal().range( value ).range() );
  };
  return chart;
});
