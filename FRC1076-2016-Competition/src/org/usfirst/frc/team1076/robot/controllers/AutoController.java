package org.usfirst.frc.team1076.robot.controllers;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.udp.Channel;
import org.usfirst.frc.team1076.udp.IChannel;
import org.usfirst.frc.team1076.udp.SensorData;
import org.usfirst.frc.team1076.udp.SensorData.FieldPosition;

public class AutoController implements IRobotController {
	SensorData sensorData;
	AutoState autoState;
	
	public AutoController(AutoState mode) {
		this.autoState = mode;
		IChannel channel = new Channel(5880);
		sensorData = new SensorData(channel, FieldPosition.Right);
	}
	
	@Override
	public void robotInit(IRobot robot) { }

	@Override
	public void teleopInit(IRobot robot) { }

	@Override
	public void teleopPeriodic(IRobot robot) { }

	@Override
	public void autonomousInit(IRobot robot) { }
	
	@Override
	public void autonomousPeriodic(IRobot robot) {
		sensorData.interpretData();
		if (autoState.shouldChange()) {
			autoState = autoState.next();
			if (autoState == null) {
				autoState = new NothingAutonomous();
			}
			autoState.init(); // Initialize the new autonomous mode
		}
		robot.setArmSpeed(autoState.armSpeed());
		robot.setIntakeSpeed(autoState.intakeSpeed());
		MotorOutput drive = autoState.driveTrainSpeed();
		robot.setLeftSpeed(drive.left);
		robot.setRightSpeed(drive.right);		
	}

	@Override
	public void testInit(IRobot robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPeriodic(IRobot robot) {
		// TODO Auto-generated method stub
		
	}
}