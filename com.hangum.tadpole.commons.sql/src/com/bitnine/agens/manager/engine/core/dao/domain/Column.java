package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long tbl;

	private int attnum;

	private String date2;

	private String name;

	private String type;

	private int stattarget;

	private String storage;

	private String isnotnull;

	private String isdropped;

	private int avgWidth;

	private String n_distinct;

	private String correlation;

	public Column() {
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

	public long getTbl() {
		return tbl;
	}

	public void setTbl(long tbl) {
		this.tbl = tbl;
	}

	public int getAttnum() {
		return attnum;
	}

	public void setAttnum(int attnum) {
		this.attnum = attnum;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStattarget() {
		return stattarget;
	}

	public void setStattarget(int stattarget) {
		this.stattarget = stattarget;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getIsnotnull() {
		return isnotnull;
	}

	public void setIsnotnull(String isnotnull) {
		this.isnotnull = isnotnull;
	}

	public String getIsdropped() {
		return isdropped;
	}

	public void setIsdropped(String isdropped) {
		this.isdropped = isdropped;
	}

	public int getAvgWidth() {
		return avgWidth;
	}

	public void setAvgWidth(int avgWidth) {
		this.avgWidth = avgWidth;
	}

	public String getN_distinct() {
		return n_distinct;
	}

	public void setN_distinct(String n_distinct) {
		this.n_distinct = n_distinct;
	}

	public String getCorrelation() {
		return correlation;
	}

	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + (int) (dbid ^ (dbid >>> 32));
		result = prime * result + (int) (tbl ^ (tbl >>> 32));
		result = prime * result + attnum;

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
		final Column other = (Column) obj;
		if (snapid != other.snapid)
			return false;
		if (dbid != other.dbid)
			return false;
		if (tbl != other.tbl)
			return false;
		if (attnum != other.attnum)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"dbid=" + "'" + dbid + "'" + ", " + 
			"tbl=" + "'" + tbl + "'" + ", " + 
			"attnum=" + "'" + attnum + "'" + 
			")";
	}
	
}
