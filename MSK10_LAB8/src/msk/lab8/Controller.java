package msk.lab8;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller {
    @FXML
    private Rectangle bus;
    @FXML
    private Slider theFirstSlider;
    @FXML
    private Slider theSecondSlider;
    @FXML
    private Button buttonStart;
    private Polyline theFirstGroupOfCoordinates = new Polyline();
    private Polyline theSecondGroupOfCoordinates = new Polyline();
    private Polyline theThirdGroupOfCoordinates = new Polyline();
    private Polyline theFourthGroupOfCoordinates = new Polyline();
    private Polyline theFifthGroupOfCoordinates = new Polyline();
    private Polyline theSixthGroupOfCoordinates = new Polyline();
    private Polyline theSevenGroupOfCoordinates = new Polyline();
    private PathTransition theFirstTransition = new PathTransition();
    private PathTransition theSecondTransition = new PathTransition();
    private PathTransition theThirdTransition = new PathTransition();
    private PathTransition theFourthTransition = new PathTransition();
    private PathTransition theFifthTransition = new PathTransition();
    private PathTransition theSixthTransition = new PathTransition();
    private PathTransition theSeventhTransition = new PathTransition();
    private RotateTransition theFirstRtNinety = new RotateTransition(Duration.seconds(0.5), bus);
    private RotateTransition theSecondRtNinety = new RotateTransition(Duration.seconds(0.5), bus);
    private RotateTransition theThirdRtNinety = new RotateTransition(Duration.seconds(0.5), bus);
    private RotateTransition theFourthRtNinety = new RotateTransition(Duration.seconds(0.5), bus);
    private RotateTransition theFirstRtMinusNinety = new RotateTransition(Duration.seconds(0.5), bus);
    private RotateTransition theSecondRtMinusNinety = new RotateTransition(Duration.seconds(0.5), bus);

    public void initialize() {
        Image image = new Image("msk/lab8/bus.png");
        bus.setFill(new ImagePattern(image));

        theFirstTransition.setNode(bus);
        theFirstTransition.setDuration(Duration.seconds(1));
        theFirstTransition.setPath(theFirstGroupOfCoordinates);

        theSecondTransition.setNode(bus);
        theSecondTransition.setDuration(Duration.seconds(1));
        theSecondTransition.setPath(theSecondGroupOfCoordinates);

        theThirdTransition.setNode(bus);
        theThirdTransition.setDuration(Duration.seconds(1));
        theThirdTransition.setPath(theThirdGroupOfCoordinates);

        theFourthTransition.setNode(bus);
        theFourthTransition.setDuration(Duration.seconds(1));
        theFourthTransition.setPath(theFourthGroupOfCoordinates);

        theFifthTransition.setNode(bus);
        theFifthTransition.setDuration(Duration.seconds(1));
        theFifthTransition.setPath(theFifthGroupOfCoordinates);

        theSixthTransition.setNode(bus);
        theSixthTransition.setDuration(Duration.seconds(1));
        theSixthTransition.setPath(theSixthGroupOfCoordinates);

        theSeventhTransition.setNode(bus);
        theSeventhTransition.setDuration(Duration.seconds(1));
        theSeventhTransition.setPath(theSevenGroupOfCoordinates);

        theFirstRtNinety.setByAngle(90);
        theSecondRtNinety.setByAngle(90);
        theThirdRtNinety.setByAngle(90);
        theFourthRtNinety.setByAngle(90);
        theFirstRtMinusNinety.setByAngle(-90);
        theSecondRtMinusNinety.setByAngle(-90);
    }

    public void cleanUp() {
        theFirstGroupOfCoordinates.getPoints().remove(0, theFirstGroupOfCoordinates.getPoints().size());
        theSecondGroupOfCoordinates.getPoints().remove(0, theSecondGroupOfCoordinates.getPoints().size());
        theThirdGroupOfCoordinates.getPoints().remove(0, theThirdGroupOfCoordinates.getPoints().size());
        theFourthGroupOfCoordinates.getPoints().remove(0, theFourthGroupOfCoordinates.getPoints().size());
        theFifthGroupOfCoordinates.getPoints().remove(0, theFifthGroupOfCoordinates.getPoints().size());
        theSixthGroupOfCoordinates.getPoints().remove(0, theSixthGroupOfCoordinates.getPoints().size());
        theSevenGroupOfCoordinates.getPoints().remove(0, theSevenGroupOfCoordinates.getPoints().size());
    }

    @FXML
    public void buttonStartAction() {
        buttonStart.setDisable(true);
        // Начинаем движение с первой остановки
        if (theFirstSlider.getValue() == 1) {
            if (theSecondSlider.getValue() >= 2) {
                bus.setRotate(0);
                cleanUp();
                theSecondStop();
            } else {
                buttonStart.setDisable(false);
            }
            // Начинаем движение со второй остановки
        } else if (theFirstSlider.getValue() == 2) {
            if (theSecondSlider.getValue() >= 3) {
                bus.setRotate(90);
                cleanUp();
                theThirdStop();
            } else {
                buttonStart.setDisable(false);
            }
            // Начинаем движение с третьей остановки
        } else if (theFirstSlider.getValue() == 3) {
            if (theSecondSlider.getValue() >= 4) {
                bus.setRotate(90);
                cleanUp();
                theFourthStop();
            } else {
                buttonStart.setDisable(false);
            }
            // Начинаем движение с четвертой остановки
        } else if (theFirstSlider.getValue() == 4) {
            if (theSecondSlider.getValue() >= 5) {
                bus.setRotate(180);
                cleanUp();
                theFifthStop();
            } else {
                buttonStart.setDisable(false);
            }
        } else if (theFirstSlider.getValue() == 5) {
            if (theSecondSlider.getValue() >= 6) {
                bus.setRotate(0);
                cleanUp();
                theSixthStop();
            } else {
                buttonStart.setDisable(false);
            }
        } else if (theFirstSlider.getValue() == 6) {
            if (theSecondSlider.getValue() == 7) {
                bus.setRotate(180);
                cleanUp();
                theSeventhStop();
            } else {
                buttonStart.setDisable(false);
            }
        }
    }

    // Движемся до второй остановки
    public void theSecondStop() {
        // Координаты начала второй остановки
        theFirstGroupOfCoordinates.getPoints().addAll(0.0, 0.0,
                256.0, 0.0);
        theSecondGroupOfCoordinates.getPoints().addAll(256.0, 0.0,
                256.0, 77.0);
        SequentialTransition seqT = new SequentialTransition (bus, theFirstTransition, theFirstRtNinety, theSecondTransition);
        seqT.play();

        // Движемся до третьей остановки
        if (theSecondSlider.getValue() >= 3) {
            theThirdStop();
        } else {
            seqT.setOnFinished(event -> buttonStart.setDisable(false));
        }
    }

    // Движемся до третьей остановки
    public void theThirdStop() {
        // Координаты начала третьей остановки
        theSecondGroupOfCoordinates.getPoints().addAll(256.0, 77.0,
                256.0, 215.0);
        SequentialTransition seqT;
        try {
            seqT = new SequentialTransition (bus, theFirstTransition, theFirstRtNinety, theSecondTransition);
            seqT.play();
        } catch (Exception e) {
            seqT = new SequentialTransition (bus, theSecondTransition);
            seqT.play();
        }

        // Движемся до четвертой остановки
        if (theSecondSlider.getValue() >= 4) {
            theFourthStop();
        } else {
            seqT.setOnFinished(event -> buttonStart.setDisable(false));
        }
    }

    // Движемся до четвертой остановки
    public void theFourthStop() {
        // Координаты начала четвертой остановки
        theSecondGroupOfCoordinates.getPoints().addAll(256.0, 215.0,
                256.0, 412.0);
        theThirdGroupOfCoordinates.getPoints().addAll(256.0, 412.0,
                0.0, 412.0);
        SequentialTransition seqT;
        try {
            seqT = new SequentialTransition (bus, theFirstTransition, theFirstRtNinety, theSecondTransition, theSecondRtNinety, theThirdTransition);
            seqT.play();
        } catch (Exception e) {
            seqT = new SequentialTransition (bus, theSecondTransition, theSecondRtNinety, theThirdTransition);
            seqT.play();
        }

        if (theSecondSlider.getValue() >= 5) {
            theFifthStop();
        } else {
            seqT.setOnFinished(event -> buttonStart.setDisable(false));
        }
    }

    // Движемся до пятой остановки
    public void theFifthStop() {
        // Координаты начала пятой остановки
        theThirdGroupOfCoordinates.getPoints().addAll(0.0, 412.0,
                -236.0, 412.0);
        theFourthGroupOfCoordinates.getPoints().addAll(-236.0, 412.0,
                -236.0, 274.0);
        SequentialTransition seqT;

        if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 2) {
            seqT = new SequentialTransition(bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition);
            seqT.play();
        } else if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 3) {
            seqT = new SequentialTransition (bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition);
            seqT.play();
         } else if (theSecondGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 4) {
            seqT = new SequentialTransition (bus, theThirdTransition, theThirdRtNinety, theFourthTransition);
            seqT.play();
          } else {
            seqT = new SequentialTransition(bus, theFirstTransition, theFirstRtNinety, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition);
            seqT.play();
        }

        if (theSecondSlider.getValue() >= 6) {
            theSixthStop();
        } else {
            seqT.setOnFinished(event -> buttonStart.setDisable(false));
        }
    }

    // Движемся до шестой остановки
    public void theSixthStop() {
        // Координаты начала шестой остановки
        theFifthGroupOfCoordinates.getPoints().addAll(-236.0, 273.0,
                -105.0, 273.0);
        theSixthGroupOfCoordinates.getPoints().addAll(-105.0, 273.0,
                -105.0, 121.0);
        SequentialTransition seqT;
        if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 2) {
            seqT = new SequentialTransition(bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition);
            seqT.play();
        } else if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 3) {
            seqT = new SequentialTransition (bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition);
            seqT.play();
        } else if (theSecondGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 4) {
            seqT = new SequentialTransition (bus, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition);
            seqT.play();
        } else if (theThirdGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 5) {
            seqT = new SequentialTransition (bus, theFifthTransition, theFirstRtMinusNinety, theSixthTransition);
            seqT.play();
        } else {
            seqT = new SequentialTransition(bus, theFirstTransition, theFirstRtNinety, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition);
            seqT.play();
        }

        if (theSecondSlider.getValue() >= 7) {
            theSeventhStop();
        } else {
            seqT.setOnFinished(event -> buttonStart.setDisable(false));
        }
    }

    // Движемся до седьмой остановки
    public void theSeventhStop() {
        // Координаты начала седьмой остановки
        theSevenGroupOfCoordinates.getPoints().addAll(-105.0, 120.0,
                -395.0, 120.0);
        SequentialTransition seqT;
        if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 2) {
            seqT = new SequentialTransition(bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition, theSecondRtMinusNinety, theSeventhTransition);
            seqT.play();
        } else if (theFirstGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 3) {
            seqT = new SequentialTransition (bus, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition, theSecondRtMinusNinety, theSeventhTransition);
            seqT.play();
        } else if (theSecondGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 4) {
            seqT = new SequentialTransition (bus, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition, theSecondRtMinusNinety, theSeventhTransition);
            seqT.play();
        } else if (theThirdGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 5) {
            seqT = new SequentialTransition (bus, theFifthTransition, theFirstRtMinusNinety, theSixthTransition, theSecondRtMinusNinety, theSeventhTransition);
            seqT.play();
        } else if (theThirdGroupOfCoordinates.getPoints().size() == 0 && theFirstSlider.getValue() == 6) {
            seqT = new SequentialTransition (bus, theSeventhTransition);
            seqT.play();
        } else {
            seqT = new SequentialTransition(bus, theFirstTransition, theFirstRtNinety, theSecondTransition, theSecondRtNinety, theThirdTransition, theThirdRtNinety, theFourthTransition, theFourthRtNinety, theFifthTransition, theFirstRtMinusNinety, theSixthTransition, theSecondRtMinusNinety, theSeventhTransition);
            seqT.play();
        }
        seqT.setOnFinished(event -> buttonStart.setDisable(false));
    }
}
