/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.connectfour;

import java.util.Random;
import java.util.Scanner;

/***
 *
 * @author OliverTLee
 */
public class ConnectFourmain {

    public static void main(String[] args) {
        // This game plays the game of Connect four
        // ~~~~~~~~~GAME LOOP~~~~~~~~~
        // steps: Setup:
        // Setup phase: Take input of both names, method: return String[] getNames();
        // Generate the board                             return char[][] generateBoard();
        // Randomly select who goes first. 

        // MainGame: player turns
        // Player 1 move    return char[][] playerMove('x');    input type is the tile they play as
        // Ask player 1 for move
        // Apply move to board
        // Check if move wins game || fills board 
        // Player 2 move :
        // Same as 1
        // if game end (winner found or board full) prompt to play again 
        boolean letsPlay = true;
        while (letsPlay) {
            letsPlay = runGame();
        }

    }

    public static boolean runGame() {
        // Declare my Scanner and Random
        Random myRandom = new Random();
        Scanner myScanner = new Scanner(System.in);
        // Initialize the board and playernames
        char[][] myBoard = generateBoard();
        String[] players = getPlayerNames();
        System.out.println("Flipping Coin to determine who goes first...");
        int currPlayer = myRandom.nextInt(2);
        char[] tokens = {'X', '.'};
        System.out.println(""); // make more white space
        System.out.println("Player " + players[currPlayer] + " will begin first, playing as " + tokens[currPlayer]);
        // 
        printBoard(myBoard);
        int turnNumber = 0;

        while (!checkWinner(myBoard) && turnNumber < 42) {
            System.out.println("It is " + players[currPlayer] + "'s turn");
            if (players[currPlayer].equals("computer")) {
                myBoard = playTurn(myBoard, currPlayer, true);
            } else {
                myBoard = playTurn(myBoard, currPlayer, false);
            }
            printBoard(myBoard);
            if (currPlayer == 1) {
                currPlayer = 0;
            } else {
                currPlayer = 1;
            }
            turnNumber++;
        }

        if (turnNumber >= 42) {
            System.out.println("TIE game! Board is full. ");
        } else {
            System.out.println("CONGRATULATIONS");
            System.out.println("~~ ~ ~   ~  ~ ``` .    `~  ~`   ` `~` `  ~  ~   . .~  ~.  ~  ~.  ~  ~.  ~ ");
            System.out.println("  ~` .  ~ ~  ~  This is confetti  ~ ~  ~~   ~  ~ ```~   ~  ~ ```");
            System.out.println("~ ~ ~   ~.  ~   ~   `  .   `  ~  `. ~ ~   ~  ~ ```~   ~  ~ ``` '' ;; ");
            System.out.println("~ ~  ~`  ` ` ``  `~~  ~  ~  ~~ ``  '  ' .. .  ~~ ``  '  ' .  ~~ ``  '  ' .");
            System.out.println(players[currPlayer] + " is the Loser :( ");
        }
        System.out.println("Would you like to play again? (y/n)");
        String continueGame = myScanner.next();
        return continueGame.contains("y");
    }

    public static char[][] generateBoard() {
        // height of 6, width of 7
        char[][] myBoard = {
            {'-', '-', '-', '-', '-', '-', '-',},
            {'-', '-', '-', '-', '-', '-', '-',},
            {'-', '-', '-', '-', '-', '-', '-',},
            {'-', '-', '-', '-', '-', '-', '-',},
            {'-', '-', '-', '-', '-', '-', '-',},
            {'-', '-', '-', '-', '-', '-', '-',}
        };
        return myBoard;
    }

    public static String getPlayerName() {
        Scanner myScanner = new Scanner(System.in);
        return myScanner.next();
    }

    private static boolean checkWinner(char[][] myBoard) {
        return (checkHorizontal(myBoard) || checkVertical(myBoard) || checkDiag1(myBoard) || checkDiag2(myBoard));

    }

    private static String[] getPlayerNames() {
        System.out.println("ENTER NAME \"computer\" for play versus a computer.");
        System.out.println("What is player one's name?");
        String player1 = getPlayerName();
        System.out.println("What is player two's name?");
        String player2 = getPlayerName();
        String[] players = {player1, player2};
        return players;
    }

    private static char[][] playTurn(char[][] myBoard, int currPlayer, boolean computerPlayer) {
        // this method takes user input, and adds to the board.
        // Get turn :
        Random myRandom = new Random();
        char[] tokens = {'X', '.'};
        char playerToken = tokens[currPlayer];
        Scanner myScanner = new Scanner(System.in);
        int currMove = -1;
        if (computerPlayer == false) {
            do {
                System.out.println("What column would you like to play in? (1-7)");
                String stringMove = myScanner.next();
                try {
                    currMove = Integer.parseInt(stringMove) - 1;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid column 1-7");
                }
            } while (!validMove(myBoard, currMove));
        } else {
            do {
                currMove = myRandom.nextInt(7) + 1;
            } while (!validMove(myBoard, currMove));
        }
        // now that its valid, we can apply it.
        //APPLY TO COLUMN
        for (int i = 0; i < 6; i++) {
            if (myBoard[5 - i][currMove] == '-') {
                myBoard[5 - i][currMove] = playerToken;
                break;
            }
        }
        return myBoard;
    }

    public static boolean validMove(char[][] myBoard, int currMove) {
        // If the top element 
        if ((currMove < 0 || currMove > 6)) {
            return false;
        }
        if (myBoard[0][currMove] != ('-')) {
            System.out.println("That column is full!");
            return false;
        }
        return true;
    }

    public static void printBoard(char[][] myBoard) {
        System.out.println(" 1   2   3   4   5   6   7 ");
        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 7; j++) {
                System.out.print(myBoard[i][j] + "" + myBoard[i][j] + "" + myBoard[i][j] + " ");
            }
            System.out.println("");
            for (int j = 0; j < 7; j++) {
                System.out.print(myBoard[i][j] + "" + myBoard[i][j] + "" + myBoard[i][j] + " ");
            }
            System.out.println("");

            System.out.println("");
        }
        //   1     2     3     4     5     6     7   
        // ***** _____ _____ _____ _____ _____ _____        possible formatting brainstorm
        // * X * | X | | X | | X | | X | | X | | X |
        // ***** ***** ***** ***** ***** ***** *****

    }

    private static boolean checkHorizontal(char[][] myBoard) {
        // this is our --- direction
        // 7 across, 6 height. We want to check horizontal 
        for (int i = 0; i < 6; i++) { // This is our rows, we want to checking ever row 
            for (int j = 0; j < 4; j++) {// this is our columns, we only want to check 5 leftmost columns 0 1 2 3 (4 5 6) 
                if (myBoard[i][j] != '-'
                        && myBoard[i][j] == myBoard[i][j + 1]
                        && myBoard[i][j] == myBoard[i][j + 2]
                        && myBoard[i][j] == myBoard[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical(char[][] myBoard) {
        // this is our | direction

        // 7 across, 6 height. We want to check horizontal 
        for (int i = 0; i < 3; i++) { // we want to check the top 
            for (int j = 0; j < 7; j++) {// this is our columns, we only want to check 5 leftmost columns 0 1 2 3 (4 5 6) 
                if (myBoard[i][j] != '-'
                        && myBoard[i][j] == myBoard[i + 1][j]
                        && myBoard[i][j] == myBoard[i + 2][j]
                        && myBoard[i][j] == myBoard[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiag1(char[][] myBoard) {
        // This is our \ direction
        // 7 across, 6 height. We
        for (int i = 0; i < 3; i++) { // we want to check the top 
            for (int j = 0; j < 4; j++) {// this is our columns,
                if (myBoard[i][j] != '-'
                        && myBoard[i][j] == myBoard[i + 1][j + 1]
                        && myBoard[i][j] == myBoard[i + 2][j + 2]
                        && myBoard[i][j] == myBoard[i + 3][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiag2(char[][] myBoard) {
        // this is our / direction 
        for (int i = 0; i < 3; i++) { // we want to check the top 
            for (int j = 3; j < 7; j++) {// this is our columns, we only want to check 5 leftmost columns 0 1 2 3 (4 5 6) 
                if (myBoard[i][j] != '-'
                        && myBoard[i][j] == myBoard[i + 1][j - 1]
                        && myBoard[i][j] == myBoard[i + 2][j - 2]
                        && myBoard[i][j] == myBoard[i + 3][j - 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    
}
