package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.sensors.IGyro;
import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.ISensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class MockSensorData implements ISensorData {
	public double lidarHeading, lidarRange;
	public double visionHeading, visionRange;
	public double lidarRPM;
	public double leftSideBack, rightSideBack, leftSideFront, rightSideFront, leftFront, rightFront;
	public IGyro gyro;
	public IChannel channel;
	public FieldPosition position;
	
	@Override
	public void interpretData() {
		// Should this do anything?
	}

	@Override public FieldPosition getFieldPosition() { return position; }
	@Override public void setFieldPosition(FieldPosition pos) { position = pos; }
	@Override public double getLidarRpm() { return lidarRPM; }
	@Override public double getLidarHeading() { return lidarHeading; }
	@Override public double getLidarRange() { return lidarRange; }
	@Override public double getVisionHeading() { return visionHeading; }
	@Override public double getVisionRange() { return visionRange; }

	@Override public IChannel getChannel() { return channel; }
	@Override public double getLeftSideBack() { return leftSideBack; }
	@Override public double getRightSideBack() { return rightSideBack; }
	@Override public double getLeftSideFront() { return leftSideFront; }
	@Override public double getRightSideFront() { return rightSideFront; }
	@Override public double getLeftFront() { return leftFront; }
	@Override public double getRightFront() { return rightFront; }
	@Override public IGyro getGyro() { return gyro; }

	@Override
	public void setLidar(double h, double r) {
		this.lidarHeading = h;
		this.lidarRange = r;
	}

	@Override
	public void setVision(double h, double r) {
		this.visionHeading = h;
		this.visionRange = r;
	}

	@Override public void sendAttackColor(String destination, String color) { }
}
