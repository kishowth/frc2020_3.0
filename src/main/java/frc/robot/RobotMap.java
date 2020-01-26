/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.hal.sim.mockdata.AnalogInDataJNI;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.I2C;

public class RobotMap {

  // Chassis motor ports
  public static int leftBackMotor = 0;
  public static int leftFrontMotor = 1;
  public static int rightBackMotor = 2;
  public static int rightFrontMotor = 3;

  public static I2C.Port i2cPort = I2C.Port.kOnboard;  

<<<<<<< HEAD
 
=======
  public static int seeifthisworks = 7;

  public static int ultrasonicSensor = 0;
>>>>>>> 92e871c38045cb477b63951dc27fd4cb4233bd01
  
}
