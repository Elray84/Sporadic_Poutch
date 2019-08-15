/*
 * Copyright (c) 2019.
 * Author : Elian Raynaud
 * All Rights Reserved
 */

package Connards;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Perceval extends Connard{

    @Override
    public String toString() {
        return super.toString();
    }

    public static Connard getInstance(int lvl) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("resources/Perceval" + lvl + ".json"));
        Gson g = new Gson();
        return g.fromJson(reader, Perceval.class);
    }

}
