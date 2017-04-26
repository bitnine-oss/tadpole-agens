package com.bitnine.angens.manager.core.editors.parts.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Database size table composite
 * 
 * @author hangum
 *
 */
public class DatabaseSizeTableComposite extends AgensTimeseriesChartComposite {
	private static final Logger logger = Logger.getLogger(DatabaseSizeTableComposite.class);
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param userDB
	 * @param instance
	 */
	public DatabaseSizeTableComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
		setLayout(new GridLayout(1, false));

		Group grpTransactionStatistics = new Group(this, SWT.NONE);
		grpTransactionStatistics.setLayout(new GridLayout(1, false));
		GridData gd_grpTransactionStatistics = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTransactionStatistics.minimumHeight = 350;
		gd_grpTransactionStatistics.heightHint = 350;
		grpTransactionStatistics.setLayoutData(gd_grpTransactionStatistics);
		grpTransactionStatistics.setText("Database Size");

		browserChart = new Browser(grpTransactionStatistics, SWT.NONE);
		browserChart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		initializeUIData("'Timestamp'", "'%H:%M'", "'Database Size (MB)'", "d3.format('.02f')");
	}
	
	/**
	 * get cpu data
	 * @return
	 * @throws Exception
	 */
	public List<?> getUIData() throws Exception {
		List<Map> listLine = AgensManagerSQLImpl.getSQLMapQueryInfo(userDB, "database_size", getRangeSnapId());
		if (listLine.isEmpty()) return new ArrayList<>();
		
		Map<String, List<TimeDataItem2D>> mapAllData = new HashMap<>();
		for (int i=0; i<listLine.size(); i++) {
			Map mapData = listLine.get(i);
			
			String strTime = String.format("new Date(\"%s\")", ""+mapData.get("timestamp"));
			String datname = ""+mapData.get("datname");
			
			String mapCommitTps = datname + "_size";
			List<TimeDataItem2D> listTimeSiesData = mapAllData.get(mapCommitTps);
			if(listTimeSiesData == null) {
				List<TimeDataItem2D> listData = new ArrayList<>(); 
				listData.add(new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("size")).doubleValue(), datname));
				
				mapAllData.put(mapCommitTps, listData);
			} else {
				listTimeSiesData.add(new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("size")).doubleValue(), datname));
			}
		}
		
		List<TimeDataGroup> listDataGroup = new ArrayList<>();
		int i = 0;
		for (String key : mapAllData.keySet()) {
			List<TimeDataItem2D> listTimeData = mapAllData.get(key);
			listDataGroup.add(new TimeDataGroup(listTimeData.toArray(new TimeDataItem2D[listTimeData.size()]), 	key, 	ColorsSWTUtils.CAT10_COLORS[i++]));
		}
		
		return listDataGroup;
	}
}