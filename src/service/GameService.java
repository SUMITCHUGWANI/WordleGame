package service;

import repo.ScoreCardRepo;
import repo.WordRepo;
import models.User;

import java.util.*;

public class GameService {

    private final WordRepo wordRepo;

    private final ScoreCardRepo scoreCardRepo;

    public GameService(WordRepo wordRepo, ScoreCardRepo scoreCardRepo) {
        this.wordRepo = wordRepo;
        this.scoreCardRepo = scoreCardRepo;
    }

    public void playGame(String word, int maxAttempts, User user){
        System.out.println("Welcome to the game  "+ user.getName());
        List<String> previousAttempts = new ArrayList<>();
        HashSet<Character> wordSet = new HashSet<>();
        for (Character c : word.toCharArray()){
            wordSet.add(c);
        }

        int i = 0;
        Scanner sc  = new Scanner(System.in);
        boolean correctGuess = false;
        while (i  < maxAttempts){
            if (correctGuess){
                System.out.println(user.getName() + " has Already played this round");
                i += 1;
                continue;
            }
            String inputWord =  sc.nextLine();
            String output = matchWords(word, inputWord, wordSet);
            previousAttempts.add(output);
            printPreviousAttempts(previousAttempts);
            i += 1;
            if (word.equalsIgnoreCase(inputWord)){
                correctGuess = true;
                int difficultyLevel = wordRepo.getDifficultyLevel(word);
                double score = (double) difficultyLevel / i;
                scoreCardRepo.updateUserScore(user, score);
                System.out.println("word is correct, score added: "+ score);

            }
        }
    }

    private void printPreviousAttempts(List<String> previousAttempts) {
        for (String previousAttempt : previousAttempts) {
            System.out.println(previousAttempt);
        }
    }

    private String matchWords(String word, String inputWord, HashSet<Character> wordSet) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< inputWord.length(); i++){
            if (word.charAt(i) == inputWord.charAt(i)){
                sb.append("C");
                sb.append(" ");
            }else if (wordSet.contains(inputWord.charAt(i))){
                sb.append("P");
                sb.append(" ");
            }else{
                sb.append("I");
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
