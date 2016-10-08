package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Table implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private long tbl;

	private long nsp;

	private Date date2;

	private long tbs;

	private String name;

	private long toastrelid;

	private long toastidxid;

	private String relkind;

	private int relpages;

	private String reltuples;

	private String reloptions;

	private long size;

	private long seqScan;

	private long seqTupRead;

	private long idxScan;

	private long idxTupFetch;

	private long n_tup_ins;

	private long n_tup_upd;

	private long n_tup_del;

	private long n_tup_hot_upd;

	private long n_live_tup;

	private long n_dead_tup;

	private long n_mod_since_analyze;

	private long heapBlksRead;

	private long heapBlksHit;

	private long idxBlksRead;

	private long idxBlksHit;

	private long toastBlksRead;

	private long toastBlksHit;

	private long tidxBlksRead;

	private long tidxBlksHit;

	private Date lastVacuum;

	private Date lastAutovacuum;

	private Date lastAnalyze;

	private Date lastAutoanalyze;

	public Table() {
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

	public long getNsp() {
		return nsp;
	}

	public void setNsp(long nsp) {
		this.nsp = nsp;
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

	public long getToastrelid() {
		return toastrelid;
	}

	public void setToastrelid(long toastrelid) {
		this.toastrelid = toastrelid;
	}

	public long getToastidxid() {
		return toastidxid;
	}

	public void setToastidxid(long toastidxid) {
		this.toastidxid = toastidxid;
	}

	public String getRelkind() {
		return relkind;
	}

	public void setRelkind(String relkind) {
		this.relkind = relkind;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSeqScan() {
		return seqScan;
	}

	public void setSeqScan(long seqScan) {
		this.seqScan = seqScan;
	}

	public long getSeqTupRead() {
		return seqTupRead;
	}

	public void setSeqTupRead(long seqTupRead) {
		this.seqTupRead = seqTupRead;
	}

	public long getIdxScan() {
		return idxScan;
	}

	public void setIdxScan(long idxScan) {
		this.idxScan = idxScan;
	}

	public long getIdxTupFetch() {
		return idxTupFetch;
	}

	public void setIdxTupFetch(long idxTupFetch) {
		this.idxTupFetch = idxTupFetch;
	}

	public long getN_tup_ins() {
		return n_tup_ins;
	}

	public void setN_tup_ins(long n_tup_ins) {
		this.n_tup_ins = n_tup_ins;
	}

	public long getN_tup_upd() {
		return n_tup_upd;
	}

	public void setN_tup_upd(long n_tup_upd) {
		this.n_tup_upd = n_tup_upd;
	}

	public long getN_tup_del() {
		return n_tup_del;
	}

	public void setN_tup_del(long n_tup_del) {
		this.n_tup_del = n_tup_del;
	}

	public long getN_tup_hot_upd() {
		return n_tup_hot_upd;
	}

	public void setN_tup_hot_upd(long n_tup_hot_upd) {
		this.n_tup_hot_upd = n_tup_hot_upd;
	}

	public long getN_live_tup() {
		return n_live_tup;
	}

	public void setN_live_tup(long n_live_tup) {
		this.n_live_tup = n_live_tup;
	}

	public long getN_dead_tup() {
		return n_dead_tup;
	}

	public void setN_dead_tup(long n_dead_tup) {
		this.n_dead_tup = n_dead_tup;
	}

	public long getN_mod_since_analyze() {
		return n_mod_since_analyze;
	}

	public void setN_mod_since_analyze(long n_mod_since_analyze) {
		this.n_mod_since_analyze = n_mod_since_analyze;
	}

	public long getHeapBlksRead() {
		return heapBlksRead;
	}

	public void setHeapBlksRead(long heapBlksRead) {
		this.heapBlksRead = heapBlksRead;
	}

	public long getHeapBlksHit() {
		return heapBlksHit;
	}

	public void setHeapBlksHit(long heapBlksHit) {
		this.heapBlksHit = heapBlksHit;
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

	public long getToastBlksRead() {
		return toastBlksRead;
	}

	public void setToastBlksRead(long toastBlksRead) {
		this.toastBlksRead = toastBlksRead;
	}

	public long getToastBlksHit() {
		return toastBlksHit;
	}

	public void setToastBlksHit(long toastBlksHit) {
		this.toastBlksHit = toastBlksHit;
	}

	public long getTidxBlksRead() {
		return tidxBlksRead;
	}

	public void setTidxBlksRead(long tidxBlksRead) {
		this.tidxBlksRead = tidxBlksRead;
	}

	public long getTidxBlksHit() {
		return tidxBlksHit;
	}

	public void setTidxBlksHit(long tidxBlksHit) {
		this.tidxBlksHit = tidxBlksHit;
	}

	public Date getLastVacuum() {
		return lastVacuum;
	}

	public void setLastVacuum(Date lastVacuum) {
		this.lastVacuum = lastVacuum;
	}

	public Date getLastAutovacuum() {
		return lastAutovacuum;
	}

	public void setLastAutovacuum(Date lastAutovacuum) {
		this.lastAutovacuum = lastAutovacuum;
	}

	public Date getLastAnalyze() {
		return lastAnalyze;
	}

	public void setLastAnalyze(Date lastAnalyze) {
		this.lastAnalyze = lastAnalyze;
	}

	public Date getLastAutoanalyze() {
		return lastAutoanalyze;
	}

	public void setLastAutoanalyze(Date lastAutoanalyze) {
		this.lastAutoanalyze = lastAutoanalyze;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + (int) (dbid ^ (dbid >>> 32));
		result = prime * result + (int) (tbl ^ (tbl >>> 32));

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
		final Table other = (Table) obj;
		if (snapid != other.snapid)
			return false;
		if (dbid != other.dbid)
			return false;
		if (tbl != other.tbl)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"dbid=" + "'" + dbid + "'" + ", " + 
			"tbl=" + "'" + tbl + "'" + 
			")";
	}
	
}
