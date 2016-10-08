package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Memory implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long memfree;

	private long buffers;

	private long cached;

	private long swap;

	private long dirty;

	public Memory() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public long getMemfree() {
		return memfree;
	}

	public void setMemfree(long memfree) {
		this.memfree = memfree;
	}

	public long getBuffers() {
		return buffers;
	}

	public void setBuffers(long buffers) {
		this.buffers = buffers;
	}

	public long getCached() {
		return cached;
	}

	public void setCached(long cached) {
		this.cached = cached;
	}

	public long getSwap() {
		return swap;
	}

	public void setSwap(long swap) {
		this.swap = swap;
	}

	public long getDirty() {
		return dirty;
	}

	public void setDirty(long dirty) {
		this.dirty = dirty;
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
		final Memory other = (Memory) obj;
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
