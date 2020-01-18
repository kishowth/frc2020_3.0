/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

/**
 * Add your docs here.
 */
public class Pixy2Camera {

    private static Pixy2Camera instance;
    public static Pixy2Camera get()
    {
        if (instance == null)
        {
            System.out.println("Failed to get the pixy 2 camera logic, not initialized yet!");
            return null;
        }

        return instance;
    }

    public static void init()
    {
        if (instance != null)
        {
            System.out.println("Already initialized!");
            return;
        }

        System.out.println("Initializing pixy2");
        instance = new Pixy2Camera();
    }

    
    private Pixy2 p2;

    public static final float BALL_DIAMETER_M = 0.1778f;
    public static final float CAMERA_FOV_HORIZONTAL_DEG = 60f;
    public static final float CAMERA_FOV_VERTICAL_DEG = 40f;

    public static final float METERS_TO_INCHES = 39.3701f;

    
    private Pixy2Camera()
    {
        p2 = Pixy2.createInstance(LinkType.SPI);
        p2.init();
    }

    public void step()
    {
        Pixy2CCC ccc = p2.getCCC();
        if (ccc == null)
        {
            System.out.println("Pixy2 CCC is null!");
            return;
        }


        boolean wait = true;
        int yellowBall = 1;

        int x = ccc.getBlocks(wait, yellowBall, 255);

        if (x < 0){
            System.out.println("Doesn't work");
            System.out.println(x);
        }
        else{
            System.out.println(x);
        }


        ArrayList<Block> blocks = ccc.getBlocks();
        if (blocks == null)
        {
            System.out.println("Pixy2 CCC blocks are null!");
            return;
        }

        for(Block block : blocks)
        {
            int index = block.getIndex();
            int positionX = block.getX();
            int positionY = block.getY();
            int width = block.getWidth();
 
            System.out.print("Got block at " + positionX + ", " + positionY + ". Width = " + width + ". Index = " + index);

            if (isBlockWidthCompletelyInsideFrame(block))
            {
                float fromCameraMeters = calculateDistanceToBlockInMeters(block);
                float fromCameraInches = METERS_TO_INCHES * fromCameraMeters;

                float fromCameraMetersShort = ((int)Math.round(fromCameraMeters * 100)) / 100.0f;
                float fromCameraInchesShort = ((int)Math.round(fromCameraInches * 100)) / 100.0f;
                System.out.print("> and it's " + fromCameraMetersShort + " meters " + "(" + fromCameraInchesShort + " inches) from the camera!");
            }
        }
    }

    /**  ___________________________________________
     *  |                                           |
     *  |          _                                |
     *  |        /   \                              |
     *  |       |  x  |                             |
     *  |        \ _ / <- fully inside frame       _|
     *  |                                        /  |
     *  |                                       |  x|   <- Not fully inside frame
     *  |                                        \ _|
     *  |___________________________________________|
     */
    public boolean isBlockWidthCompletelyInsideFrame(Block block)
    {
        int blockWidth = block.getWidth();
        int blockPosition = block.getX();

        // We round up (also known as "ceiling" or "ceil") to be safe
        int halfWidth = (int)Math.ceil(blockWidth / 2.0f);

        // Touching the left side of the frame?
        if ((blockPosition - halfWidth) <= 0)
        {
            return false;
        }
        // Touching the right side of the frame?
        else if ((blockPosition + halfWidth) >= p2.getFrameWidth())
        {
            return false;
        }
        else
        {
            // Not touching the left or right sides, therefore it must be within the frame!
            return true;
        }
    }

    /**
     * Calculates and returns the forward-horizontal distance between
     * the camera and the center of the given block:
     * 
     *\                   /
     * \               _ /
     *  \            /   \ <- Block (A ball in this case)
     *   \          |  x  |     -   \
     *    \          \ _ /      |    \
     *     \         /          |     \
     *      \       /           |       Returns this distance
     *       \     /            |    /
     *        \   /             -   /
     *         \-/ <-Camera  
     * 
     * IMPORTANT! This assumes the camera is level with the ground! If it's not
     * you'll have to do another step of trig that includes the angle of the 
     * camera from the ground
     */        
    public float calculateDistanceToBlockInMeters(Block block)
    {   
        // NOTE:
        //      If the block isn't completely inside the frame then the block width
        //      will be smaller than expected and therefore be calculated to be
        //      farther away then it actually is

        // Solve for this
        float distanceToBlockInMeters;

        // Figure out how big the block is within the frame
        int blockWidth = block.getWidth();
        float halfFrameWidth = p2.getFrameWidth() / 2.0f;
        float blockWidthAsPercentOfFrame = (float)blockWidth / halfFrameWidth;
        
        // Once we know how big it is in the frame (say 50%), we can figure out how wide the camera
        // frame is at that distance! (We can do this because we know how wide the ball is)
        float numberOfBallsThatCanFitInFrameAtThatDistance = (1.0f / blockWidthAsPercentOfFrame);
        float frameWidthAtBlockInMeters = numberOfBallsThatCanFitInFrameAtThatDistance * BALL_DIAMETER_M;

        // Now that we know how wide the frame is, we use trig to work out how far away
        // that frame point is from the camera
        double halfHorizontalFovInRadians = Math.toRadians(CAMERA_FOV_HORIZONTAL_DEG / 2.0f);
        distanceToBlockInMeters = frameWidthAtBlockInMeters / (float)Math.tan(halfHorizontalFovInRadians);

        return distanceToBlockInMeters;
    }
}
