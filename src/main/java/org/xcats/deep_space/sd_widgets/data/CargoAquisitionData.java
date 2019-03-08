package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class CargoAquisitionData extends ComplexData<CargoAquisitionData>
{
    private final double mArmAngle;
    private final double mArmSpeed;
    private final double mCargoSpeed;
    private final Double mGoalAngle;
    private final boolean mHasBall;

    public CargoAquisitionData()
    {
        this(0, 0, 0, 0, false, false);
    }

    public CargoAquisitionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public CargoAquisitionData(String aPrefix, Map<String, Object> aMap)
    {
        this((Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_ANGLE_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_SPEED_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_ROLLERS_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_GOAL_ANGLE_NAME, 0.0),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_IS_MM, false),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_HAS_BALL, false));
    }

    public CargoAquisitionData(double aHeight, double aArmSpeed, double aRollersSpeed, double aGoalAngle, boolean aIsMm, boolean aHasBall)
    {
        mArmAngle = aHeight;
        mArmSpeed = aArmSpeed;
        mCargoSpeed = aRollersSpeed;
        mHasBall = aHasBall;

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
        map.put(aPrefix + SmartDashboardNames.CARGO_ANGLE_NAME, mArmAngle);
        map.put(aPrefix + SmartDashboardNames.CARGO_SPEED_NAME, mArmSpeed);
        map.put(aPrefix + SmartDashboardNames.CARGO_ROLLERS_NAME, mCargoSpeed);
        map.put(aPrefix + SmartDashboardNames.CARGO_GOAL_ANGLE_NAME, mGoalAngle);
        map.put(aPrefix + SmartDashboardNames.CARGO_IS_MM, mGoalAngle != null);
        map.put(aPrefix + SmartDashboardNames.CARGO_HAS_BALL, mHasBall);
        return map;
    }

    public double getArmAngle()
    {
        return mArmAngle;
    }

    public double getArmSpeed()
    {
        return mArmSpeed;
    }

    public double getRollerSpeed()
    {
        return mCargoSpeed;
    }

    public Double getGoalAngle()
    {
        return mGoalAngle;
    }

    public boolean hasBall()
    {
        return mHasBall;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_ANGLE_NAME)
                || aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_SPEED_NAME)
                || aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_GOAL_ANGLE_NAME)
                || aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_ROLLERS_NAME)
                || aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_HAS_BALL);
    }

}
