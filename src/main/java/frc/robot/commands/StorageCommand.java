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
    
    boolean ballInput = Robot.m_oi.getStorageSystemInward();
    boolean ballOutput = Robot.m_oi.getStorageSystemOutward();

    boolean momentumUp = Robot.m_oi.getleadingShooterWheelINPUT();
    boolean momentumDown = Robot.m_oi.getleadingShooterWheelOUTPUT();
    


    if (ballInput)
    {
      Robot.storageSubsystem.storePowerCells(0.3);
    }
    else if(ballOutput)
    {
      Robot.storageSubsystem.storePowerCells(-0.3);
    }
    else
    {
      Robot.storageSubsystem.storePowerCells(0.0);
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
