package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public class ForwardAutonomous extends AutoState {
	int counter = 0; // replace with encoders or timer
	int limit = 100;
	double speed = 1;
	
	public ForwardAutonomous(int millis, double speed) {
		this.limit = millis / 20;
		this.speed = speed;
	}
	
	public ForwardAutonomous(int millis) {
		this(millis, 1);
	}
	
	public void init() { }
	
	public boolean shouldChange() {
		return counter > limit;
	}
	
	public MotorOutput driveTrainSpeed() {
		counter++;
		return new MotorOutput(speed, speed);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
