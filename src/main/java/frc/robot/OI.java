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
public boolean getIntakeInput()
{
  return GameControllerConstants.RightTrigger();
}

public boolean getIntakeOutput()
{
  return GameControllerConstants.LeftTrigger();
}



//climb 
public boolean getClimbUp()
{
  return GameControllerConstants.LeftBumper();
}

public boolean getClimbRelease()
{
  return GameControllerConstants.RightBumper();
}

public boolean getPistonPull()
{
  return GameControllerConstants.ButtonX();
}
public boolean getPistonPush(){
  return GameControllerConstants.ButtonA();
}

public boolean getwinchOut()
{
  return GameControllerConstants.ButtonB();
}

public boolean getwinchIn()
{
  return GameControllerConstants.ButtonY();
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
