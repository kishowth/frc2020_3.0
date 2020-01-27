/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.RobotMap;
import frc.robot.commands.ColourWheelArmCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * This mechanism is for rotating the colour wheel
 * Uses a colour sensor to either:  Spin a certain amount of rotations or to set a desired position
 */
public class ColourWheelArmSubsystem extends Subsystem {

  //motor instantiations
  private VictorSP armMech = new VictorSP(RobotMap.colourMech);
  

  //sensor instantations
  private final ColorSensorV3 colorSensor = new ColorSensorV3(RobotMap.i2cPort);  
  private Encoder colourWheelEncoder = new Encoder(RobotMap.colourWheelEncoderA, RobotMap.colourWheelEncoderB);

  ColorMatch colorMatch = new ColorMatch();  


  //arm control functions
  public void armSpinRight(double speed){
    armMech.set(speed);
  }
  public void armSpinLeft(double speed){
    armMech.set(-speed);
  }
 
  //get RED, BLUE, and GREEN values
  public double RedValue(){
    return colorSensor.getRed();
  }
  public double BlueValue(){
    return colorSensor.getBlue();
  }

  public double GreenValue(){
    return colorSensor.getGreen();
  } 

  //making actual colours from the r g b values. [not final r g b parameters]
  private Color k_red = ColorMatch.makeColor(0.561, 0.232, 0.114); 
  private Color k_green = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private Color k_blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private Color k_yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);



  //colour detected from sensor
  public Color colourDetected()
  {
    return colorSensor.getColor();
  }



  //reading encoder values
  public double colourWheelEncoderValues(){
    return colourWheelEncoder.getDistance();
  } 

  double colourWheelEncoderInInches =  colourWheelEncoderValues() * 20;


  //function to print this subsystem's sensor values onto the dashboard.
  public void colourWheelSystemDashboard()
  {
    SmartDashboard.putNumber("red", RedValue());
    SmartDashboard.putNumber("green", BlueValue());
    SmartDashboard.putNumber("blue", GreenValue());
    SmartDashboard.putNumber("ColourWheel Encoder In inches", colourWheelEncoderInInches);

    System.out.println(colourDetected()); //Print out predicted colour on console
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ColourWheelArmCommand());
  }
}
