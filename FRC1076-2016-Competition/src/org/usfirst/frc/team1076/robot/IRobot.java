package org.usfirst.frc.team1076.robot;

public interface IRobot {
	public void setLeftSpeed(double speed);
	public void setRightSpeed(double speed);
	public void setArmSpeed(double speed);
	public void setIntakeSpeed(double speed);
	public void setBrakes(boolean enabled);
	void setLidarSpeed(double speed);
}
