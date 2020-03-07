/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbCommand;

/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid climbRelease = new DoubleSolenoid(RobotMap.climbA, RobotMap.climbB);

  private VictorSP winchMotor = new VictorSP(RobotMap.climb);

  public void pushClimbPiston(){
    climbRelease.set(Value.kForward);
  }
  public void retractClimbPiston(){
    climbRelease.set(Value.kReverse);
  }
  public void leaveClimbState(){
    climbRelease.set(Value.kOff);
  }

  public void winchControl(double speed){
    winchMotor.set(speed);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbCommand());
  }
}
