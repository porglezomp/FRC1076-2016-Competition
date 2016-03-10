package org.usfirst.frc.team1076.robot.statemachine;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.gamepad.IOperatorInput.IntakeRaiseState;

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

	public void init() { }
	
	public abstract boolean shouldChange();
	
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(0, 0);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}

	public IntakeRaiseState intakeRaiseState() {
		return IntakeRaiseState.Neutral;
	}
}