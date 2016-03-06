package org.usfirst.frc.team1076.robot.sensors;

public interface IEncoder {
	double getDistance();
	double getRate();
	void reset();
	  
}
