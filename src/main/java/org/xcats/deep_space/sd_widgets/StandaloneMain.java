package org.xcats.deep_space.sd_widgets;

import java.io.IOException;

import org.xcats.deep_space.sd_widgets.controllers.SuperStructureController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StandaloneMain
{
    private final SuperStructureController mRobotController;
    private double mElevatorSpeed;
    private double mCargoWheelSpeed;
    private boolean mCargoHasBall;
    private boolean mHatchVelcro;
    private boolean mHatchPusher;

    private double mElevatorHeight;

    private Double mElevatorGoal;

    public StandaloneMain(Scene aScene, SuperStructureController aRobotController)
    {
        mRobotController = aRobotController;
        mElevatorHeight = 0;

        aScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(KeyEvent aEvent)
            {
                KeyCode code = aEvent.getCode();
                switch (code)
                {
                case W:
                    mElevatorSpeed = 1;
                    mElevatorGoal = 70.0;
                    break;
                case S:
                    mElevatorSpeed = -1;
                    mElevatorGoal = 30.0;
                    break;

                case A:
                    mCargoWheelSpeed = -1;
                    break;
                case D:
                    mCargoWheelSpeed = 1;
                    break;

                case I:
                    mCargoHasBall = true;
                    break;
                case K:
                    mHatchVelcro = true;
                    break;
                case J:
                    mHatchPusher = true;
                    break;

                default:
                    // ignored
                }
                handleUpdate();
            }

        });

        aScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {

            @Override
            public void handle(KeyEvent aEvent)
            {
                KeyCode code = aEvent.getCode();
                switch (code)
                {
                case W:
                case S:
                    mElevatorSpeed = 0;
                    mElevatorGoal = null;
                    break;
                case A:
                case D:
                    mCargoWheelSpeed = 0;
                    break;
                case I:
                    mCargoHasBall = false;
                    break;
                case K:
                    mHatchVelcro = false;
                    break;
                case J:
                    mHatchPusher = false;
                    break;
                default:
                    // ignored
                }
                handleUpdate();
            }

        });
    }

    private void handleUpdate()
    {
        mElevatorHeight += mElevatorSpeed * .8;

        mRobotController.setElevatorData(mElevatorHeight, mElevatorSpeed, mElevatorGoal);
        mRobotController.setCargoData(mCargoWheelSpeed, mCargoHasBall);
        mRobotController.setHatchAquisitionData(mHatchVelcro, mHatchPusher);
    }


    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("super_structure_display.fxml"));
            Pane root = loader.load();
            SuperStructureController robotCotroller = loader.getController();

            Scene scene = new Scene(root);
            aPrimaryStage.setScene(scene);

            new StandaloneMain(scene, robotCotroller);
            aPrimaryStage.show();
        }
    }

    @SuppressWarnings("JavadocMethod")
    public static void main(String[] aArgs)
    {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        // System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, aArgs);
    }
}
