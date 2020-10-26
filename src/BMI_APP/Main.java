package BMI_APP;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // initial values to window size for quick rescaling
        int windowWidth = 345;
        int windowHeight = 505;
        Pane pane = new Pane();
        pane.setPrefSize(windowWidth,windowHeight);

        Rectangle rectangleTop = new Rectangle(windowWidth,windowHeight/7.8, Color.BLACK);
        Rectangle rectangleMid = new Rectangle(windowWidth,windowHeight/11f, Color.BLACK);
        rectangleMid.setY(278);

        Label topLabel = new Label("BMI Calculator");;
        topLabel.setFont(Font.font ("Bauhaus 93", 30));
        topLabel.setTextFill(Color.WHITE);
        topLabel.setTextAlignment(TextAlignment.RIGHT);
        topLabel.setLayoutX(57.0);
        topLabel.setTranslateY(9.0);

        Button calculateButton = new Button("Calculate");
        calculateButton.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 25;");
        calculateButton.setFont(Font.font ("Bauhaus 93", 20));
        calculateButton.setLayoutX(202.0);
        calculateButton.setTranslateY(208.0);

        Label errorLabel = new Label();
        errorLabel.setFont(Font.font (18));
        errorLabel.setLayoutX(14.0);
        errorLabel.setTranslateY(90.0);

        Label weightLabel = new Label("Weight:");
        weightLabel.setFont(Font.font (18));
        weightLabel.setLayoutX(14.0);
        weightLabel.setTranslateY(118.0);

        Label heightLabel = new Label("Height:");
        heightLabel.setFont(Font.font (18));
        heightLabel.setLayoutX(16.0);
        heightLabel.setTranslateY(158.0);

        TextField tfWeight = new TextField();
        tfWeight.setFont(Font.font (12));
        tfWeight.setLayoutX(97.0);
        tfWeight.setTranslateY(118.0);
        tfWeight.setStyle("-fx-background-radius: 8;");

        TextField tfHeight = new TextField();
        tfHeight.setFont(Font.font (12));
        tfHeight.setLayoutX(97.0);
        tfHeight.setTranslateY(158.0);
        tfHeight.setStyle("-fx-background-radius: 8;");

        ChoiceBox<String> choiceBoxWeight = new ChoiceBox<>(FXCollections.observableArrayList(
                "kg", "lbs"));
        choiceBoxWeight.setTooltip(new Tooltip("Select Metric or Imperial system."));
        choiceBoxWeight.setLayoutX(262.0);
        choiceBoxWeight.setTranslateY(118);
        choiceBoxWeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; -fx-border-radius: 8; -fx-pref-width: 45;");

        ChoiceBox<String> choiceBoxHeight = new ChoiceBox<>(FXCollections.observableArrayList(
                "cm", "in"));
        choiceBoxHeight.setTooltip(new Tooltip("Select Metric or Imperial system."));
        choiceBoxHeight.setLayoutX(262.0);
        choiceBoxHeight.setTranslateY(158.0);
        choiceBoxHeight.setStyle("-fx-background-color: white; -fx-border-color: #0a151b #0a151b; -fx-border-radius: 8; -fx-pref-width: 45;");


        // Results Partition
        Label midLabel = new Label("BMI Results");;
        midLabel.setFont(Font.font ("Bauhaus 93", 30));
        midLabel.setTextFill(Color.WHITE);
        midLabel.setTextAlignment(TextAlignment.CENTER);
        midLabel.setLayoutX(80.0);
        midLabel.setTranslateY(284.0);


        Label bmiMidLabel = new Label();
        bmiMidLabel.setFont(Font.font (25));
        bmiMidLabel.setTextAlignment(TextAlignment.CENTER);
        bmiMidLabel.setLayoutX(70.0);
        bmiMidLabel.setTranslateY(330);
        bmiMidLabel.setPrefWidth(260);
        bmiMidLabel.setWrapText(true);

        Label finalResult = new Label();
        finalResult.setFont(Font.font (18));
        finalResult.setTextAlignment(TextAlignment.CENTER);
        finalResult.setLayoutX(50.0);
        finalResult.setTranslateY(330);
        finalResult.setPrefWidth(260);
        finalResult.setWrapText(true);


        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 25;");
        resetButton.setFont(Font.font ("Bauhaus 93", 20));
        resetButton.setLayoutX(224.0);
        resetButton.setTranslateY(450.0);


        pane.getChildren().addAll(rectangleTop,rectangleMid,topLabel,calculateButton,weightLabel,heightLabel,
                tfWeight,tfHeight,choiceBoxWeight, choiceBoxHeight, midLabel, finalResult, resetButton);

       /** EventHandler<ActionEvent> calculateBMI = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                finalResult.setText("You are in the healthy weight range, but at the higher end. Keep an eye on your weight and try to stay in the healthy range.");
            }
        }; */

         EventHandler<ActionEvent> calculateButtonPress = new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {

                 errorLabel.setText(""); //clear error label
                 try {
                     Double weight = Double.parseDouble(tfWeight.getText()); //get value of user weight
                     if (choiceBoxWeight.getValue() == "kg") { //if weight is in KG
                         weight = convertKgToLbs(weight);     //convert to LBS
                     }

                     if (weight == 0) { //check if weight is 0
                         errorLabel.setText("Enter valid number"); //error label
                     }

                     Double height = Double.parseDouble(tfHeight.getText()); //get value of user height
                     if (choiceBoxHeight.getValue() == "cm") { //if height is in CM
                         height = convertCmToIn(height);     //convert to inch
                     }

                     if (height < 20) { //check if height is 0
                         errorLabel.setText("Height value too low."); //based on Chandra the shortest in the world.
                     }

                     Double bmi = calculateBMI(weight, height); //calculate user BMI
                     resultBMI(bmi,bmiMidLabel, finalResult); //display result to user
                 } catch (NumberFormatException ex) {
                     errorLabel.setText("Enter valid number"); //error label
                     tfHeight.setText(""); //clear height text field
                     tfHeight.selectAll();
                     tfHeight.requestFocus();
                     tfWeight.setText(""); //clear weight text field
                     tfWeight.selectAll();
                     tfWeight.requestFocus(); //refocus at weight text field
                 }
             }
         };

        // when button is pressed
        calculateButton.setOnAction(finalResult.setText("dgd"));
        //calculateButton.setOnAction(e.); to put enter key

        /**
         public void resetButtonPressed(ActionEvent event) {
            errorLabel.setText(""); //clear error label
            finalResult.setText(""); //clear status
            tfWeight.setText(""); //clear weight text field
            tfHeight.setText(""); //clear height text field
            tfWeight.selectAll();
            tfWeight.requestFocus(); //refocus at weight text field
        }
  */


        Scene scene = new Scene(pane,windowWidth,windowHeight);
        //Parent root = FXMLLoader.load(getClass().getResource("BMI.fxml"));
        primaryStage.setTitle("BMI Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

/**DropShadow shadow = new DropShadow();
//Adding the shadow when the mouse cursor is on
button3.addEventHandler(MouseEvent.MOUSE_ENTERED,
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            button3.setEffect(shadow);
        }
});
//Removing the shadow when the mouse cursor is off
button3.addEventHandler(MouseEvent.MOUSE_EXITED,
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            button3.setEffect(null);
        }
});
 * @return*/

public Double calculateBMI(Double weight, Double height) {
    return weight / (height * height);
}

    public Double convertKgToLbs(Double kg) {
        double val = 2.20462262;

        return kg * val; //1 KG = 2.20462262 LBS
    }

    /**
     * Converts centimeter to inch
     * @param cm
     * @return converted value from cm to inch
     */
    public Double convertCmToIn(Double cm) {
        double val = 0.393701;

        return cm * val; //1 CM = 0.393701 IN
    }

    public void resultBMI(Double bmi, Label bmiMidLabel, Label finalResult) {
       // bmiValueLabel.setText(bmiValue); //set BMI value
        bmiMidLabel.setText(String.valueOf(bmi));
        if(bmi < 18.5) {
            finalResult.setText("Underweight");
        }
        else if(bmi >= 18.5 && bmi < 25) {
            finalResult.setText("Normal weight");
        }
        else if(bmi >= 25 && bmi < 30) {
            finalResult.setText("Overweight");
        }
        else finalResult.setText("Obese");
        }

    public static void main(String[] args) {
        launch(args);
    }
}
