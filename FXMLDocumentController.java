/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldcountries;

import java.io.*;
import static java.lang.System.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.media.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javax.sound.sampled.*;

/**
 *
 * @author Lleaf
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private MenuItem editBt;
    @FXML
    private MenuItem closeBt;
    @FXML
    private MenuItem freeModeBt;
    @FXML
    private MenuItem countryBt;
    @FXML
    private MenuItem capitalBt;
    @FXML
    private MenuItem flagBt;
    @FXML
    private MenuItem darkBt;
    @FXML
    private MenuItem lightBt;
    @FXML
    private VBox Vbox;
    @FXML
    private ImageView FlagImg;
    @FXML
    private TextField countryTf;
    @FXML
    private TextField capitalTf;
    @FXML
    private Button saveBt;
    @FXML
    private Button deleteBt;
    @FXML
    private Button nextBt;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView mapImg;
    @FXML
    private ImageView markerImg;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button PreviousBt;
    @FXML
    private MenuItem AllBt;
    @FXML
    private MenuItem AfricaBt;
    @FXML
    private MenuItem AmericaBt;
    @FXML
    private MenuItem AsiaBt;
    @FXML
    private MenuItem EuropeBt;
    @FXML
    private MenuItem OceniaBt;
    @FXML
    private MenuItem AmericanStatesBt;
    @FXML
    private Menu continentMb;
    @FXML
    private MenuItem doneMi;
    @FXML
    private Menu modeMb;
    @FXML
    private CheckMenuItem soundBt;

    // Global Variables
    private int x;
    private int mode;
    private Country tempCountry;
    private ArrayList<Country> countries;
    private String DataFile = "C:\\Users\\shabi\\Documents\\NetBeansProjects\\WorldCountries\\Resources\\Data_ALL.ser";
    private String path;
    private Media media;
    private MediaPlayer media_player;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        x = 0;
        nextBt.setVisible(false);
        saveBt.setVisible(false);
        deleteBt.setVisible(false);
        PreviousBt.setVisible(false);
        progressBar.setVisible(false);
        soundBt.setSelected(true);

        tempCountry = new Country();
        ReadFile();
        setMap();
        scrollPane.setHvalue(0.42337164750957856);
        scrollPane.setVvalue(0.16975308641975312);
    }

    @FXML
    private void Edit(ActionEvent event) {
        continentMb.setDisable(true);
        modeMb.setDisable(true);
        x = 0;
        mode = 2;
        ReadFile();
        //countries = tempCountry.getCountries();
        updateGUI();
        FreeMode(event);
        //DeleteDashes();
    }
    
    // This method deletes dashes of a country's name
    private void DeleteDashes() {
        //Get ride of dashes
        for (int i = 0; i < countries.size(); i++) {
            countries.get(i).setName(countries.get(i).getName().replace("-", " "));
            countries.get(i).setCapital(countries.get(i).getCapital().replace("-", " "));
        }
    }

    @FXML
    private void FreeMode(ActionEvent event) {
        x = 0;
        mode = 2;
        countryTf.setDisable(false);
        capitalTf.setDisable(false);
        progressBar.setVisible(true);
        nextBt.setVisible(true);
        PreviousBt.setVisible(true);
        saveBt.setVisible(true);
        deleteBt.setVisible(true);
        updateGUI();
        //Next(event);
    }

    @FXML
    private void Country(ActionEvent event) {
        mode = 3;
        Collections.shuffle(countries);
        countryTf.setDisable(true);
        capitalTf.setDisable(false);
        progressBar.setVisible(true);
        nextBt.setVisible(true);
        saveBt.setVisible(false);
        deleteBt.setVisible(false);
        PreviousBt.setVisible(true);
        NewSetup(event);
    }

    @FXML
    private void Capital(ActionEvent event) {
        mode = 4;
        Collections.shuffle(countries);
        countryTf.setDisable(false);
        capitalTf.setDisable(true);
        progressBar.setVisible(true);
        nextBt.setVisible(true);
        saveBt.setVisible(false);
        deleteBt.setVisible(false);
        PreviousBt.setVisible(true);
        NewSetup(event);
    }

    @FXML
    private void Flag(ActionEvent event) {
        mode = 5;
        Collections.shuffle(countries);
        countryTf.setDisable(false);
        capitalTf.setDisable(false);
        progressBar.setVisible(true);
        nextBt.setVisible(true);
        saveBt.setVisible(false);
        deleteBt.setVisible(false);
        PreviousBt.setVisible(true);
        NewSetup(event);
    }

    @FXML
    private void Dark(ActionEvent event) {
        
    }

    @FXML
    private void Light(ActionEvent event) {
        System.out.println("H: " + scrollPane.getHvalue() + " V: " + scrollPane.getVvalue());
    }

    @FXML
    private void Next(ActionEvent event) {
        setupNext();
    }

    private void setupNext() {
        if (mode != 2) {
            if (!(countryTf.getText().toUpperCase().trim().equals(countries.get(x).getName().toUpperCase())
                    && capitalTf.getText().toUpperCase().trim().equals(countries.get(x).getCapital().toUpperCase()))) {
                if (soundBt.isSelected()) {
                    PlaySound("Wrong.mp3");
                }
                return;
            }
        }

        if (soundBt.isSelected()) {
            PlaySound("Right.wav");
        }
        ConfigureX();
        updateGUI();

    }

    private void updateGUI() {

        switch (mode) {
            case 2:
                countryTf.clear();
                capitalTf.clear();
                countryTf.setText(countries.get(x).getName());
                capitalTf.setText(countries.get(x).getCapital());
                break;

            case 3:
                countryTf.setText(countries.get(x).getName());
                capitalTf.clear();
                break;

            case 4:
                countryTf.clear();
                capitalTf.setText(countries.get(x).getCapital());
                break;

            case 5:
                countryTf.clear();
                capitalTf.clear();
                break;
        }
        scrollPane.setHvalue(countries.get(x).getScrollBarH());
        scrollPane.setVvalue(countries.get(x).getScrollBarV());
        markerImg.setTranslateX(countries.get(x).getMarkerX());
        markerImg.setTranslateY(countries.get(x).getMarkerY());
        progressBar.setProgress(x / countries.size());

        try {
            File file = new File(countries.get(x).getFlagPath());
            String localUrl = file.toURI().toURL().toString();
            FlagImg.setImage(new Image(localUrl));
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ConfigureX() {
        if (x == countries.size() - 1) {
            x = 0;
        } else {
            x++;
        }
    }

    private void NewSetup(ActionEvent event) {
        x = 0;
        updateGUI();
    }

    @FXML
    private void Save(ActionEvent event) {
        countries.get(x).setName(countryTf.getText());
        countries.get(x).setCapital(capitalTf.getText());
        countries.get(x).setScrollBarH(scrollPane.getHvalue());
        countries.get(x).setScrollBarV(scrollPane.getVvalue());
        countries.get(x).setMarkerX(markerImg.getTranslateX());
        countries.get(x).setMarkerY(markerImg.getTranslateY());
        SaveData(event);
    }

    private void SaveData(ActionEvent event) {
        File file = new File(DataFile);
        if (file.delete()) {
            // Do nothing 
        } else {
            System.out.println("Failed to delete the file");
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(DataFile);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(countries);
            fileOut.close();
            objOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void mapClicked(MouseEvent event) {
        markerImg.setTranslateX(event.getX() - 110);
        markerImg.setTranslateY(event.getY() - 79);
    }

    @FXML
    private void Delete(ActionEvent event) {
        if (mode == 2) {
            countries.remove(countries.get(x));
            SaveData(event);
        }
    }

    @FXML
    private void Previous(ActionEvent event) {
        x = x - 2;
        Next(event);
    }

    private void ReadFile() {
        try {
            // Read Data
            FileInputStream fileIn = new FileInputStream(DataFile);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            countries = (ArrayList<Country>) objIn.readObject();
            fileIn.close();
            objIn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void Search(String country) {
    }

    private void ReadAndKeep(String continent) {
        try {
            FileInputStream fileIn = new FileInputStream(DataFile);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            ArrayList<Country> TempCountries = (ArrayList<Country>) objIn.readObject();

            countries.clear();
            //ArrayList<Country> TempCountries = tempCountry.getCountries();

            for (int i = 0; i < TempCountries.size(); i++) {
                if (TempCountries.get(i).getContinent().equals(continent)) {
                    countries.add(TempCountries.get(i));
                }
            }
            if (mode != 2) {
                Collections.shuffle(countries);
            }
            fileIn.close();
            objIn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Hint(MouseEvent event) {
        if (!countryTf.isDisable()) {
            countryTf.clear();
            countryTf.setText(countries.get(x).getName());
        }

        if (!capitalTf.isDisable()) {
            capitalTf.clear();
            capitalTf.setText(countries.get(x).getCapital());
        }
    }

    @FXML
    private void ExitApp(ActionEvent event) {
        exit(0);
    }

    @FXML
    private void allContinents(ActionEvent event) {
        setMap();
        ReadFile();
        updateGUI();
    }

    @FXML
    private void Africa(ActionEvent event) {
        setMap();
        ReadAndKeep("Africa");
        updateGUI();
        //System.out.print("Africa: " + countries.size());
    }

    @FXML
    private void America(ActionEvent event) {
        setMap();
        ReadAndKeep("America");
        x = 0;
        updateGUI();
        //System.out.print("America: " + countries.size());
    }

    @FXML
    private void Asia(ActionEvent event) {
        setMap();
        ReadAndKeep("Asia");
        x = 0;
        updateGUI();
        //System.out.print("Asia: " + countries.size());
    }

    @FXML
    private void Europe(ActionEvent event) {
        setMap();
        ReadAndKeep("Europe");
        x = 0;
        updateGUI();
        //System.out.print("Europe: " + countries.size());
    }

    @FXML
    private void Ocenia(ActionEvent event) {
        setMap();
        ReadAndKeep("Oceania");
        x = 0;
        updateGUI();
        //System.out.print("Ocenia: " + countries.size());
    }

    @FXML
    private void NextFrCountry(ActionEvent event) {
        if (mode == 2) {
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getName().toUpperCase().equals(countryTf.getText().toUpperCase().trim())) {
                    x = i;
                    updateGUI();
                }
            }
        } else {
            setupNext();
        }
    }

    @FXML
    private void NextFrCapital(ActionEvent event) {
        if (mode == 2) {
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getCapital().toUpperCase().equals(capitalTf.getText().toUpperCase().trim())) {
                    x = i;
                    updateGUI();
                }
            }
        } else {
            setupNext();
        }
    }

    @FXML
    private void AmericanStates(ActionEvent event) {
        //getStates();
        //SaveData(event);

        try {

            File map = new File(countries.get(x).getPathToResources() + "AmericanStatesMap.png");
            File marker = new File(countries.get(x).getPathToResources() + "Marker.png");
            String localUrlmap = map.toURI().toURL().toString();
            String localUrlmarker = marker.toURI().toURL().toString();
            mapImg.setImage(new Image(localUrlmap));
            markerImg.setImage(new Image(localUrlmarker));

        } catch (Exception ex) {
            System.out.print("\n*******************************American States");
            ex.printStackTrace();
        }
        ReadAndKeep("United States");
        x = 0;
        updateGUI();
        //System.out.print("States: " + countries.size());
    }

    public void setMap() {
        try {
            File map = new File(countries.get(x).getPathToResources() + "WorldMapDark.png");
            File marker = new File(countries.get(x).getPathToResources() + "Marker.png");
            String localUrlmap = map.toURI().toURL().toString();
            String localUrlmarker = marker.toURI().toURL().toString();
            mapImg.setImage(new Image(localUrlmap));
            markerImg.setImage(new Image(localUrlmarker));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void doneEdit(ActionEvent event) {
        continentMb.setDisable(false);
        modeMb.setDisable(false);

        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);

            for (int i = 0; i < countries.size(); i++) {
                writer.write(i + " " + countries.get(i).getName() + " " + countries.get(i).getCapital() + " "
                        + countries.get(i).getAbbr() + " " + countries.get(i).getContinent() + " "
                        + countries.get(i).getMarkerX() + " " + countries.get(i).getMarkerY() + " "
                        + countries.get(i).getScrollBarH() + " " + countries.get(i).getScrollBarV() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void PlaySound(String sound) {
        path = tempCountry.getPathToResources() + sound;
        media = new Media(new File(path).toURI().toString());
        media_player = new MediaPlayer(media);
        media_player.play();
    }
}
