package sample;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Timer timer;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Label label = new Label("");
        label.setStyle("-fx-font-size: 40pt");
        label.setOnMouseClicked(mouseEvent -> {
            double d;
            System.out.println("1 ");
            label.setStyle("-fx-background-color: yellow; -fx-font-size: 40pt");

            for (int j = 0; j < 100000000; j++) { d = Math.random(); }
            System.out.println("2 ");
        });
        timer = new Timer();
        timer.schedule(new MyTimerTask(label), 0, 1000);
        root.getChildren().addAll(label);
        Scene scene = new Scene(root, 800, 450);
        stage.setTitle("Часы с точностью до секунды");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        timer.cancel();
    }
}

class MyTimerTask extends TimerTask {
    private Label lbl;

    public MyTimerTask(Label lbl) {
        this.lbl = lbl;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {

            lbl.setText(LocalTime.now().format(
                    DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
    }
}
