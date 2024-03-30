import models.User;
import repo.ScoreCardRepo;
import repo.UserRepo;
import repo.WordRepo;
import service.GameService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("inside main class");

        WordRepo wordRepo = new WordRepo();
        wordRepo.addWord("FLY", 5);
        wordRepo.addWord("PLAY", 5);
        wordRepo.addWord("APPLE", 4);
        wordRepo.addWord("ROGUE", 6);
        wordRepo.addWord("FLOOD", 5);
        wordRepo.addWord("FLIPKART", 7);
        ScoreCardRepo scoreCardRepo = new ScoreCardRepo();

        UserRepo userRepo = new UserRepo(scoreCardRepo);
        userRepo.addUser("Hermione");
        userRepo.addUser("Ron");
        userRepo.addUser("Harry");

        User hermione = userRepo.getUser("Hermione");
        User ron = userRepo.getUser("Ron");
        User harry = userRepo.getUser("Harry");



        GameService gameService  = new GameService(wordRepo,scoreCardRepo);

        gameService.playGame("FLIPKART", 4, hermione);
        scoreCardRepo.showScoreCard();
        gameService.playGame("FLIPKART", 4, ron);
        scoreCardRepo.showScoreCard();
        gameService.playGame("FLIPKART", 4, harry);
        scoreCardRepo.showScoreCard();

    }
}