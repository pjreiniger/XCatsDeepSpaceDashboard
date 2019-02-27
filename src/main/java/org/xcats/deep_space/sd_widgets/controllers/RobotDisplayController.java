package org.xcats.deep_space.sd_widgets.controllers;

import javafx.fxml.FXML;

public class RobotDisplayController
{
    @FXML
    private SuperStructureController mSuperstructureController;

    @FXML
    private HackquisitionController mHackquisitionController;

    public void setElevatorData(double aHeight, double aMotorSpeed, Double aGoalHeight)
    {
        mSuperstructureController.setElevatorData(aHeight, aMotorSpeed, aGoalHeight);
    }

    public void setCargoData(double aAngle, double aArmMotorSpeed, double aRollerSpeed, Double aGoalAngle)
    {
        mSuperstructureController.setCargoData(aAngle, aArmMotorSpeed, aRollerSpeed, aGoalAngle);
    }

    public void setHatchAquisitionData(double aAngle, double aMotorSpeed, Double aGoalAngle, double aLeftHookSpeed, double aLeftHookPosition,
            double aRightHookSpeed, double aRightHookPosition)
    {
        mSuperstructureController.setHatchAquisitionData(aAngle, aMotorSpeed, aGoalAngle);
        mHackquisitionController.setHatchData(aLeftHookSpeed, aLeftHookPosition, aRightHookSpeed, aRightHookPosition);
    }

}
