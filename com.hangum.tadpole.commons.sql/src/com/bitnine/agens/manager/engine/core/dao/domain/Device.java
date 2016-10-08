package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;

public class Device implements Serializable {

	private static final long serialVersionUID = 1L;

	private long snapid;

	private String deviceMajor;

	private String deviceMinor;

	private String deviceName;

	private long deviceReadsector;

	private long deviceReadtime;

	private long deviceWritesector;

	private long deviceWritetime;

	private long deviceIoqueue;

	private long deviceIototaltime;

	private double deviceRspsMax;

	private double deviceWspsMax;

	private int overflowDrs;

	private int overflowDrt;

	private int overflowDws;

	private int overflowDwt;

	private int overflowDit;

	private String deviceTblspaces;

	public Device() {
	}

	public long getSnapid() {
		return snapid;
	}

	public void setSnapid(long snapid) {
		this.snapid = snapid;
	}

	public String getDeviceMajor() {
		return deviceMajor;
	}

	public void setDeviceMajor(String deviceMajor) {
		this.deviceMajor = deviceMajor;
	}

	public String getDeviceMinor() {
		return deviceMinor;
	}

	public void setDeviceMinor(String deviceMinor) {
		this.deviceMinor = deviceMinor;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public long getDeviceReadsector() {
		return deviceReadsector;
	}

	public void setDeviceReadsector(long deviceReadsector) {
		this.deviceReadsector = deviceReadsector;
	}

	public long getDeviceReadtime() {
		return deviceReadtime;
	}

	public void setDeviceReadtime(long deviceReadtime) {
		this.deviceReadtime = deviceReadtime;
	}

	public long getDeviceWritesector() {
		return deviceWritesector;
	}

	public void setDeviceWritesector(long deviceWritesector) {
		this.deviceWritesector = deviceWritesector;
	}

	public long getDeviceWritetime() {
		return deviceWritetime;
	}

	public void setDeviceWritetime(long deviceWritetime) {
		this.deviceWritetime = deviceWritetime;
	}

	public long getDeviceIoqueue() {
		return deviceIoqueue;
	}

	public void setDeviceIoqueue(long deviceIoqueue) {
		this.deviceIoqueue = deviceIoqueue;
	}

	public long getDeviceIototaltime() {
		return deviceIototaltime;
	}

	public void setDeviceIototaltime(long deviceIototaltime) {
		this.deviceIototaltime = deviceIototaltime;
	}

	public double getDeviceRspsMax() {
		return deviceRspsMax;
	}

	public void setDeviceRspsMax(double deviceRspsMax) {
		this.deviceRspsMax = deviceRspsMax;
	}

	public double getDeviceWspsMax() {
		return deviceWspsMax;
	}

	public void setDeviceWspsMax(double deviceWspsMax) {
		this.deviceWspsMax = deviceWspsMax;
	}

	public int getOverflowDrs() {
		return overflowDrs;
	}

	public void setOverflowDrs(int overflowDrs) {
		this.overflowDrs = overflowDrs;
	}

	public int getOverflowDrt() {
		return overflowDrt;
	}

	public void setOverflowDrt(int overflowDrt) {
		this.overflowDrt = overflowDrt;
	}

	public int getOverflowDws() {
		return overflowDws;
	}

	public void setOverflowDws(int overflowDws) {
		this.overflowDws = overflowDws;
	}

	public int getOverflowDwt() {
		return overflowDwt;
	}

	public void setOverflowDwt(int overflowDwt) {
		this.overflowDwt = overflowDwt;
	}

	public int getOverflowDit() {
		return overflowDit;
	}

	public void setOverflowDit(int overflowDit) {
		this.overflowDit = overflowDit;
	}

	public String getDeviceTblspaces() {
		return deviceTblspaces;
	}

	public void setDeviceTblspaces(String deviceTblspaces) {
		this.deviceTblspaces = deviceTblspaces;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (snapid ^ (snapid >>> 32));
		result = prime * result + ((deviceMajor == null) ? 0 : deviceMajor.hashCode());
		result = prime * result + ((deviceMinor == null) ? 0 : deviceMinor.hashCode());

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
		final Device other = (Device) obj;
		if (snapid != other.snapid)
			return false;
		if (deviceMajor == null) {
			if (other.deviceMajor != null)
				return false;
		} else if (!deviceMajor.equals(other.deviceMajor))
			return false;
		if (deviceMinor == null) {
			if (other.deviceMinor != null)
				return false;
		} else if (!deviceMinor.equals(other.deviceMinor))
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"snapid=" + "'" + snapid + "'" + ", " + 
			"deviceMajor=" + "'" + deviceMajor + "'" + ", " + 
			"deviceMinor=" + "'" + deviceMinor + "'" + 
			")";
	}
	
}
