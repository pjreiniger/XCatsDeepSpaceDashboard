package org.xcats.deep_space.sd_widgets.data;

import java.util.Map;
import java.util.function.Function;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class RobotDataType extends ComplexDataType<RobotData>
{
    public RobotDataType()
    {
        super(SmartDashboardNames.ROBOT_DATA_TABLE_NAME, RobotData.class);
    }

    @Override
    public Function<Map<String, Object>, RobotData> fromMap()
    {
        return map ->
        {
            return new RobotData(map);
        };
    }

    @Override
    public RobotData getDefaultValue()
    {
        return new RobotData();
    }

}
