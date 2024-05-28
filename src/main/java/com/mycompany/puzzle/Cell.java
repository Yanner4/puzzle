/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle;

/**
 *
 * @author janer
 */
// Clase que representa una celda en el tablero del rompecabezas
public class Cell {

    private int x, y; // Coordenadas de la celda en el tablero
    private Figure figure; // Figura (pieza del rompecabezas) en la celda

    // Constructor para una celda con una figura
    public Cell(int x, int y, Figure figure) {
        this.x = x; // Asigna la coordenada x de la celda
        this.y = y; // Asigna la coordenada y de la celda
        this.figure = figure; // Asigna la figura a la celda
    }

    // Constructor para una celda vacía
    public Cell(int x, int y) {
        this.x = x; // Asigna la coordenada x de la celda
        this.y = y; // Asigna la coordenada y de la celda
        figure = null; // Inicializa la figura como nula (celda vacía)
    }

    // Método para obtener la figura de la celda
    public Figure getFigure() {
        return figure; // Devuelve la figura de la celda
    }

    // Método para establecer la figura de la celda
    public void setFigure(Figure figure) {
        this.figure = figure; // Asigna la figura a la celda
    }

    // Método para obtener la coordenada x de la celda
    public int getX() {
        return x; // Devuelve la coordenada x de la celda
    }

    // Método para obtener la coordenada y de la celda
    public int getY() {
        return y; // Devuelve la coordenada y de la celda
    }
}
