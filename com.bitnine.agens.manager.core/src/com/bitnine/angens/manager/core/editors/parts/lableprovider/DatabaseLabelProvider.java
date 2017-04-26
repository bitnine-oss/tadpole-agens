package com.bitnine.angens.manager.core.editors.parts.lableprovider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bitnine.agens.manager.engine.core.dao.domain.Database;

/**
 * Database label provider
 * @author hangum
 *
 */
public class DatabaseLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Database dao = (Database)element;

//		"Database", "MiB", "MiB", "commit/s", "rollback/s", "hit%", "gets/s", "reads/s", "rows/s"
		switch (columnIndex) {
		case 0: return ""+dao.getName();
		case 1: return ""+dao.getSize();
		case 2: return ""+dao.getAge();
		case 3: return ""+dao.getXact_commit();
		case 4: return ""+dao.getXact_rollback();
		case 5: return ""+dao.getBlks_hit();
		case 6: return "";
		case 7: return ""+dao.getBlks_read();
		case 8: return "";
		}
		
		return null;
	}

}
