package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public class ForwardAutonomous implements IAutoState {
	int counter = 0; // replace with encoders
	int limit = 100;
	public ForwardAutonomous(int limit) {
		this.limit = limit;
	}
	
	public void init() {

	}
	
	public IAutoState next() {
		return new NothingAutonomous();
	}
	
	public boolean shouldChange() {
		return counter > 100;
	}
	
	public MotorOutput driveTrainSpeed() {
		counter++;
		return new MotorOutput(0.2, 0.2);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
