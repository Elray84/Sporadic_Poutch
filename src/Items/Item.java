/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

package Items;import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Item {

    protected static final Type ITEM_LIST = new TypeToken<Map<String, Map<String, Item>>>(){}.getType();

    protected String name;
    protected String special_effect;
    protected int health;
    protected int mana;
    protected int attack;
    protected int armor;
    protected int magic_res;
    protected float speed;
    protected int magic_damage;

    public Map<String, Integer> getBuff(){
        Map<String, Integer> buffs = new HashMap<String, Integer>();
        Field[] fields = Item.class.getDeclaredFields();
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

    public static Item getItem(String name, String type) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JsonReader reader = new JsonReader(new FileReader("resources/items.json"));
        Gson g = new Gson();
        Map<String, Map<String, Item>> items = g.fromJson(reader, ITEM_LIST);
        Item item = items.get(type).get(name);
        if(type.equals("legendary_items")){
            Class myClass = Class.forName(name);
            Class[] types = {Item.class};
            Constructor constructor = myClass.getConstructor(types);
            return (Item) constructor.newInstance(item);
        }
        return item;
    }

    protected Item() {
    }

    public static ArrayList<String> getSingleItemList() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("resources/items.json"));
        Gson g = new Gson();
        Map<String, Map<String, Item>> items = g.fromJson(reader, ITEM_LIST);
        return new ArrayList<String>(items.get("single_items").keySet());
    }

    public String toString(){
        Map<String, Integer> buffs = getBuff();
        String result = name + " :\n";
        for(String key:buffs.keySet()){
            result += key + " = " + buffs.get(key) + "\n";
        }
        if(this.special_effect != null) result += "Special effect : " + this.special_effect;
        return result;
    }

    public static void copyFields(Object source, Object target) {
        Field[] fieldsSource = source.getClass().getDeclaredFields();
        for (Field fieldSource : fieldsSource)
        {
            try
            {
                fieldSource.set(target, fieldSource.get(source));
            }
            catch (SecurityException e)
            {
            }
            catch (IllegalArgumentException e)
            {
            }
            catch (IllegalAccessException e) {
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Item test = getItem("transport", "legendary_items");
        System.out.println(test.toString());
    }
}
