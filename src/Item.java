/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Item {

    private static final Type ITEM_LIST = new TypeToken<Map<String, Map<String, Item>>>(){}.getType();

    private String name;
    private String description;
    private int health;
    private int mana;
    private int attack;
    private int armor;
    private int magic_res;
    private int attack_speed;
    private int magic_damage;

    public Map<String, Integer> getBuff(){
        Map<String, Integer> buffs = new HashMap<String, Integer>();
        Class c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){

            try{
                if(f.getInt(this) > 0) {
                    buffs.put(f.getName(), f.getInt(this));
                }
            } catch(Exception e){}
        }
        return buffs;
    }

    public String getSpecialBuff(){
        return "Single item, no special effect.";
    }

    public Item merge(Item to_merge){
        return null;
        //TODO
    }

    public static Item getItem(String name) {
        return null;
    }

    private Item() {
    }

    // Useless ?
    public static ArrayList<String> getItemList() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("../resources/items.json"));
        Gson g = new Gson();
        Map<String, Map<String, Item>> items = g.fromJson(reader, ITEM_LIST);
        return null;
    }

    public String toString(){
        Map<String, Integer> buffs = getBuff();
        String result = name + " :\n";
        for(String key:buffs.keySet()){
            result += key + " = " + buffs.get(key) + "\n";
        }
        return result;
    }

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        Item test = getItem("Item3");
        System.out.println(test.toString());
    }
}

