/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Barrie_Autonomous1Command extends CommandGroup {
  /**
   * For this auto, shoot 3 balls into the goal, and move back.
   */
  public Barrie_Autonomous1Command() {

    //For this auto, shoot 3 balls into the goal, and move back. 

    addSequential(new ShootIntoPortCommand());
    addSequential(new StraightLineAuto(10));
    addSequential(new TurnCommand(180));

    
   

   
  }
}
