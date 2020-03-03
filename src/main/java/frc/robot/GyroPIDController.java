// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// /*
//  *
//  * 
//  *  
//  */
// public class GyroPIDController {

    
//     //Gyro Controller PID constants
//     private final double GYRO_PID_P = 0.05;
//     private final double DEAD_ZONE = 1; 
//     private double desiredAngle;



//     public void setDesiredAngle(double newAngle)
//     {
//         this.desiredAngle = newAngle;
//     }

//     public void adjustDirectionStep() 
//     {
//         double error = getError();
//         //System.out.println("error = " + error);

//         double rightAdj = getRightAdjustment(error);
//         double leftAdj = getLeftAdjustment(error);

//         boolean isTooFarRight = rightAdj > 0;
//         boolean isTooFarLeft = leftAdj > 0;

//         double newleftSpeed = Robot.ChassisSubsystem.leftside.get();
//         double newrightSpeed = Robot.ChassisSubsystem.rightside.get();

//         if (isTooFarRight) 
//         {
//             newleftSpeed = newleftSpeed + rightAdj / 2;
//             newrightSpeed = newrightSpeed + rightAdj / 2;
//         } 
//         else if (isTooFarLeft) 
//         {
//             newleftSpeed = newleftSpeed + leftAdj / 2;
//             newrightSpeed = newrightSpeed + leftAdj / 2;
//         }



//         if (newleftSpeed > 0) 
//         {
//             newleftSpeed = 0;
//         } 
//         else if (newrightSpeed < 0) 
//         {
//             newrightSpeed = 0;
//         }

//         Robot.ChassisSubsystem.leftside.set(newleftSpeed);
//         Robot.ChassisSubsystem.rightside.set(newrightSpeed);

//         SmartDashboard.putNumber("Left adjustment", leftAdj);
//         SmartDashboard.putNumber("Right adjustment", rightAdj);

//         SmartDashboard.putNumber("Final Left Speed", newleftSpeed);
//         SmartDashboard.putNumber("Final Right Speed", newrightSpeed);
//     }


    
//     private double getRightAdjustment(double errorAngle) 
//     {

//         double rightadj = 0;

//         // Slow down one motor based on the error.
//         if (errorAngle > DEAD_ZONE) {
//             rightadj = calcPValue(errorAngle);
//         }

//         return rightadj;
//     }

//     // positive output
//     private double getLeftAdjustment(double errorAngle) 
//     {
//         double leftAdj = 0;

//         // Slow down one motor based on the error.
//         if (errorAngle < -DEAD_ZONE) {
//             leftAdj = -calcPValue(errorAngle);
//         }

//         return leftAdj; // return as +
//     }

//     private double getError() 
//     {
//         double currentAngle = Robot.ChassisSubsystem.getrobotAngle();
//         double error = currentAngle - desiredAngle;
//         return error;
//     }

//     private double calcPValue(double error)
//     {
//         return error * GYRO_PID_P;
//     }

//   }

