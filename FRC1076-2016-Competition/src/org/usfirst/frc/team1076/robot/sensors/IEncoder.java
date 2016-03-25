package org.usfirst.frc.team1076.robot.sensors;

public interface IEncoder {
	double getDistance();
	double getRate();
	double getRaw();
	void reset();
}
