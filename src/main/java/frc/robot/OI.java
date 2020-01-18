/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;

public class OI {

Joystick driverController = new Joystick(0);
Joystick operatorController = new Joystick(1); 

//controller variables
public double LEFT_JOY_Y(){
  return driverController.getRawAxis(1);
}

public double RIGHT_JOY_Y(){
  return driverController.getRawAxis(5);
}

public boolean ButtonA(){
  return driverController.getRawButton(1);
}
public boolean ButtonB(){
  return driverController.getRawButton(2);
}

public boolean ButtonY(){
  return driverController.getRawButton(3);
}












//GAME Controls
public Joystick getdriverController(){
   return driverController;
}

public Joystick getOperatorController(){
  return operatorController;
}

public double getLeftDriveSpeed(){
  return LEFT_JOY_Y();
}
public double getRightDriveSpeed(){
  return RIGHT_JOY_Y();
}


public double getsteer()
{
  return driverController.getX();
}

public double getDrive()
{
  return driverController.getY();
}

public boolean getAuto()
{
  return ButtonA();
}

public boolean LimelightON()
{
  return ButtonB();
}

public boolean LimelightOFF()
{
  return ButtonY();
}



//sensors


















}
