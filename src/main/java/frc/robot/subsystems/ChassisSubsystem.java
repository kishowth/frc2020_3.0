/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisCommand;

/**/
public class ChassisSubsystem extends Subsystem {
  
  //rangefinder ultrasonic sensr
  public AnalogInput ultraSonicSensor = new AnalogInput(RobotMap.ultrasonicSensor);

  //chassis encoders
  public Encoder leftSideEncoder = new Encoder(RobotMap.leftDtEncoderA, RobotMap.leftDtEncoderB);
  public Encoder rightSideEncoder = new Encoder(RobotMap.rightDtEncoderA, RobotMap.rightDtEncoderB);

  //gyro Instantiation
  public AHRS gyro = new AHRS(RobotMap.gyro);

  //chassis motor instantiations
  private VictorSP left1 = new VictorSP(RobotMap.leftBackMotor);
  private VictorSP left2 = new VictorSP(RobotMap.leftFrontMotor);
  private VictorSP right1 = new VictorSP(RobotMap.rightBackMotor);
  private VictorSP right2 = new VictorSP(RobotMap.rightFrontMotor);

  //grouping motor controllers
  public SpeedControllerGroup leftside = new SpeedControllerGroup(left1, left2);
  public SpeedControllerGroup rightside = new SpeedControllerGroup(right1, right2);

  //setting up a differential drive
  public DifferentialDrive m_Drive = new DifferentialDrive(rightside, leftside);  


  //getting original encoder values from both chassis encoders 
  public double leftSideEncoderValueInInches(){
    return leftSideEncoder.getDistance() * RobotConstants.ENCODER_TICKS_IN_INCHES;
  }

  public double rightSideEncoderValueInInches(){
    return rightSideEncoder.getDistance() * RobotConstants.ENCODER_TICKS_IN_INCHES;
  } 

  //read the robot's current angle
  public double getrobotAngle(){
    return gyro.getAngle();
  }
 
  //get the average range of ultrasonic values 
  public double ultValues(){
    return ultraSonicSensor.getAverageValue();
  }

  //all functions in this class that need to run periodically so values can be constantly updated
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
