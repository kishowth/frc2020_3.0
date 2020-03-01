/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class RobotMap {

  /*
   * Different Robot Mechanisms
   * 
   * -->Chassis: 4 neos
   * -->Intake: 1 775Pro
   * -->Storage System: 2 775Pros
   * -->Shooter: 4 775Pros 
   * -->Spin mech: 1 775Pro
   * -->climb: 2 775Pros
   */

  //---PCM Ports---
  public static int leftDriveShifterA = 0;
  public static int leftDriveShifterB = 1;
  public static int rightDriveShifterA = 2;
  public static int rightDriveShifterB = 3;

  //---PWM Ports---

  //DriveTrain
  public static int leftBackMotor = 0;
  public static int leftFrontMotor = 1;
  public static int rightBackMotor = 2;
  public static int rightFrontMotor = 3;

  //Intake 
  public static int intakeMotor = 4;

  //Storage 
  public static int horizontalStorageMotor = 5;
  public static int driverWheelMotor = 6;


  //shooter Motors
  public static int leftShooterMotor = 6;
  public static int rightShooterMotor = 7;


  //Colour wheel mech
  public static int colourMech = 10;

  //Climb
  public static int leftClimbArm = 11;
  public static int rightClimbArm = 12;
  

  //---Analog and digital ports----
  public static int ultrasonicSensor = 0;
  public static int leftDtEncoderA = 2;
  public static int leftDtEncoderB = 3;
  public static int rightDtEncoderA = 0;
  public static int rightDtEncoderB = 1;
  public static int colourWheelEncoderA = 5;
  public static int colourWheelEncoderB = 6;

  //other ports 
  public static I2C.Port colourSensor = I2C.Port.kOnboard; 
  public static Port gyro = SerialPort.Port.kMXP;






}
