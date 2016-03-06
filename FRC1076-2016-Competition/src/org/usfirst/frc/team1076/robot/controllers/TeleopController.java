package org.usfirst.frc.team1076.robot.controllers;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.gamepad.IOperatorInput;

public class TeleopController implements IRobotController {

	IDriverInput driverInput;
	IOperatorInput operatorInput;
	
	public TeleopController(IDriverInput driverInput, IOperatorInput operatorInput) {
		this.driverInput = driverInput;
		this.operatorInput = operatorInput;
	}
	
	@Override
	public void robotInit(IRobot robot) { }

	@Override
	public void autonomousInit(IRobot robot) { }

	@Override
	public void autonomousPeriodic(IRobot robot) { }

	@Override
	public void teleopInit(IRobot robot) { }
	
	@Override
	public void teleopPeriodic(IRobot robot) {
		robot.setArmSpeed(operatorInput.armSpeed());
		robot.setIntakeSpeed(operatorInput.intakeSpeed());
		MotorOutput drive = driverInput.driveTrainSpeed();
		robot.setLeftSpeed(drive.left);
		robot.setRightSpeed(drive.right);
		robot.setBrakes(driverInput.brakesApplied());
	}

	@Override
	public void testInit(IRobot robot) {
	}

	@Override
	public void testPeriodic(IRobot robot) {
	}

}
