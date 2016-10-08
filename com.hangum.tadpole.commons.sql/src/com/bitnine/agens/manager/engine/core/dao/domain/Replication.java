package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Replication implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private int procpid;

	private long usesysid;

	private String usename;

	private String applicationName;

	private String clientAddr;

	private String clientHostname;

	private int clientPort;

	private Date backendStart;

	private String backendXmin;

	private String state;

	private String currentLocation;

	private String sentLocation;

	private String writeLocation;

	private String flushLocation;

	private String replayLocation;

	private int syncPriority;

	private String syncState;

	public Replication() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public int getProcpid() {
		return procpid;
	}

	public void setProcpid(int procpid) {
		this.procpid = procpid;
	}

	public long getUsesysid() {
		return usesysid;
	}

	public void setUsesysid(long usesysid) {
		this.usesysid = usesysid;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getClientHostname() {
		return clientHostname;
	}

	public void setClientHostname(String clientHostname) {
		this.clientHostname = clientHostname;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public Date getBackendStart() {
		return backendStart;
	}

	public void setBackendStart(Date backendStart) {
		this.backendStart = backendStart;
	}

	public String getBackendXmin() {
		return backendXmin;
	}

	public void setBackendXmin(String backendXmin) {
		this.backendXmin = backendXmin;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getSentLocation() {
		return sentLocation;
	}

	public void setSentLocation(String sentLocation) {
		this.sentLocation = sentLocation;
	}

	public String getWriteLocation() {
		return writeLocation;
	}

	public void setWriteLocation(String writeLocation) {
		this.writeLocation = writeLocation;
	}

	public String getFlushLocation() {
		return flushLocation;
	}

	public void setFlushLocation(String flushLocation) {
		this.flushLocation = flushLocation;
	}

	public String getReplayLocation() {
		return replayLocation;
	}

	public void setReplayLocation(String replayLocation) {
		this.replayLocation = replayLocation;
	}

	public int getSyncPriority() {
		return syncPriority;
	}

	public void setSyncPriority(int syncPriority) {
		this.syncPriority = syncPriority;
	}

	public String getSyncState() {
		return syncState;
	}

	public void setSyncState(String syncState) {
		this.syncState = syncState;
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
		final Replication other = (Replication) obj;
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
