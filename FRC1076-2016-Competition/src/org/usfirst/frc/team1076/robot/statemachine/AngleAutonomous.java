package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.sensors.IGyro;

/**
 * AngleAutonomous takes an input angle and returns MotorOutputs
 * which rotate the robot on the spot. The angle must be in radians.
 * 
 * In the future, AngleAutonomous will use a PID-based system to turn accurately.
 * @author a2aaron
 *
 */
public class AngleAutonomous extends AutoState {
	double speed;
	double currAngle;
	double endAngle;
	private double deltaAngle;
	IGyro gyro; // TODO: Use SensorData instead of IGyro
	
	public AngleAutonomous(double angle, double speed, IGyro gyro) {
		this.gyro = gyro;
		this.deltaAngle = angle;
		this.currAngle = gyro.getAngle();
		this.endAngle = currAngle + angle;
		this.speed = speed;
	}

	public void init() {  }

	@Override
	public boolean shouldChange() {
		if (deltaAngle > 0) { // clockwise
			return currAngle >= endAngle;
		} else { // counter-clockwise
			return currAngle <= endAngle;
		}
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		currAngle = gyro.getAngle();
		
		if (shouldChange()) {
			return new MotorOutput(0, 0);
		}
		
		if (deltaAngle > 0) { // clockwise 					
			return new MotorOutput(speed, -speed);
		} else if (deltaAngle < 0) { // counter-clockwise 					
			return new MotorOutput(-speed, speed);
		} else {
			return new MotorOutput(0, 0);
		}
	}

	public double getAngleTurned() {
		return currAngle - endAngle;
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
