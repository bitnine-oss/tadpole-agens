package com.bitnine.angens.manager.core.editors.parts.lableprovider;

import java.util.HashMap;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;

/**
 * Database label provider
 * @author hangum
 *
 */
public class AgensMAPLabelProvider extends LabelProvider implements ITableLabelProvider {
	private Table table;
	
	public AgensMAPLabelProvider() {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		HashMap map = (HashMap)element;
		
		String title = table.getColumn(columnIndex).getText();
		String value = ""+map.get(title);
		
		return value;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
