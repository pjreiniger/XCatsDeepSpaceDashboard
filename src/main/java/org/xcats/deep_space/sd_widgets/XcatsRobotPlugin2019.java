package org.xcats.deep_space.sd_widgets;

import java.util.List;

import org.xcats.deep_space.sd_widgets.data.RobotDataType;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.data.DataType;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "org.xcats.deep_space.sd_widgets", name = "XcatsRobotPlugin2019", version = PluginVersion.VERSION, summary = "Widget for the 2019 X-Cats robot")
public class XcatsRobotPlugin2019 extends Plugin
{

    @Override
    public List<ComponentType> getComponents()
    {
        return ImmutableList.of(
                WidgetType.forAnnotatedWidget(RobotWidget.class));

    }

    @Override
    public List<DataType> getDataTypes()
    {
        return ImmutableList.of(new RobotDataType());
    }

}
