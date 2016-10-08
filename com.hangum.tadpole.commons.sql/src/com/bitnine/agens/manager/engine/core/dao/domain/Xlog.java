package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Xlog implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String location;

	private String xlogfile;

	public Xlog() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getXlogfile() {
		return xlogfile;
	}

	public void setXlogfile(String xlogfile) {
		this.xlogfile = xlogfile;
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
		final Xlog other = (Xlog) obj;
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
