package org.xcats.deep_space.sd_widgets.data;

import java.util.Map;
import java.util.function.Function;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class ElevatorDataType extends ComplexDataType<ElevatorData>
{

    protected ElevatorDataType()
    {
        super(SmartDashboardNames.ELEVATOR_TABLE_NAME, ElevatorData.class);
    }

    @Override
    public Function<Map<String, Object>, ElevatorData> fromMap()
    {
        return map ->
        {
            return new ElevatorData(map);
        };
    }

    @Override
    public ElevatorData getDefaultValue()
    {
        return new ElevatorData();
    }

}
