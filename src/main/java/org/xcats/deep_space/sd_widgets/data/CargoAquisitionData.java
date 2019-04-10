package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class CargoAquisitionData extends ComplexData<CargoAquisitionData>
{
    private final double mCargoSpeed;
    private final boolean mHasBall;

    public CargoAquisitionData()
    {
        this(0, false);
    }

    public CargoAquisitionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public CargoAquisitionData(String aPrefix, Map<String, Object> aMap)
    {
        this((Double) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_ROLLERS_NAME, 0.0),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.CARGO_HAS_BALL, false));
    }

    public CargoAquisitionData(double aRollersSpeed, boolean aHasBall)
    {
        mCargoSpeed = aRollersSpeed;
        mHasBall = aHasBall;
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
        map.put(aPrefix + SmartDashboardNames.CARGO_ROLLERS_NAME, mCargoSpeed);
        map.put(aPrefix + SmartDashboardNames.CARGO_HAS_BALL, mHasBall);
        return map;
    }

    public double getRollerSpeed()
    {
        return mCargoSpeed;
    }

    public boolean hasBall()
    {
        return mHasBall;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_ROLLERS_NAME)
                || aChanges.containsKey(SmartDashboardNames.CARGO_TABLE_NAME + "/" + SmartDashboardNames.CARGO_HAS_BALL);
    }

}
