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

import edu.wpi.cscore.CameraServerCvJNI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ShooterWheelUptakeCommand;
import frc.robot.commands.Autos.AutoCommand;
import frc.robot.commands.Autos.Barrie_Autonomous1Command;
import frc.robot.commands.Autos.Barrie_Autonomous2Command;
import frc.robot.commands.Autos.NothingAutoCommand;
import frc.robot.commands.TimeBasedAutos.Barrie1_moveAndShoot;
import frc.robot.commands.TimeBasedAutos.Barrie2_moveAwayFromLineCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.ColourWheelArmSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterWheelUptakeSubsystem;
import frc.robot.subsystems.StorageSubsystem;
//import frc.robot.subsystems.VisionSubsystem;


public class Robot extends TimedRobot {



  
  //public static VisionSubsystem VisionSubsystem = new VisionSubsystem();
  public static ChassisSubsystem ChassisSubsystem = new ChassisSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public static StorageSubsystem storageSubsystem = new StorageSubsystem();
  public static ShooterWheelUptakeSubsystem shooterUptakeSubsystem = new ShooterWheelUptakeSubsystem();
  public static OI m_oi;

  Command selected_autonomous;
  Command barrie_autonomous1Command = new Barrie_Autonomous1Command();
  Command barrie_autonomous2Command = new Barrie_Autonomous2Command();
  Command autoCommand = new AutoCommand();
  Command nothingCommand = new NothingAutoCommand();

  Command defAuto = new Barrie1_moveAndShoot();
  Command moveAuto = new Barrie2_moveAwayFromLineCommand();
  SendableChooser<Command> m_chooser = new SendableChooser<>();  

  



  //robot's initialization
  @Override
  public void robotInit() {
  

    //start Compressor
    Robot.ChassisSubsystem.compressor.start();

    //reset gyro
    Robot.ChassisSubsystem.resetGyro();

    // //reset encoders
    // Robot.ChassisSubsystem.leftSideEncoder.reset();
    // Robot.ChassisSubsystem.rightSideEncoder.reset(); 

    //initialize pixy
    //Pixy2Camera.init();

    //instantiate OI
    m_oi = new OI();

    //listing all autonomous options on dashboard
    m_chooser.setDefaultOption("NO AUTO", new NothingAutoCommand());

    m_chooser.addOption("AUTO 1", new Barrie_Autonomous1Command());
    m_chooser.addOption("AUTO 2", new Barrie_Autonomous2Command());
    m_chooser.addOption("Auto 3", new AutoCommand());

    m_chooser.addOption("Def auto",new Barrie1_moveAndShoot());
    m_chooser.addOption("Simple auto", new Barrie2_moveAwayFromLineCommand());

    SmartDashboard.putData("Auto Mode", m_chooser);

    
  }

 
  @Override
  public void robotPeriodic() {}

  
  @Override
  public void disabledInit() {
    // Robot.ChassisSubsystem.leftSideEncoder.reset();
    // Robot.ChassisSubsystem.rightSideEncoder.reset(); 
    //Robot.ChassisSubsystem.resetGyro();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  
  @Override
  public void autonomousInit() {
    
    selected_autonomous = m_chooser.getSelected();



    if (selected_autonomous != null) {
      selected_autonomous.start();
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
    if (selected_autonomous != null) {
      selected_autonomous.cancel();
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
