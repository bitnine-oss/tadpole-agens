package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Checkpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private long instid;

	private Date start;

	private String flags;

	private long numBuffers;

	private long xlogAdded;

	private long xlogRemoved;

	private long xlogRecycled;

	private String writeDuration;

	private String syncDuration;

	private String totalDuration;

	public Checkpoint() {
	}

	public long getInstid() {
		return instid;
	}

	public void setInstid(long instid) {
		this.instid = instid;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public long getNumBuffers() {
		return numBuffers;
	}

	public void setNumBuffers(long numBuffers) {
		this.numBuffers = numBuffers;
	}

	public long getXlogAdded() {
		return xlogAdded;
	}

	public void setXlogAdded(long xlogAdded) {
		this.xlogAdded = xlogAdded;
	}

	public long getXlogRemoved() {
		return xlogRemoved;
	}

	public void setXlogRemoved(long xlogRemoved) {
		this.xlogRemoved = xlogRemoved;
	}

	public long getXlogRecycled() {
		return xlogRecycled;
	}

	public void setXlogRecycled(long xlogRecycled) {
		this.xlogRecycled = xlogRecycled;
	}

	public String getWriteDuration() {
		return writeDuration;
	}

	public void setWriteDuration(String writeDuration) {
		this.writeDuration = writeDuration;
	}

	public String getSyncDuration() {
		return syncDuration;
	}

	public void setSyncDuration(String syncDuration) {
		this.syncDuration = syncDuration;
	}

	public String getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (instid ^ (instid >>> 32));

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
		final Checkpoint other = (Checkpoint) obj;
		if (instid != other.instid)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"instid=" + "'" + instid + "'" + 
			")";
	}
	
}
