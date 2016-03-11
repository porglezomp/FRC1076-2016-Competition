package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.udp.ISensorData;

public interface IRobot {
	void setLeftSpeed(double speed);
	void setRightSpeed(double speed);
	void setArmSpeed(double speed);
	void setIntakeSpeed(double speed);
	void setBrakes(boolean enabled);
	void setLidarSpeed(double speed);
	ISensorData getSensorData(); 
}
