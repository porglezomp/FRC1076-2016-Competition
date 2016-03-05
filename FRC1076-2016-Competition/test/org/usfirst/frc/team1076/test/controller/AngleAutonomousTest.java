package org.usfirst.frc.team1076.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.gamepad.IInput.MotorOutput;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.AngleAutonomous;


public class AngleAutonomousTest {

	private static final double EPSILON = 1e-12;
	private static final double PI = Math.PI;

	@Test
	public void testNext() {
		AutoState auto = new AngleAutonomous(0, 1);
		assertSame(null, auto.next());
		auto.setNext(auto);
		assertSame(auto, auto.next());
	}
	
	@Test
	public void testShouldNotChange() {
		AutoState autoClock = new AngleAutonomous(2*PI, 0.1);
		assertEquals(false, autoClock.shouldChange());
		
		AutoState autoCounterclock = new AngleAutonomous(-2*PI, 0.1);
		assertEquals(false, autoCounterclock.shouldChange());
	}
	
	@Test
	public void testClockwiseRotation() {
		AngleAutonomous auto = new AngleAutonomous(PI/4, 1);
		MotorOutput motorOutput = auto.driveTrainSpeed();

		try {
		    Thread.sleep(1000);                
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		motorOutput = auto.driveTrainSpeed();
		
		assertSame(true, auto.shouldChange());

	}
	
	@Test
	public void testCounterClockwiseRotation() {
		AngleAutonomous auto = new AngleAutonomous(-PI/4, 1);
		MotorOutput motorOutput = auto.driveTrainSpeed();
		
		try {
		    Thread.sleep(1000);                
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		motorOutput = auto.driveTrainSpeed();
		
		assertSame(true, auto.shouldChange());
	}
	
	@Test
	public void testNoArmMotion() {
		AutoState auto = new AngleAutonomous(0, 1);
		assertEquals(0, auto.armSpeed(), EPSILON);
	}
	
	@Test
	public void testNoIntakeMotion() {
		AutoState auto = new AngleAutonomous(0, 1);
		assertEquals(0, auto.intakeSpeed(), EPSILON);
	}

}
