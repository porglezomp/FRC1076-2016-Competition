package org.usfirst.frc.team1076.robot;
import org.usfirst.frc.team1076.robot.gamepad.IDriverInput.MotorOutput;
import org.usfirst.frc.team1076.udp.SensorData;

public interface IRobot {
	enum SolenoidValue { Off, Forward, Reverse; }
	void setLeftSpeed(double speed);
	void setRightSpeed(double speed);
	void setArmSpeed(double speed);
	void setIntakeSpeed(double speed);
	void setBrakes(boolean enabled);
	void setLidarSpeed(double speed);
	SensorData getSensorData(); 
	void setGear(SolenoidValue value);
	MotorOutput getMotorSpeed();
}
