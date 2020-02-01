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

public class VisionCommand extends Command {
  public VisionCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.VisionSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.VisionSubsystem.Update_Limelight_Tracking();

    boolean autoAlign = Robot.m_oi.getAuto();
    boolean lightsOn = Robot.m_oi.LimelightON();
    double leftDriveSpeed = Robot.m_oi.getLeftDriveSpeed();
    double rightDriveSpeed = Robot.m_oi.getRightDriveSpeed(); 


   

    if (autoAlign) {

      if (Robot.VisionSubsystem.m_LimelightHasValidTarget) {
        Robot.ChassisSubsystem.m_Drive.arcadeDrive(Robot.VisionSubsystem.m_LimelightDriveCommand,
            Robot.VisionSubsystem.m_LimelightSteerCommand);
            Robot.VisionSubsystem.forceOn();
      } else {
        Robot.ChassisSubsystem.m_Drive.arcadeDrive(0.0, 0.0);
      }
    }

    
    else {
      Robot.ChassisSubsystem.m_Drive.tankDrive(-leftDriveSpeed, -rightDriveSpeed);
    }


    //LIMELIGHT LED CONTROL
    if (lightsOn) {
      Robot.VisionSubsystem.forceOn();
    }
    else{
      Robot.VisionSubsystem.forceOff();
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
