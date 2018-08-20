/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagerp;

import inventorymanagerp.model.Product;
import inventorymanagerp.model.Storage;
import java.util.Scanner;

/**
 *
 * @author Omar
 */
public class Menu {
    Scanner reader = new Scanner(System.in);
    int option = 0,decision = 0, type = 0, id = 0, transferSto = 0, noStorage = 0, 
            product1 = 0, product2 = 0, product3 = 0, product4 = 0;
    boolean empty = false, storageCreated = false, storaged = false, deleted = false;
    String typeProduct = "";
    public Storage[] inventary = new Storage[9];
    Product[] currentProduct = new Product[100];
    
    public int pointer(int noStorage){
    int count = 0;
    for(Storage s: inventary){
        if(!(s==null)){
           if(s.getId() == noStorage){
               for(Product p: s.getStorage()){
                   if(!(p == null)){
                       count += 1;
                   }
               } 
            } 
        }
    }
        return count;
    } 
    
    public void errorMenu(String messagge, int type){
        System.out.println("*****Hubo un error en la ejecucion de la orden*****");
        System.out.println("*****"+messagge+"*****");
        reader.next();
        optionMenu(type);
        
    }
    public void optionMenu(int i){
      System.out.println("Desea realizar otra accion?"); 
      System.out.println("1. Si"); 
      System.out.println("2. No"); 
      try{option = reader.nextInt();}catch(Exception e){
          errorMenu("No se puede ingresar letras",2 );}
      switch(option){
          case 1:
            if(i==1){
                menuInventory(); 
            }
            if(i==2){
                mainMenu();
            }
            if(i==3){
                secundaryMenu();
            }
            if(i==4){
                menuReport();
            }
              break;
          case 2:
            System.exit(0);  
              break;
          default:
              errorMenu("Ingrese una opción válida", 2);
      }
    }
    public void mainMenu(){
        System.out.println("Bienvenido al programa de gestión de inventarios");
        System.out.println("Por favor ingrese el numero de productos del tipo ♠ que desea manejar");
        try{product1 = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",2);}   
        System.out.println("Por favor ingrese el numero de productos del tipo ♦ que desea manejar");
        try{product2 = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",2);}
        System.out.println("Por favor ingrese el numero de productos del tipo ► que desea manejar");
        try{product3 = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",2);}
        System.out.println("Por favor ingrese el numero de productos del tipo ♣ que desea manejar");
        try{product4 = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",2);} 
        if(!storageCreated){
            createStorage();
        }
        secundaryMenu();
    }
    
    public void createStorage(){
        for(int i = 1; i<=9; i++){
        Product[] product = new Product[100];
        Storage storage = new Storage(i, "Bodega " + i, product);
        inventary[i - 1] = storage;
        storageCreated = true;
        }
    }
    
    
    public void secundaryMenu(){ 
       System.out.println("Bienvenido al programa de gestión de inventarios");
       System.out.println("Por favor ingrese el numero de la opcion que desea realizar");
       System.out.println("1. Inventario");
       System.out.println("2. Reportes");
       System.out.println("3. Salir");
       try{decision = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",3);}
       switch(decision){
           case 1:
                System.out.println("Por favor ingrese el numero de bodega");
                try{noStorage = reader.nextInt();
                }catch(Exception e){System.out.println("Numero de bodega incorrecto");
                secundaryMenu();}
                if(noStorage<10 && noStorage>0){
                menuInventory();    
                }else{errorMenu("Ingrese una bodega valida",3);}  
           break;
           case 2:
               menuReport();
           break;
           case 3:
               System.exit(0);
           break;  
           default:  
            errorMenu("Ingrese una opción válida",3);   
       }
    }  
    
    public void menuInventory(){
       decision = 0;
       System.out.println("Bienvenido al sistema de gestion de inventarios");
       System.out.println("Usted se encuentra en la Bodega "+ noStorage);
       System.out.println("Seleccione la opcion que desea realizar");
       System.out.println("1. Ver inventario actual");
       System.out.println("2. Entrada de mercaderia");
       System.out.println("3. Salida de mercaderia");
       System.out.println("4. Transferir mercadería");
       System.out.println("5. Menu anterior");
       System.out.println("6. Salir");
        try{decision = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras", 1);}
        switch(decision){
            case 1:
            seeStorage(); 
            optionMenu(1);
            break;
            case 2: 
            System.out.println("Seleccione el tipo de producto que ingresará:");
            System.out.println("1. Producto ♠");
            System.out.println("2. Producto ♦");
            System.out.println("3. Producto ►");
            System.out.println("4. Producto ♣");
            try{decision = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",1);}
            switch(decision){
               case 1:
                   typeProduct = "♠";
               break;
               case 2:
                   typeProduct = "♦";
               break;
               case 3:
                   typeProduct = "►";
               break;
               case 4:
                   typeProduct = "♣";
               break;
               default:
                   errorMenu("Ingrese una opción válida",1);      
               }
               System.out.println("Ingrese la cantidad de productos que ingresaran");
               int cantidad = 0;
               try{cantidad = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",1);}   
                addProduct(noStorage, typeProduct,cantidad);
                System.out.println("--- PRODUCTO(S) INGRESADOS EXITOSAMENTE ---");
                optionMenu(1);
            break;
            case 3:
            seeStorage();    
            if(!empty){
                System.out.println("Por favor ingrese el ID del producto que desea eliminar");
                try{id = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",1);}
                if(existId(id)){
                    deleteProduct(id);
                    System.out.println("--- PRODUCTO ELIMINADO EXITOSAMENTE ---");
                    optionMenu(1);
                }else{errorMenu("El ID que selecciono no se encuentra dentro de la bodega",1);}
                
            }else{optionMenu(1);}
                break;
            case 4:
                seeStorage();
                if(!empty){
                    System.out.println("Por favor ingrese el ID del producto que desea transferir");
                    try{id = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",1);}
                    System.out.println("Por favor ingrese el numero de la bodega a "
                            + "la que se transferira el producto");
                    try{transferSto = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras",1);}
                    if(existId(id) && existStorage(transferSto)){
                        transferProduct(id, transferSto);
                        System.out.println("El producto ha sido transladado exitosamente");
                        optionMenu(1);
                    }else{errorMenu("El ID que selecciono no se encuentra dentro de la bodega "
                            + "o el numero de bodega no existe",1);}
                }else{optionMenu(1);}
                
            optionMenu(1);
                break;
            case 5:
                secundaryMenu();
                break;
            case 6:
                System.exit(0);
                break;
            default:
            errorMenu("Ingrese una opción válida", 1);
        } 
    }
    
    public void menuReport(){
        System.out.println("Bienvenido al sistema de gestion de reportes");
        System.out.println("Por favor ingrese el numero de la opcion que desea realizar");
        System.out.println("1. Ver el inventario de todas las bodegas");
        System.out.println("2. Ver la bodega con menor cantidad de productos");
        System.out.println("3. Ver la bodega con mayor cantidad de productos");
        System.out.println("4. Ver la bodega con menor cantidad de productos (separadas por cada articulo)");
        System.out.println("5. Ver la bodega con mayor cantidad de productos (separadas por cada articulo)");
        System.out.println("6. Promedio de articulos por bodega");
        System.out.println("7. Mediana de unidades por articulo");
        System.out.println("8. Moda de unidades por articulo");
        System.out.println("9. Cantidad de todos productos que no están en bodega.");
        System.out.println("10. Menu anterior");
        System.out.println("11. Salir");
        try{decision = reader.nextInt();}catch(Exception e){errorMenu("No se puede ingresar letras", 3);}
        switch(decision){
            case 1:
                inventary();
                break;
            case 2:
                Storage smaller = smallerStorage();
                if(!(pointer(smaller.getId()) == 0)){
                   System.out.println("La "+ smaller.getStorageName()+" tiene "+pointer(smaller.getId())+
                           " productos en total.");   
                   optionMenu(4);
                }else{
                    errorMenu("Inventario Vacio", 3);
                }               
                break;
            case 3:
                Storage bigger = biggerStorage();
                if(!(pointer(bigger.getId()) == 0)){
                   System.out.println("La "+ bigger.getStorageName()+" tiene "+pointer(bigger.getId())+
                           " productos en total.");   
                   optionMenu(4);
                }else{
                    errorMenu("Inventario Vacio", 3);
                }
                break;
            case 4:
                int type1 = 0, type2=0, type3 = 0, type4=0;
                Storage unitySmaller = smallerStorage();
                if(!(pointer(unitySmaller.getId()) == 0)){
                    for(Product p: unitySmaller.getStorage()){
                        if(!(p==null)){
                            if(p.getSymbol()=="♠"){
                                type1 +=1;
                            }
                            if(p.getSymbol()=="♦"){
                                type2 += 1;
                            }
                            if(p.getSymbol()=="►"){
                                type3 += 1;
                            }
                            if(p.getSymbol()=="♣"){
                                type4 += 1;
                            }
                        }
                    }
                    System.out.println("El articulo ♠ tiene "+type1+" unidades en la "
                            +unitySmaller.getStorageName());
                    System.out.println("El articulo ♦ tiene "+type2+" unidades en la "
                            +unitySmaller.getStorageName());
                    System.out.println("El articulo ► tiene "+type3+" unidades en la "
                            +unitySmaller.getStorageName());
                    System.out.println("El articulo ♣ tiene "+type4+" unidades en la "
                            +unitySmaller.getStorageName());
                   optionMenu(4);
                }else{
                    errorMenu("Inventario Vacio", 3);
                } 
                
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            default:
        
        }
    
    }
    public void seeStorage(){
        for(Storage s: inventary){
            if(s.getId() == noStorage){
                empty = printStorage(s.getStorage());
            }
        }
    }
    public boolean existStorage(int Storage){
        boolean exists = false;
        for(Storage s: inventary){
            if(s.getId() == noStorage){
                exists = true;
            }
        }
        return exists;
    }
    public boolean existId(int id){
        boolean exists = false;
        for(Storage s: inventary){
            if(s.getId() == noStorage){
                currentProduct = s.getStorage();
                    for(Product p: currentProduct){
                        if(!(p==null)){
                            if(p.getId() == id){
                            exists = true;
                        }
                    }
                }
            }
        }
        return exists;
    }
    public boolean printStorage(Product[] products){
        boolean em =false;
        if(pointer(noStorage) == 0){
            em = true;
        }
        for(Product p: products){
            if(!(p == null)){
                System.out.println("ID: " + p.getId());
                System.out.println("Product: " + p.getSymbol());
                System.out.println("-------------------------------");
            }
        }
        if(em){
           System.out.println("----- ESTA BODEGA SE ENCUENTRA ACTUALMENTE VACIA -----");
        }
        return em;
    }
    public void addProduct(int noStorage, String typeProduct, int cantidad){
        if(cantidad<=100){
            switch(typeProduct){
            case "♠":
                if(!(cantidad>product1)){
                    product1 -= cantidad;
                }else{errorMenu("El inventario es insufienciente",1);}
                break;
            case "♦":
                if(!(cantidad>product2)){
                    product1 -= cantidad;
                }else{errorMenu("El inventario es insufienciente",1);}
                break;
            case "►":
                if(!(cantidad>product3)){
                    product1 -= cantidad;
                }else{errorMenu("El inventario es insufienciente",1);}
                break;
            case "♣":
                if(!(cantidad>product4)){
                    product1 -= cantidad;
                }else{errorMenu("El inventario es insufienciente",1);}
                break;     
            }
            for(Storage s: inventary){
                if(s.getId() == noStorage){
                    for(int i = 0; i<cantidad; i++){
                        currentProduct = s.getStorage();
                        Product pr = new Product(pointer(noStorage) +1, typeProduct, noStorage);
                        try{currentProduct[pointer(noStorage)] = pr;}catch(Exception e){errorMenu("Ya no se pueden"
                                + "ingresar mas productos a la bodega",1);}
                    }
                }
            }   
        }else{errorMenu("No puede ingresar mas de 100 productos a la bodega",1);}
    } 
    public void deleteProduct(int id){
        for(Storage s: inventary){
            if(s.getId() == noStorage){
                currentProduct = s.getStorage();
                    for(Product p: currentProduct){
                        if(!(p==null)){
                            if(p.getId() == id){
                                try{currentProduct[p.getId() - 1] = null;}
                                catch(Exception e){errorMenu("Problema al intentar eliminar el elemento, "
                                        + "por favor intentelo de nuevo",1);}
                                deleted = true;
                        }
                        if(deleted && !(p.getId() == id)){
                            Product pe = new Product(p.getId()-1, p.getSymbol(), p.getNoStorage());
                            try{currentProduct[p.getId()-1] = null;
                            currentProduct[p.getId()-2] = pe;}
                            catch(Exception e){errorMenu("Problema al intentar eliminar el elemento, "
                                    + "por favor intentelo de nuevo",1);}
                        }
                    }
                }
            }
        }
    }
    public void transferProduct(int id, int storage){
        for(Storage s: inventary){
            if(s.getId() == noStorage){
                for(Product p: s.getStorage()){
                    if(!(p==null)){
                        if(p.getId() == id){
                        deleteProduct(id);    
                        addProduct(storage,p.getSymbol(), 1);
                        }
                    }
                }
            }
        }
    }
     void inventary(){
        for(Storage s: inventary){
            for(Product p: s.getStorage()){
                if(!(p==null)){
                    if(s.getId() == p.getNoStorage()){
                        String cadena = "";
                        cadena += p.getSymbol();
                        System.out.printf("%-20s%-20s%-20s\n","Bodega "
                                + noStorage, cadena);
                    }
                }
            }
        }
    }
    Storage biggerStorage(){
        Storage st =inventary[0];
        for(Storage s: inventary){
            int leng = pointer(s.getId());
            if(leng>pointer(st.getId())){
               st = s;  
            }
        }
        return st;
    }
    Storage smallerStorage(){
        Storage st =inventary[0];
        for(Storage s: inventary){
            int leng = pointer(s.getId());
            if(leng<pointer(st.getId()) && !(pointer(s.getId())==0)){
                 st = s;  
            }
        }
        return st;
    }
}
