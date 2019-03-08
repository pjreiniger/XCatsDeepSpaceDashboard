package org.xcats.deep_space.sd_widgets.controllers;

import org.xcats.deep_space.sd_widgets.Utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

public class SuperStructureController
{
    private static final double ELEVATOR_MAX_HEIGHT = 70;

    private static final double CARGO_HEIGHT = 3;
    private static final double CARGO_WIDTH = 20;
    private static final double CARGO_ROLLER_RADIUS = CARGO_HEIGHT / 2;

    private static final double HATCH_HEIGHT = 3;

    private static final double CARGO_ELEVATOR_OFFSET = 20;
    private static final double HATCH_ELEVATOR_OFFSET = 17;

    private static final double MAX_WIDTH = CARGO_WIDTH;
    private static final double MAX_HEIGHT = ELEVATOR_MAX_HEIGHT + CARGO_WIDTH;

    private static final Color DEFAULT_ELEVATOR_COLOR = Color.CADETBLUE;
    private static final Color DEFAULT_CARGO_COLOR = Color.CORNFLOWERBLUE;
    private static final Color DEFAULT_HATCH_COLOR = Color.DEEPSKYBLUE;

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
    private Rectangle mCargoAquisition;

    @FXML
    private Rectangle mCargoAquisitionGoal;

    @FXML
    private Circle mCargoRollers;

    @FXML
    private Rectangle mHatchAquisition;

    @FXML
    private Circle mHasCargoIndicator;

    private Rotate mCargoRotation;
    private Rotate mCargoGoalRotation;
    private Rotate mHatchRotation;

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

        mCargoRotation = new Rotate();
        mCargoRotation.pivotXProperty().bind(mCargoAquisition.xProperty());
        mCargoRotation.pivotYProperty().bind(mCargoAquisition.yProperty());
        mCargoAquisition.yProperty().bind(cargoYBinding);
        mCargoAquisition.getTransforms().add(mCargoRotation);

        mCargoGoalRotation = new Rotate();
        mCargoGoalRotation.pivotXProperty().bind(mCargoAquisition.xProperty());
        mCargoGoalRotation.pivotYProperty().bind(mCargoAquisition.yProperty());
        mCargoAquisitionGoal.yProperty().bind(cargoYBinding);
        mCargoAquisitionGoal.getTransforms().add(mCargoGoalRotation);

        mCargoRollers.getTransforms().add(mCargoRotation);
        mCargoRollers.centerXProperty().bind(Bindings.createObjectBinding(() ->
        {
            return mCargoAquisition.getX() + CARGO_WIDTH - CARGO_ROLLER_RADIUS;
        }, mCargoAquisition.xProperty()));
        mCargoRollers.centerYProperty().bind(Bindings.createObjectBinding(() ->
        {
            return mCargoAquisition.getY() + CARGO_ROLLER_RADIUS;
        }, mCargoAquisition.yProperty()));

        mHasCargoIndicator.setCenterX(CARGO_WIDTH + 5 * 2);
        mHasCargoIndicator.setCenterY(20);
        mHasCargoIndicator.setStroke(Color.BLACK);
        mHasCargoIndicator.setFill(Color.ORANGE);

        //////////////////////////////////////////////////
        // Hatch
        //////////////////////////////////////////////////
        ObjectBinding<Double> hatchYBinding = Bindings.createObjectBinding(() ->
        {
            return mElevator.getY() - HATCH_ELEVATOR_OFFSET + CARGO_ELEVATOR_OFFSET;
        }, mElevator.heightProperty());

        ObjectBinding<Double> hatchPivotYProperty = Bindings.createObjectBinding(() ->
        {
            return mHatchAquisition.getY() + HATCH_HEIGHT;
        }, mHatchAquisition.yProperty());


        {
            mHatchRotation = new Rotate();
            mHatchRotation.pivotXProperty().bind(mHatchAquisition.xProperty());
            mHatchRotation.pivotYProperty().bind(mHatchAquisition.yProperty());
            mHatchRotation.pivotYProperty().bind(hatchPivotYProperty);

            mHatchAquisition.getTransforms().add(mHatchRotation);
            mHatchAquisition.yProperty().bind(hatchYBinding);
        }
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

    public void setCargoData(double aAngle, double aArmMotorSpeed, double aRollerSpeed, Double aGoalAngle, boolean aHasBall)
    {
        mCargoRotation.setAngle(-aAngle); // Flip it so 90 is straight up, 0 is horizontal
        Utils.setColor(mCargoRollers, null, aRollerSpeed);
        Utils.setColor(mCargoAquisition, DEFAULT_CARGO_COLOR, aArmMotorSpeed);

        setGoalColor(mCargoAquisitionGoal, aGoalAngle);
        if (aGoalAngle != null)
        {
            mCargoGoalRotation.setAngle(-aGoalAngle);
        }

        mHasCargoIndicator.setVisible(aHasBall);
    }

    public void setHatchAquisitionData(double aAngle, double aMotorSpeed, boolean aIsRetracted, boolean aIsScoring)
    {
        mHatchRotation.setAngle(aAngle);
        Utils.setColor(mHatchAquisition, DEFAULT_HATCH_COLOR, aMotorSpeed);

        if (aIsRetracted)
        {
            mHatchAquisition.setStroke(Color.RED);
        }
        else if (aIsScoring)
        {
            mHatchAquisition.setStroke(Color.GREEN);
        }
        else
        {
            mHatchAquisition.setStroke(Color.TRANSPARENT);
        }
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

}
