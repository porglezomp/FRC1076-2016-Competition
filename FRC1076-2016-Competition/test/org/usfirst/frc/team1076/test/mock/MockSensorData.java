package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.ISensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class MockSensorData implements ISensorData {
	public double heading, distance;
	public double lidarRPM;
	public double leftSideBack, rightSideBack, leftSideFront, rightSideFront, leftFront, rightFront;
	public IChannel channel;
	public FieldPosition position;
	
	public void interpretData() {
		// Should this do anything?
	}
	
	public void set(double h, double d) {
		heading = h;
		distance = d;
	}

	public FieldPosition getFieldPosition() { return position; }
	public void setFieldPosition(FieldPosition pos) { position = pos; }
	public double getLidarRpm() { return lidarRPM; }
	public double getHeading() { return heading; }
	public double getDistance() { return distance; }
	
	public IChannel getChannel() { return channel; }
	public double getLeftSideBack() { return leftSideBack; }
	public double getRightSideBack() { return rightSideBack; }
	public double getLeftSideFront() { return leftSideFront; }
	public double getRightSideFront() { return rightSideFront; }
	public double getLeftFront() { return leftFront; }
	public double getRightFront() { return rightFront; }
}
