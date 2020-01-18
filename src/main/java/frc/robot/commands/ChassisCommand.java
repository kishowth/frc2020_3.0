/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ChassisCommand extends Command {
  public ChassisCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.chassisSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    boolean redDetected = false;
    boolean blueDetected = false;
    boolean greenDetected = false;
    boolean yellowDetected = false;


    double redValue = Robot.chassisSubsystem.red;
    double blueValue = Robot.chassisSubsystem.blue;
    double greenValue = Robot.chassisSubsystem.green;



    SmartDashboard.putBoolean("Red Detected", redDetected);
    SmartDashboard.putBoolean("Blue Detected", blueDetected);
    SmartDashboard.putBoolean("Green Detected", greenDetected);
    SmartDashboard.putBoolean("Yellow Detected", yellowDetected);

    if ((redValue > blueValue) && (redValue > greenValue)){
      redDetected = true;
    }
    else if( (blueValue > redValue) && (blueValue > greenValue)){
      blueDetected = true;
    }
    else if ((greenValue > redValue) && (greenValue > blueValue)){
      greenDetected = true;
    }
    else {
      yellowDetected = true;
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
