/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagerp.model;

/**
 *
 * @author Omar
 */
public class Product {
    
    private int id;
    private String symbol;
    private int noStorage;
    
    public Product(int id, String symbol, int noStorage){
        this.id = id;
        this.symbol = symbol;
        this.noStorage = noStorage;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNoStorage() {
        return noStorage;
    }

    public void setNoStorage(int noStorage) {
        this.noStorage = noStorage;
    }    
}
