package com.bitnine.angens.manager.core.editors.parts.activities;

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
 * Replication Delays composite
 * 
 * @author hangum
 *
 */
public class ReplicationDelaysComposite extends AgensTimeseriesChartComposite {
	private static final Logger logger = Logger.getLogger(ReplicationDelaysComposite.class);
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param userDB
	 * @param instance
	 */
	public ReplicationDelaysComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
		setLayout(new GridLayout(1, false));

		Group grpTransactionStatistics = new Group(this, SWT.NONE);
		grpTransactionStatistics.setLayout(new GridLayout(1, false));
		GridData gd_grpTransactionStatistics = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTransactionStatistics.minimumHeight = 350;
		gd_grpTransactionStatistics.heightHint = 350;
		grpTransactionStatistics.setLayoutData(gd_grpTransactionStatistics);
		grpTransactionStatistics.setText("Replication Delays");

		browserChart = new Browser(grpTransactionStatistics, SWT.NONE);
		browserChart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		initializeUIData("'Timestamp'", "'%H:%M'", "'Delaying amount(MB)'", "d3.format('.02f')");
	}
	
	/**
	 * get cpu data
	 * @return
	 * @throws Exception
	 */
	public List<?> getUIData() throws Exception {
		List<Map> listLine = AgensManagerSQLImpl.getSQLMapQueryInfo(userDB, "replication_delays", getRangeSnapId());
		if (listLine.isEmpty()) return new ArrayList<>();
		
		TimeDataItem2D[] client = new TimeDataItem2D[listLine.size()];
		TimeDataItem2D[] flush_delay_size = new TimeDataItem2D[listLine.size()];
		TimeDataItem2D[] replay_delay_size = new TimeDataItem2D[listLine.size()];
		
		for (int i=0; i<listLine.size(); i++) {
			Map mapData = listLine.get(i);
			
			String strTime = String.format("new Date(\"%s\")", ""+mapData.get("replace"));
			client[i] 				= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("client")).doubleValue());
			flush_delay_size[i] 	= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("flush_delay_size")).doubleValue());
			replay_delay_size[i] 	= new TimeDataItem2D(strTime, ((BigDecimal) mapData.get("replay_delay_size")).doubleValue());
		}
		
		List<TimeDataGroup> listDataGroup = new ArrayList<>();
		listDataGroup.add(new TimeDataGroup(client, "replace",  ColorsSWTUtils.CAT10_COLORS[0]));
		listDataGroup.add(new TimeDataGroup(flush_delay_size, "flush_delay_size", ColorsSWTUtils.CAT10_COLORS[1]));
		listDataGroup.add(new TimeDataGroup(replay_delay_size, "replay_delay_size",  ColorsSWTUtils.CAT10_COLORS[2]));
		
		return listDataGroup;
	}
}