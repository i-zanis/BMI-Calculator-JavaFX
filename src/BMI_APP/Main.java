package BMI_APP;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args); // launches the application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // initial values to window size for quick rescaling
        int windowWidth = 345;
        int windowHeight = 505;
        Pane pane = new Pane();
        pane.setPrefSize(windowWidth, windowHeight);

        Rectangle rectangleBase = new Rectangle(windowWidth, windowHeight, Color.WHITE);
        Rectangle rectangleTop = new Rectangle(windowWidth, windowHeight / 10f, Color.BLACK);
        Rectangle rectangleMid = new Rectangle(windowWidth, windowHeight / 11f, Color.BLACK);
        rectangleMid.setY(278);

        Label topLabel = new Label("BMI Calculator");
        topLabel.setFont(Font.font("Bauhaus 93", 30));
        topLabel.setTextFill(Color.WHITE);
        topLabel.setTextAlignment(TextAlignment.RIGHT);
        topLabel.setLayoutX(72.0);
        topLabel.setTranslateY(9.0);

        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 25;");
        calculateButton.setFont(Font.font("Bauhaus 93", 20));
        calculateButton.setLayoutX(202.0);
        calculateButton.setTranslateY(208.0);

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font(15));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setLayoutX(24.0);
        errorLabel.setTranslateY(90.0);

        Label weightLabel = new Label("Weight:");
        weightLabel.setFont(Font.font(18));
        weightLabel.setLayoutX(24.0);
        weightLabel.setTranslateY(118.0);

        Label heightLabel = new Label("Height:");
        heightLabel.setFont(Font.font(18));
        heightLabel.setLayoutX(26.0);
        heightLabel.setTranslateY(158.0);

        TextField tfWeight = new TextField();
        tfWeight.setFont(Font.font(12));
        tfWeight.setLayoutX(107.0);
        tfWeight.setTranslateY(118.0);
        tfWeight.setStyle("-fx-background-radius: 8;");
        tfWeight.setPromptText("Enter weight");

        TextField tfHeight = new TextField();
        tfHeight.setFont(Font.font(12));
        tfHeight.setLayoutX(107.0);
        tfHeight.setTranslateY(158.0);
        tfHeight.setStyle("-fx-background-radius: 8;");
        tfHeight.setPromptText("Enter height");

        ChoiceBox<String> choiceBoxWeight = new ChoiceBox<>(FXCollections.observableArrayList(
                "lbs", "kg"));
        choiceBoxWeight.setValue("lbs"); //initialize value to LBS
        choiceBoxWeight.setTooltip(new Tooltip("Select Metric or Imperial system."));
        choiceBoxWeight.setLayoutX(272);
        choiceBoxWeight.setTranslateY(118);
        choiceBoxWeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; -fx-border-radius: 8; -fx-pref-width: 45;");

        ChoiceBox<String> choiceBoxHeight = new ChoiceBox<>(FXCollections.observableArrayList(
                "in", "cm"));
        choiceBoxHeight.setValue("in");    //initialize value to inch
        choiceBoxHeight.setTooltip(new Tooltip("Select Metric or Imperial system."));
        choiceBoxHeight.setLayoutX(272);
        choiceBoxHeight.setTranslateY(158.0);
        choiceBoxHeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; -fx-border-radius: 8; -fx-pref-width: 45;");


        // Results Partition
        Label midLabel = new Label("BMI Result");
        midLabel.setFont(Font.font("Bauhaus 93", 30));
        midLabel.setTextFill(Color.WHITE);
        midLabel.setAlignment(Pos.CENTER);
        midLabel.setTextAlignment(TextAlignment.CENTER);
        midLabel.setLayoutX(95.0);
        midLabel.setTranslateY(284.0);


        Label finalResult = new Label();
        finalResult.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        finalResult.setTextFill(Color.BLACK);
        finalResult.setAlignment(Pos.CENTER);
        finalResult.setTextAlignment(TextAlignment.CENTER);
        finalResult.setLayoutX(80);
        finalResult.setTranslateY(335);

        Label bmiMessage = new Label();
        bmiMessage.setFont(Font.font(15));
        bmiMessage.setTextFill(Color.BLACK);
        bmiMessage.setTextAlignment(TextAlignment.CENTER);
        bmiMessage.setLayoutX(50.0);
        bmiMessage.setTranslateY(385);
        bmiMessage.setPrefWidth(260);
        bmiMessage.setWrapText(true);


        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 25;");
        resetButton.setFont(Font.font("Bauhaus 93", 20));
        resetButton.setLayoutX(224.0);
        resetButton.setTranslateY(450.0);
        resetButton.setVisible(false);


        pane.getChildren().addAll(rectangleBase, rectangleTop, rectangleMid, topLabel, errorLabel, calculateButton, weightLabel, heightLabel,
                tfWeight, tfHeight, choiceBoxWeight, choiceBoxHeight, midLabel, finalResult, bmiMessage, resetButton);


        EventHandler<ActionEvent> calculateButtonPress = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLabel.setText(""); //clear error label
                try {
                    Double weight = Double.parseDouble(tfWeight.getText()); //get value of user weight
                    if (choiceBoxWeight.getValue() == "kg") { //if weight is in KG
                        weight = convertKgToLbs(weight);     //base bmi formula is in imperial
                    }
                    if (weight == 0) { //check if weight is 0
                        errorLabel.setText("The weight cannot be zero."); //error label
                        tfWeight.setText(""); //clear weight text field
                        tfWeight.requestFocus(); //refocus at weight text field
                    }

                    Double height = Double.parseDouble(tfHeight.getText()); //get value of user height
                    if (choiceBoxHeight.getValue() == "cm") { //if height is in CM
                        height = convertCmToIn(height);    //base bmi formula is in imperial
                    }

                    if (height < 20) { //check if height is 0
                        errorLabel.setText("Height value too low."); //based on Chandra the shortest in the world.
                        tfHeight.setText(""); //clear height text field
                        tfHeight.requestFocus();
                    }
                    if (height == 0) throw new ArithmeticException(); // to prevent displaying infinity in finalResult

                    Double bmi = calculateBMI(weight, height); //calculate user BMI
                    resultBMI(bmi, finalResult, bmiMessage); //display result to user
                    resetButton.setVisible(true);
                } catch (NumberFormatException ex) {
                    errorLabel.setText("Enter valid number.gdfg"); //error label
                    tfHeight.setText(""); //clear height text field
                    tfHeight.requestFocus();
                    tfWeight.setText(""); //clear weight text field
                    tfWeight.requestFocus(); //refocus at weight text field
                }
                catch (ArithmeticException ex) {
                    errorLabel.setText("The height cannot be zero."); //error label
                    tfHeight.setText(""); //clear height text field
                    tfHeight.requestFocus();
                }
            }
        };

        EventHandler<ActionEvent> resetButtonPress = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLabel.setText(""); //clear error label
                finalResult.setText(""); //clear height text field
                bmiMessage.setText(""); //clear status
                tfWeight.setText(""); //clear weight text field
                tfHeight.setText(""); //clear height text field
                tfWeight.requestFocus(); //refocus at weight text field

            }
        };

        calculateButton.setOnAction(calculateButtonPress);
        //to put enter key
        resetButton.setOnAction(resetButtonPress);


        Scene scene = new Scene(pane, windowWidth, windowHeight);
        //Parent root = FXMLLoader.load(getClass().getResource("BMI.fxml"));
        primaryStage.setTitle("BMI Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Calculates the BMI from user input using Imperial System Formula
     */
    public Double calculateBMI(Double weight, Double height) {
        return weight / (height * height) * 703.0; //703 conversion factor for imperial
    }

    /**
     * Converts KG to Lbs as the formula is for imperial system
     */
    public Double convertKgToLbs(Double kg) {
        return kg * 2.20462262; //1 KG = 2.20462262 lbs
    }

    /**
     * Converts CM to Inches as the formula is for imperial system
     */
    public Double convertCmToIn(Double cm) {
        return cm * 0.393701;  //1 CM = 0.393701 IN
    }

    /**
     * Accepts the bmi and displays corresponding messages to finalResult label
     * and bmiMessage label in the middle of second half of the screen.
     */
    public void resultBMI(Double bmi, Label finalResult, Label bmiMessage) {
        finalResult.setText(String.format("Your BMI is %-2.1f", bmi));
        // finalResult.setText(bmi.toString());
        if (bmi < 18.5) {
            bmiMessage.setText("Your result suggests that you are underweight.");
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiMessage.setText("Your result suggests that you have healthy weight.");
        } else if (bmi >= 25 && bmi < 30) {
            bmiMessage.setText("Your result suggests you are overweight.");
        } else if (bmi > 30) {
            bmiMessage.setText("Your result suggests that you should skip your daily meetings with Ronald.");
        }
    }
}
