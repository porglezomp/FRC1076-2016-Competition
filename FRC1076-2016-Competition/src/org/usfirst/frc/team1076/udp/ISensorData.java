package org.usfirst.frc.team1076.udp;

import org.usfirst.frc.team1076.robot.sensors.IGyro;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public interface ISensorData {
	void interpretData();
	void setLidar(double h, double r);
	void setVision(double h, double r);
	FieldPosition getFieldPosition();
	void setFieldPosition(FieldPosition pos);
	
	double getLidarRpm();
	double getLidarHeading();
	double getLidarRange();
	double getVisionHeading();
	double getVisionRange();
	IChannel getChannel();
	double getLeftSideBack();
	double getRightSideBack();
	double getLeftSideFront();
	double getRightSideFront();
	double getLeftFront();
	double getRightFront();
	IGyro getGyro();
}
