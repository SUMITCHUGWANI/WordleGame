package repo;

import exception.UserAlreadyPresentException;
import models.User;

import java.util.HashMap;

public class UserRepo {
    HashMap<String, User>  userHashMap;
    private ScoreCardRepo scoreCardRepo;

    public UserRepo(ScoreCardRepo scoreCardRepo) {
        this.userHashMap = new HashMap<>();
        this.scoreCardRepo = scoreCardRepo;
    }



    public void addUser(String name) throws Exception {
        if (userHashMap.containsKey(name)){
            System.out.println("user with this name is already present");
            throw new UserAlreadyPresentException();
        }
        User user = new User(name);
        userHashMap.put(name, user);
        System.out.println("user is saved successfully");
        scoreCardRepo.addUser(user);

    }

    public User getUser(String user){
        return userHashMap.get(user);
    }
}
