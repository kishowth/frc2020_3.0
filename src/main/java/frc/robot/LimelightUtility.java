/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * 
 */
public class LimelightUtility {

    private static final String TABLE_KEY = "limelight";
    private static final String VAR_NAME_LED_MODE = "ledMode";

    private static final int PIPELINE_DEFAULT = 0;
    private static final int FORCE_OFF = 1;
    private static final int FORCE_BLINK = 2;
    private static final int FORCE_ON = 3;

    public static boolean resetToDefault()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(PIPELINE_DEFAULT);
    }

    public static boolean forceOff()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_OFF);
    }

    public static boolean forceBlink()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_BLINK);
    }

    public static boolean forceOn()
    {
        return NetworkTableInstance.getDefault().getTable(TABLE_KEY).getEntry(VAR_NAME_LED_MODE).setNumber(FORCE_ON);
    }
}
