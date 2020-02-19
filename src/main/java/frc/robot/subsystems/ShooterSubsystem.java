/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ShooterCommand;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends Subsystem {
  
  private VictorSP LEFT_SHOOTER_1 = new VictorSP(RobotMap.leftBackShooterMotor);
  private VictorSP LEFT_SHOOTER_2 = new VictorSP(RobotMap.leftFrontShooterMotor);
  private VictorSP RIGHT_SHOOTER_1 = new VictorSP(RobotMap.rightFrontShooterMotor);
  private VictorSP RIGHT_SHOOTER_2 = new VictorSP(RobotMap.rightBackShooterMotor);


  public void leftShooterActivate(double speed)
  {
    LEFT_SHOOTER_1.set(-speed);
  }

  public void rightShooterActivate(double speed)
  {
    LEFT_SHOOTER_2.set(-speed);
  }

  public void left2ShooterActivate(double speed)
  {
    RIGHT_SHOOTER_1.set(-speed);
  }

  public void right2ShooterActivate(double speed)
  {
    RIGHT_SHOOTER_2.set(speed);
  }




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ShooterCommand());
  }
}
