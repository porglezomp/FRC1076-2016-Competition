package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.sensors.IAccelerometer;
import org.usfirst.frc.team1076.udp.SensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class UDPAutonomous extends AutoState {
	private int port;
	private SensorData sensors;
	private IAccelerometer accel;
	private AutoState nextState = null;
	
	public UDPAutonomous(int port, FieldPosition position) {
		this.port = port;
		sensors = new SensorData(this.port, position); 
	}

	public void init() { }
	
	public boolean shouldChange() {
		return false;
	}
	
	public MotorOutput driveTrainSpeed() {
		sensors.interpretData();
		if (Math.abs(sensors.getHeading() - sensors.currentHeading()) > 1) {
			return new MotorOutput(1, -1);
		}
		return new MotorOutput(1, 1);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
