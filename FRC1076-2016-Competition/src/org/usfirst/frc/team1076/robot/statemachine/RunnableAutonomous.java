package org.usfirst.frc.team1076.robot.statemachine;
import org.usfirst.frc.team1076.robot.controllers.AutoRun;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;

public class RunnableAutonomous extends AutoState {
	private AutoRun customWorker;

	public RunnableAutonomous(AutoRun autoRun) {
		customWorker = autoRun;
	}

	public void init() {
		customWorker.init();
	}
	
	public boolean shouldChange() {
		return customWorker.shouldChange();
	}

	public MotorOutput driveTrainSpeed() {
		return customWorker.driveTrainSpeed();
	}

	public double armSpeed() {
		return customWorker.armSpeed();
	}

	public double intakeSpeed() {
		return customWorker.intakeSpeed();
	}

}
