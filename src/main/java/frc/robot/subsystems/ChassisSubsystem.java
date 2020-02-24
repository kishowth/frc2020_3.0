/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisCommand;

/**/
public class ChassisSubsystem extends Subsystem {
  
  //rangefinder ultrasonic sensr
  public AnalogInput ultraSonicSensor = new AnalogInput(RobotMap.ultrasonicSensor);

  //chassis encoders
  public Encoder leftSideEncoder = new Encoder(RobotMap.leftDtEncoderA, RobotMap.leftDtEncoderB, false);
  public Encoder rightSideEncoder = new Encoder(RobotMap.rightDtEncoderA, RobotMap.rightDtEncoderB, false);

  //gyro Instantiation
  private AHRS gyro = new AHRS(RobotMap.gyro);

  //chassis motor instantiations
  private Spark left1 = new Spark(RobotMap.leftBackMotor);
  private Spark left2 = new Spark(RobotMap.leftFrontMotor);
  private Spark right1 = new Spark(RobotMap.rightBackMotor);
  private Spark right2 = new Spark(RobotMap.rightFrontMotor); 

  //inversions
  public ChassisSubsystem()
  {
    left1.setInverted(false);
    left2.setInverted(false);
    right1.setInverted(false);
    right2.setInverted(false);
  }

  //Ball shifters
  DoubleSolenoid leftshifter = new DoubleSolenoid(RobotMap.leftDriveShifterA, RobotMap.leftDriveShifterB);
  DoubleSolenoid rightShifter = new DoubleSolenoid(RobotMap.rightDriveShifterA, RobotMap.rightDriveShifterB);


  //grouping motor controllers
  public SpeedControllerGroup leftside = new SpeedControllerGroup(left1, left2);
  public SpeedControllerGroup rightside = new SpeedControllerGroup(right1, right2); 

  public DifferentialDrive m_drive = new DifferentialDrive(leftside, rightside);
  //compressor
  public Compressor compressor = new Compressor();
  
  // when piston is pushed out, robot is on low gear
  // when piston is retracted, robot is on high gear

  public void shiftToSlow()
  {
    leftshifter.set(Value.kForward);
    rightShifter.set(Value.kForward);
  }

  public void shiftToFast()
  {
    leftshifter.set(Value.kReverse);
    rightShifter.set(Value.kReverse);
  }

  public void leaveShifterState()
  {
    leftshifter.set(Value.kOff);
    rightShifter.set(Value.kOff);
    
  }


  //getting original encoder values from both chassis encoders 
  public double leftSideEncoderValueInInches(){
    return leftSideEncoder.getRaw() / RobotConstants.ENCODER_TICKS_IN_INCHES;
  }

  public double rightSideEncoderValueInInches(){
    return rightSideEncoder.getRaw() / RobotConstants.ENCODER_TICKS_IN_INCHES;
  } 


  //read the robot's current angle
  public double getrobotAngle(){
    return gyro.getAngle();
  }
  
  //function to reset gyro
  public void resetGyro()
  {
    gyro.reset();
  } 
  //get the average range of ultrasonic values 
  public double ultValues(){
    return ultraSonicSensor.getAverageValue();
  }

  //all functions in this class that need to run periodically so values can be constantly updated. Call this in chassis command class
  public void periodicCommands()
  {
    leftSideEncoderValueInInches();
    rightSideEncoderValueInInches();
    getrobotAngle();
    ultValues();
  }
  
  //Function to put all sensor values from this subsystem on the dashboard
  public void chassisSystemDashboard()
  {
    SmartDashboard.putNumber("Ultrasonic Values", ultValues());
    SmartDashboard.putNumber("Left Encoder Value (inches)", leftSideEncoderValueInInches());
    SmartDashboard.putNumber("Right Encoder Value (inches)", rightSideEncoderValueInInches());  

    SmartDashboard.putNumber("Gyro", getrobotAngle());
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChassisCommand());
  }
}
