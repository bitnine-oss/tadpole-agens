package com.bitnine.agens.manager.engine.core.dao.domain.custom;

/**
 * Instance process raion
 * 
 * @author hangum
 *
 */
public class InstanceProcessRation {
	double idle;
	double idleInXact;
	double waiting;
	double running;
	
	public InstanceProcessRation() {
	}

	public double getIdle() {
		return idle;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}

	public double getIdleInXact() {
		return idleInXact;
	}

	public void setIdleInXact(double idleInXact) {
		this.idleInXact = idleInXact;
	}

	public double getWaiting() {
		return waiting;
	}

	public void setWaiting(double waiting) {
		this.waiting = waiting;
	}

	public double getRunning() {
		return running;
	}

	public void setRunning(double running) {
		this.running = running;
	}
	
	
}
