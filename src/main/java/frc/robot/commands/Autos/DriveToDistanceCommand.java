/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToDistanceCommand extends Command {
  public DriveToDistanceCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);
  }


  // Called just before this Command runs the first time
  //@Override
  // protected void initialize() 
  // {
  //   Robot.ChassisSubsystem.leftSideEncoder.reset();
  //   Robot.ChassisSubsystem.rightSideEncoder.reset();

  //   encoderCountOnStart = Robot.ChassisSubsystem.leftSideEncoderValueInInches();
  // } 

  double requiredDistance = 30; //inches
  double encoderCountOnStart;
  

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.ChassisSubsystem.chassisSystemDashboard();
    Robot.ChassisSubsystem.periodicCommands();

    //double totalDistanceCovered = Robot.ChassisSubsystem.leftSideEncoderValueInInches() - encoderCountOnStart;

    // if (totalDistanceCovered < requiredDistance)
    // {
    //   Robot.ChassisSubsystem.leftside.set(0.2);
    //   Robot.ChassisSubsystem.rightside.set(0.2);
    // }




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
