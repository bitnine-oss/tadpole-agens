package com.bitnine.agens.manager.engine.core.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	private long instid;

	private Date timestamp;

	private String username;

	private String database;

	private int pid;

	private String clientAddr;

	private String sessionId;

	private long sessionLineNum;

	private String psDisplay;

	private Date sessionStart;

	private String vxid;

	private long xid;

	private String elevel;

	private String sqlstate;

	private String message;

	private String detail;

	private String hint;

	private String query;

	private int queryPos;

	private String context;

	private String userQuery;

	private int userQueryPos;

	private String location;

	private String applicationName;

	public Log() {
	}

	public long getInstid() {
		return instid;
	}

	public void setInstid(long instid) {
		this.instid = instid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getSessionLineNum() {
		return sessionLineNum;
	}

	public void setSessionLineNum(long sessionLineNum) {
		this.sessionLineNum = sessionLineNum;
	}

	public String getPsDisplay() {
		return psDisplay;
	}

	public void setPsDisplay(String psDisplay) {
		this.psDisplay = psDisplay;
	}

	public Date getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(Date sessionStart) {
		this.sessionStart = sessionStart;
	}

	public String getVxid() {
		return vxid;
	}

	public void setVxid(String vxid) {
		this.vxid = vxid;
	}

	public long getXid() {
		return xid;
	}

	public void setXid(long xid) {
		this.xid = xid;
	}

	public String getElevel() {
		return elevel;
	}

	public void setElevel(String elevel) {
		this.elevel = elevel;
	}

	public String getSqlstate() {
		return sqlstate;
	}

	public void setSqlstate(String sqlstate) {
		this.sqlstate = sqlstate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getQueryPos() {
		return queryPos;
	}

	public void setQueryPos(int queryPos) {
		this.queryPos = queryPos;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(String userQuery) {
		this.userQuery = userQuery;
	}

	public int getUserQueryPos() {
		return userQueryPos;
	}

	public void setUserQueryPos(int userQueryPos) {
		this.userQueryPos = userQueryPos;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (instid ^ (instid >>> 32));

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
		final Log other = (Log) obj;
		if (instid != other.instid)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
			"(" + 
			"instid=" + "'" + instid + "'" + 
			")";
	}
	
}
