package tournament;

public class Tournament {

    /**
      * @param scorecard the scores in the tournament and is not null; {@code scorecard[i][j]} represents the score of Player i in Game j. Each row in this array has {@code sets * games} entries, and each entry has a value between 0 and 5 (limits inclusive).
      * @param sets the number of sets played in the tournament, {@code sets > 0}
      * @param games the number of games per set, {@code games > 0}
      * @return a list of players in order of their performance (best to worst)
      */
    public static int[] rankPlayers(int[][] scorecard, int sets, int games) {
        int[] playerRankings;
        // TODO: Your implementation goes here
        //List<Integer> finalSetScore = new ArrayList<>();
        
        int[][] tempcard = scorecard;
        int[] tempscores;
        int numPlayer = tempcard.length;
        int[] finalSetScore = new int[numPlayer];
        playerRankings = new int[numPlayer];
        
        int ptemp;
        boolean isSwap = false;
        boolean tieBreaker = false;

        for (int i=0; i<numPlayer; i++) {
            playerRankings[i] = i;
        }

        for (int i=0; i<numPlayer; i++) {
            for (int j = sets*games-1; j > games*(sets-1)-1; j--) {
                finalSetScore[i] += tempcard[i][j];
            }
        }

        for (int i=numPlayer; i>0; i--) {
            for (int j=1; j<i; j++) {
                isSwap = false;
                if (finalSetScore[j-1] < finalSetScore[j]) {
                    isSwap = true;
                } else if (finalSetScore[j-1] == finalSetScore[j]) {
                    tieBreaker = false;
                    for (int k=games*sets-1; k>=0 && !tieBreaker; k--) {
                        if (tempcard[j-1][k] < tempcard[j][k]) {
                            isSwap = true;
                            tieBreaker = true;
                        } else if (tempcard[j-1][k] > tempcard[j][k]) {
                            isSwap = false;
                            tieBreaker = true;
                        } 
                    }
                }

                if (isSwap) {
                    tempscores = tempcard[j];
                    tempcard[j] = tempcard[j-1];
                    tempcard[j-1] = tempscores;
                    ptemp = finalSetScore[j];
                    finalSetScore[j] = finalSetScore[j-1];
                    finalSetScore[j-1] = ptemp;
                    ptemp = playerRankings[j];
                    playerRankings[j] = playerRankings[j-1];
                    playerRankings[j-1] = ptemp;
                }
            }
        }



        
        // System.out.println("Results");
        // for (int i=0; i<numPlayer; i++) {
        //     System.out.println(playerRankings[i] + ": " + finalSetScore[i]);
        // }
        
        return playerRankings;
    }
}
