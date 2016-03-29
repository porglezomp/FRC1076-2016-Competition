package org.usfirst.frc.team1076.robot.controllers;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.robot.physical.GearShifter;

public class AutoController implements IRobotController {
	AutoState autoState;
	GearShifter gearShifter = new GearShifter();
	
	public AutoController(AutoState mode) {
		this.autoState = mode;
	}

	@Override
	public void teleopInit(IRobot robot) { }

	@Override
	public void teleopPeriodic(IRobot robot) { }

	@Override
	public void autonomousInit(IRobot robot) {
		gearShifter.shiftLow(robot);
		if (autoState != null) {
			autoState.init();
		}
	}
	
	private final double RPM_MIN = 260;
	private final double RPM_MAX = 280;
    public double motorSpeed = 7;
	
	@Override
	public void autonomousPeriodic(IRobot robot) {
		gearShifter.shiftLow(robot);
		robot.getSensorData().interpretData();
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
		robot.setIntakeElevation(autoState.intakeRaiseState());
		
	    if (robot.getSensorData().getLidarRpm() < RPM_MIN) {
	    	motorSpeed *= 1.01;
	    } else if (robot.getSensorData().getLidarRpm() > RPM_MAX) {
	    	motorSpeed *= 0.99;
	    }
	    robot.setLidarSpeed(motorSpeed);
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
