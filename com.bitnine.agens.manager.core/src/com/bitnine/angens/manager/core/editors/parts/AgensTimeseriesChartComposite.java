package com.bitnine.angens.manager.core.editors.parts;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.rap.addons.chart.basic.TimeDataGroup;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.bitnine.agens.manager.engine.core.dao.domain.Instance;
import com.bitnine.angens.manager.core.editors.template.AgensChartTemplate;
import com.bitnine.angens.manager.core.utils.AgensChartUtils;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;

public abstract class AgensTimeseriesChartComposite extends AgensChartComposite {
	private static final Logger logger = Logger.getLogger(AgensTimeseriesChartComposite.class);
	
	/** TIMESERIES CHART HTML */
	protected String _TEMPLATE_TIMESERIES_CHART_HTML = "";
	/** define line chart */
	protected Browser browserChart;

	public AgensTimeseriesChartComposite(Composite parent, UserDBDAO userDB, Instance instance) {
		super(parent, userDB, instance);
	}
	
	/**
	 * initialize ui data
	 * 
	 * @param xLabel
	 * @param xLabelFormat
	 * @param yLabel
	 * @param yLabelFormat
	 */
	protected void initializeUIData(String xLabel, String xLabelFormat, String yLabel, String yLabelFormat) {
		try {
			_TEMPLATE_TIMESERIES_CHART_HTML = AgensChartTemplate.getTimeseriesLineChart();
			_TEMPLATE_TIMESERIES_CHART_HTML = StringUtils.replace(_TEMPLATE_TIMESERIES_CHART_HTML, "_XAXIS_LABEL_", 		xLabel);
			_TEMPLATE_TIMESERIES_CHART_HTML = StringUtils.replace(_TEMPLATE_TIMESERIES_CHART_HTML, "_FORMAT_XAXIS_LABEL", 	xLabelFormat);
			_TEMPLATE_TIMESERIES_CHART_HTML = StringUtils.replace(_TEMPLATE_TIMESERIES_CHART_HTML, "_YAXIS_LABEL_", 		yLabel);
			_TEMPLATE_TIMESERIES_CHART_HTML = StringUtils.replace(_TEMPLATE_TIMESERIES_CHART_HTML, "_FORMAT_YAXIS_LABEL", 	yLabelFormat);
						
			try {
				List<?> listData = getUIData();
				TimeDataGroup[] arryDataGroup = listData.toArray(new TimeDataGroup[listData.size()]);
				_TEMPLATE_TIMESERIES_CHART_HTML = StringUtils.replace(
	    				_TEMPLATE_TIMESERIES_CHART_HTML, 
	    				AgensChartTemplate.TIMESERIESCHART_TEMPLATE, 
	    				AgensChartUtils.jsonToFulliyTimeChart(arryDataGroup));				
			} catch (Exception e) {
			}
			
			browserChart.setText(_TEMPLATE_TIMESERIES_CHART_HTML);
						
		} catch (IOException e) {
			logger.error("get template htmll", e);
		}
	}
	
	/**
	 * refresh UI
	 * @param listCPU
	 */
	public void refreshUI(final List<?> listData) {
		
		final Display display = browserChart.getDisplay();
	    display.syncExec( new Runnable() {
	    	public void run() {
	    		try {
/*
	    			TimeDataGroup[] arryDataGroup = listData.toArray(new TimeDataGroup[listData.size()]);
		    		String strHtml = StringUtils.replace(
		    				_TEMPLATE_TIMESERIES_CHART_HTML, 
		    				AgensChartTemplate.TIMESERIESCHART_TEMPLATE, 
		    				AgensChartUtils.jsonToFulliyTimeChart(arryDataGroup));
		    		if(logger.isDebugEnabled()) logger.debug("[output hthml]\n" + strHtml);
		    		browserChart.setText(strHtml);

*/
		    		// 부자형 추천 코드.
	      			TimeDataGroup[] arryDataGroup = listData.toArray(new TimeDataGroup[listData.size()]);
	    			String jsondata = AgensChartUtils.jsonToFulliyTimeChart(arryDataGroup);
	    			browserChart.evaluate(String.format("tdb_refresh('%s');", jsondata));
	    			
	    		} catch(Exception e) {
	    			logger.error("print timeseries chart : " + e.getMessage());
	    		}
	    	}
	    });	// end display
	}

}
