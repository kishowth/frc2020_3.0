/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;


import frc.robot.Robot;

public class ColourWheelArmCommand extends Command {
  public ColourWheelArmCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.colourWheelArmSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}


  boolean est_red = false;
  boolean est_green = false;
  boolean est_blue = false;
  boolean est_yellow = false;
 // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {

    Robot.colourWheelArmSubsystem.colourWheelSystemDashboard();

    Color KRED = Robot.colourWheelArmSubsystem.k_red;
    Color KBLUE = Robot.colourWheelArmSubsystem.k_blue;
    Color KGREEN = Robot.colourWheelArmSubsystem.k_green;
    Color KYELLOW = Robot.colourWheelArmSubsystem.k_yellow;
    Color detectedColor = Robot.colourWheelArmSubsystem.colourDetected();

    //making the colours
    Robot.colourWheelArmSubsystem.color_Match.addColorMatch(KRED); 
    Robot.colourWheelArmSubsystem.color_Match.addColorMatch(KBLUE); 
    Robot.colourWheelArmSubsystem.color_Match.addColorMatch(KGREEN); 
    Robot.colourWheelArmSubsystem.color_Match.addColorMatch(KYELLOW); 

    String colorString; 
    ColorMatchResult match = Robot.colourWheelArmSubsystem.color_Match.matchClosestColor(detectedColor);
    
    if(match.color == KBLUE)
    {
      colorString = "The colour is blue.";
      est_blue = true;

      est_red = false;
      est_green = false;
      est_yellow = false;
    }
    else if(match.color == KRED)
    {
      colorString = "The colour is Red";
      est_red = true;

      est_blue = false;
      est_green = false;
      est_yellow = false;
      
    }
    else if(match.color == KGREEN)
    {
      colorString = "The colour is Green";
      est_green = true;
      
      est_blue = false;
      est_red = false;
      est_yellow = false;
    }
    else if (match.color == KYELLOW)
    {
      colorString = "The colour is yellow"; 
      est_yellow = true;
      est_blue = false;
      est_red = false;
      est_green = false;
    }
    else
    {
      colorString = "There is no colour.";
    }

    SmartDashboard.putString("COLOUR DETECTED", colorString);

    SmartDashboard.putBoolean("BLUE DETECTED", est_blue );
    SmartDashboard.putBoolean("RED DETECTED", est_red );
    SmartDashboard.putBoolean("GREEN DETECTED", est_green );
    SmartDashboard.putBoolean("YELLOW DETECTED", est_yellow);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
