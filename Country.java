/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldcountries;

import java.io.*;
import java.util.*;

public class Country implements Serializable {

    private String name;
    private String capital;
    private String abbr;
    private String continent;
    private double scrollBarH;
    private double scrollBarV;
    private double markerX;
    private double markerY;
    private String pathToResources = "C:\\Users\\shabi\\Documents\\NetBeansProjects\\WorldCountries\\Resources\\";
    private String pathToDataFile = pathToResources + "Data_ALL.ser";
    
    public Country(){
        
    }

    public Country(String name, String capital, String abbr, String continent) {
        this.name = name;
        this.capital = capital;
        this.abbr = abbr;
        this.continent = continent;
    }

    public void setDimemsion(double scrollBarH, double scrollBarV, double markerX, double markerY) {
        this.scrollBarH = scrollBarH;
        this.scrollBarV = scrollBarV;
        this.markerX = markerX;
        this.markerY = markerY;
    }

    public ArrayList<Country> getCountries() {
        ArrayList<Country> countryList = new ArrayList<>();
        String line;
        try {
            File file = new File(pathToResources + "Data.txt");
            BufferedReader input = new BufferedReader(new FileReader(file));
            
            while ((line = input.readLine()) != null) {
                String[] word = line.split(" ");
                countryList.add(new Country(word[0], word[1], word[2], word[3]));
            }
        } catch (Exception ex) {
            System.out.println("\nError " + ex.getLocalizedMessage());
        }
        return countryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    
    public String getFlagPath() {
        return pathToResources + "Flags\\" + getAbbr() + ".png";
    }

    public void setFlagPath(String abbr) {
        this.abbr = abbr;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getScrollBarH() {
        return scrollBarH;
    }

    public void setScrollBarH(double scrollBarH) {
        this.scrollBarH = scrollBarH;
    }

    public double getScrollBarV() {
        return scrollBarV;
    }

    public void setScrollBarV(double scrollBarV) {
        this.scrollBarV = scrollBarV;
    }

    public double getMarkerX() {
        return markerX;
    }

    public void setMarkerX(double markerX) {
        this.markerX = markerX;
    }

    public double getMarkerY() {
        return markerY;
    }

    public void setMarkerY(double markerY) {
        this.markerY = markerY;
    }

    public String getPathToResources() {
        return pathToResources;
    }

    public void setPathToResources(String pathToResources) {
        this.pathToResources = pathToResources;
    }
    
    public String getPathToDataFile(){
        return pathToDataFile;
    }
    
    public void setPathToDataFile(String pathToDataFile){
        this.pathToDataFile = pathToDataFile;
    }
}
