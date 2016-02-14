package org.usfirst.frc.team1076.robot.gamepad;

public interface IInput {
	public class MotorOutput {
		public final double left, right;
		
		public MotorOutput(double left, double right) {
			this.left = left;
			this.right = right;
		}
	}
	
	MotorOutput driveTrainSpeed();
	double armSpeed();
	double intakeSpeed();
}
