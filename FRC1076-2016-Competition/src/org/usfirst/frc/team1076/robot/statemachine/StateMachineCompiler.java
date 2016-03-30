package org.usfirst.frc.team1076.robot.statemachine;

import org.usfirst.frc.team1076.robot.gamepad.IOperatorInput.IntakeRaiseState;
import org.usfirst.frc.team1076.udp.ISensorData;

public class StateMachineCompiler {
	public static AutoState compile(String program, ISensorData sensorData) {
		AutoState state = new NothingAutonomous();
		for (String segment : program.split(";")) {
			segment = segment.trim();
			String[] parts = segment.split(" ");
			double time, speed;
			try {
				AutoState nextState;
				switch (parts[0].toLowerCase()) {
				case "f":
				case "forward":
					time = Double.parseDouble(parts[1]);
					speed = 1;
					if (parts.length > 2) {
						speed = Double.parseDouble(parts[2]);
					}
					nextState = new ForwardAutonomous((int) (time * 1000), -speed);
					break;
				case "r":
				case "rotate":
					RotateAutonomous.TurnDirection direction;
					if (parts[1].toLowerCase().equals("left")) {
						direction = RotateAutonomous.TurnDirection.Left;
					} else if (parts[1].toLowerCase().equals("right")) {
						direction = RotateAutonomous.TurnDirection.Right;
					} else {
						throw new Exception("Unexpected rotation direction " + parts[1]);
					}
					time = Double.parseDouble(parts[2]);
					speed = 1;
					if (parts.length > 3) {
						speed = Double.parseDouble(parts[3]);
					}
					nextState = new RotateAutonomous((int) (time * 1000), -speed, direction);
					break;
				case "v":
				case "vision":
					time = Double.parseDouble(parts[1]);
					speed = 1;
					if (parts.length > 2) {
						speed = Double.parseDouble(parts[2]);
					}
					nextState = new VisionAutonomous((int) (time * 1000), -speed, sensorData);
				case "i":
				case "intake":
					time = Double.parseDouble(parts[1]);
					speed = 1;
					if (parts.length > 2) {
						if (parts[2].toLowerCase().equals("in")) {
							speed = -1;
						} else if (parts[2].toLowerCase().equals("out")) {
							speed = 1;
						} else {
							speed = Double.parseDouble(parts[2]);
						}
					}
					nextState = new IntakeAutonomous((int) (time * 1000), speed);
					break;
				case "e":
				case "elevate":
					if (parts[1].toLowerCase().equals("up")) {
						nextState = new IntakeElevationAutonomous(IntakeRaiseState.Lowered);
					} else if (parts[1].toLowerCase().equals("down")) {
						nextState = new IntakeElevationAutonomous(IntakeRaiseState.Raised);
					} else {
						throw new Exception("Elevation must be 'up' or 'down', got " + parts[1]);
					}
					break;
				case "b":
				case "brake":
					if (parts[1].toLowerCase().equals("on") ||
							parts[1].toLowerCase().equals("true")) {
						nextState = new BrakeAutonomous(true);
					} else if (parts[1].toLowerCase().equals("off") ||
							parts[1].toLowerCase().equals("false")) {
						nextState = new BrakeAutonomous(false);
					} else {
						throw new Exception("Brakes must be 'on' or 'off', got " + parts[1]);
					}
					break;
				case "n":
				case "nothing":
					nextState = new NothingAutonomous();
					break;
				default:
					throw new Exception("Unrecognized operation " + parts[0]);
				}
				state.addNext(nextState);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Error in " + segment);
				continue;
			}
		}
		return state;
	}
}
