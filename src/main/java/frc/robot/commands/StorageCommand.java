/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StorageCommand extends Command {
  public StorageCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.storageSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    boolean ballInput = Robot.m_oi.getHorizontalStorageSystem();
    boolean ballOutput = Robot.m_oi.getHorizontalStorageSystem2();
    boolean momentumUp = Robot.m_oi.getVerticalStorageSystem();
    boolean momentumDown = Robot.m_oi.getVerticalStorageSystem2();
    


    if (ballInput)
    {
      Robot.storageSubsystem.storePowerCells(0.5);
    }
    else if(ballOutput)
    {
      Robot.storageSubsystem.storePowerCells(-0.5);
    }
    else if (momentumUp)
    {
      Robot.storageSubsystem.feedPowerCellsIntoShooter(0.5);
    }
    else if (momentumDown)
    {
      Robot.storageSubsystem.feedPowerCellsIntoShooter(-0.5);
    }
    else
    {
      Robot.storageSubsystem.storePowerCells(0.0);
      Robot.storageSubsystem.feedPowerCellsIntoShooter(0.0);
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
