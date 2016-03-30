package org.usfirst.frc.team1076.robot.statemachine;

public class BrakeAutonomous extends AutoState {
	boolean workDone = false;
	boolean brakeStatus;
	public BrakeAutonomous(boolean brakeStatus) {
		this.brakeStatus = brakeStatus;
	}
	
	@Override
	public boolean shouldChange() {
		return workDone;
	}
	
	@Override
	public boolean setBrakes() {
		workDone = true;
		return brakeStatus;
	}
}
