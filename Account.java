package com.company;
import java.util.ArrayList;
import java.util.Collections;

public class Account {
    private final ArrayList <Character> characters;
    private final Information info;
    private final int gamesPlayed;
    public Account(Information info, ArrayList <Character> chars ,int nrmaps){
        this.info=info;
        this.characters=chars;
        this.gamesPlayed=nrmaps;
    }
    static class Information {
        private final Credentials credentials;
        private final ArrayList <String> favouriteGames;
        private final String name;
        private final String country;
        private Information(InformationBuilder builder) {
            this.name = builder.name;
            this.favouriteGames = builder.favouriteGames;
            this.country = builder.country;
            this.credentials=builder.credentials;
        }
        public String getName(){
            return name;
        }
        public String getCountry(){
            return country;
        }
        public ArrayList <String> getFavouriteGames(){
            return favouriteGames;
        }
        public Credentials getCredentials(){
            return credentials;
        }
        public String toString() {
            return "User: " + this.name + " " + this.country + " " + this.credentials.getEmail();
        }
        public static class InformationBuilder {
            private final Credentials credentials;
            private ArrayList <String> favouriteGames = new ArrayList<>();
            private String name;
            private String country;
            public InformationBuilder(Credentials credentials){
                this.credentials=credentials;
            }
            public InformationBuilder setName(String name){
                this.name=name;
                return this;
            }
            public InformationBuilder setCountry(String country){
                this.country=country;
                return this;
            }
            public InformationBuilder setFavouriteGames(ArrayList <String> favouriteGame){
                Collections.sort(favouriteGames);
                this.favouriteGames=favouriteGame;
                return this;
            }
            public Information build(){
                return new Information(this);
            }

        }
    }
    public Information getInfo(){
        return this.info;
    }
    public ArrayList<Character> getCharacters(){
        return characters;
    }
    public int getGamesPlayed(){
        return gamesPlayed;
    }
}
