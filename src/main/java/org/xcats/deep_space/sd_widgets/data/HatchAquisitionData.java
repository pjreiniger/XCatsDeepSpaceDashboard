package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class HatchAquisitionData extends ComplexData<HatchAquisitionData>
{

    private final double mAngle;
    private final double mMotorSpeed;
    private final Double mGoalAngle;

    public HatchAquisitionData()
    {
        this(0, 0, 0, false);
    }

    public HatchAquisitionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public HatchAquisitionData(String aPrefix, Map<String, Object> aMap)
    {
        this((Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_ANGLE_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_SPEED_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_GOAL_ANGLE_NAME, 0.0),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_IS_MM, false));
    }

    public HatchAquisitionData(double aHeight, double aMotorSpeed, double aGoalAngle, boolean aIsMm)
    {
        mAngle = aHeight;
        mMotorSpeed = aMotorSpeed;

        if (aIsMm)
        {
            mGoalAngle = aGoalAngle;
        }
        else
        {
            mGoalAngle = null;
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
        map.put(aPrefix + SmartDashboardNames.HATCH_ANGLE_NAME, mAngle);
        map.put(aPrefix + SmartDashboardNames.HATCH_SPEED_NAME, mMotorSpeed);
        return map;
    }

    public double getAngle()
    {
        return mAngle;
    }

    public double getMotorSpeed()
    {
        return mMotorSpeed;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_SPEED_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_ANGLE_NAME);
    }

    public Double getGoalAngle()
    {
        return mGoalAngle;
    }
}
