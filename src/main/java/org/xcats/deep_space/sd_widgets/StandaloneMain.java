package org.xcats.deep_space.sd_widgets;

import java.io.IOException;

import org.xcats.deep_space.sd_widgets.controllers.RobotDisplayController;

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
    private final RobotDisplayController mRobotController;
    private double mElevatorSpeed;
    private double mCargoSpeed;
    private double mHatchArmSpeed;
    private double mHatchHookSpeed;
    private double mHatchLefthHookPosition;
    private double mHatchRighthHookPosition;

    private double mElevatorHeight;
    private double mCargoAngle;
    private double mHatchAngle;

    private Double mElevatorGoal;
    private Double mCargoGoalAngle;
    private Double mHatchGoalAngle;

    public StandaloneMain(Scene aScene, RobotDisplayController aRobotController)
    {
        mRobotController = aRobotController;
        mElevatorHeight = 0;
        mCargoAngle = 90;
        mHatchAngle = 0;

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
                    mCargoSpeed = -1;
                    mCargoGoalAngle = 90.0;
                    break;
                case D:
                    mCargoSpeed = 1;
                    mCargoGoalAngle = 0.0;
                    break;

                case I:
                    mHatchArmSpeed = 1;
                    mHatchGoalAngle = 45.0;
                    break;
                case K:
                    mHatchArmSpeed = -1;
                    mHatchGoalAngle = 20.0;
                    break;

                case J:
                    mHatchHookSpeed = 1;
                    break;
                case L:
                    mHatchHookSpeed = -1;
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
                    mCargoSpeed = 0;
                    mCargoGoalAngle = null;
                    break;
                case I:
                case K:
                    mHatchArmSpeed = 0;
                    mHatchGoalAngle = null;
                    break;
                case J:
                case L:
                    mHatchHookSpeed = 0;
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
        mCargoAngle += mCargoSpeed * 1;
        mHatchAngle += mHatchArmSpeed * 1;

        mHatchLefthHookPosition += mHatchHookSpeed *= 1;
        mHatchRighthHookPosition += mHatchHookSpeed *= 1.2;

        System.out.println(mElevatorHeight + ", " + mCargoAngle + ", " + mHatchAngle); // NOPMD
        mRobotController.setElevatorData(mElevatorHeight, mElevatorSpeed, mElevatorGoal);
        mRobotController.setCargoData(mCargoAngle, mCargoSpeed, .5, mCargoGoalAngle);
        mRobotController.setHatchAquisitionData(mHatchAngle, mHatchArmSpeed, mHatchGoalAngle, mHatchHookSpeed, mHatchLefthHookPosition,
                -mHatchHookSpeed, mHatchRighthHookPosition);
    }


    public static class PseudoMain extends Application
    {

        @Override
        public void start(Stage aPrimaryStage) throws IOException
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("robot_display.fxml"));
            Pane root = loader.load();
            RobotDisplayController robotCotroller = loader.getController();

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
