/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import frc.robot.commands.ChassisCommand;

/* */
public class ChassisSubsystem extends Subsystem {

  private VictorSP left1 = new VictorSP(RobotMap.leftBackMotor);
  private VictorSP left2 = new VictorSP(RobotMap.leftFrontMotor);
  private VictorSP right1 = new VictorSP(RobotMap.rightBackMotor);
  private VictorSP right2 = new VictorSP(RobotMap.rightFrontMotor);

  public SpeedControllerGroup leftside = new SpeedControllerGroup(left1, left2);
  public SpeedControllerGroup rightside = new SpeedControllerGroup(right1, right2);

  public void tankDrive(double speed){
    leftside.set(speed);
    rightside.set(speed);
  }

  public DifferentialDrive m_Drive = new DifferentialDrive(leftside, rightside);


  //colour sensor
  public final ColorSensorV3 colorSensor = new ColorSensorV3(RobotMap.i2cPort);

  Color detectedColor = colorSensor.getColor();

  public int red = colorSensor.getRed();
  public int blue = colorSensor.getBlue();
  public int green = colorSensor.getGreen();



  public void colorValues(){

    Color detectedColor = colorSensor.getColor();
    SmartDashboard.putNumber("red", detectedColor.red);
    SmartDashboard.putNumber("green", detectedColor.green);
    SmartDashboard.putNumber("blue", detectedColor.blue);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChassisCommand());
  }
}
