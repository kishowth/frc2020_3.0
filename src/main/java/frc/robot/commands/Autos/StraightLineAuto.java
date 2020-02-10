/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GyroPIDController;
import frc.robot.Robot;

public class StraightLineAuto extends Command {



  public StraightLineAuto(double driveToDistance) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);

    //in this command, it uses targetInInches as its 'distance', so by making the variable 
    //driveToDistance = targetInInches, whatever number is inputted in the parenthesis of 
    //this method will automatically apply to this class. This gets rid of making multiple
    //autoCommands for various distances
    targetinInches = driveToDistance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    //reset encoders 
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset();

    //stating variables to use in autonomous
    odometerOnStart = Robot.ChassisSubsystem.leftSideEncoderValueInInches();
    desiredAngle = Robot.ChassisSubsystem.getrobotAngle();
  }
  


  //variables for this command
  double targetinInches;
  double odometerOnStart;
  double desiredAngle; 


  //Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //update the periodic commands and methods
    Robot.ChassisSubsystem.periodicCommands();
    Robot.ChassisSubsystem.chassisSystemDashboard();
    
    //Function for the total distance the robot has travelled so far
    double totalDistanceTravelled = Robot.ChassisSubsystem.leftSideEncoderValueInInches() - odometerOnStart;

    //until the total distance covered by the robot is not met, run the chassis at this speed, when it reaches target value, stop all motors.
    if (totalDistanceTravelled < targetinInches)
    {
      Robot.ChassisSubsystem.leftside.set(-0.2);
      Robot.ChassisSubsystem.rightside.set(0.2);
    }
    
    else
    {
      Robot.ChassisSubsystem.leftside.set(0.0);
      Robot.ChassisSubsystem.rightside.set(0.0);
    }

    //update the robot's total distance travelled on the dashboard
    SmartDashboard.putNumber("total distance travelled", totalDistanceTravelled);

    
    //auto adjust the Robot Chassis using the gyro controller so the robot is going in a straight line 
    GyroPIDController.adjustDirectionStep();

     //adjustDirectionStep();

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























  // private void adjustDirectionStep()
  // {
  //   double error = getError();
  //   System.out.println("error = " + error);

  //   double rightAdj = getRightAdjustment(error);
  //   double leftAdj =  getLeftAdjustment(error); 

  //   boolean isTooFarRight = rightAdj > 0;
  //   boolean isTooFarLeft = leftAdj > 0;

  //   double newleftSpeed = Robot.ChassisSubsystem.leftside.get();
  //   double newrightSpeed = Robot.ChassisSubsystem.rightside.get();

  //   if (isTooFarRight)
  //   {
  //   newleftSpeed = newleftSpeed + rightAdj / 2;
  //   newrightSpeed = newrightSpeed + rightAdj / 2 ;
  //   }
  //   else if(isTooFarLeft)
  //   {
  //     newleftSpeed = newleftSpeed + leftAdj / 2;
  //     newrightSpeed = newrightSpeed + leftAdj / 2 ;
  //   }
    
  //   if (newleftSpeed > 0)
  //   {
  //     newleftSpeed = 0;
  //   }
  //   else if (newrightSpeed < 0)
  //   {
  //     newrightSpeed = 0;
  //   }

  //   Robot.ChassisSubsystem.leftside.set(newleftSpeed);
  //   Robot.ChassisSubsystem.rightside.set(newrightSpeed);

  //   SmartDashboard.putNumber("Left adjustment", leftAdj);
  //   SmartDashboard.putNumber("Right adjustment", rightAdj);


  //   SmartDashboard.putNumber("Final Left Speed", newleftSpeed);
  //   SmartDashboard.putNumber("Final Right Speed", newrightSpeed);
  // }

  // // positive output
  // public double getRightAdjustment(double errorAngle){

    	
	// 	double rightadj = 0;

	// 	// Slow down one motor based on the error.
  //   if (errorAngle > DEAD_ZONE) 
  //   {
  //     rightadj = calcPValue(errorAngle);
  //   }

  //     return rightadj;
  // }

  
  // // positive output
	// public double getLeftAdjustment(double errorAngle){


	// 	double leftAdj = 0;

	// 	// Slow down one motor based on the error.
  //   if (errorAngle < -DEAD_ZONE) 
  //   {
  //       leftAdj = -calcPValue(errorAngle);
  //   }

  //     return leftAdj; //return as +
  //     }

  //   public double getError()
  //   {
  //     double currentAngle = Robot.ChassisSubsystem.getrobotAngle();
  //     double error = currentAngle - desiredAngle;
  //     return error;
  //   }

  //   private double calcPValue(double error){
  //     return error * GYRO_PID_P;
  //   }

  //   private double clampAngle(double angle)
  //   {
  //     if (angle > 180.0)  
  //     { 
  //       angle -= 360.0; 
  //     }

  //     if (angle > -180.0) 
  //     {
  //       angle += 360.0;
  //     }

  //     return angle;
  //   }
}
