/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisCommand;

/**/
public class ChassisSubsystem extends Subsystem {
  


  //gyro Instantiation
  private AHRS gyro = new AHRS(RobotMap.gyro);

  //chassis motor instantiations
  private Spark left1 = new Spark(RobotMap.leftBackMotor);
  private Spark left2 = new Spark(RobotMap.leftFrontMotor);
  private Spark right1 = new Spark(RobotMap.rightBackMotor);
  private Spark right2 = new Spark(RobotMap.rightFrontMotor); 

  //inversions
  public ChassisSubsystem()
  {
    left1.setInverted(true);
    left2.setInverted(true);
    right1.setInverted(true);
    right2.setInverted(true);
  }

  // shifters
  DoubleSolenoid leftshifter = new DoubleSolenoid(RobotMap.leftDriveShifterA, RobotMap.leftDriveShifterB);
  DoubleSolenoid rightShifter = new DoubleSolenoid(RobotMap.rightDriveShifterA, RobotMap.rightDriveShifterB);


  //grouping motor controllers
  public SpeedControllerGroup leftside = new SpeedControllerGroup(left1, left2);
  public SpeedControllerGroup rightside = new SpeedControllerGroup(right1, right2); 
  
  
  public DifferentialDrive m_drive = new DifferentialDrive(leftside, rightside);
 
  // compressor
  public Compressor compressor = new Compressor();
  
 // when piston is pushed out, robot is on low gear
  //when piston is retracted, robot is on high gear
  
  public void shiftToSlow()
  {
    leftshifter.set(Value.kForward);
    rightShifter.set(Value.kForward);
  }

  public void shiftToFast()
  {
    leftshifter.set(Value.kReverse);
    rightShifter.set(Value.kReverse);
  }

  public void leaveShifterState()
  {
    leftshifter.set(Value.kOff);
    rightShifter.set(Value.kOff);
    
  }





  //read the robots current angle
  public double getrobotAngle()
  {
    return gyro.getAngle();
  }
  
  //function to reset gyro
  public void resetGyro()
  {
    gyro.reset();
  } 


  //all functions in this class that need to run periodically so values can be constantly updated. Call this in chassis command class
  public void periodicCommands()
  {
    getrobotAngle();
  }
  
  //Function to put all sensor values from this subsystem on the dashboard
  public void chassisSystemDashboard()
  {
   SmartDashboard.putNumber("Gyro", getrobotAngle());
  }

  public boolean m_LimelightHasValidTarget = false;
  public double m_LimelightDriveCommand = 0.0;
  public double m_LimelightSteerCommand = 0.0;

  public void Update_Limelight_Tracking()
  {
      
    //These numbers must be tuned!!!!
    //steer is between 0.01-0.02
    //steerK is final
    //0.025
    final double STEER_K = 0.0170;                     // how hard to turn toward the target
    final double DRIVE_K = 0.00;                     // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 18.0;         // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.1;                     // Simple speed limit so we don't drive too fast

    final double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    final double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    final double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    final double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    

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

public boolean forceBlink()
  {
    return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_BLINK);
  }

public boolean forceOn()
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
    setDefaultCommand(new ChassisCommand());
  }
}
