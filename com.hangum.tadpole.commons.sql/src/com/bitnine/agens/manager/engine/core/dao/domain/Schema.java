package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Schema implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long nsp;

	private String name;

	public Schema() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public long getDbid() {
		return dbid;
	}

	public void setDbid(long dbid) {
		this.dbid = dbid;
	}

	public long getNsp() {
		return nsp;
	}

	public void setNsp(long nsp) {
		this.nsp = nsp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + (int) (dbid ^ (dbid >>> 32));
		result = prime * result + (int) (nsp ^ (nsp >>> 32));

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
		final Schema other = (Schema) obj;
		if (snapid != other.snapid)
			return false;
		if (dbid != other.dbid)
			return false;
		if (nsp != other.nsp)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"dbid=" + "'" + dbid + "'" + ", " + 
			"nsp=" + "'" + nsp + "'" + 
			")";
	}
	
}
