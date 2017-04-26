/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

/*global topojson:false */

rwt.chart.register( "topojson-world", function( widget ) {

  var _dataPath;
  var _width;
  var _height;
  var _world;
  var _path;
  var _showGraticule;
  var _toolTip;
  var _scaleFactor = 1;
  var _center = [ 0, 0 ];
  var _countries = {};
  var _colors = [];

  var chart = function( svg, widget ) {
    if( !_world ) {
      if( _dataPath ) {
        createToolTip( widget._element );
        load( svg );
      }
    } else {
      update( svg );
    }
  };

  chart.dataPath = function( path ) {
    _dataPath = path;
    return chart;
  };

  chart.width = function( width ) {
    _width = width;
    return chart;
  };

  chart.height = function( height ) {
    _height = height;
    return chart;
  };

  chart.countries = function( countries ) {
    _countries = countries;
    return chart;
  };

  chart.colors = function( colors ) {
    _colors = colors;
    return chart;
  };

  chart.showGraticule = function( show ) {
    _showGraticule = show;
    return chart;
  };

  chart.scaleFactor = function( factor ) {
    _scaleFactor = factor;
    return chart;
  };

  chart.center = function( center ) {
    _center = center;
    return chart;
  };

  function createToolTip( parentElement ) {
    if( !_toolTip ) {
      _toolTip = d3.select( parentElement ).append( "div" ).attr( "class", "tooltip hidden" );
    }
  }

  function load( svg ) {
    d3.json( _dataPath, function( error, world ) {
      if( error ) {
        throw error;
      }
      _world = world;
      update( svg );
    });
  }

  function update( svg ) {
    var countries = topojson.feature( _world, _world.objects.countries );
    calculateColorIndices( countries.features );
    updateProjection( countries );
    updateGraticule( svg );
    svg.selectAll( ".country" )
      .data( countries.features )
      .enter()
      .insert( "path", ".graticule" )
      .attr( "class", "country" )
      .attr( "id", function( d ) { return d.id; } )
      .on( "click", function( d ) {
        widget.notifySelection( d.id, 0, d.properties.code3 );
      })
      .on( "mouseover", function( d ) {
        if( getCountryData( d ) ) {
          var text = rwt.util.Encoding.escapeText( getCountryData( d ).label );
          text = rwt.util.Encoding.replaceNewLines( text, "<br/>" );
          _toolTip.classed( "hidden", false ).html( text );
        }
      })
      .on( "mouseout", function() {
        _toolTip.classed( "hidden", true );
      })
      .on( "mousemove", function( d ) {
        if( getCountryData( d ) ) {
          var mouse = d3.mouse( svg.node() ).map( function( d ) { return parseInt( d ); } );
          var left = mouse[ 0 ] + 10;
          var top = mouse[ 1 ] + 15;
          _toolTip.attr( "style", "left:" + left + "px;top:" + top + "px;" );
        }
      });
    svg.selectAll( ".country" )
      .attr( "d", _path )
      .style( "fill", fill );
  }

  function updateProjection( countries ) {
    var scale = 150;
    var offset = [ _width / 2, _height / 2 ];
    var projection = d3.geo.equirectangular()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    _path = d3.geo.path().projection( projection );
    var bounds  = _path.bounds( countries );
    var hscale  = scale * _width  / ( bounds[ 1 ][ 0 ] - bounds[ 0 ][ 0 ] );
    var vscale  = scale * _height / ( bounds[ 1 ][ 1 ] - bounds[ 0 ][ 1 ] );
    scale = ( hscale < vscale ) ? hscale : vscale;
    projection = d3.geo.equirectangular()
      .scale( scale * _scaleFactor )
      .translate( offset )
      .precision( 0.1 )
      .center( _center );
    _path = _path.projection( projection );
  }

  function updateGraticule( svg ) {
    if( _showGraticule ) {
      if( svg.selectAll( ".graticule" ).size() === 0 ) {
        var graticule = d3.geo.graticule();
        svg.append( "path" )
          .datum( graticule )
          .attr( "class", "graticule" );
        svg.append( "path" )
          .datum( graticule.outline )
          .attr( "class", "graticule outline" );
      }
      svg.selectAll( ".graticule" ).attr( "d", _path );
    } else {
      svg.selectAll( ".graticule" ).remove();
    }
  }

  function fill( d ) {
    if( getCountryData( d ) ) {
      return getCountryData( d ).color;
    }
    return _colors[ d.col % _colors.length ];
  }

  function calculateColorIndices( countries ) {
    var neighbors = topojson.neighbors( _world.objects.countries.geometries );
    countries.forEach( function( d, i ) {
      var getCol = function( n ) {
        return countries[ n ].col;
      };
      d.col = d3.max( neighbors[ i ], getCol ) + 1 | 0;
    });
  }

  function getCountryData( d ) {
    var props = d.properties;
    return _countries[ d.id ] || _countries[ props.code2 ] || _countries[ props.code3 ];
  }

  widget._scheduleUpdate();

  return chart;

});
