package org.xcats.deep_space.sd_widgets.data;

import java.util.Map;
import java.util.function.Function;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class HatchAquisitionDataType extends ComplexDataType<HatchAquisitionData>
{
    protected HatchAquisitionDataType()
    {
        super(SmartDashboardNames.HATCH_TABLE_NAME, HatchAquisitionData.class);
    }

    @Override
    public Function<Map<String, Object>, HatchAquisitionData> fromMap()
    {
        return map ->
        {
            return new HatchAquisitionData(map);
        };
    }

    @Override
    public HatchAquisitionData getDefaultValue()
    {
        return new HatchAquisitionData();
    }

}
