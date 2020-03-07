/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/*
opr for operator commands 
*/
public class GameControllerConstants {


    private static int GRN_BUTTON_A = 3;
    private static int GRN_BUTTON_B = 2;
    private static int GRN_BUTTON_X = 4;
    private static int GRN_BUTTON_Y = 1;
    private static int GRN_LT = 7;
    private static int GRN_RT = 8;
    private static int GRN_LB = 5;
    private static int GRN_RB = 6;

    private static int GRN_LEFTJOY_BUTTON = 11;
    private static int GRN_RIGHTJOY_BUTTON = 12;



    private static int GRN_LEFTJOY_Y = 1;
    private static int GRN_RIGHTJOY_Y = 3;




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
    static Joystick operatorController = new Joystick(1);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                      DRIVER CONTROLLER METHODS                                                    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*------------AXIS METHODS----------------------*/
    
    public static double LEFT_JOYSTICK_Y()
    {
        return driverController.getRawAxis(GRN_LEFTJOY_Y);
    }

    public static double RIGHT_JOYSTICK_Y()
    {
        return driverController.getRawAxis(GRN_RIGHTJOY_Y);
    }

    // public static double LEFT_JOYSTICK_X(){
    //     return driverController.getRawAxis(LEFT_JOY_X);
    // }

    // public static double RIGHT_JOYSTICK_X(){
    //     return driverController.getRawAxis(RIGHT_JOY_X);
    // }

    /*------------BUTTON METHODS--------------- */
    public static boolean RightTrigger(){
        return driverController.getRawButton(GRN_RT);
    }

    public static boolean LeftTrigger(){
        return driverController.getRawButton(GRN_LT);
    }

    public static boolean ButtonA()
    {
        return driverController.getRawButton(GRN_BUTTON_A);
    }

    public static boolean ButtonB() 
    {
        return driverController.getRawButton(GRN_BUTTON_B);
    }

    public static boolean ButtonX()
    {
        return driverController.getRawButton(GRN_BUTTON_X);
    } 

    public static boolean ButtonY()
    {
        return driverController.getRawButton(GRN_BUTTON_Y);
    }

    public static boolean LeftBumper()
    {
        return driverController.getRawButton(GRN_LB);
    }

    public static boolean RightBumper()
    {
        return driverController.getRawButton(GRN_RB);
    }

    public static boolean LeftJoystickButton()
    {
        return driverController.getRawButton(GRN_LEFTJOY_BUTTON);
    }

    public static boolean RightJoystickButton()
    {
        return driverController.getRawButton(GRN_RIGHTJOY_BUTTON);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                      OPERATOR CONTROLLER METHODS                                                  //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static double oprLEFT_JOYSTICK_Y()
    {
        return operatorController.getRawAxis(LEFT_JOY_Y);
    }

    public static double oprRIGHT_JOYSTICK_Y()
    {
        return operatorController.getRawAxis(RIGHT_JOY_Y);
    }

    public static double oprLEFT_JOYSTICK_X(){
        return operatorController.getRawAxis(LEFT_JOY_X);
    }

    public static double oprRIGHT_JOYSTICK_X(){
        return operatorController.getRawAxis(RIGHT_JOY_X);
    }

    public static double oprRightTrigger(){
        return operatorController.getRawAxis(RIGHT_TRIGGER);
    }

    public static double oprLeftTrigger(){
        return operatorController.getRawAxis(LEFT_TRIGGER);
    }


    /*------------BUTTON METHODS--------------- */

    public static boolean oprButtonA()
    {
        return operatorController.getRawButton(BUTTON_A);
    }

    public static boolean oprButtonB() 
    {
        return operatorController.getRawButton(BUTTON_B);
    }

    public static boolean oprButtonX()
    {
        return operatorController.getRawButton(BUTTON_X);
    } 

    public static boolean oprButtonY()
    {
        return operatorController.getRawButton(BUTTON_Y);
    }

    public static boolean oprLeftBumper()
    {
        return operatorController.getRawButton(LEFT_BUMPER);
    }

    public static boolean oprRightBumper()
    {
        return operatorController.getRawButton(RIGHT_BUMPER);
    }

    public static boolean oprLeftJoystickButton()
    {
        return operatorController.getRawButton(LEFT_JOYSTICK_BUTTON);
    }

    public static boolean oprRightJoystickButton()
    {
        return operatorController.getRawButton(RIGHT_JOYSTICK_BUTTON);
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
