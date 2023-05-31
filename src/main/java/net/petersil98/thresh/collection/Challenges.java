package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Challenge;

import java.util.List;

public class Challenges {

    private static List<Challenge> challenges;

    public static Challenge getChallenge(int id) {
        return challenges.stream().filter(challenge -> challenge.getId() == id).findFirst().orElse(null);
    }

    public static List<Challenge> getChallenges() {
        return challenges;
    }

    public static void setChallenges(List<Challenge> challenges) {
        Challenges.challenges = challenges;
    }
}
