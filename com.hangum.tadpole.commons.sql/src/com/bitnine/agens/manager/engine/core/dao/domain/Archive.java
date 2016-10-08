package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Archive implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long archivedCount;

	private String lastArchivedWal;

	private Date lastArchivedTime;

	private long failedCount;

	private String lastFailedWal;

	private Date lastFailedTime;

	private Date statsReset;

	public Archive() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public long getArchivedCount() {
		return archivedCount;
	}

	public void setArchivedCount(long archivedCount) {
		this.archivedCount = archivedCount;
	}

	public String getLastArchivedWal() {
		return lastArchivedWal;
	}

	public void setLastArchivedWal(String lastArchivedWal) {
		this.lastArchivedWal = lastArchivedWal;
	}

	public Date getLastArchivedTime() {
		return lastArchivedTime;
	}

	public void setLastArchivedTime(Date lastArchivedTime) {
		this.lastArchivedTime = lastArchivedTime;
	}

	public long getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}

	public String getLastFailedWal() {
		return lastFailedWal;
	}

	public void setLastFailedWal(String lastFailedWal) {
		this.lastFailedWal = lastFailedWal;
	}

	public Date getLastFailedTime() {
		return lastFailedTime;
	}

	public void setLastFailedTime(Date lastFailedTime) {
		this.lastFailedTime = lastFailedTime;
	}

	public Date getStatsReset() {
		return statsReset;
	}

	public void setStatsReset(Date statsReset) {
		this.statsReset = statsReset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));

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
		final Archive other = (Archive) obj;
		if (snapid != other.snapid)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + 
			")";
	}
	
}
