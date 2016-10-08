package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Index implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long idx;

	private long tbl;

	private Date date2;

	private long tbs;

	private String name;

	private long relam;

	private int relpages;

	private String reltuples;

	private String reloptions;

	private String isunique;

	private String isprimary;

	private String isclustered;

	private String isvalid;

	private String indkey;

	private String indexdef;

	private long size;

	private long idxScan;

	private long idxTupRead;

	private long idxTupFetch;

	private long idxBlksRead;

	private long idxBlksHit;

	public Index() {
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

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getTbl() {
		return tbl;
	}

	public void setTbl(long tbl) {
		this.tbl = tbl;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public long getTbs() {
		return tbs;
	}

	public void setTbs(long tbs) {
		this.tbs = tbs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRelam() {
		return relam;
	}

	public void setRelam(long relam) {
		this.relam = relam;
	}

	public int getRelpages() {
		return relpages;
	}

	public void setRelpages(int relpages) {
		this.relpages = relpages;
	}

	public String getReltuples() {
		return reltuples;
	}

	public void setReltuples(String reltuples) {
		this.reltuples = reltuples;
	}

	public String getReloptions() {
		return reloptions;
	}

	public void setReloptions(String reloptions) {
		this.reloptions = reloptions;
	}

	public String getIsunique() {
		return isunique;
	}

	public void setIsunique(String isunique) {
		this.isunique = isunique;
	}

	public String getIsprimary() {
		return isprimary;
	}

	public void setIsprimary(String isprimary) {
		this.isprimary = isprimary;
	}

	public String getIsclustered() {
		return isclustered;
	}

	public void setIsclustered(String isclustered) {
		this.isclustered = isclustered;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getIndkey() {
		return indkey;
	}

	public void setIndkey(String indkey) {
		this.indkey = indkey;
	}

	public String getIndexdef() {
		return indexdef;
	}

	public void setIndexdef(String indexdef) {
		this.indexdef = indexdef;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getIdxScan() {
		return idxScan;
	}

	public void setIdxScan(long idxScan) {
		this.idxScan = idxScan;
	}

	public long getIdxTupRead() {
		return idxTupRead;
	}

	public void setIdxTupRead(long idxTupRead) {
		this.idxTupRead = idxTupRead;
	}

	public long getIdxTupFetch() {
		return idxTupFetch;
	}

	public void setIdxTupFetch(long idxTupFetch) {
		this.idxTupFetch = idxTupFetch;
	}

	public long getIdxBlksRead() {
		return idxBlksRead;
	}

	public void setIdxBlksRead(long idxBlksRead) {
		this.idxBlksRead = idxBlksRead;
	}

	public long getIdxBlksHit() {
		return idxBlksHit;
	}

	public void setIdxBlksHit(long idxBlksHit) {
		this.idxBlksHit = idxBlksHit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + (int) (dbid ^ (dbid >>> 32));
		result = prime * result + (int) (idx ^ (idx >>> 32));

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
		final Index other = (Index) obj;
		if (snapid != other.snapid)
			return false;
		if (dbid != other.dbid)
			return false;
		if (idx != other.idx)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"dbid=" + "'" + dbid + "'" + ", " + 
			"idx=" + "'" + idx + "'" + 
			")";
	}
	
}
