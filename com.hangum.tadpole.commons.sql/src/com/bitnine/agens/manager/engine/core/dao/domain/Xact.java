package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Xact implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String client;

	private int pid;

	private Date start;

	private double duration;

	private String query;

	public Xact() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + pid;
		result = prime * result + ((start == null) ? 0 : start.hashCode());

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
		final Xact other = (Xact) obj;
		if (snapid != other.snapid)
			return false;
		if (pid != other.pid)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"pid=" + "'" + pid + "'" + ", " + 
			"start=" + "'" + start + "'" + 
			")";
	}
	
}
