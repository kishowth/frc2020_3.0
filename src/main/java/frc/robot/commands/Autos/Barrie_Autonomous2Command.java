/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Barrie_Autonomous2Command extends CommandGroup {
  /**
   * in this auto, move the robot away from the starting line
   */
  public Barrie_Autonomous2Command() {

   //in this auto, move the robot away from the starting line

   addSequential(new StraightLineAuto(5));
  }
}
