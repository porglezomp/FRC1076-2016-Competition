package org.usfirst.frc.team1076.robot.statemachine;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public interface IAutoState {
	void init();
	IAutoState next();
	boolean shouldChange();
	MotorOutput driveTrainSpeed();
	double armSpeed();
	double intakeSpeed();
}