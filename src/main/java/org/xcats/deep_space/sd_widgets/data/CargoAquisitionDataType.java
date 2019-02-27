package org.xcats.deep_space.sd_widgets.data;

import java.util.Map;
import java.util.function.Function;

import org.xcats.deep_space.sd_widgets.SmartDashboardNames;

import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

public class CargoAquisitionDataType extends ComplexDataType<CargoAquisitionData>
{

    protected CargoAquisitionDataType()
    {
        super(SmartDashboardNames.CARGO_TABLE_NAME, CargoAquisitionData.class);
    }

    @Override
    public Function<Map<String, Object>, CargoAquisitionData> fromMap()
    {
        return map ->
        {
            return new CargoAquisitionData(map);
        };
    }

    @Override
    public CargoAquisitionData getDefaultValue()
    {
        return new CargoAquisitionData();
    }

}
