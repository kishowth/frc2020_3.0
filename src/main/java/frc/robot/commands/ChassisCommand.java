/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Pixy2Camera;
import frc.robot.Robot;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class ChassisCommand extends Command {
  public ChassisCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.chassisSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    boolean redDetected = false;
    boolean blueDetected = false;
    boolean greenDetected = false;
    boolean yellowDetected = false;


    double redValue = Robot.chassisSubsystem.red;
    double blueValue = Robot.chassisSubsystem.blue;
    double greenValue = Robot.chassisSubsystem.green;



    SmartDashboard.putBoolean("Red Detected", redDetected);
    SmartDashboard.putBoolean("Blue Detected", blueDetected);
    SmartDashboard.putBoolean("Green Detected", greenDetected);
    SmartDashboard.putBoolean("Yellow Detected", yellowDetected);

    if ((redValue > blueValue) && (redValue > greenValue)){
      redDetected = true;
    }
    else if( (blueValue > redValue) && (blueValue > greenValue)){
      blueDetected = true;
    }
    else if ((greenValue > redValue) && (greenValue > blueValue)){
      greenDetected = true;
    }
    else {
      yellowDetected = true;
    }

    Block ball = Pixy2Camera.get().getTheBall(); 
    if (ball != null)
    {
        System.out.println("I see the ball");

        boolean ballIsLeftOfPixy = Pixy2Camera.get().isLeft(ball);
        boolean ballIsRightOfPixy = Pixy2Camera.get().isRight(ball);
    
        if (ballIsLeftOfPixy)
        {
          Robot.chassisSubsystem.rightside.set(0.1);
          System.out.println("Turning left");
        }
        else if (ballIsRightOfPixy)
        {
          Robot.chassisSubsystem.leftside.set(0.1);
          System.out.println("Turning right");
        }
        else
        {
          Robot.chassisSubsystem.leftside.set(0.0);
          Robot.chassisSubsystem.rightside.set(0.0);
          System.out.println("Not turning");
        }
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
