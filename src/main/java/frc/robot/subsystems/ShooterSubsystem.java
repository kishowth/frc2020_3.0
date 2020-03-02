/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ShooterCommand;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends Subsystem {
  
  private Spark leftShooter = new Spark(RobotMap.leftShooterMotor);
  private Spark rightShooter = new Spark(RobotMap.rightShooterMotor);


  public ShooterSubsystem()
  {
    leftShooter.setInverted(true);
    rightShooter.setInverted(false);
  }


  public void leftShooterActivate(double speed)
  {
    leftShooter.set(speed);
  }

  public void rightShooterActivate(double speed)
  {
    rightShooter.set(speed);
  }





  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ShooterCommand());
  }
}
