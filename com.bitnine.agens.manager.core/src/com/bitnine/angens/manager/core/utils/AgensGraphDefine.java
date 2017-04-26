package com.bitnine.angens.manager.core.utils;

public class AgensGraphDefine {

	/** 분단위 설정을 위한 단위 지정 (60초) */
	public static final int MONITORING_CYCLE = 1000 * 60;
	
	/** 모니터링 주기 저장 (분) */
	public static final String PREFERENCE_MONITORING_TERM = "PREFERENCE_AGENS_MONITORING_TERM";
	public static final String PREFERENCE_MONITORING_TERM_VALUE = "10";
	
	/** monitring interval */
	public static int MONITORING_INTERVAL = Integer.parseInt(PREFERENCE_MONITORING_TERM_VALUE);
}
