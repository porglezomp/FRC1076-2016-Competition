package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public interface IRobot {
	enum SolenoidValue { Off, Forward, Reverse; }
	void setLeftSpeed(double speed);
	void setRightSpeed(double speed);
	void setArmSpeed(double speed);
	void setIntakeSpeed(double speed);
	void setGear(SolenoidValue value);
	MotorOutput getMotorSpeed();
}
