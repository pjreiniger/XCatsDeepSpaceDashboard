package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class HatchAquisitionData extends ComplexData<HatchAquisitionData>
{

    private final boolean mHatchVelcroSolenoid;
    private final boolean mHatchDeploySolenoid;

    public HatchAquisitionData()
    {
        this(false, false);
    }

    public HatchAquisitionData(Map<String, Object> aMap)
    {
        this("", aMap);
    }

    public HatchAquisitionData(String aPrefix, Map<String, Object> aMap)
    {
        this((Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_VELCRO_SOLENOID, false),
                (Boolean) aMap.getOrDefault(aPrefix + SmartDashboardNames.HATCH_DEPLOY_SOLENOID, false));
    }

    public HatchAquisitionData(boolean aVelcro, boolean aDeploy)
    {
        mHatchVelcroSolenoid = aVelcro;
        mHatchDeploySolenoid = aDeploy;
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
        map.put(aPrefix + SmartDashboardNames.HATCH_VELCRO_SOLENOID, mHatchVelcroSolenoid);
        map.put(aPrefix + SmartDashboardNames.HATCH_DEPLOY_SOLENOID, mHatchDeploySolenoid);

        return map;
    }

    public static boolean hasChanged(Map<String, Object> aChanges)
    {
        return aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_VELCRO_SOLENOID)
                || aChanges.containsKey(SmartDashboardNames.HATCH_TABLE_NAME + "/" + SmartDashboardNames.HATCH_DEPLOY_SOLENOID);
    }

    public boolean getVelcroSolenoid()
    {
        return mHatchVelcroSolenoid;
    }

    public boolean getDeploySolenoid()
    {
        return mHatchDeploySolenoid;
    }

}
