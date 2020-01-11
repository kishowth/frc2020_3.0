/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.VisionCommand;

/**
 *
 */
public class VisionSubsystem extends Subsystem {

  public boolean m_LimelightHasValidTarget = false;
  public double m_LimelightDriveCommand = 0.0;
  public double m_LimelightSteerCommand = 0.0;


  
  public void Update_Limelight_Tracking()
  {
      
    //These numbers must be tuned for your Robot!  Be careful!
    //steer is between 0.01-0.02
    //steerK is final
    //0.04 driveK
    //0.025
        final double STEER_K = 0.025;                     // how hard to turn toward the target
        final double DRIVE_K = 0.05; //0.02     //between 0.2-0.3    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = -13.0;  //13        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.1;    //0.1               // Simple speed limit so we don't drive too fast

        final double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        final double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        final double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        final double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        SmartDashboard.putNumber("limelightX", tx);   
        SmartDashboard.putNumber("limelightY", ty);   
        SmartDashboard.putNumber("limelightv", tv);   
        SmartDashboard.putNumber("limelightArea", ta);   

      

        

        if (tv < 1.0)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        m_LimelightHasValidTarget = true;

        

        // Start with proportional steering
        final double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA + ta) * DRIVE_K;
        
      
        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE)
        {
          drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new VisionCommand());
  }
}
