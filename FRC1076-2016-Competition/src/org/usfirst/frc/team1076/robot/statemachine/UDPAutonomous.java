package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.udp.ISensorData;
import org.usfirst.frc.team1076.udp.SensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class UDPAutonomous extends AutoState {
	private ISensorData sensors;
	
	public UDPAutonomous(ISensorData sensorData, FieldPosition position) {
		sensors = sensorData; 
	}

	@Override
	public boolean shouldChange() {
		// TODO: This seems wrong
		return true;
	}
	
	@Override
	public MotorOutput driveTrainSpeed() {
		sensors.interpretData();
		// TODO: Decide motion based on sensors
		return new MotorOutput(1, 1);
	}
}
