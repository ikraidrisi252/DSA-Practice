class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }

        // If the loop ends on Alice's turn, Alice couldn't move -> false.
        // If it ends on Bob's turn, Bob couldn't move -> true.
        return !isAliceTurn;
    }
}