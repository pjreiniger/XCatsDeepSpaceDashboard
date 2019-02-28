package org.xcats.deep_space.sd_widgets;

import java.util.Map;

import org.xcats.deep_space.sd_widgets.controllers.RobotDisplayController;
import org.xcats.deep_space.sd_widgets.data.CargoAquisitionData;
import org.xcats.deep_space.sd_widgets.data.ElevatorData;
import org.xcats.deep_space.sd_widgets.data.HatchAquisitionData;
import org.xcats.deep_space.sd_widgets.data.RobotData;

import edu.wpi.first.shuffleboard.api.widget.ComplexAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(name = "XCATS Deep Space Widget", dataTypes = {RobotData.class})
@ParametrizedController("robot_widget.fxml")
public class RobotWidget extends ComplexAnnotatedWidget<RobotData>
{
    @FXML
    private Pane mRoot;

    @FXML
    protected RobotDisplayController mRobotContainerController;

    /**
     * Called after JavaFX initialization.
     */
    @FXML
    public void initialize()
    {
        dataOrDefault.addListener((__, oldData, newData) ->
        {
            final Map<String, Object> changes = newData.changesFrom(oldData);

            if (ElevatorData.hasChanged(changes))
            {
                updateElevator(newData.getElevator());
            }

            if (CargoAquisitionData.hasChanged(changes))
            {
                updateCargo(newData.getCargo());
            }

            if (HatchAquisitionData.hasChanged(changes))
            {
                updateHatch(newData.getHatch());
            }
        });
    }

    private void updateElevator(ElevatorData aElevatorData)
    {
        mRobotContainerController.setElevatorData(aElevatorData.getHeight(), aElevatorData.getMotorSpeed(), aElevatorData.getGoalHeight());
    }

    private void updateCargo(CargoAquisitionData aCargoData)
    {
        mRobotContainerController.setCargoData(aCargoData.getArmAngle(), aCargoData.getArmSpeed(), aCargoData.getRollerSpeed(), aCargoData.getGoalAngle());
    }

    private void updateHatch(HatchAquisitionData aHatchData)
    {
        mRobotContainerController.setHatchAquisitionData(aHatchData.getAngle(), aHatchData.getTiltMotorSpeed(), aHatchData.getGoalAngle(), 0, 0, 0, 0);
    }

    @Override
    public Pane getView()
    {
        return mRoot;
    }

}
