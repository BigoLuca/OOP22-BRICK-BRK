package brickbreaker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import brickbreaker.common.Chronometer;

public class tryTimer extends JFrame {
    Chronometer c = new Chronometer();
    Thread t;
    long time = 0;
    
    public tryTimer() {
        t = new Thread(c);
        
        setTitle("Griglia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200); // Imposta le dimensioni della finestra
        setLocationRelativeTo(null);

        JButton playButton = new JButton("play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.resume();
            }
        });
        JButton pauseButton = new JButton("pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.pause();
            }
        });
        JButton stopButton = new JButton("stop");
        stopButton.addActionListener(e -> {
            System.out.println("tempo gioco: " + c.stop());
            while (t.isAlive()) {}
            int option = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler chiudere la finestra?", "Conferma", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose(); // Chiude la finestra corrente
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        t.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        new tryTimer();
    }
}

