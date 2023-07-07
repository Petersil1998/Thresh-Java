package net.petersil98.thresh.collection;

import net.petersil98.thresh.data.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Challenges {

    private static Map<Integer, Challenge> challenges;

    public static Challenge getChallenge(int id) {
        return challenges.get(id);
    }

    public static List<Challenge> getChallenges() {
        return new ArrayList<>(challenges.values());
    }
}
