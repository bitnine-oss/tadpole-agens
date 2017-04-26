package org.eclipse.rap.addons.chart.basic;

public class TimeDataItem2D {
	protected String x;
	protected double y;
	protected String text;
	
	public TimeDataItem2D() {
	}
	
	public TimeDataItem2D(String x, double y) {
		this.x = x;
		this.y = y;
	}

	public TimeDataItem2D(String x, double y, String text) {
		this.x = x;
		this.y = y;
		
		this.text = text;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
