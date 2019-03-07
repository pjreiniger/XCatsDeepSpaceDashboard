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
    private double mTrackWidth;

    @FXML
    private double mTrackHeight;

    @FXML
    private Pane mPane;

    @FXML
    private Group mGroup;

    @FXML
    private Rectangle mBoundingBox;

    @FXML
    private Rectangle mHooks;

    @FXML
    public void initialize()
    {
        final double maxWidth = mTrackWidth;
        final double maxHeight = mTrackHeight;

        double minWidthMultiplier = 4;
        mPane.setMinHeight(maxHeight * minWidthMultiplier);
        mPane.setMinWidth(maxWidth * minWidthMultiplier);

        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() -> Math.min(mPane.getWidth() / maxWidth, mPane.getHeight() / maxHeight),
                mPane.widthProperty(), mPane.heightProperty());

        Scale scale = new Scale();
        scale.xProperty().bind(scaleBinding);
        scale.yProperty().bind(scaleBinding);

        mGroup.getTransforms().add(scale);

        mBoundingBox.setStrokeWidth(.1);
        mBoundingBox.setStroke(Color.BLACK);
        mBoundingBox.setFill(Color.TRANSPARENT);

        mHooks.setStroke(Color.SKYBLUE);
        mHooks.setFill(Color.TRANSPARENT);
        mHooks.setStrokeWidth(.1);
    }

    public void setHatchData(double aHookSpeed, double aHookPosition)
    {
        mHooks.setFill(Utils.getMotorColor(aHookSpeed));
        mHooks.setX(aHookPosition);
    }

}
