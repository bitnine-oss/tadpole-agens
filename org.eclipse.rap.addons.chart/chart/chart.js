/*******************************************************************************
 * Copyright (c) 2013, 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 ******************************************************************************/

(function() {

rwt.chart = {};

rwt.chart.Chart = function( parent, renderer ) {
  this._data = [];
  this._renderer = renderer( this );
  this._element = this.createElement( parent );
  this._svg = d3.select( this._element ).append( "svg" ).attr( "class", "chart" );
  rap.on( "render", function() {
    if( this._needsRender ) {
      this._svg.datum( this._data );
      this._renderer( this._svg, this );
      this._needsRender = false;
    }
  }.bind( this ) );
  parent.addListener( "Resize", function() {
    this._resize( parent.getClientArea() );
  }.bind( this) );
  this._resize( parent.getClientArea() );
};

rwt.chart.Chart.prototype = {

  setOptions: function( options ) {
    for( var name in options ) {
      this.setOption( name, options[name] );
    }
  },

  setOption: function( name, value ) {
    var obj = this._renderer;
    name.split( "." ).forEach( function( part ) {
      if( !( part in obj ) ) {
        return;
      }
      obj = obj[part];
    });
    if( typeof obj === "function" && obj !== this._renderer ) {
      obj( value );
      this._scheduleUpdate();
    }
  },

  setItems: function( data ) {
    this._data = data;
    this._scheduleUpdate();
  },

  createElement: function( parent ) {
    var element = document.createElement( "div" );
    element.style.position = "absolute";
    element.style.left = "0";
    element.style.top = "0";
    element.style.width = "100%";
    element.style.height = "100%";
    parent.append( element );
    return element;
  },

  notifySelection: function( index, detail, text ) {
    var remoteObject = rap.getRemoteObject( this );
    var params = { "index": index };
    if( arguments.length > 1 ) {
      params.detail = detail;
    }
    if( arguments.length > 2 ) {
      params.text = text;
    }
    remoteObject.notify( "Selection", params );
  },

  _resize: function( clientArea ) {
    var width = clientArea[ 2 ];
    var height = clientArea[ 3 ];
    this._renderer.width( width ).height( height );
    this._svg.attr( "width", width ).attr( "height", height );
    this._scheduleUpdate();
  },

  _scheduleUpdate: function() {
    this._needsRender = true;
  },

  destroy: function() {
    var element = this._element;
    if( element.parentNode ) {
      element.parentNode.removeChild( element );
    }
  }

};

// UTILITIES

rwt.chart._renderers = {};

rwt.chart.register = function( name, generator ) {
  if( name in rwt.chart._renderers ) {
    throw new Error( "Chart type already registered: " + name );
  }
  rwt.chart._renderers[name] = generator;
};

rwt.chart.addConfigOptions = function( target, config ) {
  for( var prop in config ) {
    addOption( target, config, prop );
  }
};

function addOption( target, config, prop ) {
  target[prop] = function( value ) {
    if( !arguments.length ) {
      return config[prop];
    }
    config[prop] = value;
    return target;
  };
}

rwt.chart.loadCss = function( url ) {
  var element = document.createElement( "link" );
  element.rel = "stylesheet";
  element.type = "text/css";
  element.media = "screen";
  element.href = url;
  document.getElementsByTagName( "head" )[0].appendChild( element );
};

// TYPE HANDLER

rap.registerTypeHandler( "rwt.chart.Chart", {

  factory: function( properties ) {
    var parent = rap.getObject( properties.parent );
    var renderer = rwt.chart._renderers[properties.renderer];
    return new rwt.chart.Chart( parent, renderer );
  },

  destructor: "destroy",

  properties: [ "items" ],

  methods : [ "setOptions" ],

  events: [ "Selection" ]

});

})();
