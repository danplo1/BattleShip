package com.company;

import java.util.Random;
import java.util.Scanner;

public class BattleShip {
    public static class battleShip {

        public static void main(String[] args) {
            int[][] board = new int[5][5];
            int[][] ships = new int[3][2];
            int[] shoot = new int[2];
            int attempts = 0,
                    shotHit = 0;

            initBoard(board);
            initShips(ships);

            System.out.println();

            do {
                showBoard(board);
                shoot(shoot);
                attempts++;

                if (hit(shoot, ships)) {
                    hint(shoot, ships, attempts);
                    shotHit++;
                } else
                    hint(shoot, ships, attempts);

                changeboard(shoot, ships, board);


            } while (shotHit != 3);

            System.out.println("\n\n\n Gra w statki zakończona! Uderzyłeś 3 statki w " + attempts + " próbach!");
            showBoard(board);
        }

        public static void initBoard(int[][] board) {
            for (int row = 0; row < 5; row++)
                for (int column = 0; column < 5; column++)
                    board[row][column] = -1;
        }

        public static void showBoard(int[][] board) {
            System.out.println("\t1 \t2 \t3 \t4 \t5");
            System.out.println();

            for (int row = 0; row < 5; row++) {
                System.out.print((row + 1) + "");
                for (int column = 0; column < 5; column++) {
                    if (board[row][column] == -1) {
                        System.out.print("\t" + "~");
                    } else if (board[row][column] == 0) {
                        System.out.print("\t" + "*");
                    } else if (board[row][column] == 1) {
                        System.out.print("\t" + "X");
                    }

                }
                System.out.println();
            }

        }

        public static void initShips(int[][] ships) {
            Random random = new Random();

            for (int ship = 0; ship < 3; ship++) {
                ships[ship][0] = random.nextInt(5);
                ships[ship][1] = random.nextInt(5);

                //sprawdzamy, czy ten strzał był już wypróbowany
                //jeśli tak, wystarczy dokończyć procedurę do ..., podczas gdy nowa para zostanie losowo wybrana

                for (int last = 0; last < ship; last++) {
                    if ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1]))
                        do {
                            ships[ship][0] = random.nextInt(5);
                            ships[ship][1] = random.nextInt(5);
                        } while ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1]));
                }

            }
        }

        public static void shoot(int[] shoot) {
            Scanner input = new Scanner(System.in);

            System.out.print("Rząd/Wiersz: ");
            shoot[0] = input.nextInt();
            shoot[0]--;

            System.out.print("Kolumna: ");
            shoot[1] = input.nextInt();
            shoot[1]--;

        }

        public static boolean hit(int[] shoot, int[][] ships) {

            for (int ship = 0; ship < ships.length; ship++) {
                if (shoot[0] == ships[ship][0] && shoot[1] == ships[ship][1]) {
                    System.out.printf("Uderzyłeś statek znajdujący się w (%d,%d)\n", shoot[0] + 1, shoot[1] + 1);
                    return true;
                }
            }
            return false;
        }

        public static void hint(int[] shoot, int[][] ships, int attempt) {
            int row = 0,
                    column = 0;

            for (int line = 0; line < ships.length; line++) {
                if (ships[line][0] == shoot[0])
                    row++;
                if (ships[line][1] == shoot[1])
                    column++;
            }

            System.out.printf("\nWskazówka %d: \nRząd %d -> %d statki/-ów\n" +
                    "Kolumna %d -> %d statki/-ów\n", attempt, shoot[0] + 1, row, shoot[1] + 1, column);
        }

        public static void changeboard(int[] shoot, int[][] ships, int[][] board) {
            if (hit(shoot, ships))
                board[shoot[0]][shoot[1]] = 1;
            else
                board[shoot[0]][shoot[1]] = 0;
        }
    }
}