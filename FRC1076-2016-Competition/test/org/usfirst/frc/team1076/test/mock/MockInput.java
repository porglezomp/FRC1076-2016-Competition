package org.usfirst.frc.team1076.test.mock;

import org.usfirst.frc.team1076.robot.gamepad.IInput;

public class MockInput implements IInput {

	double left, right, arm, intake;
	
	public void reset() {
		left = right = arm = intake = 0;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(left, right);
	}

	@Override
	public double armSpeed() {
		return arm;
	}

	@Override
	public double intakeSpeed() {
		return intake;
	}
}
