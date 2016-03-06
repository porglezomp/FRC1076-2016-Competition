package org.usfirst.frc.team1076.robot.sensors;

public interface IGyro {
	void calibrate();
	double getAngle();
	double getRate();
}
