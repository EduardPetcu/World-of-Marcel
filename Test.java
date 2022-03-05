package com.company;

public class Test {

    public static void main(String[] args) throws InvalidCommandException, InvalidPasswordException {
        Game game = Game.getInstance();
        StoryParser st = new StoryParser();
        st.setStories();
        AccountParser.setAccounts();
        game.run();
    }
}
