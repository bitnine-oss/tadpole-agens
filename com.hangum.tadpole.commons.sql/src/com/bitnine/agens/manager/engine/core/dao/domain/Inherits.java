package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Inherits implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long inhrelid;

	private long inhparent;

	private int inhseqno;

	public Inherits() {
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

	public long getInhrelid() {
		return inhrelid;
	}

	public void setInhrelid(long inhrelid) {
		this.inhrelid = inhrelid;
	}

	public long getInhparent() {
		return inhparent;
	}

	public void setInhparent(long inhparent) {
		this.inhparent = inhparent;
	}

	public int getInhseqno() {
		return inhseqno;
	}

	public void setInhseqno(int inhseqno) {
		this.inhseqno = inhseqno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + (int) (dbid ^ (dbid >>> 32));
		result = prime * result + (int) (inhrelid ^ (inhrelid >>> 32));
		result = prime * result + inhseqno;

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
		final Inherits other = (Inherits) obj;
		if (snapid != other.snapid)
			return false;
		if (dbid != other.dbid)
			return false;
		if (inhrelid != other.inhrelid)
			return false;
		if (inhseqno != other.inhseqno)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"dbid=" + "'" + dbid + "'" + ", " + 
			"inhrelid=" + "'" + inhrelid + "'" + ", " + 
			"inhseqno=" + "'" + inhseqno + "'" + 
			")";
	}
	
}
