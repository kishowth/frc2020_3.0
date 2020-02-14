/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.GameControllerConstants;;

public class OI {

//chassis bindings
public double getLeftDriveSpeed()
{
  return GameControllerConstants.LEFT_JOYSTICK_Y();
}
public double getRightDriveSpeed()
{
  return GameControllerConstants.RIGHT_JOYSTICK_Y();
}
//vision Auto-Alignment Binding
public boolean getAuto()
{
  return GameControllerConstants.ButtonA();
}
//For pixy
public boolean getFindPowerCells()
{
  return GameControllerConstants.ButtonY();
}
//LED control
public boolean LimelightON()
{
  return GameControllerConstants.ButtonB();
}

public double getShooting()
{
  return GameControllerConstants.RightTrigger();
}

/*--------------------------------------------------------------------------------------------- */
//vision algorithm methods
public double getsteer()
{
  return GameControllerConstants.functionOfX();
}

public double getDrive()
{
  return GameControllerConstants.functionOfY();
} 


public boolean getBallShift()
{
  return GameControllerConstants.LeftJoystickButton();
}




















}
