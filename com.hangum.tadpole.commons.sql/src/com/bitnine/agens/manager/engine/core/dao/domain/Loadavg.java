package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Loadavg implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String loadavg1;

	private String loadavg5;

	private String loadavg15;

	public Loadavg() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getLoadavg1() {
		return loadavg1;
	}

	public void setLoadavg1(String loadavg1) {
		this.loadavg1 = loadavg1;
	}

	public String getLoadavg5() {
		return loadavg5;
	}

	public void setLoadavg5(String loadavg5) {
		this.loadavg5 = loadavg5;
	}

	public String getLoadavg15() {
		return loadavg15;
	}

	public void setLoadavg15(String loadavg15) {
		this.loadavg15 = loadavg15;
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
		final Loadavg other = (Loadavg) obj;
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
