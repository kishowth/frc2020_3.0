/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.Autos.AutoCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ColourWheelArmSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class Robot extends TimedRobot {
  
  public static VisionSubsystem VisionSubsystem = new VisionSubsystem();
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static ChassisSubsystem ChassisSubsystem = new ChassisSubsystem();
  public static ColourWheelArmSubsystem colourWheelArmSubsystem = new ColourWheelArmSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  Command autoCommand = new AutoCommand();
  SendableChooser<Command> m_chooser = new SendableChooser<>(); 

  //robot's initialization
  @Override
  public void robotInit() {

    //reset gyro
    Robot.ChassisSubsystem.resetGyro();

    //reset encoders
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset(); 

    //initialize pixy
    Pixy2Camera.init();
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    //chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    
  }

 
  @Override
  public void robotPeriodic() {}

  
  @Override
  public void disabledInit() {
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset(); 
    Robot.ChassisSubsystem.resetGyro();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  
  @Override
  public void autonomousInit() {
  //m_autonomousCommand = m_chooser.getSelected();
    autoCommand = new AutoCommand();
    //timedCommand =  new Auto2timeCommand();
    //timedCommand.start();
    autoCommand.start();
    

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    
  }

  
  //This function is called periodically during autonomous. 
  @Override
  public void autonomousPeriodic() {
    Robot.ChassisSubsystem.periodicCommands();

    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  
  //This function is called periodically during operator control.
 
  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  
   //This function is called periodically during test mode.
  @Override
  public void testPeriodic() {}
}
