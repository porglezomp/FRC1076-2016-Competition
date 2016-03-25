package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IOperatorInput.IntakeRaiseState;

public class IntakeElevationAutonomous extends AutoState {
	private boolean workDone = false;
	private IntakeRaiseState raiseState;
	
	public IntakeElevationAutonomous(IntakeRaiseState raiseState) {
		this.raiseState = raiseState;
	}
	
	@Override
	public boolean shouldChange() {
		return workDone;
	}
	
	@Override
	public IntakeRaiseState intakeRaiseState() {
		System.out.println("Request " + raiseState);
		workDone = true;
		return raiseState;
	}
}
