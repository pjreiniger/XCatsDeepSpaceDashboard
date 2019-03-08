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

    public void setCargoData(double aAngle, double aArmMotorSpeed, double aRollerSpeed, Double aGoalAngle, boolean aHasBall)
    {
        mSuperstructureController.setCargoData(aAngle, aArmMotorSpeed, aRollerSpeed, aGoalAngle, aHasBall);
    }

    public void setHatchAquisitionData(double aAngle, double aMotorSpeed, boolean aIsRetracted, boolean aIsScoring, double aHookSpeed,
            double aHookPosition)
    {
        mSuperstructureController.setHatchAquisitionData(aAngle, aMotorSpeed, aIsRetracted, aIsScoring);
        mHackquisitionController.setHatchData(aHookSpeed, aHookPosition);
    }

}
