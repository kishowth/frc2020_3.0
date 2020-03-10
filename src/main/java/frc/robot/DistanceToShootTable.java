package frc.robot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a table that maps distances from the target to shooting power:
 * 
 *  /------------------------------\
 *  | Distance (m)  | Shoot Power  |
 *  |------------------------------|
 *  |     0.0       |    1.0       |
 *  |---------------|--------------|
 *  |     0.5       |    2.0       |
 *  |---------------|--------------|
 *  |     1.0       |    2.5       |
 *  |---------------|--------------|
 *  |     1.5       |    4.0       |
 *  |---------------|--------------|
 *  |     2.0       |    6.0       |
 *  |---------------|--------------|
 * 
 *  Contains functions to add entries to the table, and
 *  calculate an ideal shoot power from any distance
 * 
 * @author Tyler
 */
public class DistanceToShootTable
{
    private final List<Row> distanceMetersToShootPower = new ArrayList();
    
    
    /**
     * Adds a distance to power map to the shooter table. The more entries
     * are added, the more accurate the calculator will bes
     * 
     * @param distanceMeters
     * @param shootPower 
     */
    public void addRow(double distanceMeters, double shootPower)
    {
        // Check if we already have a row for this distance        
        Row toUpdate = null;
        for (Row row : distanceMetersToShootPower)
        {
            if (row.distanceMeters == distanceMeters)
            {
                toUpdate = row;
                break;
            }
        }
        
        // If we do, update the value
        if (toUpdate != null)
        {
            toUpdate.shootPower = shootPower;
        }
        // Otherwise, add a new row to the table
        else
        {
            Row entry = new Row(distanceMeters, shootPower);
            distanceMetersToShootPower.add(entry);

            // Sort the table so we can easily interpolate values later
            distanceMetersToShootPower.sort(new Comparator<Row>() {
                @Override
                public int compare(Row o1, Row o2)
                {
                    return Double.compare(o1.distanceMeters, o2.distanceMeters);
                }
            });
        }
    }
    
    /**
    * Searches the table for the closest entries and returns an interpolated
    * value
    */
    public double calculateShootPowerFor(double currentDistanceMeters)
    {
        // First, see if we have a row for that exact distance
        for (Row row : distanceMetersToShootPower)
        {
            if (row.distanceMeters == currentDistanceMeters)
            {
                return row.shootPower;
            }
        }
        
        
        // If not, we have to interpolate between two rows:
        /**
        *  For example, if this is our table:
        * 
        *  /------------------------------------\
        *  |  Distance (m)   |  Shoot Power     |
        *  |------------------------------------|
        *  |     0.0         |    1.0           |
        *  |-----------------|------------------|
        *  |     0.5         |    2.0           |
        *  |-----------------|------------------|
        *  |     1.0         |    2.5           |
        *  |-----------------|------------------|
        *  |     1.5         |    4.0           |
        *  |-----------------|------------------|
        *  |     2.0         |    6.0           |
        *  |-----------------|------------------|
        * 
        *  ...and we're looking for the best shoot power at 1.1 meters,
        *    then we'd want to interpolate between these two rows:
        * 
        *  /------------------------------------\
        *  |  Distance (m)   |  Shoot Power     |
        *  |------------------------------------|
        *  |     0.0         |    1.0           |
        *  |-----------------|------------------|
        *  |     0.5         |    2.0           |
        *  |-----------------|------------------|
        *  |     1.0         |    2.5           |
        *  |-----------------|------------------| <----- here!
        *  |     1.5         |    4.0           |
        *  |-----------------|------------------|
        *  |     2.0         |    6.0           |
        *  |-----------------|------------------|
        */
        
        // So, knowing that, search for the two rows we want:
        Row closestRowAbove = findClosestRowAbove(currentDistanceMeters);
        Row closestRowBelow = findClosestRowBelow(currentDistanceMeters);
        
        // Then figure out the shoot power we need
        return interpolateShootPower(currentDistanceMeters, closestRowAbove, closestRowBelow);            
    }
    
    private Row findClosestRowAbove(double currentDistanceMeters)
    {
        // Check each row, starting at the first row (the smallest distance) and moving down the table
        for (int rowIndex = 0; rowIndex < distanceMetersToShootPower.size(); rowIndex++)
        {
            Row row = distanceMetersToShootPower.get(rowIndex);
            
            // ... If current row we're checking has a greater distance than the one we want,
            // then we know we've just gone past the row above that we want, so return the last row we checked
            if (row.distanceMeters > currentDistanceMeters)
            {
                // ... but wait! what if we're only on the first row?                
                if (rowIndex == 0)
                {
                    // That's okay, return null (which means the row above
                    // does not exist), and we'll handle it later!
                    return null;
                }
                else
                {
                    // Otherwise, return the previous row
                    return distanceMetersToShootPower.get(rowIndex - 1);
                }
            }
        }
        
        // Hold on, if we got here, that means we went through the entire table without
        // finding the row above us, in that case, return the last row in the list
        int indexOfLastRow = distanceMetersToShootPower.size() - 1;
        return distanceMetersToShootPower.get(indexOfLastRow);
    }
    
    private Row findClosestRowBelow(double currentDistanceMeters)
    {
        // Check each row, starting at the last row (the largest distance) and moving up the table
        int indexOfLastRow = distanceMetersToShootPower.size() - 1;
        for (int rowIndex = indexOfLastRow; rowIndex >= 0; rowIndex--)
        {
            Row row = distanceMetersToShootPower.get(rowIndex);
            
            // ... If current row we're checking has a distance than the one we want,
            // then we know we've just gone past the row below that we want, so return the last row we checked
            if (row.distanceMeters < currentDistanceMeters)
            {
                // ... but wait! what if we're only on the last row?                
                if (rowIndex == indexOfLastRow)
                {
                    // That's okay, return null (which means the row above
                    // does not exist), and we'll handle it later!
                    return null;
                }
                else
                {
                    // Otherwise, return the last row we checked
                    return distanceMetersToShootPower.get(rowIndex + 1);
                }
            }
        }
        
        // Hold on, if we got here, that means we went through the entire table without
        // finding the row below us, in that case, return the first row in the list
        return distanceMetersToShootPower.get(0);
    }
    
    private double interpolateShootPower(double currentDistanceMeters, Row closestRowAbove, Row closestRowBelow)
    {
        // First, handle some edge cases where our table didn't have enough data:
        if (closestRowAbove == null && closestRowBelow == null)
        {
            // Looks like the table was empty!
            // return 0 to be safe
            return 0.0;
        }
        else if (closestRowBelow == null)
        {
            // Will happen if the distance we want is smaller than the smallest one in the table
            // In that case, just return the shoot power of the smallest distance we have data for
            return closestRowAbove.shootPower;
        }
        else if (closestRowAbove == null)
        {
            // Will happen if the distance we want is larger than the largest one in the table
            // In that case, just return the shoot power of the largest distance we have data for
            return closestRowBelow.shootPower;
        }
        
        // If we're still here, we have to interpolate!
            
        /** Now, to interpolate accurately we need to know how close our given distance is
        *  to each row. Using our current example, imagine it like a graph:
        *
        * 
        *  Shoot Power
        * 
        *  4.0 |                    o
        *      |                /--/
        *  3.0 |              /-      <-- Our desired shoot power would be here somewhere
        *      |             o    
        *  2.0 |      o-----/   
        *      |  ---/          
        *  1.0 |o/              
        *      |                
        *  0.0 \-------------------------
        *      0     0.5    1.0    1.5   
        *           Distance (m)
        *  
        *
        *  If we zoom in on this graph
        *       Point A = the row above us on the table
        *       Point B = the row below us on the table
        *       Point S = the point we want
        * 
        *  4.0   |                   B-- (1.5m, 4.0)
        *        |                  /
        *        |                 /
        *        |                /
        *        |               /
        *        |              /
        *        |             /
        *        |            /
        *        |           /
        *        |          /
        *        |         /
        *  ???   |        S <-- this is the point we want (1.1m, Y)
        *        |       /
        *        |      /
        *  2.5   |   --A (1.0m, 2.5)
        *        |     
        *        |-----------------------------
        *             1.0         1.5
        *
        * 
        *  How do we solve for the Y value of point S?
        * 
        *  Well, Y will be some % of the way between 2.5 and 4.0.
        *  This % will be identical to the distance that 1.1 is between 1.0 and 1.5:
        * 
        *     %   =   (1.1 - 1.0)
        *             -----------
        *             (1.5 - 1.0)
        * 
        *     %   =   0.1 / 0.5   =   20%
        *
        *   Turns out it's 20%
        * 
        * 
        *  Since it's a straight line between A and B we can apply the same math to the shoot power along the Y-axis:
        * 
        *     20% =   ( Y  - 2.5)
        *             -----------
        *             (4.0 - 2.5)
        *    
        *    (4.0 - 2.5) * 20% = Y - 2.5
        *    
        *    ((4.0 - 2.5) * 20%) + 2.5 = Y
        * 
        *    0.3 + 2.5 = Y
        *    
        *    2.8 = Y
        * 
        *
        *  That's all good, but how do we do this with code so we can solve for any value?
        *  First, figure out the equations to the get the percentage between the two points on the graph:
        * 
        *     Let %  = Percent along the graph
        *     Let A = Our point on the graph that matches the row above our distance on the original table
        *     Let B = Our point on the graph that matches the row below our distance on the original table
        *     Let S = Our point on the graph that matches the shoot power we want
        *  
        *     % = (S.x - A.x)              % = (S.y - A.y)
        *         -----------     (and)        -----------
        *         (B.x - A.x)                  (B.y - A.y)
        * 
        * Use the first equation to compute the percent between the rows above and below:
        */      
        
        // Lets rename some variables so it's easier to follow
        double Ax = closestRowAbove.distanceMeters;
        double Ay = closestRowAbove.shootPower;
        double Bx = closestRowBelow.distanceMeters;
        double By = closestRowBelow.shootPower;
        double Sx = currentDistanceMeters;
        double Sy;
        
        double percent = (Sx - Ax) / (Bx - Ax);
        
        /** Now we've got the percent, next, rearrange the other equation to solve for S.y:
         *   % = (S.y - A.y)
         *       -----------
         *       (B.y - A.y)
         * 
         *   % * (B.y - A.y)        = S.y - A.y
         *  (% * (B.y - A.y)) + A.y = S.y;
         * 
         *  S.y = (% * (B.y - A.y)) + A.y
         */
        
        Sy = (percent * (By - Ay)) + Ay;
        
        // We're done!
        return Sy;
    }
    
    private class Row
    {
        public final double distanceMeters;
        public double shootPower;

        public Row(double distanceMeters, double shootPower)
        {
            this.distanceMeters = distanceMeters;
            this.shootPower = shootPower;
        }
    }
}
   