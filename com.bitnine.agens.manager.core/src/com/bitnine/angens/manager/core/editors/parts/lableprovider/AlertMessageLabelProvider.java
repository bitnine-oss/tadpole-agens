package com.bitnine.angens.manager.core.editors.parts.lableprovider;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bitnine.agens.manager.engine.core.dao.domain.AlertMessage;

/**
 * alter message label provider
 * @author hangum
 *
 */
public class AlertMessageLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		AlertMessage dao = (AlertMessage)element;

		String strMessage = dao.getMessage();
		String strBeforeTime = StringUtils.substringAfter(strMessage, "in snapshot '");
		strBeforeTime = StringUtils.substringBefore(strBeforeTime, "' ---");

		switch (columnIndex) {
		case 0: return strBeforeTime;
		case 1: return strMessage;
		}
		
		return null;
	}

}
