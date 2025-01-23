/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2.dönem2.proje;



/**
 *
 * @author İrem Serra
 */
public abstract class Crop { //mahsül

      
    protected String name;
    protected double weight; // supplier(sıplayır) de kullanılıyor
    private String cultivatedSeason;
    

    public Crop() {
        
         } 
    
    public Crop(String name, double weight, String cultivatedSeason) {
        this.name = name;
        this.weight = weight;
        this.cultivatedSeason = cultivatedSeason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getWeight() {
        return weight;
    }
 public void setWeight(double weight) {
        this.weight = weight;
    }
 
    public String getCultivatedSeason() {
        return cultivatedSeason;
    }
     public void setCultivatedSeason(String cultivatedSeason) {
        this.cultivatedSeason = cultivatedSeason;
    }

   
 

    public abstract String consumeIt();

    public abstract void storeIt( Store s); 

    @Override
    public String toString() {
        return "Crop{" + "name=" + name + ", weight=" + weight + ", cultivatedSeason=" + cultivatedSeason + '}';
    }

} 
 