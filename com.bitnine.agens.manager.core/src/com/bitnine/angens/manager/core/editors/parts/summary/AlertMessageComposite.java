package com.bitnine.angens.manager.core.editors.parts.summary;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.bitnine.agens.manager.engine.core.AgensManagerSQLImpl;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.AgensTableComposite;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * alert message table
 * 
 * @author hangum
 *
 */
public class AlertMessageComposite extends AgensTableComposite {
	private static final Logger logger = Logger.getLogger(AlertMessageComposite.class);
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param title
	 * @param userDB
	 * @param instance
	 */
	public AlertMessageComposite(Composite parent, UserDBDAO userDB, Instance instance, LabelProvider labelProvider) {
		super(parent, "Alert", userDB, instance, labelProvider);
	}
	
	/**
	 * get cpu data
	 * @return
	 * @throws Exception
	 */
	public List<?> getUIData() throws Exception {
		return AgensManagerSQLImpl.getAlertMessageInfo(userDB, instance);
	}
	
	/**
	 * make columns
	 */
	public void createTableColumn() {
		String[] columnName = {"timestamp", "Message"};
		int[] columnSize = {140, 800 };
		int[] align = {SWT.LEFT, SWT.LEFT};
		
		for(int i=0; i<columnName.length; i++) {
			final TableViewerColumn tableColumn = new TableViewerColumn(tableView, align[i]);
			tableColumn.getColumn().setText( columnName[i] );
			tableColumn.getColumn().setWidth( columnSize[i] );
			tableColumn.getColumn().setResizable(true);
			tableColumn.getColumn().setMoveable(false);
		}
	}
}
