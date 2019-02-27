package org.xcats.deep_space.sd_widgets.data;

import java.util.HashMap;
import java.util.Map;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexData;

public class RobotData extends ComplexData<RobotData>
{
    private final CargoAquisitionData mCargo;
    private final ElevatorData mElevator;
    private final HatchAquisitionData mHatch;

    /**
     * Constructor.
     */
    public RobotData()
    {
        mCargo = new CargoAquisitionData();
        mElevator = new ElevatorData();
        mHatch = new HatchAquisitionData();
    }

    /**
     * Constructor. Initializes the data from the map.
     * 
     * @param aMap
     *            The map from NetworkTables
     */
    public RobotData(Map<String, Object> aMap)
    {
        mCargo = new CargoAquisitionData(SmartDashboardNames.CARGO_TABLE_NAME + "/", aMap);
        mElevator = new ElevatorData(SmartDashboardNames.ELEVATOR_TABLE_NAME + "/", aMap);
        mHatch = new HatchAquisitionData(SmartDashboardNames.HATCH_TABLE_NAME + "/", aMap);

    }

    @Override
    public Map<String, Object> asMap()
    {
        Map<String, Object> map = new HashMap<>();
        map.putAll(mCargo.asMap(SmartDashboardNames.CARGO_TABLE_NAME + "/"));
        map.putAll(mElevator.asMap(SmartDashboardNames.ELEVATOR_TABLE_NAME + "/"));
        map.putAll(mHatch.asMap(SmartDashboardNames.HATCH_TABLE_NAME + "/"));
        return map;
    }

    public ElevatorData getElevator()
    {
        return mElevator;
    }

    public CargoAquisitionData getCargo()
    {
        return mCargo;
    }

    public HatchAquisitionData getHatch()
    {
        return mHatch;
    }
}
