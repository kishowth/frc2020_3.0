/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

/**
 * Add your docs here.
 */
public class Pixy2Example {

    private Pixy2 p2;

    public static final float BALL_DIAMETER_M = 0.1778f;
    public static final float CAMERA_FOV_HORIZONTAL_DEG = 60f;
    public static final float CAMERA_FOV_VERTICAL_DEG = 40f;


    public void init()
    {
        System.out.println("Initializing pixy2");

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

        ArrayList<Block> blocks = ccc.getBlocks();
        if (blocks == null)
        {
            System.out.println("Pixy2 CCC blocks are null!");
            return;
        }

        for (int i = 1; i < 10; i++)
        {
            System.out.println(i);
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
                System.out.print("> and it's " + fromCameraMeters + " meters from the camera!");
            }
        }
    }

    public boolean isBlockWidthCompletelyInsideFrame(Block block)
    {
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
        float frameWidthAtBlockInMeters = (1.0f / blockWidthAsPercentOfFrame) * BALL_DIAMETER_M;

        // Now that we know how wide the frame is, we use trig to work out how far away
        // that frame point is from the camera
        double halfHorizontalFovInRadians = Math.toRadians(CAMERA_FOV_HORIZONTAL_DEG / 2.0f);
        distanceToBlockInMeters = frameWidthAtBlockInMeters / (float)Math.tan(halfHorizontalFovInRadians);

        return distanceToBlockInMeters;
    }
}
