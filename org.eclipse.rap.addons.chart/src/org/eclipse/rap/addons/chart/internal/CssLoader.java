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
package org.eclipse.rap.addons.chart.internal;

import static org.eclipse.rap.rwt.RWT.getClient;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.rap.rwt.client.service.JavaScriptExecutor;


public class CssLoader {

  private final Set<String> loadedUrls = new HashSet<>();

  public void requireCss( String location ) {
    if( !loadedUrls.contains( location ) ) {
      loadedUrls.add( location );
      JavaScriptExecutor executor = getClient().getService( JavaScriptExecutor.class );
      executor.execute( "rwt.chart.loadCss('" + location + "');" );
    }
  }

}
