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
            SmartDashboard.putString("Debug Information", "Got block at " + positionX + ", " + positionY + ". Width = " + width);
        }
    }

    /**
     * Calculates and returns the forward-horizontal distance between
     * the camera and the center of the given block:
     * 
     *          |
     *          |      _
     *          |    /   \ <- Block (A ball in this case)
     *          |   |  x  |     -   \
     *          |    \ _ /      |    \
     *          |               |     \
     *          |               |       Returns this distance
     *          |               |    /
     *          |               -   /
     *         \ / <-Camera  
     */        
    public float calculateDistanceToBlockInMeters(Block block)
    {   
        // Solve for this
        float distanceToBlockInMeters;


        int blockWidth = block.getWidth();
        float halfFrameWidth = p2.getFrameWidth() / 2.0f;
        float blockWidthAsPercentOfFrame = (float)blockWidth / halfFrameWidth;
        
        float frameWidthAtBlockInMeters = (1.0f / blockWidthAsPercentOfFrame) * BALL_DIAMETER_M;
        double halfHorizontalFovInRadians = Math.toRadians(CAMERA_FOV_HORIZONTAL_DEG / 2.0f);
        distanceToBlockInMeters = frameWidthAtBlockInMeters / (float)Math.tan(halfHorizontalFovInRadians);

        return distanceToBlockInMeters;
    }
}
