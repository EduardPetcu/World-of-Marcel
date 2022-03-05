package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoryParser {
    public StoryParser(){

    }
    public void setStories(){
        new JSONArray();
        JSONArray stories;
        JSONParser jsonParser = new JSONParser();
        ArrayList<String> emp = new ArrayList<>();
        ArrayList <String> ene = new ArrayList<>();
        ArrayList <String> sho = new ArrayList<>();
        ArrayList <String> fin = new ArrayList<>();
        try (FileReader reader = new FileReader("src\\com\\company\\json\\stories.json")) {
            Object obj = jsonParser.parse(reader);
            stories = (JSONArray) obj;
            stories.forEach( poveste -> parseStory(( (JSONObject) poveste),emp,ene,fin,sho));
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        Game.story.put(CellEnum.EMPTY,emp);
        Game.story.put(CellEnum.ENEMY,ene);
        Game.story.put(CellEnum.SHOP,sho);
        Game.story.put(CellEnum.FINISH,fin);
    }
    public void parseStory (JSONObject poveste,ArrayList <String> emp,ArrayList <String> ene, ArrayList <String> fin, ArrayList <String> sho){

        String type = (String) poveste.get("type");
        //System.out.println(type);

        String story = (String) poveste.get("value");
        //System.out.println(story);
        if(type.equals("EMPTY")) {
            emp.add(story);
        }
        if(type.equals("ENEMY")) {
            ene.add(story);
        }
        if(type.equals("SHOP")) {
            sho.add(story);
        }
        if(type.equals("FINISH")) {
            fin.add(story);
        }
    }
}
