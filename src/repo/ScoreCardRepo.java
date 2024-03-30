package repo;

import models.User;

import java.util.TreeSet;

public class ScoreCardRepo {
    TreeSet<User> scoreRepo;

    public ScoreCardRepo() {
        this.scoreRepo = new TreeSet<>((a, b) -> {
            int scoreComparator = Double.compare(b.getScore(), a.getScore());
            if (scoreComparator == 0){
                return a.getName().compareTo(b.getName());
            }
            return scoreComparator;
        });
    }

    public void addUser(User user){
        scoreRepo.add(user);
    }

    public void updateUserScore(User user, double score){
        scoreRepo.remove(user);
        user.addScore(score);
        scoreRepo.add(user);
        System.out.println(scoreRepo);
    }

    public void showScoreCard(){
        //System.out.println("inside showScoreCard ");
        //System.out.println(scoreRepo);
        int i = 1;
        for (User u : scoreRepo){
            System.out.print(i + ".");
            System.out.println(u);
            i += 1;
        }
    }
}
