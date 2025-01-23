/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2.dönem2.proje;

import java.util.ArrayList;

/**
 * 
 * @author İrem Serra
 */
public class Store implements CropKeeper {
    
    public int ID;
    private String name;
    private double maxCapacityArea;
    private double usedCapacityArea; 
    private double KGperSquareMeter=10;
    
  protected ArrayList<Fruit>fruitList= new ArrayList<>();

   public Store() {
               
    }

    public Store(String name, int ID, double maxCapacityArea, double usedCapacityArea) {
        this.ID = ID;
        this.name = name;
        this.maxCapacityArea = maxCapacityArea;
        this.usedCapacityArea = usedCapacityArea;
    }

  

    
    public String getName() {
        return name;
    }

    public double getMaxCapacityArea() {
        return maxCapacityArea;
    }

    public double getUsedCapacityArea() {
        return usedCapacityArea;
    }

    public double getKGperSquareMeter() {
        return KGperSquareMeter;
    }

    public ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    
    public double availabeCapacity(){
    return maxCapacityArea-usedCapacityArea;
    } 
    
    public boolean canBeStore(Fruit f){
       return maxCapacityArea >= usedCapacityArea + f.weight;
    
    }
    
    
    public void importCrop(Fruit f) throws CapacityNotEnoughException{
        if(canBeStore(f)){
           fruitList.add(f);
           usedCapacityArea += f.weight * KGperSquareMeter;
        } else{
            throw new CapacityNotEnoughException ("Not enough capacity");
        }
    }

    
    public void exportCrop(Fruit f) throws FruitNotFoundException {
    if(fruitList.contains(f)){
            usedCapacityArea -= f.weight * KGperSquareMeter;
            fruitList.remove(f);
        } else{ 
            throw new FruitNotFoundException ("Fruit not found");
        }
    }   
    
    @Override
    public String howToStore() {
        return "fruits in large refrigerated cooler rooms and vegetables in sheds, not listed" ;
    }
    
    public void showCrops(){
        System.out.println("Store{id="+ID+", name="+name+", maxCapacity="+maxCapacityArea+", usedCapacity="+usedCapacityArea+"}");
        for(Crop c: fruitList){
            System.out.println("\t"+c);
            c.consumeIt();
        }
    }
    
    
}
 