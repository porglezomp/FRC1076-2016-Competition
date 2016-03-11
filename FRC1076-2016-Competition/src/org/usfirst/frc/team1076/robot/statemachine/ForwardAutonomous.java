package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import java.util.concurrent.TimeUnit;

public class ForwardAutonomous extends AutoState {
	long timeStart;
	long timeLimit;
	double speed = 1;
	
	public ForwardAutonomous(int millis, double speed) {
		timeStart = System.nanoTime();
		this.timeLimit = millis;
		this.speed = speed;
	}
	
	public ForwardAutonomous(int millis) {
		this(millis, 1);
	}
	
	@Override
	public boolean shouldChange() {
		return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - timeStart) > timeLimit;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		} else {
			return new MotorOutput(speed, speed);
		}
	}
}
