package org.usfirst.frc.team1076.robot.controllers;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.gamepad.IInput;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.IAutoState;

public class AutoController implements IRobotController {

	IAutoState autoState;
	
	public AutoController(IAutoState mode) {
		this.autoState = mode;
	}
	
	@Override
	public void robotInit(IRobot robot) {
	}

	@Override
	public void teleopInit(IRobot robot) {
	}

	@Override
	public void teleopPeriodic(IRobot robot) {
	}

	@Override
	public void autonomousInit(IRobot robot) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void autonomousPeriodic(IRobot robot) {
		if(autoState.shouldChange()) {
			autoState = autoState.next();
			autoState.init(); // Initialize the new autonomous mode
		}
		robot.setArmSpeed(autoState.armSpeed());
		robot.setIntakeSpeed(autoState.intakeSpeed());
		MotorOutput drive = autoState.driveTrainSpeed();
		robot.setLeftSpeed(drive.left);
		robot.setRightSpeed(drive.right);		
	}
}