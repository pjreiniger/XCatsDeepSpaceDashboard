package org.xcats.deep_space.sd_widgets.controllers;

import org.xcats.deep_space.sd_widgets.Utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;

public class SuperStructureController
{
    private static final double ELEVATOR_MAX_HEIGHT = 70;

    private static final double CARGO_WIDTH = 20;

    private static final double CARGO_ELEVATOR_OFFSET = 20;

    private static final double MAX_WIDTH = 20;
    private static final double MAX_HEIGHT = ELEVATOR_MAX_HEIGHT + CARGO_WIDTH;

    private static final Color DEFAULT_ELEVATOR_COLOR = Color.CADETBLUE;

    private static final Color DEFAULT_PREVIEW_COLOR = new Color(0, 0, 0, .1);

    @FXML
    private Pane mPane;

    @FXML
    private Group mGroup;

    @FXML
    private Rectangle mElevator;

    @FXML
    private Rectangle mElevatorGoal;

    @FXML
    private Rectangle mHatchVelcro;

    @FXML
    private Rectangle mHatchDeploy;

    @FXML
    private Circle mCargoRollers;

    @FXML
    private Circle mHasCargoIndicator;

    @FXML
    private Arc mCargoStructure;

    @FXML
    public void initialize()
    {
        double minWidthMultiplier = 4;
        mPane.setMinHeight(MAX_HEIGHT * minWidthMultiplier);
        mPane.setMinWidth(MAX_WIDTH * minWidthMultiplier);

        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() -> Math.min(mPane.getWidth() / MAX_WIDTH, mPane.getHeight() / MAX_HEIGHT),
                mPane.widthProperty(), mPane.heightProperty());

        Scale scale = new Scale();
        scale.xProperty().bind(scaleBinding);
        scale.yProperty().bind(scaleBinding);

        mGroup.getTransforms().add(scale);

        //////////////////////////////////////////////////
        // Cargo
        //////////////////////////////////////////////////

        final ObjectBinding<Double> cargoYBinding = Bindings.createObjectBinding(() ->
        {
            return mElevator.getY() - CARGO_ELEVATOR_OFFSET + CARGO_ELEVATOR_OFFSET;
        }, mElevator.heightProperty());

        mCargoStructure.centerYProperty().bind(cargoYBinding);
        mCargoRollers.centerYProperty().bind(cargoYBinding);
        mHasCargoIndicator.centerYProperty().bind(cargoYBinding);
        mHatchVelcro.yProperty().bind(cargoYBinding);
        mHatchDeploy.yProperty().bind(cargoYBinding);
    }

    public void setElevatorData(double aHeight, double aMotorSpeed, Double aGoalHeight)
    {
        double y = MAX_HEIGHT - aHeight;
        mElevator.setY(y - CARGO_ELEVATOR_OFFSET);
        mElevator.setHeight(aHeight + CARGO_ELEVATOR_OFFSET);

        Utils.setColor(mElevator, DEFAULT_ELEVATOR_COLOR, aMotorSpeed);

        setGoalColor(mElevatorGoal, aGoalHeight);
        if (aGoalHeight != null)
        {
            double goalY = MAX_HEIGHT - aGoalHeight;
            mElevatorGoal.setY(goalY - CARGO_ELEVATOR_OFFSET);
            mElevatorGoal.setHeight(aGoalHeight + CARGO_ELEVATOR_OFFSET);
        }
    }

    public void setCargoData(double aRollerSpeed, boolean aHasBall)
    {
        Utils.setColor(mCargoRollers, null, aRollerSpeed);
        mHasCargoIndicator.setVisible(aHasBall);
    }

    private void setGoalColor(Shape aShape, Double aValue)
    {
        if (aValue == null)
        {
            aShape.setFill(Color.TRANSPARENT);
            aShape.setStroke(Color.TRANSPARENT);
        }
        else
        {
            aShape.setFill(DEFAULT_PREVIEW_COLOR);
            aShape.setStroke(Color.BLACK);
        }
    }

    public void setHatchAquisitionData(boolean aVelcroSolenoid, boolean aDeploySolenoid)
    {
        mHatchVelcro.setVisible(aVelcroSolenoid);
        mHatchDeploy.setVisible(aDeploySolenoid);
    }

}
