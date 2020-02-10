/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class GyroPIDController {

    static double desiredAngle;

    public static void adjustDirectionStep() {
        double error = getError();
        System.out.println("error = " + error);

        double rightAdj = getRightAdjustment(error);
        double leftAdj = getLeftAdjustment(error);

        boolean isTooFarRight = rightAdj > 0;
        boolean isTooFarLeft = leftAdj > 0;

        double newleftSpeed = Robot.ChassisSubsystem.leftside.get();
        double newrightSpeed = Robot.ChassisSubsystem.rightside.get();

        if (isTooFarRight) {
            newleftSpeed = newleftSpeed + rightAdj / 2;
            newrightSpeed = newrightSpeed + rightAdj / 2;
        } else if (isTooFarLeft) {
            newleftSpeed = newleftSpeed + leftAdj / 2;
            newrightSpeed = newrightSpeed + leftAdj / 2;
        }

        if (newleftSpeed > 0) {
            newleftSpeed = 0;
        } else if (newrightSpeed < 0) {
            newrightSpeed = 0;
        }

        Robot.ChassisSubsystem.leftside.set(newleftSpeed);
        Robot.ChassisSubsystem.rightside.set(newrightSpeed);

        SmartDashboard.putNumber("Left adjustment", leftAdj);
        SmartDashboard.putNumber("Right adjustment", rightAdj);

        SmartDashboard.putNumber("Final Left Speed", newleftSpeed);
        SmartDashboard.putNumber("Final Right Speed", newrightSpeed);
    }

    public static double getRightAdjustment(double errorAngle) {

        double rightadj = 0;

        // Slow down one motor based on the error.
        if (errorAngle > RobotConstants.DEAD_ZONE) {
            rightadj = calcPValue(errorAngle);
        }

        return rightadj;
    }

    // positive output
    public static double getLeftAdjustment(double errorAngle) {
        double leftAdj = 0;

        // Slow down one motor based on the error.
        if (errorAngle < -RobotConstants.DEAD_ZONE) {
            leftAdj = -calcPValue(errorAngle);
        }

        return leftAdj; // return as +
    }

    public static double getError() {
        double currentAngle = Robot.ChassisSubsystem.getrobotAngle();
        double error = currentAngle - desiredAngle;
        return error;
    }

    public static double calcPValue(double error)
    {
        return error * RobotConstants.GYRO_PID_P;
    }

    public double clampAngle(double angle)
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
  










































