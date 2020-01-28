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
      
    //These numbers must be tuned!!!!
    //steer is between 0.01-0.02
    //steerK is final
  
    final double STEER_K = 0.025;                     // how hard to turn toward the target
    final double DRIVE_K = 0.05;                      // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = -13.0;         // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.1;                     // Simple speed limit so we don't drive too fast

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
        
      
    // set a max speed
    if (drive_cmd > MAX_DRIVE)
    {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
  }



  //utilites
  private static final String TABLE_KEY = "limelight";
  private static final String VAR_NAME_LED_MODE = "ledMode";
  private static final String LIMELIGHT_MODE = "camMode";


  private static final int PIPELINE_DEFAULT = 0;
  private static final int FORCE_OFF = 1;
  private static final int FORCE_BLINK = 2;
  private static final int FORCE_ON = 3;
  private static final int VISION_CAMERA = 0;
  private static final int DRIVER_CAMERA = 1;

  public  boolean resetToDefault()
    {
      return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(PIPELINE_DEFAULT);
    }

  public  boolean forceOff()
    {
      return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_OFF);
    }

  public  boolean forceBlink()
    {
      return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_BLINK);
    }

  public  boolean forceOn()
    {
      return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_ON);
    }

  public boolean DriverMode()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(LIMELIGHT_MODE).setNumber(DRIVER_CAMERA);
    }

  public boolean VisionMode()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(LIMELIGHT_MODE).setNumber(VISION_CAMERA);
    }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new VisionCommand());
  }
}
