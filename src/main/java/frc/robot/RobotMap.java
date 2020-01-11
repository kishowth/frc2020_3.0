/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {

  // Chassis motor ports
  public static int leftBackMotor = 0;
  public static int leftFrontMotor = 1;
  public static int rightBackMotor = 2;
  public static int rightFrontMotor = 3;

  public static I2C.Port i2cPort = I2C.Port.kOnboard; 

 
  
}
