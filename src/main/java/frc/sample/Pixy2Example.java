/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.sample;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

/**
 * Add your docs here.
 */
public class Pixy2Example {

    private Pixy2 p2;

    public Pixy2Example()
    {
        Pixy2 p2 = Pixy2.createInstance(LinkType.SPI);
        p2.init();
    }

    public void step()
    {
        for(Block block : p2.getCCC().getBlocks())
        {
            int positionX = block.getX();
            int positionY = block.getY();
            int width = block.getWidth();

            SmartDashboard.putString("Debug Information", "Got block at " + positionX + ", " + positionY + ". Width = " + width);
        }
    }
}
