package BMI_APP;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * BMI Calculator application created in JavaFX.
 * Created non-standard for educational purposes without FXML/Scene Builder - Gluon.

 *
 * Made on OpenJDK14.
 * Please download "javafx-sdk-11.0" and add all the jar-files in
 * Javafx-sdk-11.0.2\lib\ to the global library.
 * VM options --module-path %java path% --add-modules javafx.controls,javafx.fxml
 *
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args); // launches the application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // initial values of window size for quick rescaling
        int windowWidth = 345;
        int windowHeight = 505;
        Pane pane = new Pane();
        pane.setPrefSize(windowWidth, windowHeight);

        // Settings of top partition

        // Creation of the white background (rectangle), top black rectangle, mid rectangle
        Rectangle rectangleBase = new Rectangle(windowWidth, windowHeight, Color.WHITE);
        Rectangle rectangleTop = new Rectangle(windowWidth, windowHeight / 10f, Color.BLACK);
        Rectangle rectangleMid = new Rectangle(windowWidth, windowHeight / 11f, Color.BLACK);
        rectangleMid.setY(278);

        // title BMI calculator at the top of the window
        Label topLabel = new Label("BMI Calculator");
        topLabel.setFont(Font.font("Bauhaus 93", 30));
        topLabel.setTextFill(Color.WHITE);
        topLabel.setTextAlignment(TextAlignment.RIGHT);
        topLabel.setLayoutX(69.0);
        topLabel.setTranslateY(9.0);

        // Settings for Calculate button
        Button calculateButton = new Button("Calculate");
        // remove the default focus(blue glow) on button press
        calculateButton.setStyle("-fx-focus-color: black; -fx-faint-focus-color: black; -fx-inner-border: white; " +
                "-fx-background-color: black -fx-faint-focus-color, " +
                "-fx-focus-color, -fx-inner-border;");
        calculateButton.setFont(Font.font("Bauhaus 93", 20));
        calculateButton.setTooltip(new Tooltip("Press to calculate your BMI.")); // mouseover tooltip
        calculateButton.setLayoutX(202.0);
        calculateButton.setTranslateY(208.0);

        // Settings for error label on invalid input under calculateButton/above weightLabel
        Label errorLabel = new Label();
        errorLabel.setFont(Font.font(15));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setLayoutX(24.0);
        errorLabel.setTranslateY(90.0);

        // Settings for label displaying "Weight": on the left
        Label weightLabel = new Label("Weight:");
        weightLabel.setFont(Font.font(18));
        weightLabel.setLayoutX(24.0);
        weightLabel.setTranslateY(118.0);

        // Settings for label displaying "Height" on the left
        Label heightLabel = new Label("Height:");
        heightLabel.setFont(Font.font(18));
        heightLabel.setLayoutX(24.0);
        heightLabel.setTranslateY(158.0);

        // Settings for TextField next to weightLabel
        TextField tfWeight = new TextField();
        tfWeight.setFont(Font.font(12));
        // weaken default(blue glow) focus to match the app's colors
        tfWeight.setStyle("-fx-focus-color: black; -fx-background-radius: 15;");
        tfWeight.setLayoutX(107.0);
        tfWeight.setTranslateY(118.0);
        tfWeight.setPromptText("Enter weight"); // gray text implying input

        // Settings for TextField next to heightLabel
        TextField tfHeight = new TextField();
        // weaken default(blue glow) focus to match the app's colors
        tfHeight.setStyle("-fx-focus-color: black; -fx-background-radius: 15;");
        tfHeight.setFont(Font.font(12));
        tfHeight.setLayoutX(107.0);
        tfHeight.setTranslateY(158.0);
        tfHeight.setPromptText("Enter height"); // gray text implying input

        // Settings for Options for height Imperial/Metric on the right of tfWeight
        ChoiceBox<String> choiceBoxWeight = new ChoiceBox<>(FXCollections.observableArrayList("lbs", "kg"));
        choiceBoxWeight.setValue("lbs"); // set initial value to lbs
        choiceBoxWeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; " +
                "-fx-border-radius: 8; -fx-pref-width: 45;");
        choiceBoxWeight.setTooltip(new Tooltip("Select Metric or Imperial system.")); // mouseover tooltip
        choiceBoxWeight.setLayoutX(272);
        choiceBoxWeight.setTranslateY(118);


        // Settings for Options for height Imperial/Metric on the right of tfHeight
        ChoiceBox<String> choiceBoxHeight = new ChoiceBox<>(FXCollections.observableArrayList("in", "cm"));
        choiceBoxHeight.setValue("in");    // set initial value to inch
        choiceBoxHeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; " +
                "-fx-border-radius: 8; -fx-pref-width: 45;");
        choiceBoxHeight.setTooltip(new Tooltip("Select Metric or Imperial system.")); // mouseover tooltip
        choiceBoxHeight.setLayoutX(272);
        choiceBoxHeight.setTranslateY(158.0);


        // End of Settings for top partition


        // Start of second partition Settings "Results"

        // Settings for middle center label "BMI Result"
        Label midLabel = new Label("BMI Result");
        midLabel.setFont(Font.font("Bauhaus 93", 30));
        midLabel.setTextFill(Color.WHITE);
        midLabel.setAlignment(Pos.CENTER);
        midLabel.setTextAlignment(TextAlignment.CENTER);
        midLabel.setLayoutX(103.0);
        midLabel.setTranslateY(284.0);

        // Settings for finalResult label under midLabel -shows final BMI result
        Label finalResult = new Label();
        finalResult.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        finalResult.setTextFill(Color.BLACK);
        finalResult.setAlignment(Pos.CENTER);
        finalResult.setTextAlignment(TextAlignment.CENTER);
        finalResult.setLayoutX(80);
        finalResult.setTranslateY(335);

        // Settings for displayed suggesting message under finalResult
        Label bmiMessage = new Label();
        bmiMessage.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        bmiMessage.setTextFill(Color.BLACK);
        bmiMessage.setTextAlignment(TextAlignment.CENTER);
        bmiMessage.setLayoutX(50.0);
        bmiMessage.setTranslateY(380);
        bmiMessage.setPrefWidth(260);
        bmiMessage.setWrapText(true);

        // Settings for resetButton that appears after calculateButton is pressed
        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-focus-color: black; -fx-faint-focus-color: black; -fx-inner-border: white; " +
                "-fx-background-color: black -fx-faint-focus-color, " +
                "-fx-focus-color, -fx-inner-border;");
        resetButton.setFont(Font.font("Bauhaus 93", 20));
        resetButton.setTooltip(new Tooltip("Press to reset all fields.")); // mouseover tooltip
        resetButton.setLayoutX(224.0);
        resetButton.setTranslateY(450.0);
        resetButton.setVisible(false);
        // End of second partition Settings "Results"

        // Add all nodes to pane. New nodes won't display if not added here
        pane.getChildren().addAll(rectangleBase, rectangleTop, rectangleMid, topLabel, errorLabel, calculateButton, weightLabel, heightLabel,
                tfWeight, tfHeight, choiceBoxWeight, choiceBoxHeight, midLabel, finalResult, bmiMessage, resetButton);

        // Start of Event settings for buttons

        // Event handler for the calculateButton
        EventHandler<ActionEvent> calculateButtonPress = Event -> {
            errorLabel.setText(""); // clear error label
            // to control displaying of large decimals on unrealistic/incorrect weight/height inputs that take space
            boolean proceed = true;
            try {
                Double weight = Double.parseDouble(tfWeight.getText()); // get weight from TextField
                if (choiceBoxWeight.getValue().equals("kg")) {          // if weight is in KG
                    weight = convertKgToLbs(weight);                    // base bmi formula is in imperial
                }

                if (weight == 0) { //check if weight is 0
                    errorLabel.setText("The weight cannot be zero."); //errorLabel
                    tfWeight.setText("");    // clear weight TextField
                    tfWeight.requestFocus(); // goes back to weight TextField
                }
                //slightly larger than Jon Brower Minnoch the heaviest in the world in pounds.
                if (weight > 1000) {
                    errorLabel.setText("Weight value too high."); //errorLabel
                    tfWeight.setText("");    // clear height TextField
                    tfWeight.requestFocus(); // goes back to weight TextField
                    // to prevent displaying large decimal number of BMI on high weight on incorrect inputs
                    proceed = false;
                }
                Double height = Double.parseDouble(tfHeight.getText()); //get height from TextField
                if (choiceBoxHeight.getValue().equals("cm")) { // if height is in CM
                    height = convertCmToIn(height);            // base bmi formula is in imperial
                }
                // control the display of numbers with many digits that don't fit in the window
                if (height < 22) {
                    errorLabel.setText("Height value too low."); // based on Chandra the shortest in the world.
                    tfHeight.setText("");                        // clear height text field
                    tfHeight.requestFocus();                     // goes back to height TextField
                    // to prevent displaying large decimal number of BMI on low height on incorrect inputs
                    proceed = false;
                }
                if (height == 0.0) throw new ArithmeticException(); // to prevent displaying infinity in finalResult
                // to control displaying of large decimals that take space
                if (proceed) {
                    Double bmi = calculateBMI(weight, height);// calculate user BMI
                    resultBMI(bmi, finalResult, bmiMessage);  // display result to user
                    resetButton.setVisible(true);             // reveals the resetButton when calculate is pressed
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Enter a valid number."); // errorLabel
                tfHeight.setText("");                        // clear height TextField
                tfHeight.requestFocus();                     // goes back to the height TextField
                tfWeight.setText("");                        // clear weight TextField
                tfWeight.requestFocus();                     // goes back to the weight TextField
            } catch (ArithmeticException ex) {
                errorLabel.setText("The height cannot be zero."); // errorLabel
                tfHeight.setText("");                             // clear height TextField
                tfHeight.requestFocus();                          // goes back to the height TextField
            }
        };

        // Pressing enter will move to the next TextField (tfHeight)
        tfWeight.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        tfHeight.requestFocus(); // goes back to height TextField
                    }
                }
        );

        // Pressing enter will fire the calculateButton
        tfHeight.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        calculateButton.fire();
                    }
                }
        );

        // Event handler for resetButton
        EventHandler<ActionEvent> resetButtonPress = event -> {
            errorLabel.setText("");  // clear errorLabel
            finalResult.setText(""); // clear height TextField
            bmiMessage.setText("");  // clear bmiMessage text
            tfWeight.setText("");    // clear weight TextField
            tfHeight.setText("");    // clear height TextField
            tfWeight.requestFocus(); // goes back at weight TextField

        };

        // Setting event source objects
        // calculate button on press
        calculateButton.setOnAction(calculateButtonPress);

        // resetButton on press
        resetButton.setOnAction(resetButtonPress);

        // End of Event settings for buttons

        Scene scene = new Scene(pane, windowWidth, windowHeight);
        primaryStage.setTitle("BMI Calculator");
        primaryStage.getIcons().add(new Image("BMI_APP/bmiIcon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Calculates the BMI from user input using Imperial System Formula.
     */
    public Double calculateBMI(Double weight, Double height) {
        return weight / (height * height) * 703.0; // 703 conversion factor for imperial
    }

    /**
     * Converts KG to Lbs because the formula is for Imperial System
     */
    public Double convertKgToLbs(Double kg) {
        return kg * 2.20462262; // 1 KG = 2.20462262 lbs
    }

    /**
     * Converts CM to Inches because the formula is for Imperial System.
     */
    public Double convertCmToIn(Double cm) {
        return cm * 0.393701;  // 1 CM = 0.393701 IN
    }

    /**
     * Accepts the BMI and displays corresponding messages to finalResult label
     * and bmiMessage label in the middle of second half of the screen.
     */
    public void resultBMI(Double bmi, Label finalResult, Label bmiMessage) {
        // display format to 1 decimal digit
        finalResult.setText(String.format("Your BMI is %-2.1f", bmi));
        if (bmi < 18.5) {
            bmiMessage.setText("Your result suggests that you are underweight.");
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiMessage.setText("Your result suggests that you have healthy weight.");
        } else if (bmi >= 25 && bmi < 30) {
            bmiMessage.setText("Your result suggests you are overweight.");
        } else if (bmi > 30) {
            bmiMessage.setText("Your result suggest that you are obese.");


        }
    }
}
