package com.example.hotelmanagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminMainPageController implements Initializable {
    @FXML
    private ImageView appLogo;

    @FXML
    private Label clockLabel;

    @FXML
    private StackPane mainContent;

    @FXML
    private ImageView userAvatar;

    @FXML
    private Label userRole;

    @FXML
    private Label dateLabel;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startClock();
        setDateLabel();
    }

    private void startClock() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime now = LocalTime.now();
                    clockLabel.setText(now.format(timeFormatter));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setDateLabel() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateLabel.setText(today.format(formatter));
    }
}
