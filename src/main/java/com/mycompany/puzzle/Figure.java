/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author janer
 */
public class Figure extends JButton implements ActionListener {

    private final int xSolPos, ySolPos;
    private int xPos, yPos;
    private int dimension;

    public Figure(int xsolpos, int ysolpos, ImageIcon subimagen, int dimension) {
        this.xSolPos = xsolpos;
        this.ySolPos = ysolpos;
        this.dimension = dimension;
        xPos = xsolpos;
        yPos = ysolpos;
        this.setIcon(subimagen);
        this.setPreferredSize(new Dimension(subimagen.getIconWidth(), subimagen.getIconHeight()));
        this.addActionListener(this);
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxSolPos() {
        return xSolPos;
    }

    public int getySolPos() {
        return ySolPos;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Move();
    }

    private void Move() {
        Cell[][] board = Board.board;
        try {
            if (board[xPos][yPos - 1].getFigure() == null) {//arriba 
                Board.board[xPos][yPos - 1].setFigure(this);
                Board.board[xPos][yPos].setFigure(null);
                yPos--;
                Puzzle.board.remover();
                ComprobarRespuesta();
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            /**
             * sirve para que el programa no haga buuum pero no muestra nada
             */
        }
        try {
            if (board[xPos][yPos + 1].getFigure() == null) {//abajo
                Board.board[xPos][yPos + 1].setFigure(this);
                Board.board[xPos][yPos].setFigure(null);
                yPos++;
                Puzzle.board.remover();
                ComprobarRespuesta();
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            /**
             * sirve para que el programa no haga buuum pero no muestra nada
             */
        }
        try {
            if (board[xPos + 1][yPos].getFigure() == null) {//derecha
                Board.board[xPos + 1][yPos].setFigure(this);
                Board.board[xPos][yPos].setFigure(null);
                xPos++;
                Puzzle.board.remover();
                ComprobarRespuesta();
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            /**
             * sirve para que el programa no haga buuum pero no muestra nada
             */
        }
        try {
            if (board[xPos - 1][yPos].getFigure() == null) {//izquierda
                Board.board[xPos - 1][yPos].setFigure(this);
                Board.board[xPos][yPos].setFigure(null);
                xPos--;
                Puzzle.board.remover();
                ComprobarRespuesta();
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            /**
             * sirve para que el programa no haga buuum pero no muestra nada
             */
        }
    }

    private void ComprobarRespuesta() {
        Figure figura = null;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                figura = Board.board[i][j].getFigure();
                if (figura == null) {
                    continue;
                } else if (figura.getxPos() != figura.getxSolPos() || figura.getyPos() != figura.getySolPos()) {
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, " me quiero morir por hacer esto");
    }
}
