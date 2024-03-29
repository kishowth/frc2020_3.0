/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TimeBasedAutos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FeedBallsIntoShooterCommand extends Command {
  public FeedBallsIntoShooterCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.storageSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute(){

  Robot.ChassisSubsystem.Update_Limelight_Tracking();


  double leftDriveSpeed = Robot.m_oi.getLeftDriveSpeed();
  double rightDriveSpeed = Robot.m_oi.getRightDriveSpeed(); 
  {
    if (Robot.ChassisSubsystem.m_LimelightHasValidTarget) 
    {
   // Robot.VisionSubsystem.forceOn();
    Robot.ChassisSubsystem.m_drive.arcadeDrive(Robot.ChassisSubsystem.m_LimelightDriveCommand, Robot.ChassisSubsystem.m_LimelightSteerCommand);
    } 

    Robot.ChassisSubsystem.m_drive.tankDrive(-leftDriveSpeed / 1.25, -rightDriveSpeed / 1.25);
  }
    Robot.storageSubsystem.storePowerCells(0.25);
    Robot.shooterUptakeSubsystem.feedPowerCellsIntoShooter(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
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
