/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.GameControllerConstants;

public class OI {

//Driver bindings

//chassis bindings
public double getLeftDriveSpeed()
{
  return GameControllerConstants.LEFT_JOYSTICK_Y();
}
public double getRightDriveSpeed()
{
  return GameControllerConstants.RIGHT_JOYSTICK_Y();
}

//shifters
public boolean getBallShift1()
{
  return GameControllerConstants.LeftJoystickButton();
}

public boolean getBallShift2()
{
  return GameControllerConstants.RightJoystickButton();
}

//Intake 
public double getIntakeInput()
{
  return GameControllerConstants.RightTrigger();
}

public double getIntakeOutput()
{
  return GameControllerConstants.LeftTrigger();
}



//Pixy Ball Tracking

public boolean getPixyTracking()
{
  return GameControllerConstants.RightBumper();
}



//OPERATOR 

//shooter
public double getShooterOuttake()
{
  return GameControllerConstants.oprRightTrigger();
}


//Limelight Auto-align
public boolean getShooterAutoAlign()
{
  return GameControllerConstants.oprButtonY();
} 

//Storage

public boolean getStorageSystemInward(){
  return GameControllerConstants.oprLeftBumper();
}
public boolean getStorageSystemOutward(){
  return GameControllerConstants.oprRightBumper();
}

public boolean getleadingShooterWheelINPUT(){
  return GameControllerConstants.oprButtonX();
}
public boolean getleadingShooterWheelOUTPUT(){
  return GameControllerConstants.oprButtonA();
}








































}
