package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Statement implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long userid;

	private long queryid;

	private String query;

	private long calls;

	private double totalTime;

	private long rows;

	private long sharedBlksHit;

	private long sharedBlksRead;

	private long sharedBlksDirtied;

	private long sharedBlksWritten;

	private long localBlksHit;

	private long localBlksRead;

	private long localBlksDirtied;

	private long localBlksWritten;

	private long tempBlksRead;

	private long tempBlksWritten;

	private double blkReadTime;

	private double blkWriteTime;

	public Statement() {
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

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getQueryid() {
		return queryid;
	}

	public void setQueryid(long queryid) {
		this.queryid = queryid;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
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

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getSharedBlksHit() {
		return sharedBlksHit;
	}

	public void setSharedBlksHit(long sharedBlksHit) {
		this.sharedBlksHit = sharedBlksHit;
	}

	public long getSharedBlksRead() {
		return sharedBlksRead;
	}

	public void setSharedBlksRead(long sharedBlksRead) {
		this.sharedBlksRead = sharedBlksRead;
	}

	public long getSharedBlksDirtied() {
		return sharedBlksDirtied;
	}

	public void setSharedBlksDirtied(long sharedBlksDirtied) {
		this.sharedBlksDirtied = sharedBlksDirtied;
	}

	public long getSharedBlksWritten() {
		return sharedBlksWritten;
	}

	public void setSharedBlksWritten(long sharedBlksWritten) {
		this.sharedBlksWritten = sharedBlksWritten;
	}

	public long getLocalBlksHit() {
		return localBlksHit;
	}

	public void setLocalBlksHit(long localBlksHit) {
		this.localBlksHit = localBlksHit;
	}

	public long getLocalBlksRead() {
		return localBlksRead;
	}

	public void setLocalBlksRead(long localBlksRead) {
		this.localBlksRead = localBlksRead;
	}

	public long getLocalBlksDirtied() {
		return localBlksDirtied;
	}

	public void setLocalBlksDirtied(long localBlksDirtied) {
		this.localBlksDirtied = localBlksDirtied;
	}

	public long getLocalBlksWritten() {
		return localBlksWritten;
	}

	public void setLocalBlksWritten(long localBlksWritten) {
		this.localBlksWritten = localBlksWritten;
	}

	public long getTempBlksRead() {
		return tempBlksRead;
	}

	public void setTempBlksRead(long tempBlksRead) {
		this.tempBlksRead = tempBlksRead;
	}

	public long getTempBlksWritten() {
		return tempBlksWritten;
	}

	public void setTempBlksWritten(long tempBlksWritten) {
		this.tempBlksWritten = tempBlksWritten;
	}

	public double getBlkReadTime() {
		return blkReadTime;
	}

	public void setBlkReadTime(double blkReadTime) {
		this.blkReadTime = blkReadTime;
	}

	public double getBlkWriteTime() {
		return blkWriteTime;
	}

	public void setBlkWriteTime(double blkWriteTime) {
		this.blkWriteTime = blkWriteTime;
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
		final Statement other = (Statement) obj;
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
