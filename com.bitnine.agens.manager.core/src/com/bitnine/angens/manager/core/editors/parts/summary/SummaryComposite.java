package com.bitnine.angens.manager.core.editors.parts.summary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * Summary 
 * 
 * @author hangum
 *
 */
public class SummaryComposite extends Composite {
	private Table tableSummary;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SummaryComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		
		Group grpComp = new Group(this, SWT.NONE);
		grpComp.setLayout(new GridLayout(1, false));
		grpComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpComp.setText("Summary");
		
		tableSummary = new Table(grpComp, SWT.BORDER | SWT.FULL_SELECTION);
		tableSummary.setLinesVisible(true);
		tableSummary.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_grpConnectionInfo = new GridLayout(1, false);
		gl_grpConnectionInfo.verticalSpacing = 0;
		gl_grpConnectionInfo.horizontalSpacing = 0;
		tableSummary.setLayout(gl_grpConnectionInfo);
		
		TableColumn tblclmnColumnLabel = new TableColumn(tableSummary, SWT.NONE);
		tblclmnColumnLabel.setWidth(150);
		tblclmnColumnLabel.setText("Label");
		
		TableColumn tblclmnColumnValue = new TableColumn(tableSummary, SWT.NONE);
		tblclmnColumnValue.setWidth(300);
		tblclmnColumnValue.setText("Value");
		
		initValue(instance);
	}
	
	private void initValue(Instance instance) {
		TableItem itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "instance name", instance.getName()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "host name", instance.getHostname()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "port", ""+instance.getPort()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "pg_version", ""+instance.getPg_version()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "count", ""+instance.getCount()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "sum", ""+instance.getSum()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "max", ""+instance.getMax()});
	    
	    itemSummary = new TableItem(tableSummary, SWT.NONE);
	    itemSummary.setText(new String[] { "max_time", ""+instance.getMax_time()});
	    
//	    itemSummary = new TableItem(tableSummary, SWT.NONE);
//	    itemSummary.setText(new String[] { "total_commits", ""});
//	    
//	    itemSummary = new TableItem(tableSummary, SWT.NONE);
//	    itemSummary.setText(new String[] { "total_rollbacks", ""});
	}

	@Override
	protected void checkSubclass() {
	}

}
