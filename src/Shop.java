/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Shop {

    private static final int SHOP_SIZE = 5;
    private static int current_max_lvl;
    static private Map connards;
    private List<Connard> myConnards;

    private void roll(int player_lvl){
        HashMap<Integer, List<Connard>> _connards = (HashMap<Integer, List<Connard>>) Shop.connards;
        List<Connard> global_shop = new ArrayList<Connard>(_connards.get(player_lvl));
        try {
            for(Connard connard:myConnards){
                if(connard.getInvisible()){
                    connard.setInvisible(false);
                }
            }
            global_shop.addAll(myConnards);
        } catch(NullPointerException e){}
        Collections.shuffle(global_shop);
        myConnards = new ArrayList<Connard>(global_shop.subList(0, SHOP_SIZE));
        _connards.put(player_lvl, new ArrayList<Connard>(global_shop.subList(SHOP_SIZE, global_shop.size())));
        connards = _connards;
    }

    public static void generateConnards(){
        Map<Integer, List<Connard>> _connards;
        if (current_max_lvl == 1) {
            _connards = new HashMap<>();
        }
        else {
            _connards = (HashMap<Integer, List<Connard>>) connards;
        }
        List<Connard> new_connards = new ArrayList<Connard>();
        for(int i=0; i<20;i++) {
            new_connards.add(new Connard("Connard" + i));
        }
        _connards.put(current_max_lvl, new_connards);
        connards = _connards;
    }

    public List<Connard> getMyConnards(){
        return myConnards;
    }

    public Connard buy(int i){
        Connard myConnard = myConnards.get(i);
        if(myConnard.getInvisible()){
            return null;
        }
        myConnards.remove(i);
        //TODO: generate a new connard
        Connard newconnard = new Connard("Test");
        newconnard.setInvisible(true);
        //////////////////////////////
        myConnards.add(newconnard);
        return myConnard;
    }

    public static void initShop(){
        current_max_lvl = 1;
        generateConnards();
    }

    public static void main(String[] args) {
        initShop();
        System.out.println("Global shop :");
        System.out.println(connards.get(1));
        Shop shop = new Shop();
        Shop othershop = new Shop();
        shop.roll(1);
        othershop.roll(1);
        System.out.println("Shop 1 :");
        System.out.println(shop.getMyConnards());
        System.out.println("Shop 2 :");
        System.out.println(othershop.getMyConnards());
        othershop.buy(3);
        shop.buy(4);
        System.out.println("Global shop :");
        System.out.println(connards.get(1));
        othershop.roll(1);
        shop.roll(1);
        System.out.println("Shop 1 :");
        System.out.println(shop.getMyConnards());
        System.out.println("Shop 2 :");
        System.out.println(othershop.getMyConnards());
        System.out.println("Global shop :");
        System.out.println(connards.get(1));
    }

}
