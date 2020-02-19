/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GameControllerConstants;
import frc.robot.Pixy2Camera;
import frc.robot.Robot;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class ChassisCommand extends Command {
  public ChassisCommand() {
    requires(Robot.ChassisSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  boolean desiredValue = false;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //constantly run these functions
    Robot.ChassisSubsystem.periodicCommands(); 
    Robot.ChassisSubsystem.chassisSystemDashboard(); 

    boolean chassisShift = Robot.m_oi.getBallShift1();
    boolean chassisShift2 = Robot.m_oi.getBallShift2();

    double leftMotorControl = Robot.m_oi.getLeftDriveSpeed();
    double rightMotorControl = Robot.m_oi.getRightDriveSpeed();
    
    //set up tank Drive, half speed due to fast chassis
    Robot.ChassisSubsystem.leftside.set(leftMotorControl / 2);
    Robot.ChassisSubsystem.rightside.set(rightMotorControl / 2);
    
    if(chassisShift)
    {
      Robot.ChassisSubsystem.shiftToFast();
    }
    else if (chassisShift2)
    {
      Robot.ChassisSubsystem.shiftToSlow();
    }
    else 
    {
      Robot.ChassisSubsystem.leaveShifterState();
    }











    
  //   /*-----------------------------------------------------------------------------------------------------*/
  //   Pixy2Camera.get().step();
  //   Block ball = Pixy2Camera.get().getTheBall();  
  //   boolean findBall = Robot.m_oi.getFindPowerCells();

  //   if (findBall)
  //   {
  //     if (ball != null)
  //     {
  //         boolean ballIsLeftOfPixy = Pixy2Camera.get().isLeft(ball);
  //         boolean ballIsRightOfPixy = Pixy2Camera.get().isRight(ball);
    
  //         if (ballIsLeftOfPixy)
  //         {
  //           Robot.ChassisSubsystem.leftside.set(-0.25); //-.25
  //           Robot.ChassisSubsystem.rightside.set(-0.25);
  //           System.out.println("Turning left");
  //         }
  //         else if (ballIsRightOfPixy)
  //         {
  //           Robot.ChassisSubsystem.leftside.set(0.25); //.25
  //           Robot.ChassisSubsystem.rightside.set(0.25);
  //           System.out.println("Turning right");
  //         }
  //         else
  //         {
  //         Robot.ChassisSubsystem.leftside.set(0.0);
  //         Robot.ChassisSubsystem.rightside.set(0.0);
  //         System.out.println("Not turning");
  //         }
  //   }
  // }
  //   /*-----------------------------------------------------------------------------------------------------*/


  //    if (Robot.ChassisSubsystem.ultValues() == 4.0) {
  //     desiredValue = true;
  //   }
  //   else {
  //     desiredValue = false;
  //   }
  //   SmartDashboard.putBoolean("desired", desiredValue);

  //   /*-----------------------------------------------------------------------------------------------------*/


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
