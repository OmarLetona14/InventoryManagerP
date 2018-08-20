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
public class Storage {
    
    private int id;
    private String storageName;
    private Product[] storage;

    public Storage(int id, String storageName, Product[] storage) {
        this.id = id;
        this.storageName = storageName;
        this.storage = storage;
    }
    public Storage(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Product[] getStorage() {
        return storage;
    }

    public void setStorage(Product[] storage) {
        this.storage = storage;
    } 
}
