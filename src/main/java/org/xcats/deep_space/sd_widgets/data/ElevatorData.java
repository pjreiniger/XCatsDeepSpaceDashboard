package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class ElevatorData extends ComplexData<ElevatorData>
{
    private final double mHeight;
    private final double mMotorSpeed;
    private final Double mGoalHeight;

    public ElevatorData()
    {
        this(0, 0, 0, false);
    }

    public ElevatorData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public ElevatorData(String aPrefix, Map<String, Object> aMap)
    {
        this(
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.ELEVATOR_HEIGHT_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.ELEVATOR_SPEED_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.ELEVATOR_GOAL_HEIGHT_NAME, 0.0),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.ELEVATOR_IS_MM, false));
    }


    public ElevatorData(double aHeight, double aMotorSpeed, double aGoalHeight, boolean aIsMotionMagic)
    {
        mHeight = aHeight;
        mMotorSpeed = aMotorSpeed;

        if (aIsMotionMagic)
        {
            mGoalHeight = aGoalHeight;
        }
        else
        {
            mGoalHeight = null;
        }
    }

    @Override
    public Map<String, Object> asMap()
    {
        return asMap("");
    }

    /**
     * Gets a representation of this data as a map.
     * 
     * @param aPrefix
     *            The prefix to prepend to the field names
     * @return The map representation
     */
    public Map<String, Object> asMap(String aPrefix)
    {
        Map<String, Object> map = new HashMap<>();
        map.put(aPrefix + SmartDashboardNames.ELEVATOR_HEIGHT_NAME, mHeight);
        map.put(aPrefix + SmartDashboardNames.ELEVATOR_SPEED_NAME, mMotorSpeed);
        return map;
    }

    public double getHeight()
    {
        return mHeight;
    }

    public double getMotorSpeed()
    {
        return mMotorSpeed;
    }

    public Double getGoalHeight()
    {
        return mGoalHeight;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.ELEVATOR_TABLE_NAME + "/" + SmartDashboardNames.ELEVATOR_SPEED_NAME)
                || aChanges.containsKey(SmartDashboardNames.ELEVATOR_TABLE_NAME + "/" + SmartDashboardNames.ELEVATOR_HEIGHT_NAME);
    }
}
