package com.bitnine.angens.manager.core.editors.parts;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;

import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.lableprovider.AgensMAPLabelProvider;
import com.bitnine.angens.manager.core.editors.parts.statistics.DatabaseStatisticsTableComposite;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

public abstract class AgensTableComposite extends AgensThreadComposite {
	private static final Logger logger = Logger.getLogger(DatabaseStatisticsTableComposite.class);
	protected TableViewer tableView;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param title
	 * @param userDB
	 * @param instance
	 */
	public AgensTableComposite(Composite parent, String title, UserDBDAO userDB, Instance instance, LabelProvider labelProvider) {
		super(parent, userDB, instance);
		setLayout(new GridLayout(1, false));
		
		Group grpComp = new Group(this, SWT.NONE);
		grpComp.setLayout(new GridLayout(1, false));

		// scrollable composite vertical true
		grpComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpComp.setText(title);
		
		tableView = new TableViewer(grpComp, SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tableView.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd.minimumHeight = 100;
		table.setLayoutData(gd);
		GridLayout gl_grpConnectionInfo = new GridLayout(1, false);
		gl_grpConnectionInfo.verticalSpacing = 0;
		gl_grpConnectionInfo.horizontalSpacing = 0;
		table.setLayout(gl_grpConnectionInfo);
		
		createTableColumn();
		
		tableView.setContentProvider(new ArrayContentProvider());
		tableView.setLabelProvider(labelProvider);
		if(labelProvider instanceof AgensMAPLabelProvider) {
			AgensMAPLabelProvider mapLabelProvider = (AgensMAPLabelProvider)labelProvider;
			mapLabelProvider.setTable(tableView.getTable());
		}
	}
	
	/**
	 * refresh UI
	 * @param listCPU
	 */
	public void refreshUI(final List<?> listData) {
		try {
			final Display display = tableView.getTable().getDisplay();
		    display.asyncExec( new Runnable() {
		    	public void run() {
		    		tableView.setInput(listData);
		    	}
		    } );
			
		} catch(Exception e) {
			logger.error("initialize data", e);
		}
	}
	
	/**
	 * get cpu data
	 * @return
	 * @throws Exception
	 */
	public abstract List<?> getUIData() throws Exception;
	
	/**
	 * make columns
	 */
	public abstract void createTableColumn();
	
	public TableViewer getTableViewer() {
		return tableView;
	}
}