package org.usfirst.frc.team1076.robot.sensors;

public interface IDistanceEncoder extends IEncoder {
	void updateDistance();
	double getDistance();
}
