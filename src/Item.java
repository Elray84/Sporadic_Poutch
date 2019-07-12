/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.CharBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class Item {

    private String name;
    private String description;
    private int life_buff;
    private int mana_buff;
    private int attack_buff;
    private int armor_buff;
    private int magic_res_buff;
    private int attack_speed_buff;

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

    public static Item getItem(String name) throws IOException, NoSuchFieldException, IllegalAccessException {
        return parseResource().get(name);
    }

    private Item() {
    }

    public static ArrayList<String> getItemList() throws IOException, NoSuchFieldException, IllegalAccessException {
        Map<String, Item> items = parseResource();
        return new ArrayList<>(items.keySet());
    }

    private static Map<String, Item> parseResource() throws IOException, NoSuchFieldException, IllegalAccessException {
        Path path = FileSystems.getDefault().getPath("resources", "items.txt");
        String resource = Files.readString(path);
        resource = resource.replaceAll(" ", "").replaceAll(Pattern.quote("\n"), "").replaceAll(Pattern.quote("\r"), "");
        Map<String, Item> result = new HashMap<>();
        String[] items = resource.split(Pattern.quote("|"));
        for(String item:items){
            Item _item = new Item();
            _item.name = item.split(":")[0];
            String test = item.split(":")[1].split(Pattern.quote("."))[0].replaceAll("_", " ");
            _item.description = item.split(":")[1].split(Pattern.quote("."))[0].replaceAll("_", " ");
            for(String buff:item.split(":")[1].split(Pattern.quote("."))[1].split(",")){
                Field field = Item.class.getDeclaredField(buff.split("=")[0]);
                field.set(_item, Integer.parseInt(buff.split("=")[1]));
            }
            result.put(_item.name, _item);
        }
        return result;
    }

    public String toString(){
        Map<String, Integer> buffs = getBuff();
        String result = name + " :\n" + description + "\n";
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

