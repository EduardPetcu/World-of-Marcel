package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccountParser {
    public static void setAccounts() {
        JSONArray accs;
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src\\com\\company\\json\\accounts.json")) {
            Object obj = jsonParser.parse(reader);
            accs = (JSONArray) obj;
            accs.forEach( acc -> parseAccounts( (JSONObject) acc));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public static void parseAccounts(JSONObject account){
        // Preluarea campurilor unui cont
        JSONObject credentials = (JSONObject) account.get("credentials");
        String mail = (String) credentials.get("email");
        String password = (String) credentials.get("password");
        String name = (String) account.get("name");
        String country = (String) account.get("country");
        ArrayList <String> favoriteGames = (ArrayList <String>) account.get("favorite_games");
        String string_maps = (String) account.get("maps_completed");
        ArrayList <JSONObject> characters = (ArrayList<JSONObject>) account.get("characters");

        // Prelucrarea datelor din json si salvarea unui cont in vectorul accounts din clasa Game
        ArrayList <Character> listCharacters = new ArrayList<>();
        int nrmaps = Integer.parseInt(string_maps);
        Credentials credits = new Credentials();
        credits.setEmail(mail);
        credits.setPassword(password);
        for(JSONObject character: characters){
            String characterName = (String) character.get("name");
            String characterClass = (String) character.get("profession");
            String strLevel = (String) character.get("level");
            int characterLevel = Integer.parseInt(strLevel);
            Long experience_l = (Long) character.get("experience");
            int experience = experience_l.intValue();
            Character characterCurent = CharacterFactory.createCharacter(characterClass,
                    characterName,
                    experience,
                    characterLevel);
            listCharacters.add(characterCurent);
        }
        Account.Information userInfo = new Account.Information.InformationBuilder(credits)
                .setName(name)
                .setCountry(country)
                .setFavouriteGames(favoriteGames)
                .build();
        Account user = new Account(userInfo, listCharacters, nrmaps);
        Game.accounts.add(user);
    }
}
