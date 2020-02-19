/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommand extends CommandGroup {
  /**
   * TEST COMMAND
   */
  public AutoCommand() {
    

    //there are primarily 2 main functions for the chassis base:
    //1. StraightLineAuto basically drives the robot to a set distance 
    //2. Turn to angle basically turns the robot to a desired angle relative to the field.
    // StraightLineAuto is measured in inches. 
                             
    //To run 2 commands in sequence
    //Use the *addSequenntial* method
    //in its parameters, write:  new StraightLineAuto OR new TurnToAngle and the number specific for that


    //for example go forward 45 inches and turn 90DEG Clockwise: 

    //==========================================
    // addSequential(new StraightLineAuto(45));
    // addSequential(new TurnToAngleCommand(90));
    //==========================================

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel. 

    //For example, go 30 inches WHILE turning 40 DEG Counterclockwise:

    //============================================
    // addParallel(new StraightLineAuto(30));
    // addSequential(new TurnToAngleCommand(-40)); 
    //============================================


    //     NOTE:
    //     (-) is backward for chassis
    //     (-) is counterclockwise for gyro turning



    addSequential(new StraightLineAuto(25));
    addSequential(new TurnToAngleCommand(90));



  }
}
