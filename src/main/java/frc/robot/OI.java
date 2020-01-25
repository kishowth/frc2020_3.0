/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
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
//vision auto Alignment Binding
public boolean getAuto()
{
  return GameControllerConstants.ButtonA();
}

public boolean LimelightON()
{
  return GameControllerConstants.ButtonB();
}


public boolean getLimeLightMode()
{
  return GameControllerConstants.ButtonB();
}






public double getsteer()
{
  return GameControllerConstants.functionOfX();
}

public double getDrive()
{
  return GameControllerConstants.functionOfY();
}



//sensors


















}
