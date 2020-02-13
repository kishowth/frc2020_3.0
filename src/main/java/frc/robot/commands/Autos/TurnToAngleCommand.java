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

  public TurnToAngleCommand(double angle) 
  {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);
    turnAmount = angle;
  }

  //instantiate variables
  final double turnAmount;
  double desiredWorldAngle;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

    //making a variable for the Robot's current angle
    double currentAngle = Robot.ChassisSubsystem.getrobotAngle();

    //reset chassis encoders
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset();

    //desired angle relative to the game field
    desiredWorldAngle = Robot.ChassisSubsystem.getrobotAngle() + turnAmount;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() { 

    //if the inputted angle is positive, turning to the right, set the chassis' motors to a certain speed until this the robot's desired angle is met
    if(turnAmount < 0)
    {
      Robot.ChassisSubsystem.leftside.set(-0.3);
      Robot.ChassisSubsystem.rightside.set(-0.3);

    }
    //if the inputted angle is negative, turning to the left, set the chassis' motors to a certain speed until this the robot's desired angle is met
    else if(turnAmount > 0)
    {
     Robot.ChassisSubsystem.leftside.set(0.3);
      Robot.ChassisSubsystem.rightside.set(0.3);
    }
    //input the amount the robot has turned, in degrees.
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
