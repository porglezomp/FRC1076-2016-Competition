package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.sensors.IAccelerometer;
import org.usfirst.frc.team1076.udp.SensorData;

public class UDPAutonomous implements IAutoState {
	private int port;
	private SensorData sensors;
	private IAccelerometer accel;
	
	public UDPAutonomous(int port) {
		this.port = port;
		sensors = new SensorData(this.port);
	}
	
	public void init() { }
	
	public IAutoState next() {
		return this;
	}
	
	public boolean shouldChange() {
		return false;
	}
	
	public MotorOutput driveTrainSpeed() {
		return new MotorOutput(0, 0);
	}
	
	public double armSpeed() {
		return 0;
	}
	
	public double intakeSpeed() {
		return 0;
	}
}
