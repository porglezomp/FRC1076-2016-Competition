package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.ISensorData;
import org.usfirst.frc.team1076.udp.SensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class UDPAutonomous extends AutoState {
	private ISensorData sensors;
	public UDPAutonomous(IChannel channel, FieldPosition position) {
		sensors = new SensorData(channel, position); 
	}

	public void init() { }
	
	public boolean shouldChange() {
		return true;
	}
	
	public MotorOutput driveTrainSpeed() {
		sensors.interpretData();
		// TODO: Decide motion based on sensors
		return new MotorOutput(1, 1);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
