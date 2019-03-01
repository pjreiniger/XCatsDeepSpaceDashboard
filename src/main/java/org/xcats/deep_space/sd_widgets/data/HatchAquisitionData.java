package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class HatchAquisitionData extends ComplexData<HatchAquisitionData>
{

    private final double mAngle;
    private final double mTiltMotorSpeed;
    private final Double mGoalAngle;
    private final double mLeftHookPosition;
    private final double mLeftHookSpeed;
    private final double mRightHookPosition;
    private final double mRightHookSpeed;

    public HatchAquisitionData()
    {
        this(0, 0, 0, false, 0, 0, 0, 0);
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
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_IS_MM, false),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_LEFT_HOOK_POSITION_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_LEFT_HOOK_SPEED_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_RIGHT_HOOK_POSITION_NAME, 0.0),
                (Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_RIGHT_HOOK_SPEED_NAME, 0.0));
    }

    public HatchAquisitionData(double aHeight, double aMotorSpeed, double aGoalAngle, boolean aIsMm, double aLeftHookPosition, double aLeftHookSpeed,
            double aRightHookPosition, double aRightHookSpeed)
    {
        mAngle = aHeight;
        mTiltMotorSpeed = aMotorSpeed;
        mLeftHookPosition = aLeftHookPosition;
        mLeftHookSpeed = aLeftHookSpeed;
        mRightHookPosition = aRightHookPosition;
        mRightHookSpeed = aRightHookSpeed;

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
        map.put(aPrefix + SmartDashboardNames.HATCH_SPEED_NAME, mTiltMotorSpeed);
        map.put(aPrefix + SmartDashboardNames.HATCH_LEFT_HOOK_POSITION_NAME, mRightHookPosition);
        map.put(aPrefix + SmartDashboardNames.HATCH_LEFT_HOOK_SPEED_NAME, mLeftHookSpeed);
        map.put(aPrefix + SmartDashboardNames.HATCH_RIGHT_HOOK_POSITION_NAME, mRightHookPosition);
        map.put(aPrefix + SmartDashboardNames.HATCH_RIGHT_HOOK_SPEED_NAME, mRightHookSpeed);

        return map;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_SPEED_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_ANGLE_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_LEFT_HOOK_POSITION_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_LEFT_HOOK_SPEED_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_RIGHT_HOOK_POSITION_NAME)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_RIGHT_HOOK_SPEED_NAME);
    }

    public double getAngle()
    {
        return mAngle;
    }

    public double getTiltMotorSpeed()
    {
        return mTiltMotorSpeed;
    }

    public Double getGoalAngle()
    {
        return mGoalAngle;
    }

    public double getLeftHookPosition()
    {
        return mLeftHookPosition;
    }

    public double getLeftHookSpeed()
    {
        return mLeftHookSpeed;
    }

    public double getRightHookPosition()
    {
        return mRightHookPosition;
    }

    public double getRightHookSpeed()
    {
        return mRightHookSpeed;
    }

}
