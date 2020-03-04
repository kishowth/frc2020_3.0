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
import frc.robot.commands.StorageCommand;

/**
 * Add your docs here.
 */
public class StorageSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private VictorSP horizontalPulleySystem = new VictorSP(RobotMap.horizontalStorageMotor);
  private VictorSP storageWheelUptake = new VictorSP(RobotMap.driverWheelMotor);

  public void storePowerCells(double speed)
  {
    horizontalPulleySystem.set(-speed);
  }

  public void feedPowerCellsIntoShooter(double speed)
  {
    storageWheelUptake.set(-speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new StorageCommand());
  }
}
