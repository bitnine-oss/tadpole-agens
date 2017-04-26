package com.bitnine.angens.manager.core.editors.template;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.service.ApplicationContext;

public class AgensChartTemplate {
	private static final Logger logger = Logger.getLogger(AgensChartTemplate.class);
	public static AgensChartTemplate instance = null;
	public static final String TIMESERIESCHART_TEMPLATE = "_TIMESERIESCHART_TEMPLATE";

	private AgensChartTemplate() {
	}
	
	public static AgensChartTemplate getInstance() {
		if(instance == null) {
			instance = new AgensChartTemplate();
		}
		return instance;
	}
	

	/**
	 * 템플릿을 글로벌 세션에서 가져오고, 없으면 생성하여 세션에 입력합니다.
	 * 
	 * @return
	 */
	public static String getTimeseriesLineChart() throws IOException {
		ApplicationContext applicationContext = RWT.getApplicationContext();
		Object temp_timesericechart = applicationContext.getAttribute(TIMESERIESCHART_TEMPLATE);
		if(temp_timesericechart == null) {
			String strTemp = IOUtils.toString(AgensChartTemplate.class.getResource("Template_TimeseriesChart.html"));
			applicationContext.setAttribute(TIMESERIESCHART_TEMPLATE, strTemp);
			
			return strTemp;
		} else {
			return ""+temp_timesericechart;	
		}
	}
	
}
