/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle;

/**
 *
 * @author janer
 */
import java.util.*;

public class PuzzleSolver {

    // Estado del puzzle
    static class PuzzleState {
        int[][] board;
        int x, y; // Coordenadas de la celda vacía
        int cost, depth;
        PuzzleState parent;

        PuzzleState(int[][] board, int x, int y, int depth, PuzzleState parent) {
            this.board = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                this.board[i] = board[i].clone();
            }
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.parent = parent;
            this.cost = depth + heuristic(board);
        }

        // Heurística: número de piezas fuera de lugar
        private int heuristic(int[][] board) {
            int h = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] != 0 && board[i][j] != i * board.length + j + 1) {
                        h++;
                    }
                }
            }
            return h;
        }

        // Genera estados vecinos
        List<PuzzleState> generateNeighbors() {
            List<PuzzleState> neighbors = new ArrayList<>();
            int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
            for (int[] dir : directions) {
                int newX = x + dir[0], newY = y + dir[1];
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board.length) {
                    int[][] newBoard = new int[board.length][board.length];
                    for (int i = 0; i < board.length; i++) {
                        newBoard[i] = board[i].clone();
                    }
                    newBoard[x][y] = newBoard[newX][newY];
                    newBoard[newX][newY] = 0;
                    neighbors.add(new PuzzleState(newBoard, newX, newY, depth + 1, this));
                }
            }
            return neighbors;
        }

        // Compara estados del puzzle para el PriorityQueue
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PuzzleState) {
                PuzzleState other = (PuzzleState) obj;
                return Arrays.deepEquals(this.board, other.board);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(board);
        }
    }

    // Encuentra la solución usando el algoritmo A*
    public List<PuzzleState> solve(int[][] initialBoard) {
        int n = initialBoard.length;
        int x = 0, y = 0;
        outerLoop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initialBoard[i][j] == 0) {
                    x = i;
                    y = j;
                    break outerLoop;
                }
            }
        }

        PuzzleState initialState = new PuzzleState(initialBoard, x, y, 0, null);
        PriorityQueue<PuzzleState> openSet = new PriorityQueue<>(Comparator.comparingInt(ps -> ps.cost));
        Set<PuzzleState> closedSet = new HashSet<>();
        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            PuzzleState currentState = openSet.poll();
            if (currentState.heuristic(currentState.board) == 0) {
                List<PuzzleState> solution = new ArrayList<>();
                while (currentState != null) {
                    solution.add(currentState);
                    currentState = currentState.parent;
                }
                Collections.reverse(solution);
                return solution;
            }
            closedSet.add(currentState);

            for (PuzzleState neighbor : currentState.generateNeighbors()) {
                if (!closedSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }
        return null;
    }
}
