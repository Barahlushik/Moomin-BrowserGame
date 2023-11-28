package servlets.game;

import java.util.ArrayList;
import java.util.List;

class Game {

    private int level;
    private final TaskAnswers[] words;
    private TaskAnswers currentWord;


    public Game() {
        this.words = TaskAnswers.values();
        this.currentWord = words[level];
        this.level = 1;

    }



    public String getTurnResult(String symbol, String view) {

        char sym = '_';
        if (symbol.length() > 0) {
            sym = Character.toUpperCase(symbol.charAt(0));
        }
        List<Integer> indexes = new ArrayList<>();
        String answer = currentWord.getAnswer();
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == sym) {
                indexes.add(i);
            }
        }
        StringBuilder newView = new StringBuilder(view);
        for (int index : indexes) {
            newView.setCharAt(index, sym);
        }

        return newView.toString();

    }

    public void next() {
        currentWord = words[level++];
    }

    public boolean hasNext() {
        return level < words.length;
    }

    public int getLevel() {
        return level;
    }

    public String getTask() {
        return currentWord.getTask();

    }

    public String getAnswer() {
        return currentWord.getAnswer();
    }
}
