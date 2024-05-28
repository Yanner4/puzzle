/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.puzzle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Puzzle extends JFrame {

    public static Board board;
    public static Container contenedor;
    private BufferedImage img = null;
    private TimerPanel timerPanel;
    private int iniciarCounter = 0;
    private static final int MAX_INICIAR_TIMES = 2;

    public Puzzle() {
        this.setTitle("Rompecabezas");
        this.setSize(900, 900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());

        timerPanel = new TimerPanel();
        contenedor.add(timerPanel, BorderLayout.NORTH);

        try {
            img = ImageIO.read(new File("src\\main\\resources\\imagenes\\liberame1.jpg"));
        } catch (IOException e) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, e);
        }

        board = new Board(3, img);
        contenedor.add(board, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());

        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iniciarCounter < MAX_INICIAR_TIMES) {
                    timerPanel.iniciarTimer();
                    board.iniciarPuzzle(); // Iniciar el puzzle solo cuando se presione el botón
                    iniciarCounter++;
                    if (iniciarCounter >= MAX_INICIAR_TIMES) {
                        btnIniciar.setEnabled(false); // Deshabilitar el botón después de 2 usos
                    }
                }
            }
        });
        panelDerecho.add(btnIniciar, BorderLayout.NORTH);

        JButton btnAyuda = new JButton("Ayuda");
        btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSolucion();
            }
        });
        panelDerecho.add(btnAyuda, BorderLayout.CENTER);

        contenedor.add(panelDerecho, BorderLayout.EAST);

        this.setVisible(true);
    }

    private void mostrarSolucion() {
        PuzzleSolver solver = new PuzzleSolver();
        int[][] currentBoard = boardToArray();
        List<PuzzleSolver.PuzzleState> solution = solver.solve(currentBoard);

        if (solution != null && !solution.isEmpty()) {
            StringBuilder steps = new StringBuilder("Solución:\n");
            for (int i = 1; i < solution.size(); i++) {
                steps.append("Paso ").append(i).append(":\n");
                for (int[] row : solution.get(i).board) {
                    for (int cell : row) {
                        steps.append(cell).append(" ");
                    }
                    steps.append("\n");
                }
                steps.append("\n");
            }
            JOptionPane.showMessageDialog(this, steps.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo encontrar una solución.");
        }
    }

    private int[][] boardToArray() {
        int dimension = Board.board.length;
        int[][] array = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Figure figure = Board.board[i][j].getFigure();
                array[i][j] = (figure != null) ? figure.getxSolPos() * dimension + figure.getySolPos() + 1 : 0;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        new Puzzle();
    }
}


