/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/* */
public class GameControllerConstants {

    /*-----variables for button parameters-----*/
    private static int BUTTON_A = 1; 
    private static int BUTTON_B = 2;
    private static int BUTTON_X = 3;
    private static int BUTTON_Y = 4;
    private static int LEFT_BUMPER = 5;
    private static int RIGHT_BUMPER = 6;
    private static int LEFT_JOYSTICK_BUTTON = 9;
    private static int RIGHT_JOYSTICK_BUTTON = 10;

    /*-----variables for axis parameters-----*/
    private static int LEFT_JOY_X = 0;
    private static int LEFT_JOY_Y = 1;
    private static int LEFT_TRIGGER = 2;
    private static int RIGHT_TRIGGER = 3;
    private static int RIGHT_JOY_X = 4;
    private static int RIGHT_JOY_Y = 5;



    static Joystick driverController = new Joystick(0);

    /*------------AXIS METHODS---------------------- */
    public static double LEFT_JOYSTICK_Y()
    {
        return driverController.getRawAxis(LEFT_JOY_Y);
    }

    public static double RIGHT_JOYSTICK_Y()
    {
        return driverController.getRawAxis(RIGHT_JOY_Y);
    }

    public static double LEFT_JOYSTICK_X(){
        return driverController.getRawAxis(LEFT_JOY_X);
    }

    public static double RIGHT_JOYSTICK_X(){
        return driverController.getRawAxis(RIGHT_JOY_X);
    }

    public static double RightTrigger(){
        return driverController.getRawAxis(RIGHT_TRIGGER);
    }

    public static double LeftTrigger(){
        return driverController.getRawAxis(LEFT_TRIGGER);
    }


    /*------------BUTTON METHODS---------------------- */


    public static boolean ButtonA()
    {
        return driverController.getRawButton(BUTTON_A);
    }

    public static boolean ButtonB() 
    {
        return driverController.getRawButton(BUTTON_B);
    }

    public static boolean ButtonX()
    {
        return driverController.getRawButton(BUTTON_X);
    } 

    public static boolean ButtonY()
    {
        return driverController.getRawButton(BUTTON_Y);
    }

    public static boolean LeftBumper()
    {
        return driverController.getRawButton(LEFT_BUMPER);
    }

    public static boolean RightBumper()
    {
        return driverController.getRawButton(RIGHT_BUMPER);
    }

    public static boolean LeftJoystickButton()
    {
        return driverController.getRawButton(LEFT_JOYSTICK_BUTTON);
    }

    public static boolean RightJoystickButton()
    {
        return driverController.getRawButton(RIGHT_JOYSTICK_BUTTON);
    }
    





    //vision constants, are final
    public static double functionOfX()
    {
        return driverController.getX();
    }

    public static double functionOfY()
    {
        return driverController.getY();
    }







}
