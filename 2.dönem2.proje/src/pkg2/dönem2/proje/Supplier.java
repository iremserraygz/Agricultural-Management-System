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
public class Supplier implements CropKeeper {

    private String name;
    public int ID;
    private int budget;
    protected ArrayList<Crop> cropList = new ArrayList<>();

    public Supplier(String name, int ID, int budget) {
        this.name = name;
        this.ID = ID;
        this.budget = budget;

    }

    

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getBudget() {
        return budget;
    }

    public ArrayList<Crop> getCropList() {
        return cropList;
    }
  
    @Override
    public String howToStore() {
        return " Fruits in big refrigerators and vegetables in the field booths ";
    }

    public void buyCrop(Fruit f) throws FruitNotAvailableException, SupplierHasNotEnougMoneyException {

        if (!cropList.contains(f)) {
            if (this.budget > f.getPrice()) {
                cropList.add(f);
                budget -= f.price;

            }
        } else if (cropList.contains(f)) {
            cropList.get(cropList.indexOf(f)).weight += f.weight;
        } else if (f.getPrice() > this.budget) {
            throw new SupplierHasNotEnougMoneyException("Supplier Has Not Enough Money");
        }
    }

    public void sellCrop(Fruit f) throws FruitNotFoundException {

        if (cropList.contains(f)) {

            cropList.remove(f);
            budget += f.price;
        } else {
            throw new FruitNotFoundException("Fruit Not Found");
        }

    }
    
    public void showCrops(){
        System.out.println("Supplier{id="+ID+", name="+name+", budget="+budget+"}");
        for(Crop c: cropList){
            System.out.println("\t"+c);
        }
    }

}
