package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Function implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long funcid;

	private long nsp;

	private String funcname;

	private String argtypes;

	private long calls;

	private double totalTime;

	private double selfTime;

	public Function() {
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

	public long getFuncid() {
		return funcid;
	}

	public void setFuncid(long funcid) {
		this.funcid = funcid;
	}

	public long getNsp() {
		return nsp;
	}

	public void setNsp(long nsp) {
		this.nsp = nsp;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getArgtypes() {
		return argtypes;
	}

	public void setArgtypes(String argtypes) {
		this.argtypes = argtypes;
	}

	public long getCalls() {
		return calls;
	}

	public void setCalls(long calls) {
		this.calls = calls;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public double getSelfTime() {
		return selfTime;
	}

	public void setSelfTime(double selfTime) {
		this.selfTime = selfTime;
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
		final Function other = (Function) obj;
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
