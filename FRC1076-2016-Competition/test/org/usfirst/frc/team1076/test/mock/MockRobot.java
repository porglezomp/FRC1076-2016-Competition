package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class MockRobot implements IRobot {
	public double left, right, arm, intake;
	public Value gear;
	
	@Override
	public void setLeftSpeed(double speed) {
		left = speed;
	}

	@Override
	public void setRightSpeed(double speed) {
		right = speed;
	}

	@Override
	public void setArmSpeed(double speed) {
		arm = speed;
	}

	@Override
	public void setIntakeSpeed(double speed) {
		intake = speed;
	}

	@Override
	public void setGear(Value value) {
		gear = value;
	}

	@Override
	public MotorOutput getMotorSpeed() {
		return new MotorOutput(left, right);
	}

}
