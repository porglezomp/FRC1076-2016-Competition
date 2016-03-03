package org.usfirst.frc.team1076.robot.statemachine;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;

public class RotateAutonomous extends AutoState {
	long timeStart;
	long timeLimit;
	double speed = 1;
	enum TurnDirection { Left, Right }
	TurnDirection turnDirection;

	public RotateAutonomous(int millis, double speed, TurnDirection turnDirection) {
		timeStart = System.nanoTime();
		this.timeLimit = millis;
		this.speed = speed;
		this.turnDirection = turnDirection;
	}
	
	public RotateAutonomous(int millis, TurnDirection turnDirection) {
		this(millis, 1, turnDirection);
	}
	
	@Override
	public void init() { }

	@Override
	public boolean shouldChange() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) > timeLimit;
	}

	@Override
	public MotorOutput driveTrainSpeed() {
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		} else {
			switch (turnDirection) {
			case Left:
				return new MotorOutput(-speed, speed);
			case Right:
				return new MotorOutput(speed, -speed);
			default:
				return new MotorOutput(0, 0);
			}
		}
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
