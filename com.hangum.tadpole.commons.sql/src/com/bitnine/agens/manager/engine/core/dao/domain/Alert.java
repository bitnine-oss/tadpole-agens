package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Alert implements Serializable {

	private static final long serialVersionUID = 1L;

	private long instid;

	private long rollbackTps;

	private long commitTps;

	private long garbageSize;

	private int garbagePercent;

	private int garbagePercentTable;

	private long responseAvg;

	private long responseWorst;

	private int backendMax;

	private int fragmentPercent;

	private int diskRemainPercent;

	private String loadavg1min;

	private String loadavg5min;

	private String loadavg15min;

	private int swapSize;

	private int repFlushDelay;

	private int repReplayDelay;

	private String enableAlert;

	public Alert() {
	}

	public long getInstid() {
		return instid;
	}

	public void setInstid(long instid) {
		this.instid = instid;
	}

	public long getRollbackTps() {
		return rollbackTps;
	}

	public void setRollbackTps(long rollbackTps) {
		this.rollbackTps = rollbackTps;
	}

	public long getCommitTps() {
		return commitTps;
	}

	public void setCommitTps(long commitTps) {
		this.commitTps = commitTps;
	}

	public long getGarbageSize() {
		return garbageSize;
	}

	public void setGarbageSize(long garbageSize) {
		this.garbageSize = garbageSize;
	}

	public int getGarbagePercent() {
		return garbagePercent;
	}

	public void setGarbagePercent(int garbagePercent) {
		this.garbagePercent = garbagePercent;
	}

	public int getGarbagePercentTable() {
		return garbagePercentTable;
	}

	public void setGarbagePercentTable(int garbagePercentTable) {
		this.garbagePercentTable = garbagePercentTable;
	}

	public long getResponseAvg() {
		return responseAvg;
	}

	public void setResponseAvg(long responseAvg) {
		this.responseAvg = responseAvg;
	}

	public long getResponseWorst() {
		return responseWorst;
	}

	public void setResponseWorst(long responseWorst) {
		this.responseWorst = responseWorst;
	}

	public int getBackendMax() {
		return backendMax;
	}

	public void setBackendMax(int backendMax) {
		this.backendMax = backendMax;
	}

	public int getFragmentPercent() {
		return fragmentPercent;
	}

	public void setFragmentPercent(int fragmentPercent) {
		this.fragmentPercent = fragmentPercent;
	}

	public int getDiskRemainPercent() {
		return diskRemainPercent;
	}

	public void setDiskRemainPercent(int diskRemainPercent) {
		this.diskRemainPercent = diskRemainPercent;
	}

	public String getLoadavg1min() {
		return loadavg1min;
	}

	public void setLoadavg1min(String loadavg1min) {
		this.loadavg1min = loadavg1min;
	}

	public String getLoadavg5min() {
		return loadavg5min;
	}

	public void setLoadavg5min(String loadavg5min) {
		this.loadavg5min = loadavg5min;
	}

	public String getLoadavg15min() {
		return loadavg15min;
	}

	public void setLoadavg15min(String loadavg15min) {
		this.loadavg15min = loadavg15min;
	}

	public int getSwapSize() {
		return swapSize;
	}

	public void setSwapSize(int swapSize) {
		this.swapSize = swapSize;
	}

	public int getRepFlushDelay() {
		return repFlushDelay;
	}

	public void setRepFlushDelay(int repFlushDelay) {
		this.repFlushDelay = repFlushDelay;
	}

	public int getRepReplayDelay() {
		return repReplayDelay;
	}

	public void setRepReplayDelay(int repReplayDelay) {
		this.repReplayDelay = repReplayDelay;
	}

	public String getEnableAlert() {
		return enableAlert;
	}

	public void setEnableAlert(String enableAlert) {
		this.enableAlert = enableAlert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (instid ^ (instid >>> 32));

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Alert other = (Alert) obj;
		if (instid != other.instid)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"instid=" + "'" + instid + "'" + 
			")";
	}
	
}
