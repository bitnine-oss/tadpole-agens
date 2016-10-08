package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Snapshot implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long instid;

	private Date time;

	private String comment;

	private String execTime;

	private long snapshotIncreaseSize;

	public Snapshot() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public long getInstid() {
		return instid;
	}

	public void setInstid(long instid) {
		this.instid = instid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExecTime() {
		return execTime;
	}

	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}

	public long getSnapshotIncreaseSize() {
		return snapshotIncreaseSize;
	}

	public void setSnapshotIncreaseSize(long snapshotIncreaseSize) {
		this.snapshotIncreaseSize = snapshotIncreaseSize;
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
		final Snapshot other = (Snapshot) obj;
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
