/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.sample;

import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * 
 */
public class PIDTest {

    private PIDController pid;

    private static final double kP = 1;
    private static final double kI = 1;
    private static final double kD = 1;

    private double current = 1;

    public void init()
    {
        pid = new PIDController(kP, kI, kD);
    }

    public void step()
    {
        double target = 10;

        double output = pid.calculate(current, target);
    }
}
