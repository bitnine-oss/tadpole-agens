package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

/**
 * Activity
 * 
 * @author hangum
 *
 */
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private double idle;

	private double idle_in_xact;

	private double waiting;

	private double running;

	private int max_backends;

	public Activity() {
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
	 * @return the idle
	 */
	public double getIdle() {
		return idle;
	}


	/**
	 * @param idle the idle to set
	 */
	public void setIdle(double idle) {
		this.idle = idle;
	}


	/**
	 * @return the idle_in_xact
	 */
	public double getIdle_in_xact() {
		return idle_in_xact;
	}


	/**
	 * @param idle_in_xact the idle_in_xact to set
	 */
	public void setIdle_in_xact(double idle_in_xact) {
		this.idle_in_xact = idle_in_xact;
	}


	/**
	 * @return the waiting
	 */
	public double getWaiting() {
		return waiting;
	}


	/**
	 * @param waiting the waiting to set
	 */
	public void setWaiting(double waiting) {
		this.waiting = waiting;
	}


	/**
	 * @return the running
	 */
	public double getRunning() {
		return running;
	}


	/**
	 * @param running the running to set
	 */
	public void setRunning(double running) {
		this.running = running;
	}


	/**
	 * @return the max_backends
	 */
	public int getMax_backends() {
		return max_backends;
	}


	/**
	 * @param max_backends the max_backends to set
	 */
	public void setMax_backends(int max_backends) {
		this.max_backends = max_backends;
	}


	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + 
			")";
	}
	
}
