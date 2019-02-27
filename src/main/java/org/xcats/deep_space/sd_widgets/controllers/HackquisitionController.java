package org.xcats.deep_space.sd_widgets.controllers;

import org.xcats.deep_space.sd_widgets.Utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

public class HackquisitionController
{
    @FXML
    private double TRACK_WIDTH;

    @FXML
    private double TRACK_HEIGHT;

    @FXML
    private Pane mPane;

    @FXML
    private Group mGroup;


    @FXML
    private Rectangle mBoundingBox;

    @FXML
    private Rectangle mLeftHook;

    @FXML
    private Rectangle mRightHook;

    @FXML
    public void initialize()
    {
        final double MAX_WIDTH = TRACK_WIDTH;
        final double MAX_HEIGHT = TRACK_HEIGHT;

        double minWidthMultiplier = 10;
        mPane.setMinHeight(MAX_HEIGHT * minWidthMultiplier);
        mPane.setMinWidth(MAX_WIDTH * minWidthMultiplier);

        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() -> Math.min(mPane.getWidth() / MAX_WIDTH, mPane.getHeight() / MAX_HEIGHT),
                mPane.widthProperty(), mPane.heightProperty());

        Scale scale = new Scale();
        scale.xProperty().bind(scaleBinding);
        scale.yProperty().bind(scaleBinding);

        mGroup.getTransforms().add(scale);

        mBoundingBox.setStrokeWidth(.1);
        mBoundingBox.setStroke(Color.BLACK);
        mBoundingBox.setFill(Color.TRANSPARENT);

        mLeftHook.setStroke(Color.SKYBLUE);
        mLeftHook.setFill(Color.TRANSPARENT);
        mLeftHook.setStrokeWidth(.1);

        mRightHook.setStroke(Color.DARKBLUE);
        mRightHook.setFill(Color.TRANSPARENT);
        mRightHook.setStrokeWidth(.1);
    }

    public void setHatchData(double leftSpeed, double leftPosition, double rightSpeed, double rightPosition)
    {
        mLeftHook.setFill(Utils.getMotorColor(leftSpeed));
        mLeftHook.setX(leftPosition);

        mRightHook.setFill(Utils.getMotorColor(rightSpeed));
        mRightHook.setX(rightPosition);
    }

}
