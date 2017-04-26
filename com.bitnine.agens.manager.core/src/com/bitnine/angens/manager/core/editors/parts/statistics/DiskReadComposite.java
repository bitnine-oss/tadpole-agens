package com.bitnine.angens.manager.core.editors.parts.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.rap.addons.chart.basic.DataItem;
import org.eclipse.rap.addons.chart.basic.PieChart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import com.bitnine.agens.manager.engine.core.AgensManagerSQLImpl;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.AgensChartComposite;
import com.hangum.tadpole.commons.util.ColorsSWTUtils;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * Disk size chart
 * 
 * @author hangum
 *
 */
public class DiskReadComposite extends AgensChartComposite {
	private static final Logger logger = Logger.getLogger(DiskReadComposite.class);
	private PieChart pieDiskSize;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param title
	 * @param userDB
	 * @param instance
	 */
	public DiskReadComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
		setLayout(new GridLayout(1, false));

		Group grpTransactionStatistics = new Group(this, SWT.NONE);
		grpTransactionStatistics.setLayout(new GridLayout(1, false));
		GridData gd_grpTransactionStatistics = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTransactionStatistics.minimumHeight = 200;
		gd_grpTransactionStatistics.minimumWidth = 200;
		gd_grpTransactionStatistics.heightHint = 200;
		gd_grpTransactionStatistics.widthHint = 200;
		grpTransactionStatistics.setLayoutData(gd_grpTransactionStatistics);
		grpTransactionStatistics.setText("Disk Read");

		pieDiskSize = new PieChart(grpTransactionStatistics, SWT.NONE);
		GridLayout gl_grpConnectionInfo = new GridLayout(1, false);
		gl_grpConnectionInfo.verticalSpacing = 0;
		gl_grpConnectionInfo.horizontalSpacing = 0;
		gl_grpConnectionInfo.marginHeight = 0;
		gl_grpConnectionInfo.marginWidth = 0;
		pieDiskSize.setLayout(gl_grpConnectionInfo);
		pieDiskSize.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		pieDiskSize.setLabelsOutside(true);
		pieDiskSize.setLabelSunbeamLayout(false);
		pieDiskSize.setShowLabels(false);
		pieDiskSize.setLegendPosition("right");

		// 초기 파이차트의 유형을 잡아준다.
		try {
			List<?> listDataItem = getUIData();
			pieDiskSize.setItems(listDataItem.toArray(new DataItem[listDataItem.size()]));
		} catch(Exception e) {
			logger.error("initialize Disk size", e);
		}
		
	}

	@Override
	public List<?> getUIData() throws Exception {
		List<DataItem> listDataItem = new ArrayList<>();
		
		try {
			List<Map> listLine = AgensManagerSQLImpl.getSQLMapQueryInfo(getUserDB(), "disk_read", getLastSnapId());
			int intColumnPos = 0;
			for (Map mapTableSize : listLine) {
				String strTitle = ""+mapTableSize.get("Title");
				int intMB = Integer.parseInt(""+mapTableSize.get("per"));
				
				listDataItem.add(new DataItem(intMB, strTitle, ColorsSWTUtils.CAT10_COLORS[ intColumnPos++ ]));
			}
			
		} catch(Exception e) {
			logger.error("initialize Table size", e);
		}
		
		return listDataItem;
	}

	@Override
	public void refreshUI(final List<?> listData) {
		try {
			final Display display = pieDiskSize.getDisplay();
		    display.asyncExec( new Runnable() {
		    	public void run() {
		    		pieDiskSize.setItems(listData.toArray(new DataItem[listData.size()]));
		    	}
		    } );
			
		} catch(Exception e) {
			logger.error("initialize data", e);
		}
	}
	
}