/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ChassisAdjustment extends Command {
  public ChassisAdjustment() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ChassisSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    lastAngle = Robot.ChassisSubsystem.getrobotAngle();


  }

  double lastAngle; 

   //Gyro PID Control Variables
	public static final double GYRO_PID_P = 0.15;	
	public static final double GYRO_PID_D = 0.1;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {


    double error = getError(lastAngle);

    double rightAdjSpeed = getRightAdjustment(error);
    double leftAdjspeed =  getLeftAdjustment(error); 



    Robot.ChassisSubsystem.leftside.set(leftAdjspeed);
    Robot.ChassisSubsystem.rightside.set(rightAdjSpeed);


    lastAngle = Robot.ChassisSubsystem.getrobotAngle();
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


  public double getRightAdjustment(double errorAngle){

    if (errorAngle > 180.0)  
      { 
        errorAngle -= 360.0; 
      }

      if (errorAngle < -180.0) 
      {
        errorAngle += 360.0;
      }
    	
		double rightadj = 0;

		// Slow down one motor based on the error.
    if (errorAngle > 0) 
    {
      rightadj -= calcPValue(errorAngle);
    }

      return rightadj;
      }

  

	public double getLeftAdjustment(double errorAngle){

    	
      if (errorAngle > 180.0)  
      { 
        errorAngle -= 360.0; 
      }

      if (errorAngle < -180.0) 
      {
        errorAngle += 360.0;
      }
    	
		double leftAdj = 0;

		// Slow down one motor based on the error.
    if (errorAngle < 0) 
    {
        leftAdj = leftAdj + calcPValue(errorAngle);
    }

      return leftAdj;
      }

    public double getError(double previousAngle)
    {
      double currentAngle = Robot.ChassisSubsystem.getrobotAngle();
      double error = currentAngle - previousAngle;
      return error;
    }

    private double calcPValue(double error){
      return error * GYRO_PID_P;
    }

    


}

