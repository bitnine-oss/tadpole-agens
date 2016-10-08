package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Tablespace implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long tbs;

	private String name;

	private String location;

	private String device;

	private long avail;

	private long total;

	private String spcoptions;

	public Tablespace() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public long getTbs() {
		return tbs;
	}

	public void setTbs(long tbs) {
		this.tbs = tbs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public long getAvail() {
		return avail;
	}

	public void setAvail(long avail) {
		this.avail = avail;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getSpcoptions() {
		return spcoptions;
	}

	public void setSpcoptions(String spcoptions) {
		this.spcoptions = spcoptions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());

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
		final Tablespace other = (Tablespace) obj;
		if (snapid != other.snapid)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"name=" + "'" + name + "'" + 
			")";
	}
	
}
