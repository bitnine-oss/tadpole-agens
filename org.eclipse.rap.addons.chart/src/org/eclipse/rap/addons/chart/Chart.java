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
package org.eclipse.rap.addons.chart;

import static org.eclipse.rap.json.JsonValue.valueOf;
import static org.eclipse.rap.rwt.RWT.getClient;
import static org.eclipse.rap.rwt.SingletonUtil.getUniqueInstance;
import static org.eclipse.rap.rwt.widgets.WidgetUtil.getId;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.addons.chart.internal.CssLoader;
import org.eclipse.rap.addons.chart.internal.Resources;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.remote.AbstractOperationHandler;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.service.ResourceLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * An extensible Chart widget based on <a href="http://d3js.org/">d3.js</a>.
 * <p>
 * Be default, the d3 library (<code>d3.v3.min.js</code>) is loaded from a CDN. To change the URL,
 * you can set the system property <em>org.eclipse.rap.addons.chart.d3JsUrl</em> to a custom URL.
 * </p>
 * <p>
 * Subclasses must provide a client implementation and refer to it using a renderer id in the
 * constructor. Have a look at the existing implementations of this class.
 * </p>
 * @see "http://d3js.org/"
 */
@SuppressWarnings( "deprecation" ) // RAP 3.0 compatibility
public abstract class Chart extends Canvas {

  private static final String PROP_D3_JS_URL = "org.eclipse.rap.addons.chart.d3JsUrl";
  private static final String DEF_D3_JS_URL = "https://d3js.org/d3.v3.min.js";
  private static final String REMOTE_TYPE = "rwt.chart.Chart";

  private Resources resources;
  private CssLoader cssLoader;

  protected final RemoteObject remoteObject;

  /**
   * Create a chart instance that is implemented by the given <code>renderer</code>.
   */
  protected Chart( Composite parent, int style, String renderer ) {
    super( parent, style );
    remoteObject = RWT.getUISession().getConnection().createRemoteObject( REMOTE_TYPE );
    remoteObject.set( "parent", getId( this ) );
    remoteObject.set( "renderer", renderer );
    remoteObject.setHandler( new AbstractOperationHandler() {
      @Override
      public void handleNotify( String eventName, JsonObject properties ) {
        if( "Selection".equals( eventName ) ) {
          Event event = new Event();
          event.index = properties.get( "index" ).asInt();
          JsonValue detail = properties.get( "detail" );
          if( detail != null ) {
            event.detail = detail.asInt();
          }
          JsonValue text = properties.get( "text" );
          if( text != null ) {
            event.text = text.asString();
          }
          notifyListeners( SWT.Selection, event );
        }
      }
    } );
    resources = getUniqueInstance( Resources.class, RWT.getApplicationContext() );
    cssLoader = getUniqueInstance( CssLoader.class, RWT.getUISession() );
    requireJs( System.getProperty( PROP_D3_JS_URL, DEF_D3_JS_URL ) );
    requireJs( registerResource( "chart/chart.js" ) );
  }

  /**
   * Set the data items. This will usually be a {@link JsonArray}.
   *
   * @param data the data
   */
  protected void setItems( JsonValue data ) {
    checkWidget();
    remoteObject.set( "items", data );
  }

  /**
   * Sets an option of the client-side chart. If a function of the given name exists on the
   * client-side chart object, it will be called with the given value as parameter. By using a
   * dot-separated name, a function on a sub-object can be called.
   * <p>
   * Subclasses should call this method when the value of this option actually changes.
   * </p>
   *
   * @param name the name of the option, may be separated by dots to refer to a sub-object
   * @param value the value of the option
   */
  protected void setOption( String name, int value ) {
    setOption( name, valueOf( value ) );
  }

  /**
   * Sets an option of the client-side chart. If a function of the given name exists on the
   * client-side chart object, it will be called with the given value as parameter. By using a
   * dot-separated name, a function on a sub-object can be called.
   * <p>
   * Subclasses should call this method when the value of this option actually changes.
   * </p>
   *
   * @param name the name of the option, may be separated by dots to refer to a sub-object
   * @param value the value of the option
   */
  protected void setOption( String name, double value ) {
    setOption( name, valueOf( value ) );
  }

  /**
   * Sets an option of the client-side chart. If a function of the given name exists on the
   * client-side chart object, it will be called with the given value as parameter. By using a
   * dot-separated name, a function on a sub-object can be called.
   * <p>
   * Subclasses should call this method when the value of this option actually changes.
   * </p>
   *
   * @param name the name of the option, may be separated by dots to refer to a sub-object
   * @param value the value of the option
   */
  protected void setOption( String name, boolean value ) {
    setOption( name, valueOf( value ) );
  }

  /**
   * Sets an option of the client-side chart. If a function of the given name exists on the
   * client-side chart object, it will be called with the given value as parameter. By using a
   * dot-separated name, a function on a sub-object can be called.
   * <p>
   * Subclasses should call this method when the value of this option actually changes.
   * </p>
   *
   * @param name the name of the option, may be separated by dots to refer to a sub-object
   * @param value the value of the option
   */
  protected void setOption( String name, String value ) {
    setOption( name, valueOf( value ) );
  }

  /**
   * Sets an option of the client-side chart. If a function of the given name exists on the
   * client-side chart object, it will be called with the given value as parameter. By using a
   * dot-separated name, a function on a sub-object can be called.
   * <p>
   * Subclasses should call this method when the value of this option actually changes.
   * </p>
   *
   * @param name the name of the option, may be separated by dots to refer to a sub-object
   * @param value the value of the option
   */
  protected void setOption( String name, JsonValue value ) {
    remoteObject.call( "setOptions", new JsonObject().add( name, value ) );
  }

  /**
   * Instruct the client to immediately load and evaluate a JavaScript file from the given URL. If
   * the file has already been loaded by the client, it won't be loaded again. You can use
   * {@link #registerResource(String)} to register the resource with the resource manager.
   *
   * @param url the URL from which to load the JavaScript file
   */
  protected void requireJs( String url ) {
    getClient().getService( JavaScriptLoader.class ).require( url );
  }

  /**
   * Instruct the client to immediately load and include CSS file from the given URL. If the file
   * has already been loaded by the client, it won't be loaded again. You can use
   * {@link #registerResource(String)} to register the resource with the resource manager.
   *
   * @param url the URL from which to load the CSS file
   */
  protected void requireCss( String url ) {
    cssLoader.requireCss( url );
  }

  /**
   * Loads a resource with the given name from the class loader of this class and registers it with
   * the resource manager if it was not registered already. If the resource has already been
   * registered, it won't be loaded again.
   *
   * @param url the resource name in the format supported by the class loader
   * @see ClassLoader#getResource(String)
   */
  protected String registerResource( String resourceName ) {
    return resources.register( resourceName, resourceName, getResourceLoader() );
  }

  private ResourceLoader getResourceLoader() {
    final ClassLoader classLoader = getClass().getClassLoader();
    return new ResourceLoader() {
      @Override
      public InputStream getResourceAsStream( String resourceName ) throws IOException {
        return classLoader.getResourceAsStream( resourceName );
      }
    };
  }

  @Override
  public void dispose() {
    super.dispose();
    remoteObject.destroy();
  }

  @Override
  public void addListener( int eventType, Listener listener ) {
    boolean wasListening = isListening( SWT.Selection );
    super.addListener( eventType, listener );
    if( eventType == SWT.Selection && !wasListening ) {
      remoteObject.listen( "Selection", true );
    }
  }

  @Override
  public void removeListener( int eventType, Listener listener ) {
    boolean wasListening = isListening( SWT.Selection );
    super.removeListener( eventType, listener );
    if( eventType == SWT.Selection && wasListening && !isListening( SWT.Selection ) ) {
      remoteObject.listen( "Selection", false );
    }
  }

}
