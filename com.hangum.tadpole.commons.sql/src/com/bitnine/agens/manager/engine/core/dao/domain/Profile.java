package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String processing;

	private long execute;

	private double totalExecTime;

	public Profile() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public long getExecute() {
		return execute;
	}

	public void setExecute(long execute) {
		this.execute = execute;
	}

	public double getTotalExecTime() {
		return totalExecTime;
	}

	public void setTotalExecTime(double totalExecTime) {
		this.totalExecTime = totalExecTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + ((processing == null) ? 0 : processing.hashCode());

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
		final Profile other = (Profile) obj;
		if (snapid != other.snapid)
			return false;
		if (processing == null) {
			if (other.processing != null)
				return false;
		} else if (!processing.equals(other.processing))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"processing=" + "'" + processing + "'" + 
			")";
	}
	
}
