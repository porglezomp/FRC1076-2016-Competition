package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.RotateAutonomous.TurnDirection;
import org.usfirst.frc.team1076.udp.ISensorData;

public class LocateTargetAutonomous extends AutoState {
	private static final double FAST_THRESHOLD = 6;
	private static final double FAST_SPEED = 1;
	private static final double SLOW_THRESHOLD = 1;
	private static final double SLOW_SPEED = 0.4;
	ISensorData sensorData;
	public final TurnDirection turnDirection;
	public final double speed;
	
	public LocateTargetAutonomous(ISensorData sensorData, TurnDirection turnDirection, double speed) {
		this.sensorData = sensorData;
		this.turnDirection = turnDirection;
		this.speed  = speed;
	}
	
	@Override
	public boolean shouldChange() {
		if (sensorData.isVisionStale()) {
			return false;
		}
		return Math.abs(sensorData.getVisionHeading()) <= SLOW_THRESHOLD;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		double angle = sensorData.getVisionHeading();
		
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		}
		
		// If the vision measurement is stale, we can't rely on it for choosing our motion
		if (!sensorData.isVisionStale()) {
			// Turn fast if we're far away
			// The angle is the angle that the target is away from us, so if 
			if (angle >= FAST_THRESHOLD) {
				return new MotorOutput(FAST_SPEED, -FAST_SPEED);
			} else if (angle <= -FAST_THRESHOLD) {
				return new MotorOutput(-FAST_SPEED, FAST_SPEED);
			}
		
			// Turn slower as we get closer
			if (angle >= SLOW_THRESHOLD) {
				return new MotorOutput(SLOW_SPEED, -SLOW_SPEED);
			} else if (angle <= -SLOW_THRESHOLD) {
				return new MotorOutput(-SLOW_SPEED, SLOW_SPEED);
			}
			
			return new MotorOutput(0, 0);
		}
		
		// If we can't see the target, we should just turn in the direction we
		// were asked to turn.
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
