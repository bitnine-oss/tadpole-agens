package com.bitnine.agens.manager.engine.core.dao.domain.custom;

/**
 *  Statics 항목의 wal statistics  
 *  
 * @author hangum
 *
 */
public class WALStatistics {
	int write_total = 0;
	int write_speed = 0;
	
	public WALStatistics() {
	}

	public int getWrite_total() {
		return write_total;
	}

	public void setWrite_total(int write_total) {
		this.write_total = write_total;
	}

	public int getWrite_speed() {
		return write_speed;
	}

	public void setWrite_speed(int write_speed) {
		this.write_speed = write_speed;
	}
	
}
