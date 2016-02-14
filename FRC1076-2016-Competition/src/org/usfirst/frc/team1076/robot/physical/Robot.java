
package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.controllers.IRobotController;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements IRobot {	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	IRobotController teleopController;
	IRobotController autoController;
	
	@Override
    public void robotInit() {
    	if (teleopController != null) {
    		teleopController.robotInit(this);
    	} else {
    		System.out.println("Teleop Controller on Robot is null in robotInit()");
    	}
    	if (autoController != null) {
    		autoController.robotInit(this);
    	} else {
    		System.out.println("Autonomous Controller on Robot is null in robotInit()");
    	}
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
    public void autonomousInit() {
    	if (autoController != null) {
    		autoController.autonomousInit(this);
    	} else {
    		System.out.println("Autonomous Controller on Robot is null in autonomousInit()");
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
	@Override
    public void autonomousPeriodic() {
    	if (autoController != null) {
    		autoController.autonomousPeriodic(this);
    	} else {
    		System.out.println("Autonomous Controller on Robot is null in autonomousPeriodic()");
    	}
    }

    @Override
    public void teleopInit() {
    	if (teleopController != null) {
    		teleopController.teleopInit(this);
    	} else {
    		System.out.println("Teleop Controller on Robot is null in teleopInit()");
    	}
    }
    
    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        if (teleopController != null) {
        	teleopController.teleopPeriodic(this);
        } else {
    		System.out.println("Teleop Controller on Robot is null in teleopPeriodic()");
    	}
    }

	@Override
	public void setLeftSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRightSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArmSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntakeSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}
}
