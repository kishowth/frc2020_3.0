/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TimeBasedAutos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShooterSpinningCommand extends Command {
  public ShooterSpinningCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.shooterSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    setTimeout(14);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.shooterSubsystem.leftShooterActivate(1);
    Robot.shooterSubsystem.rightShooterActivate(1);
    


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
