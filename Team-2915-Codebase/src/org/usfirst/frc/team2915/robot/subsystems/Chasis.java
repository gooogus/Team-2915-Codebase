package org.usfirst.frc.team2915.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2915.robot.ControlMap;
import org.usfirst.frc.team2915.robot.RobotMap;
import org.usfirst.frc.team2915.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Chasis extends Subsystem {
    public static Talon talonRightA;
    public static Talon talonRightB;
    public static Talon talonLeftA;
    public static Talon talonLeftB;
    
	public static Encoder rightDriveEncoder;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	protected void initialize() {
		talonRightA = new Talon(RobotMap.motorPortRightA);
		talonRightB = new Talon(RobotMap.motorPortRightB);
		talonLeftA = new Talon(RobotMap.motorPortLeftA);
		talonLeftB = new Talon(RobotMap.motorPortLeftB);
		
		rightDriveEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		
		//The circumference of the wheel is 31.416 inches.
		//Per pulse, the robot should move 7.854 inches because it is a k4x encoder.
		rightDriveEncoder.setDistancePerPulse(7.854);
		
		//correct direction of left talons to avoid confusing negatives
		talonLeftA.setInverted(true);
		talonLeftB.setInverted(true);
		
	}
	
    public void initDefaultCommand() {
    	//This makes DriveWithJoystick the default control for the chassis
    	//It is overriden by another command that requires the chassis
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public void driveWithJoystick(Joystick joy){
    	talonRightA.set(joy.getRawAxis(ControlMap.axisSpeed) - joy.getRawAxis(ControlMap.axisTurn));
    	talonRightB.set(joy.getRawAxis(ControlMap.axisSpeed) - joy.getRawAxis(ControlMap.axisTurn));
    	talonLeftA.set(joy.getRawAxis(ControlMap.axisSpeed) - joy.getRawAxis(ControlMap.axisTurn));
    	talonLeftB.set(joy.getRawAxis(ControlMap.axisSpeed) - joy.getRawAxis(ControlMap.axisTurn));
    }
    
    public void stop(){
    	talonRightA.set(0);
    	talonRightB.set(0);
    	talonLeftA.set(0);
    	talonLeftB.set(0);
    }
    
}

