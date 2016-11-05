package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Instance implements Serializable {

	private static final long serialVersionUID = 1L;

	private long instid;

	private String name;

	private String hostname;

	private int port;

	private String pg_version;

	private long xlog_file_size;
	private long count;
	private long sum;
	private long max;
	private String max_time;

	public Instance() {
	}

	/**
	 * @return the instid
	 */
	public long getInstid() {
		return instid;
	}

	/**
	 * @param instid the instid to set
	 */
	public void setInstid(long instid) {
		this.instid = instid;
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
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the pg_version
	 */
	public String getPg_version() {
		return pg_version;
	}

	/**
	 * @param pg_version the pg_version to set
	 */
	public void setPg_version(String pg_version) {
		this.pg_version = pg_version;
	}

	/**
	 * @return the xlog_file_size
	 */
	public long getXlog_file_size() {
		return xlog_file_size;
	}

	/**
	 * @param xlog_file_size the xlog_file_size to set
	 */
	public void setXlog_file_size(long xlog_file_size) {
		this.xlog_file_size = xlog_file_size;
	}
	
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the sum
	 */
	public long getSum() {
		return sum;
	}

	/**
	 * @param sum the sum to set
	 */
	public void setSum(long sum) {
		this.sum = sum;
	}

	/**
	 * @return the max
	 */
	public long getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(long max) {
		this.max = max;
	}

	/**
	 * @return the max_time
	 */
	public String getMax_time() {
		return max_time;
	}

	/**
	 * @param max_time the max_time to set
	 */
	public void setMax_time(String max_time) {
		this.max_time = max_time;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"instid=" + "'" + instid + "'" + 
			")";
	}
	
}
