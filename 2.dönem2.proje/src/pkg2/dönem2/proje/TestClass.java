/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2.dönem2.proje;


import java.io.*;  
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author İrem Serra  
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        ArrayList<Crop> crops = new ArrayList<>();
        ArrayList<Store> stores = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();

        FileReader cropFile = new FileReader("Crops.txt");
        FileReader storeFile = new FileReader("Stores.txt");
        FileReader supplierFile = new FileReader("Suppliers.txt");

        BufferedReader cropBr = new BufferedReader(cropFile);
        BufferedReader storeBr = new BufferedReader(storeFile);
        BufferedReader supplierBr = new BufferedReader(supplierFile);

         
        String entry = storeBr.readLine();
         while(entry != null){
            String [] token = entry.split(", ");
            
             Store s = new Store(token[0], Integer.parseInt(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3]));
            stores.add(s);

            entry = storeBr.readLine();
        } 

        entry = supplierBr.readLine();
        while(entry != null){
            String [] token = entry.split(", ");
            
            Supplier s = new Supplier(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2]));
            suppliers.add(s);
            
            entry = supplierBr.readLine();
        }

        entry = cropBr.readLine();
        while(entry != null){
            String [] token = entry.split(", ");
            Crop c;
            int ID;
            
            if(token[1].equals("fruit")){
                c = new Fruit(token[0], Double.parseDouble(token[2]), token[3], token[4], Double.parseDouble(token[5]));
                ID = Integer.parseInt(token[6]);
                if(ID/1000 == 5){
                    for(Store s: stores)
                        if(s.ID == ID) {
                            try {
                                s.importCrop((Fruit) c);
                            } catch (CapacityNotEnoughException e) {
                                e.printStackTrace();
                            }
                        }
                }else if(ID/1000 == 1)
                    for(Supplier s: suppliers)
                        if(s.ID == ID)
                            s.cropList.add(c);            
            }else{
                c = new Vegetable(token[0], Double.parseDouble(token[2]), "unknown", token[3]);
                ID = Integer.parseInt(token[4]);
                if(ID/1000 == 1)
                    for(Supplier s: suppliers)
                        if(s.ID == ID) 
                            s.cropList.add(c);
            }
            crops.add(c);
            
            entry = cropBr.readLine();
        }
        
        
        Scanner scn = new Scanner(System.in);
         
        String menu = "0.Quit\n" + 
                        "1.Display all suppliers and their crop list\n"+
                        "2.Display all stores and their fruit list\n" +
                        "3.Buy a fruit crop for a Supplier and add it\n"+
                        "4.Sell a fruit crop of a Supplier and remove it\n"+
                        "5.Remove a fruit from a store\n"+
                        "6.Remove a crop from a supplier\n"+
                        "7.Add crop\n"+
                        "8.Show remaining budget\n"+
                        "9.Show remaining capacity.\n";
                
        while(true){ 
            System.out.println("---------------------------------------------");
            System.out.println(menu);
            System.out.println("---------------------------------------------");
            System.out.println("Please choose (0-9)...");
            String choice = scn.nextLine();
            
            
            if(choice.equals("0")) {
                System.out.println("Quit...");
                break;
            }else if(choice.equals("1")){
                for(Supplier s: suppliers){
                    s.showCrops();
                    System.out.println();
                }
            }else if (choice.equals("2")) {
                for(Store s: stores){
                    s.showCrops();
                    s.howToStore();
                    System.out.println();
                }
            }else if(choice.equals("3")){
                System.out.print("Enter fruit name: ");
                String fruitName = scn.nextLine();
                System.out.print("Enter store name: ");
                String storeName = scn.nextLine();
                System.out.print("Enter a supplier name");
                String suppName = scn.nextLine();
                
                Fruit f = null;
                for(Store s: stores){
                    boolean find = false;
                    if(s.getName().equals(storeName))
                    for(int i = 0;i<s.fruitList.size();i++){
                        if(s.fruitList.get(i).name.equals(fruitName)){
                            f = s.fruitList.get(i);
                            try {
                                s.exportCrop(f);
                            } catch (FruitNotFoundException e) {
                                e.printStackTrace();
                            }
                            find = true;
                            break;
                        }
                    }
                    if(find)
                        break;
                }

                for(Supplier s:suppliers){
                    if(s.getName().equals(suppName))
                    try{
                        s.buyCrop(f);
                    }catch (SupplierHasNotEnougMoneyException | FruitNotAvailableException e) {
                        System.out.println(e.getMessage());

                    }
                }
            }else if(choice.equals("4")){
                System.out.print("Enter fruit name: ");
                String fruitName = scn.nextLine();
                System.out.print("Enter store name: ");
                String storeName = scn.nextLine();
                System.out.print("Enter a supplier name");
                String suppName = scn.nextLine();

                Fruit f = null;
                for(Supplier s:suppliers){
                    boolean find = false;
                    if(s.getName().equals(suppName))
                        for(int i = 0;i<s.cropList.size();i++){
                            if(s.cropList.get(i).name.equals(fruitName)){
                                f = (Fruit) s.cropList.get(i);
                                try{
                                    s.sellCrop(f);
                                }catch (FruitNotFoundException e) {
                                    System.out.println(e.getMessage());

                                }
                                find = true;
                                break;
                            }
                        }
                    if(find)
                        break;
                }
                
                for(Store s: stores){
                    if(s.getName().equals(storeName)) {
                        try {
                            s.importCrop(f);
                        } catch (CapacityNotEnoughException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (choice.equals("5")){
                System.out.print("Enter the name of fruit you want to remove: ");
                String fruitName = scn.nextLine();
                System.out.print("Enter the name of store: ");
                String storeName = scn.nextLine();
                for(int i = 0;i<stores.size();i++){
                    if(stores.get(i).getName().equals(storeName)){
                        for(int j = 0;j<stores.get(i).fruitList.size();j++){
                            if(stores.get(i).fruitList.get(j).name.equals(fruitName)) {
                                stores.get(i).fruitList.remove(j);
                                break;
                            }
                        }
                    }
                }
            }else if (choice.equals("6")){
                System.out.print("Enter the name of crop you want to remove: ");
                String cropName = scn.nextLine();
                System.out.print("Enter the name of supplier: ");
                String supplierName = scn.nextLine();
                for(int i = 0;i<suppliers.size();i++){
                    if(suppliers.get(i).getName().equals(supplierName)){
                        for(int j = 0;j<suppliers.get(i).cropList.size();j++){
                            if(suppliers.get(i).cropList.get(j).name.equals(cropName)) {
                                suppliers.get(i).cropList.remove(j);
                                break;
                            }
                        }
                    }
                }
            }else if (choice.equals("7")){
                System.out.print("Which one do you want to create [(f)ruit/(v)egetable]:");
                char type = scn.nextLine().charAt(0);
                Crop c = null;
                if(type == 'f'){
                    System.out.print("Enter fruit name: ");
                    String fruitName = scn.nextLine();
                    System.out.print("Enter weight: ");
                    double weight = scn.nextDouble(); scn.nextLine();
                    System.out.print("Enter season: ");
                    String season = scn.nextLine();
                    System.out.print("Enter taste: ");
                    String taste = scn.nextLine();
                    System.out.print("Enter price: ");
                    double price = scn.nextDouble(); scn.nextLine();
                    c = new Fruit(fruitName, weight, season, taste, price);
                }else if(type == 'v'){
                    System.out.print("Enter veg name: ");
                    String vegName = scn.nextLine();
                    System.out.print("Enter weight: ");
                    double weight = scn.nextDouble(); scn.nextLine();
                    System.out.print("Enter season: ");
                    String cultivatedSeason = scn.nextLine();
                    System.out.print("Enter region: ");
                    String cultivatedRegion = scn.nextLine();
                    c = new Vegetable(vegName, weight, cultivatedSeason, cultivatedRegion);
                }
                if(c == null){
                    System.out.println("Crop couldn't be generated!");
                    continue;
                }
                
                System.out.print("Enter where do you want to add (store/supplier)");
                String input = scn.nextLine();
                if(input.equals("store")){
                    System.out.print("Enter the store name: ");
                    String name = scn.nextLine();
                    for(Store s: stores){
                        if(s.getName().equals(name)) {
                            try {
                                s.importCrop((Fruit) c);
                            } catch (CapacityNotEnoughException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else if(input.equals("supplier")){
                    System.out.print("Enter the supplier name: ");
                    String name = scn.nextLine();
                    for(Supplier s: suppliers){
                        if(s.getName().equals(name)){
                            s.cropList.add(c);
                        }
                    }
                }
            }
            else if (choice.equals("8")){
                System.out.println("Enter supplier name: ");
                String supplierName = scn.nextLine();
                for(Supplier s: suppliers){
                    if(s.getName().equals(supplierName)){
                        System.out.println("Remaining budget is: " + s.getBudget());
                    }
                }
            } else if (choice.equals("9")){
                System.out.println("Enter store name: ");
                String storeName = scn.nextLine();
                for(Store s: stores){
                    if(s.getName().equals(storeName)){
                        System.out.println("Remaining capacity is: " + s.availabeCapacity());
                    }
                }
            } else {
                System.out.println("Invalid action ... Select the action you want to perform in the menu.(0-9)");
            }
        }
    }
}
