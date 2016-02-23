package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public class NothingAutonomous extends AutoState {
	public void init() { }
	
	public boolean shouldChange() {
		return false;
	}
	
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(0, 0);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
