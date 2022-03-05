package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveAccount {
    public static void saveAccounts() {
        try (FileWriter writer = new FileWriter("src\\com\\company\\json\\accounts.json")) {
            writeAccounts(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAccounts(FileWriter writer) throws IOException {
        JSONArray accountList = new JSONArray();
        for (int i = 0; i < Game.accounts.size(); i++) {
            JSONObject accountPlayer = new JSONObject();
            JSONObject credentials = new JSONObject();
            credentials.put("email", Game.accounts.get(i).getInfo().getCredentials().getEmail());
            credentials.put("password", Game.accounts.get(i).getInfo().getCredentials().getPassword());
            accountPlayer.put("credentials", credentials);
            accountPlayer.put("name", Game.accounts.get(i).getInfo().getName());
            accountPlayer.put("country", Game.accounts.get(i).getInfo().getCountry());
            JSONArray jarray = new JSONArray();
            ArrayList<String> gamesPlayed = Game.accounts.get(i).getInfo().getFavouriteGames();
            jarray.addAll(gamesPlayed);
            accountPlayer.put("favorite_games", jarray);
            Integer nrgamesPlayed = Game.accounts.get(i).getGamesPlayed();
            String nrgames = nrgamesPlayed.toString();
            accountPlayer.put("maps_completed", nrgames);
            JSONArray jarrayCharacters = new JSONArray();
            for (int j = 0; j < Game.accounts.get(i).getCharacters().size(); j++) {
                JSONObject Caracter = new JSONObject();
                String numeErou = Game.accounts.get(i).getCharacters().get(j).name;
                Caracter.put("name", numeErou);
                String profesie = Game.accounts.get(i).getCharacters().get(j).getClass().getSimpleName();
                Caracter.put("profession", profesie);
                Integer character_level = Game.accounts.get(i).getCharacters().get(j).getLevel();
                String level = character_level.toString();
                Caracter.put("level", level);
                Integer exp = Game.accounts.get(i).getCharacters().get(j).getExp();
                long experience = exp.longValue();
                Caracter.put("experience", experience);
                jarrayCharacters.add(j, Caracter);
            }
            accountPlayer.put("characters", jarrayCharacters);
            accountList.add(i,accountPlayer);
        }
        writer.write(accountList.toJSONString());
    }
}
