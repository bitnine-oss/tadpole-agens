package com.bitnine.angens.manager.core.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.rap.addons.chart.basic.TimeDataGroup;

import com.hangum.tadpole.engine.query.dao.system.UserInfoDataDAO;
import com.hangum.tadpole.engine.query.sql.TadpoleSystem_UserInfoData;
import com.hangum.tadpole.session.manager.SessionManager;

public class AgensChartUtils {
	private static final Logger logger = Logger.getLogger(AgensChartUtils.class);
	/**
	 * json to fully time chart
	 * 
	 * @param arryDataGroup
	 * @return
	 */
	public static String jsonToFulliyTimeChart(TimeDataGroup[] arryDataGroup) {
		StringBuffer sbJson = new StringBuffer("[");
		for (TimeDataGroup timeDataGroup : arryDataGroup) {
			sbJson.append(jsonStrToDate(timeDataGroup.toJson().toString())).append(",");
		}
		String sbFullJson = StringUtils.removeEnd(sbJson.toString(), ",");
		sbFullJson += "]";
		
		return sbFullJson;
	}
	
	/**
	 * json to string data
	 * 
	 * @param strJsonData
	 * @return
	 */
	public static String jsonStrToDate(String strJsonData) {
		//String strString = StringUtils.replace(strJsonData, "\"new Date(\\\"", "new Date(\"");
		//strString = StringUtils.replace(strString, "\\\")\"", "\")");
		
		// 부자형 코드.
		String strString = StringUtils.replace(strJsonData, "\"new Date(\\\"", "\"");
		strString = StringUtils.replace(strString, "\\\")\"", "\"");
		
		return strString;
	}
	
	/**
	 * Setting monitoring term
	 * 
	 * @param strMonitoringTerm
	 */
	public static void setMonitoringTerm(String strMonitoringTerm) {
		try {
			TadpoleSystem_UserInfoData.updateValue(AgensGraphDefine.PREFERENCE_MONITORING_TERM, strMonitoringTerm);
			AgensGraphDefine.MONITORING_INTERVAL = Integer.parseInt(strMonitoringTerm);
		} catch(Exception e) {
			logger.error("update monitoring term", e);
		}
	}
	
	/**
	 * Getting monitoring term
	 * 
	 * @return
	 */
	public static String getMonitoringTerm() {
		UserInfoDataDAO userInfo = SessionManager.getUserInfo(AgensGraphDefine.PREFERENCE_MONITORING_TERM, 
				AgensGraphDefine.PREFERENCE_MONITORING_TERM_VALUE);
		
		AgensGraphDefine.MONITORING_INTERVAL = Integer.parseInt(userInfo.getValue0()); 
		return userInfo.getValue0();
	}
}
