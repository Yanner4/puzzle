/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle;

/**
 *
 * @author janer
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel {

    private JLabel timerLabel; // Etiqueta para mostrar el tiempo transcurrido
    private Timer timer; // Objeto Timer que controla el temporizador
    private int segundosTranscurridos; // Variable para almacenar el tiempo transcurrido en segundos
    private PuzzleCompletionListener completionListener; // Listener para notificar cuando el rompecabezas se completa

    public TimerPanel() {
        timerLabel = new JLabel("Tiempo: 0 segundos"); // Inicializa la etiqueta del temporizador con el tiempo inicial
        segundosTranscurridos = 0; // Inicializa el contador de segundos transcurridos
        timer = new Timer(1000, new ActionListener() { // Crea un Timer que se ejecuta cada 1000 ms (1 segundo)
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosTranscurridos++; // Incrementa el contador de segundos transcurridos
                actualizarTimerLabel(); // Actualiza el texto de la etiqueta del temporizador
                if (completionListener != null && completionListener.isPuzzleComplete()) { // Verifica si el rompecabezas se ha completado
                    detenerTimer(); // Detiene el temporizador si el rompecabezas está completo
                    JOptionPane.showMessageDialog(null, "¡Felicidades! ¡Has completado el rompecabezas!"); // Muestra un mensaje de felicitación
                }
            }
        });
        add(timerLabel); // Agrega la etiqueta del temporizador al panel
    }

    public void iniciarTimer() {
        timer.start(); // Inicia el temporizador
    }

    public void detenerTimer() {
        timer.stop(); // Detiene el temporizador
    }

    public void reiniciarTimer() {
        segundosTranscurridos = 0; // Reinicia el contador de segundos transcurridos
        actualizarTimerLabel(); // Actualiza el texto de la etiqueta del temporizador
    }

    private void actualizarTimerLabel() {
        timerLabel.setText("Tiempo: " + segundosTranscurridos + " segundos"); // Actualiza el texto de la etiqueta del temporizador con el tiempo transcurrido
    }

    public void setCompletionListener(PuzzleCompletionListener listener) {
        this.completionListener = listener; // Establece el listener para notificar cuando el rompecabezas se completa
    }

    public int getSegundosTranscurridos() {
        return segundosTranscurridos; // Devuelve el tiempo transcurrido en segundos
    }

    public interface PuzzleCompletionListener {
        boolean isPuzzleComplete(); // Interfaz para notificar cuando el rompecabezas se completa
    }
}
