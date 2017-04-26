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

import org.eclipse.rap.json.JsonArray;
import org.eclipse.swt.graphics.RGB;


public class ColorUtil {

  public static JsonArray toJson( RGB[] colors ) {
    JsonArray colorSequence = new JsonArray();
    for( RGB color : colors ) {
      colorSequence.add( toHtmlString( color ) );
    }
    return colorSequence;
  }

  public static String toHtmlString( RGB color ) {
    StringBuilder result = new StringBuilder( 7 ).append( '#' );
    append2x( result, color.red );
    append2x( result, color.green );
    append2x( result, color.blue );
    return result.toString();
  }

  private static void append2x( StringBuilder result, int red ) {
    if( red < 16 ) {
      result.append( '0' );
    }
    result.append( Integer.toHexString( red ) );
  }

  private ColorUtil() {
    // prevent instantiation
  }

}
