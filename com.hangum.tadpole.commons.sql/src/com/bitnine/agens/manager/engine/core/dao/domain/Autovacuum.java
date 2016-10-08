package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Autovacuum implements Serializable {

	private static final long serialVersionUID = 1L;

	private long instid;

	private Date start;

	private String database;

	private String schema;

	private String table;

	private int indexScans;

	private int pageRemoved;

	private int pageRemain;

	private long tupRemoved;

	private long tupRemain;

	private long tupDead;

	private int pageHit;

	private int pageMiss;

	private int pageDirty;

	private double readRate;

	private double writeRate;

	private String duration;

	public Autovacuum() {
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

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getIndexScans() {
		return indexScans;
	}

	public void setIndexScans(int indexScans) {
		this.indexScans = indexScans;
	}

	public int getPageRemoved() {
		return pageRemoved;
	}

	public void setPageRemoved(int pageRemoved) {
		this.pageRemoved = pageRemoved;
	}

	public int getPageRemain() {
		return pageRemain;
	}

	public void setPageRemain(int pageRemain) {
		this.pageRemain = pageRemain;
	}

	public long getTupRemoved() {
		return tupRemoved;
	}

	public void setTupRemoved(long tupRemoved) {
		this.tupRemoved = tupRemoved;
	}

	public long getTupRemain() {
		return tupRemain;
	}

	public void setTupRemain(long tupRemain) {
		this.tupRemain = tupRemain;
	}

	public long getTupDead() {
		return tupDead;
	}

	public void setTupDead(long tupDead) {
		this.tupDead = tupDead;
	}

	public int getPageHit() {
		return pageHit;
	}

	public void setPageHit(int pageHit) {
		this.pageHit = pageHit;
	}

	public int getPageMiss() {
		return pageMiss;
	}

	public void setPageMiss(int pageMiss) {
		this.pageMiss = pageMiss;
	}

	public int getPageDirty() {
		return pageDirty;
	}

	public void setPageDirty(int pageDirty) {
		this.pageDirty = pageDirty;
	}

	public double getReadRate() {
		return readRate;
	}

	public void setReadRate(double readRate) {
		this.readRate = readRate;
	}

	public double getWriteRate() {
		return writeRate;
	}

	public void setWriteRate(double writeRate) {
		this.writeRate = writeRate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
		final Autovacuum other = (Autovacuum) obj;
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
