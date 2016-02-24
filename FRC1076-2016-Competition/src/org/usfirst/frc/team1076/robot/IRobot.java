package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface IRobot {
	void setLeftSpeed(double speed);
	void setRightSpeed(double speed);
	void setArmSpeed(double speed);
	void setIntakeSpeed(double speed);
	void setGear(Value value);
	MotorOutput getMotorSpeed();
}
