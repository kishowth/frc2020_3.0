/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.Autos.AutoCommand;
import frc.robot.commands.Autos.Barrie_Autonomous1Command;
import frc.robot.commands.Autos.Barrie_Autonomous2Command;
import frc.robot.commands.Autos.NothingAutoCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystems.ChassisSubsystem;
//import frc.robot.subsystems.ColourWheelArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;


public class Robot extends TimedRobot {



  
  public static VisionSubsystem VisionSubsystem = new VisionSubsystem();
  public static ChassisSubsystem ChassisSubsystem = new ChassisSubsystem();
  //public static ColourWheelArmSubsystem colourWheelArmSubsystem = new ColourWheelArmSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  public static OI m_oi;

  Command m_autonomousCommand;
  Command barrie_autonomous1Command = new Barrie_Autonomous1Command();
  Command barrie_autonomous2Command = new Barrie_Autonomous2Command();
  Command autoCommand = new AutoCommand();
  Command nothingCommand = new NothingAutoCommand();
  SendableChooser<Command> m_chooser = new SendableChooser<>();  







  //robot's initialization
  @Override
  public void robotInit() {
  

    //start Compressor
   // Robot.ChassisSubsystem.compressor.start();

    //reset gyro
    Robot.ChassisSubsystem.resetGyro();

    //reset encoders
    Robot.ChassisSubsystem.leftSideEncoder.reset();
    Robot.ChassisSubsystem.rightSideEncoder.reset(); 

    //initialize pixy
    Pixy2Camera.init();

    //instantiate OI
    m_oi = new OI();

    //listing all autonomous options on dashboard
    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());

    m_chooser.addDefault("NO AUTO", new NothingAutoCommand());

    m_chooser.addObject("AUTO 1", new Barrie_Autonomous1Command());
    m_chooser.addObject("AUTO 2", new Barrie_Autonomous2Command());
    m_chooser.addObject("Auto 3", new AutoCommand());

    SmartDashboard.putData("Auto Mode", m_chooser);

    
    
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
    
    m_autonomousCommand = m_chooser.getSelected();


    String autoselector = SmartDashboard.getString("Select Autonomous", "Default");



    
    switch(autoselector)
    {
      case "auto 1": nothingCommand = new NothingAutoCommand();
        break;  
      
      case "AUTO 1" : barrie_autonomous1Command = new Barrie_Autonomous1Command();
        break;
      
      case "AUTO 2" : barrie_autonomous2Command = new Barrie_Autonomous2Command();
        break;

    }

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
  }
    
    
    

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector","Default"); 
     * 
     * switch(autoSelected) 
     * { 
     * case "My Auto": autonomousCommand = new MyAutoCommand(); 
     * break; 
     * case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; 
     * }
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
