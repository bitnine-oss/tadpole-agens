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
package org.eclipse.rap.addons.chart.internal;

import static org.eclipse.rap.rwt.RWT.getResourceManager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.service.ResourceLoader;
import org.eclipse.rap.rwt.service.ResourceManager;


public class Resources {

  private final Map<String, String> locations = new HashMap<>();

  public String register( String resourceName, String registerPath, ResourceLoader resourceLoader ) {
    ResourceManager resourceManager = getResourceManager();
    String location = locations.get( registerPath );
    if( location == null ) {
      try( InputStream inputStream = resourceLoader.getResourceAsStream( resourceName ) ) {
        resourceManager.register( registerPath, inputStream );
      } catch( Exception exception ) {
        throw new RuntimeException( "Failed to register resource " + registerPath, exception );
      }
    }
    location = resourceManager.getLocation( registerPath );
    locations.put( registerPath, location );
    return location;
  }

}
