package org.usfirst.frc.team1076.robot.statemachine;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;

public abstract class AutoState {
	AutoState nextState = null;

	public AutoState setNext(AutoState nextState) {
		if (this.nextState == null) {
			this.nextState = nextState;
		} else {
			this.nextState.setNext(nextState);
		}
		return this;
	}

	public AutoState next() {
		return nextState;
	}

	public abstract void init();
	public abstract boolean shouldChange();
	public abstract MotorOutput driveTrainSpeed();
	public abstract double armSpeed();
	public abstract double intakeSpeed();
}