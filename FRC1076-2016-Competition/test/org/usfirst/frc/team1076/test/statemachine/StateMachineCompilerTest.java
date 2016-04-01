package org.usfirst.frc.team1076.test.statemachine;

import static org.junit.Assert.*;

import org.junit.Test;
import org.usfirst.frc.team1076.robot.statemachine.AutoState;
import org.usfirst.frc.team1076.robot.statemachine.ForwardAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.IntakeAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.IntakeElevationAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.LocateTargetAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.NothingAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.RotateAutonomous;
import org.usfirst.frc.team1076.robot.statemachine.StateMachineCompiler;
import org.usfirst.frc.team1076.test.mock.MockSensorData;

public class StateMachineCompilerTest {
	static private final double EPSILON = 1e-12;
	
	@Test
	public void testCompiling() {
		AutoState state = StateMachineCompiler.compile("elevate up ; forward 4 ; rotate left 1 ;"
				+ "forward 1 ; elevate down ; intake 1 in", new MockSensorData());
		assertEquals(NothingAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeElevationAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(ForwardAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(RotateAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(ForwardAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeElevationAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(IntakeAutonomous.class, state.getClass());
	}
	
	@Test
	public void testLocate() {
		AutoState state = StateMachineCompiler.compile("locate left 0.5",
				new MockSensorData());
		assertEquals(NothingAutonomous.class, state.getClass());
		state = state.next();
		assertEquals(LocateTargetAutonomous.class, state.getClass());
		assertEquals(0.5, ((LocateTargetAutonomous) state).speed, EPSILON);
		assertEquals(RotateAutonomous.TurnDirection.Left,
				((LocateTargetAutonomous) state).turnDirection);
	}
}
