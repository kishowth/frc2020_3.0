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

public class StraightLineAuto extends Command {
  public StraightLineAuto() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset();

    odometerOnStart = Robot.ChassisSubsystem.leftSideEncoderValueInInches();
    desiredAngle = Robot.ChassisSubsystem.getrobotAngle();

    System.out.println("Desired = " + desiredAngle);
  }
  
  double odometerOnStart;
  double targetinInches = 240;

  double desiredAngle; 

  //Gyro PID Control Variables
  public static final double GYRO_PID_P = 0.05;
  public static final double DEAD_ZONE = 1;
  //000000000001;	
  //public static final double GYRO_PID_D = 0.09;
  //Called repeatedly when this Command is scheduled to run

  @Override
  protected void execute() {

    Robot.ChassisSubsystem.periodicCommands();
    Robot.ChassisSubsystem.chassisSystemDashboard();
    
    double totalDistanceTravelled = Robot.ChassisSubsystem.leftSideEncoderValueInInches() - odometerOnStart;


    if (totalDistanceTravelled < targetinInches){
      Robot.ChassisSubsystem.leftside.set(-0.2);
      Robot.ChassisSubsystem.rightside.set(0.2);
    }
    
    else
    {
      Robot.ChassisSubsystem.leftside.set(0.0);
      Robot.ChassisSubsystem.rightside.set(0.0);
    }

    SmartDashboard.putNumber("total distance travelled", totalDistanceTravelled);

   adjustDirectionStep();


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


  private void adjustDirectionStep()
  {
    double error = getError();
    System.out.println("error = " + error);

    double rightAdj = getRightAdjustment(error);
    double leftAdj =  getLeftAdjustment(error); 

    boolean isTooFarRight = rightAdj > 0;
    boolean isTooFarLeft = leftAdj > 0;

    double newleftSpeed = Robot.ChassisSubsystem.leftside.get();
    double newrightSpeed = Robot.ChassisSubsystem.rightside.get();

    if (isTooFarRight)
    {
    newleftSpeed = newleftSpeed + rightAdj / 2;
    newrightSpeed = newrightSpeed + rightAdj / 2 ;
    }
    else if(isTooFarLeft)
    {
      newleftSpeed = newleftSpeed + leftAdj / 2;
      newrightSpeed = newrightSpeed + leftAdj / 2 ;
    }
    
    if (newleftSpeed > 0)
    {
      newleftSpeed = 0;
    }
    else if (newrightSpeed < 0)
    {
      newrightSpeed = 0;
    }

    Robot.ChassisSubsystem.leftside.set(newleftSpeed);
    Robot.ChassisSubsystem.rightside.set(newrightSpeed);

    SmartDashboard.putNumber("Left adjustment", leftAdj);
    SmartDashboard.putNumber("Right adjustment", rightAdj);


    SmartDashboard.putNumber("Final Left Speed", newleftSpeed);
    SmartDashboard.putNumber("Final Right Speed", newrightSpeed);
  }

  // positive output
  public double getRightAdjustment(double errorAngle){

    	
		double rightadj = 0;

		// Slow down one motor based on the error.
    if (errorAngle > DEAD_ZONE) 
    {
      rightadj = calcPValue(errorAngle);
    }

      return rightadj;
  }

  
  // positive output
	public double getLeftAdjustment(double errorAngle){


		double leftAdj = 0;

		// Slow down one motor based on the error.
    if (errorAngle < -DEAD_ZONE) 
    {
        leftAdj = -calcPValue(errorAngle);
    }

      return leftAdj; //return as +
      }

    public double getError()
    {
      double currentAngle = Robot.ChassisSubsystem.getrobotAngle();
      double error = currentAngle - desiredAngle;
      return error;
    }

    private double calcPValue(double error){
      return error * GYRO_PID_P;
    }

    private double clampAngle(double angle)
    {
      if (angle > 180.0)  
      { 
        angle -= 360.0; 
      }

      if (angle > -180.0) 
      {
        angle += 360.0;
      }

      return angle;
    }
}
