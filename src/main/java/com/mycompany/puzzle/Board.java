/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {

    public static Cell[][] board;
    private ArrayList<Cell> completeBoard = new ArrayList<Cell>();
    private int dimesion;
    private int figureWidth, figureHeight;
    private JLabel empety;
    private BufferedImage rompecabezas;

    public Board(int dimesion, BufferedImage rompecabezas) {
        this.dimesion = dimesion;
        this.rompecabezas = rompecabezas;
        board = new Cell[dimesion][dimesion];
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    public void iniciarPuzzle() {
        completeBoard.clear();
        int x = 0;
        int y = 0;
        figureWidth = rompecabezas.getWidth() / dimesion;
        figureHeight = rompecabezas.getHeight() / dimesion;

        for (int i = 0; i < dimesion; i++) {
            for (int j = 0; j < dimesion; j++) {
                if (i == dimesion - 1 && j == dimesion - 1) {
                    continue;
                }
                completeBoard.add(new Cell(i, j, new Figure(i, j, new ImageIcon(rompecabezas.getSubimage(x, y, figureWidth, figureHeight)), dimesion)));
                x += figureWidth;
            }
            x = 0;
            y += figureHeight;
        }
        desordenar();
        remover();
    }

    private void desordenar() {
        Random generardor = new Random();
        ArrayList<Cell> copia = new ArrayList<Cell>(completeBoard);
        for (int i = 0; i < dimesion; i++) {
            for (int j = 0; j < dimesion; j++) {
                if (i == dimesion - 1 && j == dimesion - 1) {
                    board[i][j] = new Cell(i, j);
                    continue;
                }
                int aleatorio = generardor.nextInt(completeBoard.size());
                completeBoard.get(aleatorio).getFigure().setxPos(i);
                completeBoard.get(aleatorio).getFigure().setyPos(j);
                board[i][j] = new Cell(i, j, completeBoard.get(aleatorio).getFigure());
                completeBoard.remove(aleatorio);
            }
        }
        completeBoard = copia;
        remover();
    }

    private void actualizar() {
        for (int i = 0; i < dimesion; i++) {
            for (int j = 0; j < dimesion; j++) {
                if (board[i][j].getFigure() == null) {
                    empety = new JLabel();
                    empety.setPreferredSize(new Dimension(figureWidth, figureHeight));
                    this.add(empety);
                    continue;
                }
                this.add(board[i][j].getFigure());
            }
        }
        Puzzle.contenedor.validate();
    }

    public void remover() {
        this.removeAll();
        actualizar();
    }
    public boolean isPuzzleComplete() {
        int dimension = board.length;
        int correctPieces = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Figure figure = board[i][j].getFigure();
                if (figure != null && figure.getxPos() == figure.getxSolPos() && figure.getyPos() == figure.getySolPos()) {
                    correctPieces++;
                }
            }
        }
                return correctPieces == dimension * dimension - 1; // El rompecabezas está completo si todas las piezas están en su posición correcta
            }
}
