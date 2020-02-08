/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnToAngleCommand extends Command {
  public TurnToAngleCommand(double angle) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);

    turnAmount = angle;
  }

  final double turnAmount;
  double desiredWorldAngle;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    double currentAngle = Robot.ChassisSubsystem.getrobotAngle();


    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset();

    desiredWorldAngle = Robot.ChassisSubsystem.getrobotAngle() + turnAmount;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 

    if(turnAmount < 0)
    {
      Robot.ChassisSubsystem.leftside.set(-0.3);
      Robot.ChassisSubsystem.rightside.set(-0.3);

    }
    else if(turnAmount > 0)
    {
     Robot.ChassisSubsystem.leftside.set(0.3);
      Robot.ChassisSubsystem.rightside.set(0.3);
    }
    SmartDashboard.putNumber("TURN AMOUNT", turnAmount);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println(desiredWorldAngle);
    
    if (turnAmount < 0)
    {
      return Robot.ChassisSubsystem.getrobotAngle() <= desiredWorldAngle;
    }
    else 
    { 
      return Robot.ChassisSubsystem.getrobotAngle() >= desiredWorldAngle;
    }
    
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
