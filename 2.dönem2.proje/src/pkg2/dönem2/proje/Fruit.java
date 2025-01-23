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
public class Fruit extends Crop implements Comparable {

    private String taste;
    protected double price;  // supplier classta kullandık
    private String color;

    public Fruit() {

    }

    public Fruit(String name, double weight, String cultivatedSeason, String taste, double price) {
        super(name, weight, cultivatedSeason);
        this.price = price;
        this.taste = taste;
    }
   

    @Override
    public String consumeIt() {
        return "fruits are consumed raw";
    }

    @Override
    public void storeIt(Store s) {
        for(Fruit f: s.fruitList){
        s.fruitList.add(f); 
        
        }
    }

    public String getTaste() {
        return taste;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Fruit{" + super.toString() + "taste=" + taste + ", price=" + price + ", color=" + color + '}';
    }

    @Override
    public int compareTo(Object C) {
        Fruit f = (Fruit) C;
        if (this.name.equals(f.name) && this.color.equals(f.color)) {
            return 0;
        } else {
            return (int) (this.weight - f.weight);
        }
    }
 
    @Override
    public boolean equals(Object o) {
        Fruit f = (Fruit) o;
        if (name.equals(f.name)) {
            return true;
        }
        return false;
    }

}
