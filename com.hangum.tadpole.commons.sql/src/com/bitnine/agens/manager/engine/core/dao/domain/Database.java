package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Database implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private long dbid;

	private String name;

	private long size;

	private int age;

	private long xact_commit;

	private long xact_rollback;

	private long blks_read;

	private long blks_hit;

	private long tup_returned;

	private long tup_fetched;

	private long tup_inserted;

	private long tup_updated;

	private long tup_deleted;

	private long confl_tablespace;

	private long confl_lock;

	private long confl_snapshot;

	private long confl_bufferpin;

	private long confl_deadlock;

	private long temp_files;

	private long temp_bytes;

	private long deadlocks;

	private double blk_read_time;

	private double blk_write_time;

	public Database() {
	}


	
	/**
	 * @return the snapid
	 */
	public long getSnapid() {
		return snapid;
	}



	/**
	 * @param snapid the snapid to set
	 */
	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}



	/**
	 * @return the dbid
	 */
	public long getDbid() {
		return dbid;
	}



	/**
	 * @param dbid the dbid to set
	 */
	public void setDbid(long dbid) {
		this.dbid = dbid;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}



	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}



	/**
	 * @return the xact_commit
	 */
	public long getXact_commit() {
		return xact_commit;
	}



	/**
	 * @param xact_commit the xact_commit to set
	 */
	public void setXact_commit(long xact_commit) {
		this.xact_commit = xact_commit;
	}



	/**
	 * @return the xact_rollback
	 */
	public long getXact_rollback() {
		return xact_rollback;
	}



	/**
	 * @param xact_rollback the xact_rollback to set
	 */
	public void setXact_rollback(long xact_rollback) {
		this.xact_rollback = xact_rollback;
	}



	/**
	 * @return the blks_read
	 */
	public long getBlks_read() {
		return blks_read;
	}



	/**
	 * @param blks_read the blks_read to set
	 */
	public void setBlks_read(long blks_read) {
		this.blks_read = blks_read;
	}



	/**
	 * @return the blks_hit
	 */
	public long getBlks_hit() {
		return blks_hit;
	}



	/**
	 * @param blks_hit the blks_hit to set
	 */
	public void setBlks_hit(long blks_hit) {
		this.blks_hit = blks_hit;
	}



	/**
	 * @return the tup_returned
	 */
	public long getTup_returned() {
		return tup_returned;
	}



	/**
	 * @param tup_returned the tup_returned to set
	 */
	public void setTup_returned(long tup_returned) {
		this.tup_returned = tup_returned;
	}



	/**
	 * @return the tup_fetched
	 */
	public long getTup_fetched() {
		return tup_fetched;
	}



	/**
	 * @param tup_fetched the tup_fetched to set
	 */
	public void setTup_fetched(long tup_fetched) {
		this.tup_fetched = tup_fetched;
	}



	/**
	 * @return the tup_inserted
	 */
	public long getTup_inserted() {
		return tup_inserted;
	}



	/**
	 * @param tup_inserted the tup_inserted to set
	 */
	public void setTup_inserted(long tup_inserted) {
		this.tup_inserted = tup_inserted;
	}



	/**
	 * @return the tup_updated
	 */
	public long getTup_updated() {
		return tup_updated;
	}



	/**
	 * @param tup_updated the tup_updated to set
	 */
	public void setTup_updated(long tup_updated) {
		this.tup_updated = tup_updated;
	}



	/**
	 * @return the tup_deleted
	 */
	public long getTup_deleted() {
		return tup_deleted;
	}



	/**
	 * @param tup_deleted the tup_deleted to set
	 */
	public void setTup_deleted(long tup_deleted) {
		this.tup_deleted = tup_deleted;
	}



	/**
	 * @return the confl_tablespace
	 */
	public long getConfl_tablespace() {
		return confl_tablespace;
	}



	/**
	 * @param confl_tablespace the confl_tablespace to set
	 */
	public void setConfl_tablespace(long confl_tablespace) {
		this.confl_tablespace = confl_tablespace;
	}



	/**
	 * @return the confl_lock
	 */
	public long getConfl_lock() {
		return confl_lock;
	}



	/**
	 * @param confl_lock the confl_lock to set
	 */
	public void setConfl_lock(long confl_lock) {
		this.confl_lock = confl_lock;
	}



	/**
	 * @return the confl_snapshot
	 */
	public long getConfl_snapshot() {
		return confl_snapshot;
	}



	/**
	 * @param confl_snapshot the confl_snapshot to set
	 */
	public void setConfl_snapshot(long confl_snapshot) {
		this.confl_snapshot = confl_snapshot;
	}



	/**
	 * @return the confl_bufferpin
	 */
	public long getConfl_bufferpin() {
		return confl_bufferpin;
	}



	/**
	 * @param confl_bufferpin the confl_bufferpin to set
	 */
	public void setConfl_bufferpin(long confl_bufferpin) {
		this.confl_bufferpin = confl_bufferpin;
	}



	/**
	 * @return the confl_deadlock
	 */
	public long getConfl_deadlock() {
		return confl_deadlock;
	}



	/**
	 * @param confl_deadlock the confl_deadlock to set
	 */
	public void setConfl_deadlock(long confl_deadlock) {
		this.confl_deadlock = confl_deadlock;
	}



	/**
	 * @return the temp_files
	 */
	public long getTemp_files() {
		return temp_files;
	}



	/**
	 * @param temp_files the temp_files to set
	 */
	public void setTemp_files(long temp_files) {
		this.temp_files = temp_files;
	}



	/**
	 * @return the temp_bytes
	 */
	public long getTemp_bytes() {
		return temp_bytes;
	}



	/**
	 * @param temp_bytes the temp_bytes to set
	 */
	public void setTemp_bytes(long temp_bytes) {
		this.temp_bytes = temp_bytes;
	}



	/**
	 * @return the deadlocks
	 */
	public long getDeadlocks() {
		return deadlocks;
	}



	/**
	 * @param deadlocks the deadlocks to set
	 */
	public void setDeadlocks(long deadlocks) {
		this.deadlocks = deadlocks;
	}



	/**
	 * @return the blk_read_time
	 */
	public double getBlk_read_time() {
		return blk_read_time;
	}



	/**
	 * @param blk_read_time the blk_read_time to set
	 */
	public void setBlk_read_time(double blk_read_time) {
		this.blk_read_time = blk_read_time;
	}



	/**
	 * @return the blk_write_time
	 */
	public double getBlk_write_time() {
		return blk_write_time;
	}



	/**
	 * @param blk_write_time the blk_write_time to set
	 */
	public void setBlk_write_time(double blk_write_time) {
		this.blk_write_time = blk_write_time;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Database [snapid=" + snapid + ", dbid=" + dbid + ", name=" + name + ", size=" + size + ", age=" + age
				+ ", xact_commit=" + xact_commit + ", xact_rollback=" + xact_rollback + ", blks_read=" + blks_read
				+ ", blks_hit=" + blks_hit + ", tup_returned=" + tup_returned + ", tup_fetched=" + tup_fetched
				+ ", tup_inserted=" + tup_inserted + ", tup_updated=" + tup_updated + ", tup_deleted=" + tup_deleted
				+ ", confl_tablespace=" + confl_tablespace + ", confl_lock=" + confl_lock + ", confl_snapshot="
				+ confl_snapshot + ", confl_bufferpin=" + confl_bufferpin + ", confl_deadlock=" + confl_deadlock
				+ ", temp_files=" + temp_files + ", temp_bytes=" + temp_bytes + ", deadlocks=" + deadlocks
				+ ", blk_read_time=" + blk_read_time + ", blk_write_time=" + blk_write_time + "]";
	}

	
}
