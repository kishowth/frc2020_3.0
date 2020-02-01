/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class GyroPIDController{

    //Drive PID Control Variables
	public static final double DRIVE_LOW_PID_P = 0.18;
	public static final double DRIVE_LOW_PID_D = 0.14;
	public static final double DRIVE_HIGH_PID_P = 0.14;
    public static final double DRIVE_HIGH_PID_D = 0.25;


    //Gyro PID Control Variables
	public static final double GYRO_PID_P = 0.15;	
	public static final double GYRO_PID_D = 0.1;


	private double setSpeed;
	
	public GyroPIDController(double speed){
		setSpeed = speed;
	}
	private double calcPValue(double error){
		return error * GYRO_PID_P;
	}
	private double calcDValue(double error){
        return error * GYRO_PID_D;
        
    }




    public double LeftAdjustmentSpeed; 


}
















// 	public double getAngle(double targetAngle, double currentAngle){
// 		double angleError = targetAngle - currentAngle;
    	
//     	if (angleError > 180.0)  { angleError -= 360.0; }
//     	if (angleError < -180.0) { angleError += 360.0; }
    	
// 		double leftSpeed  = setSpeed;
// 		double rightSpeed = setSpeed;

// 		// Slow down one motor based on the error.
// 		if (angleError > 0) {
//     		rightSpeed -= calcPValue(angleError);
//     		if (rightSpeed < -setSpeed) {
//     			 rightSpeed = -setSpeed;
//     		}
//     	}
//     	else {
//     		leftSpeed -=  -calcPValue(angleError);
//     		if (leftSpeed < -setSpeed) {
//     			leftSpeed = -setSpeed;
//     		}
//     	}
// 		Robot.chassisSubsystem.setDifferentMotorSpeeds(leftSpeed, rightSpeed);
// 	}
// }

