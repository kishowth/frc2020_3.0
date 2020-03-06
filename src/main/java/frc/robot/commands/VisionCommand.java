// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Robot;

// public class VisionCommand extends Command {
//   public VisionCommand() {
//     // Use requires() here to declare subsystem dependencies
//     requires(Robot.VisionSubsystem);
    
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {

//     //Robot.VisionSubsystem.Update_Limelight_Tracking();

//     boolean autoAlign = Robot.m_oi.getShooterAutoAlign();
//     double leftMotorControl = Robot.m_oi.getLeftDriveSpeed();
//     double rightMotorControl = Robot.m_oi.getRightDriveSpeed();
//     //double leftDriveSpeed = Robot.m_oi.getLeftDriveSpeed();
//     //double rightDriveSpeed = Robot.m_oi.getRightDriveSpeed(); 


//     //Vision alignment
    
//     if (autoAlign) 
//     {

//       if (Robot.VisionSubsystem.m_LimelightHasValidTarget) 
//         {
//        // Robot.VisionSubsystem.forceOn();
//         Robot.ChassisSubsystem.m_drive.arcadeDrive(Robot.VisionSubsystem.m_LimelightDriveCommand, Robot.VisionSubsystem.m_LimelightSteerCommand);
//         } 
//         else 
//         {
//         Robot.ChassisSubsystem.m_drive.tankDrive(leftMotorControl, -rightMotorControl);
//         }
//     }


//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }
