/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisCommand;

/**/
public class ChassisSubsystem extends Subsystem {
  
  //rangefinder ultrasonic sensr
  public AnalogInput ultraSonicSensor = new AnalogInput(RobotMap.ultrasonicSensor);
  //chassis encoders
  public Encoder leftSideEncoder = new Encoder(RobotMap.leftDtEncoderA, RobotMap.leftDtEncoderB);
  public Encoder rightSideEncoder = new Encoder(RobotMap.rightDtEncoderA, RobotMap.rightDtEncoderB);

  //chassis motor instantiations
  private VictorSP left1 = new VictorSP(RobotMap.leftBackMotor);
  private VictorSP left2 = new VictorSP(RobotMap.leftFrontMotor);
  private VictorSP right1 = new VictorSP(RobotMap.rightBackMotor);
  private VictorSP right2 = new VictorSP(RobotMap.rightFrontMotor);

  //grouping motor controllers
  public SpeedControllerGroup leftside = new SpeedControllerGroup(left1, left2);
  public SpeedControllerGroup rightside = new SpeedControllerGroup(right1, right2);

  //differential drives
  public DifferentialDrive m_Drive = new DifferentialDrive(rightside, leftside);  


  //getting original encoder values from both chassis encoders
  public double leftSideEncoderValue(){
    return leftSideEncoder.getDistance();
  }

  public double rightSideEncoderValue(){
    return rightSideEncoder.getDistance();
  } 

 
  //20 encoder ticks is 1 inch
  double LeftEncoderValueInInches = leftSideEncoderValue() * 20;
  double RightEncoderValueInInches = rightSideEncoderValue() * 20; 


  //get the average range of ultrasonic values 
  public double ultValues(){
    return ultraSonicSensor.getAverageValue();
  }

  //Function to put all sensor values from this subsystem on the dashboard
  public void chassisSystemDashboard()
  {
    SmartDashboard.putNumber("Ultrasonic Values", ultValues());
    SmartDashboard.putNumber("Left Encoder Value", LeftEncoderValueInInches);
    SmartDashboard.putNumber("Right Encoder Value", RightEncoderValueInInches);
  }

 
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChassisCommand());
  }
}
