package org.xcats.deep_space.sd_widgets.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RobotDisplayController
{
    @FXML
    private VBox mPane;

    @FXML
    private SuperStructureController mSuperstructureController;

    @FXML
    private HackquisitionController mHackquisitionController;

    @FXML
    public void initialize()
    {
        VBox.setVgrow(mPane, Priority.ALWAYS);
    }



    public void setElevatorData(double aHeight, double aMotorSpeed, Double aGoalHeight)
    {
        mSuperstructureController.setElevatorData(aHeight, aMotorSpeed, aGoalHeight);
    }

    public void setCargoData(double aAngle, double aArmMotorSpeed, double aRollerSpeed, Double aGoalAngle)
    {
        mSuperstructureController.setCargoData(aAngle, aArmMotorSpeed, aRollerSpeed, aGoalAngle);
    }

    public void setHatchAquisitionData(double aAngle, double aMotorSpeed, Double aGoalAngle, double leftHookSpeed, double leftHookPosition,
            double rightHookSpeed, double rightHookPosition)
    {
        mSuperstructureController.setHatchAquisitionData(aAngle, aMotorSpeed, aGoalAngle);
        mHackquisitionController.setHatchData(leftHookSpeed, leftHookPosition, rightHookSpeed, rightHookPosition);
    }

}
