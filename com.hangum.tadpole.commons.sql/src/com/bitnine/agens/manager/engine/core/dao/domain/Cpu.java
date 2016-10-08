package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Cpu implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String cpu_id;

	private long cpu_user;

	private long cpu_system;

	private long cpu_idle;

	private long cpu_iowait;

	private int overflow_user;

	private int overflow_system;

	private int overflow_idle;

	private int overflow_iowait;

	public Cpu() {
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
	 * @return the cpu_id
	 */
	public String getCpu_id() {
		return cpu_id;
	}

	/**
	 * @param cpu_id the cpu_id to set
	 */
	public void setCpu_id(String cpu_id) {
		this.cpu_id = cpu_id;
	}

	/**
	 * @return the cpu_user
	 */
	public long getCpu_user() {
		return cpu_user;
	}

	/**
	 * @param cpu_user the cpu_user to set
	 */
	public void setCpu_user(long cpu_user) {
		this.cpu_user = cpu_user;
	}

	/**
	 * @return the cpu_system
	 */
	public long getCpu_system() {
		return cpu_system;
	}

	/**
	 * @param cpu_system the cpu_system to set
	 */
	public void setCpu_system(long cpu_system) {
		this.cpu_system = cpu_system;
	}

	/**
	 * @return the cpu_idle
	 */
	public long getCpu_idle() {
		return cpu_idle;
	}

	/**
	 * @param cpu_idle the cpu_idle to set
	 */
	public void setCpu_idle(long cpu_idle) {
		this.cpu_idle = cpu_idle;
	}

	/**
	 * @return the cpu_iowait
	 */
	public long getCpu_iowait() {
		return cpu_iowait;
	}

	/**
	 * @param cpu_iowait the cpu_iowait to set
	 */
	public void setCpu_iowait(long cpu_iowait) {
		this.cpu_iowait = cpu_iowait;
	}

	/**
	 * @return the overflow_user
	 */
	public int getOverflow_user() {
		return overflow_user;
	}

	/**
	 * @param overflow_user the overflow_user to set
	 */
	public void setOverflow_user(int overflow_user) {
		this.overflow_user = overflow_user;
	}

	/**
	 * @return the overflow_system
	 */
	public int getOverflow_system() {
		return overflow_system;
	}

	/**
	 * @param overflow_system the overflow_system to set
	 */
	public void setOverflow_system(int overflow_system) {
		this.overflow_system = overflow_system;
	}

	/**
	 * @return the overflow_idle
	 */
	public int getOverflow_idle() {
		return overflow_idle;
	}

	/**
	 * @param overflow_idle the overflow_idle to set
	 */
	public void setOverflow_idle(int overflow_idle) {
		this.overflow_idle = overflow_idle;
	}

	/**
	 * @return the overflow_iowait
	 */
	public int getOverflow_iowait() {
		return overflow_iowait;
	}

	/**
	 * @param overflow_iowait the overflow_iowait to set
	 */
	public void setOverflow_iowait(int overflow_iowait) {
		this.overflow_iowait = overflow_iowait;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
