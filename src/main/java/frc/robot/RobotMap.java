/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.I2C;

public class RobotMap {

  // PWM Ports
  public static int leftBackMotor = 0;
  public static int leftFrontMotor = 1;
  public static int rightBackMotor = 2;
  public static int rightFrontMotor = 3;
  public static int colourMech = 4; 

  //other ports
  public static I2C.Port i2cPort = I2C.Port.kOnboard;  

  //PCM 


  //Analog and digital ports
  public static int ultrasonicSensor = 0; 
  public static int leftDtEncoderA = 1;
  public static int leftDtEncoderB = 2;
  public static int rightDtEncoderA = 3;
  public static int rightDtEncoderB = 4; 
  public static int colourWheelEncoderA = 5;  
  public static int colourWheelEncoderB = 6;



  
}
