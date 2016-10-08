package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Lock implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String datname;

	private String nspname;

	private String relname;

	private String blockerAppname;

	private String blockerAddr;

	private String blockerHostname;

	private int blockerPort;

	private int blockeePid;

	private int blockerPid;

	private String blockerGid;

	private String duration;

	private String blockeeQuery;

	private String blockerQuery;

	public Lock() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getDatname() {
		return datname;
	}

	public void setDatname(String datname) {
		this.datname = datname;
	}

	public String getNspname() {
		return nspname;
	}

	public void setNspname(String nspname) {
		this.nspname = nspname;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getBlockerAppname() {
		return blockerAppname;
	}

	public void setBlockerAppname(String blockerAppname) {
		this.blockerAppname = blockerAppname;
	}

	public String getBlockerAddr() {
		return blockerAddr;
	}

	public void setBlockerAddr(String blockerAddr) {
		this.blockerAddr = blockerAddr;
	}

	public String getBlockerHostname() {
		return blockerHostname;
	}

	public void setBlockerHostname(String blockerHostname) {
		this.blockerHostname = blockerHostname;
	}

	public int getBlockerPort() {
		return blockerPort;
	}

	public void setBlockerPort(int blockerPort) {
		this.blockerPort = blockerPort;
	}

	public int getBlockeePid() {
		return blockeePid;
	}

	public void setBlockeePid(int blockeePid) {
		this.blockeePid = blockeePid;
	}

	public int getBlockerPid() {
		return blockerPid;
	}

	public void setBlockerPid(int blockerPid) {
		this.blockerPid = blockerPid;
	}

	public String getBlockerGid() {
		return blockerGid;
	}

	public void setBlockerGid(String blockerGid) {
		this.blockerGid = blockerGid;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBlockeeQuery() {
		return blockeeQuery;
	}

	public void setBlockeeQuery(String blockeeQuery) {
		this.blockeeQuery = blockeeQuery;
	}

	public String getBlockerQuery() {
		return blockerQuery;
	}

	public void setBlockerQuery(String blockerQuery) {
		this.blockerQuery = blockerQuery;
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
		final Lock other = (Lock) obj;
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
