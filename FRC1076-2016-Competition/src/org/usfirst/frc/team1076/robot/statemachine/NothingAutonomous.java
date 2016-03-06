package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;

public class NothingAutonomous extends AutoState {
	@Override
	public void init() { }
	
	@Override
	public boolean shouldChange() {
		return false;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(0, 0);
	}
	
	@Override
	public double armSpeed() {
		return 0;
	}
	
	@Override
	public double intakeSpeed() {
		return 0;
	}
}
