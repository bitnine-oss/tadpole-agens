package com.bitnine.angens.manager.core.editors.parts.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.rap.addons.chart.basic.TimeDataGroup;
import org.eclipse.rap.addons.chart.basic.TimeDataItem2D;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.bitnine.agens.manager.engine.core.AgensManagerSQLImpl;
import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.parts.AgensTimeseriesChartComposite;
import com.hangum.tadpole.commons.util.ColorsSWTUtils;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

/**
 * Instance Processes Ratio composite
 * 
 * @author hangum
 *
 */
public class InstanceProcessesTableComposite extends AgensTimeseriesChartComposite {
	private static final Logger logger = Logger.getLogger(InstanceProcessesTableComposite.class);
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param userDB
	 * @param instance
	 */
	public InstanceProcessesTableComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
		setLayout(new GridLayout(1, false));

		Group grpTransactionStatistics = new Group(this, SWT.NONE);
		grpTransactionStatistics.setLayout(new GridLayout(1, false));
		GridData gd_grpTransactionStatistics = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTransactionStatistics.minimumHeight = 350;
		gd_grpTransactionStatistics.heightHint = 350;
		grpTransactionStatistics.setLayoutData(gd_grpTransactionStatistics);
		grpTransactionStatistics.setText("Instance Processes Ratio");

		browserChart = new Browser(grpTransactionStatistics, SWT.NONE);
		browserChart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		initializeUIData("'Timestamp'", "'%H:%M'", "'Percent(%)'", "d3.format('.02f')");
	}
	
	/**
	 * get cpu data
	 * @return
	 * @throws Exception
	 */
	public List<?> getUIData() throws Exception {
		List<Map> listLine = AgensManagerSQLImpl.getSQLMapQueryInfo(getUserDB(), "instance_processes", getRangeSnapId());
		if (listLine.isEmpty()) return new ArrayList<>();
		
		TimeDataItem2D[] idle = new TimeDataItem2D[listLine.size()];
		TimeDataItem2D[] idleInXact = new TimeDataItem2D[listLine.size()];
		TimeDataItem2D[] waiting = new TimeDataItem2D[listLine.size()];
		TimeDataItem2D[] running = new TimeDataItem2D[listLine.size()];
		
		for (int i=0; i<listLine.size(); i++) {
			Map mapData = listLine.get(i);
			
			String strTime = String.format("new Date(\"%s\")", ""+mapData.get("replace"));
			idle[i] 		= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("idle")).doubleValue());
			idleInXact[i] 	= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("idle in xact")).doubleValue());
			waiting[i] 	= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("waiting")).doubleValue());
			running[i] 	= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("running")).doubleValue());
		}
		
		List<TimeDataGroup> listDataGroup = new ArrayList<>();
		listDataGroup.add(new TimeDataGroup(idle, 		"idle",  ColorsSWTUtils.CAT10_COLORS[0]));
		listDataGroup.add(new TimeDataGroup(idleInXact, "idle in xact", ColorsSWTUtils.CAT10_COLORS[1]));
		listDataGroup.add(new TimeDataGroup(waiting, 	"waiting",  ColorsSWTUtils.CAT10_COLORS[2]));
		listDataGroup.add(new TimeDataGroup(running, 	"running",  ColorsSWTUtils.CAT10_COLORS[3]));
		
		return listDataGroup;
	}
}