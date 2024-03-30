package repo;

import exception.InvalidWordException;
import exception.WordAlreadyPresentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class WordRepo {

    TreeMap<Integer, List<String>> wordMap;
    HashMap<String, Integer> wordDifficultyLevelMap;

    public WordRepo() {
        this.wordMap = new TreeMap<Integer, List<String>>();
        wordDifficultyLevelMap = new HashMap<>();
    }

    public void addWord(String word, int difficultyLevel) throws Exception {
        if (word.length() <= 2){
            throw new InvalidWordException();
        }
        if (!wordMap.containsKey(difficultyLevel)){
            wordMap.put(difficultyLevel, new ArrayList<>());
            wordMap.get(difficultyLevel).add(word);
            wordDifficultyLevelMap.put(word, difficultyLevel);
        }else{
            if (wordDifficultyLevelMap.containsKey(word)){
                System.out.println("Word already exist");
                throw new WordAlreadyPresentException();
            }else{
                List<String> wordList = wordMap.get(difficultyLevel);
                wordList.add(word);
                wordMap.put(difficultyLevel, wordList);
                wordDifficultyLevelMap.put(word, difficultyLevel);
            }
        }
        System.out.println("word added successfully");
    }

    public int getDifficultyLevel(String word){
        return wordDifficultyLevelMap.get(word);
    }
}
