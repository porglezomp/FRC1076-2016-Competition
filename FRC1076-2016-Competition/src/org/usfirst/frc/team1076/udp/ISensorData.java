package org.usfirst.frc.team1076.udp;

import org.usfirst.frc.team1076.robot.sensors.IGyro;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public interface ISensorData {
	void interpretData();
	void set(double h, double d);
	FieldPosition getFieldPosition();
	void setFieldPosition(FieldPosition pos);
	
	double getLidarRpm();
	double getHeading();
	double getDistance();
	IChannel getChannel();
	double getLeftSideBack();
	double getRightSideBack();
	double getLeftSideFront();
	double getRightSideFront();
	double getLeftFront();
	double getRightFront();
	IGyro getGyro();
}
