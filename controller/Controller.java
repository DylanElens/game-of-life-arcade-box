package controller;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import model.Direction;
import model.World;

public class Controller extends JFrame implements KeyListener, ActionListener, Runnable {

    JTextArea displayArea;
    JTextField typingArea;
    World world = World.getInstance();
    static final String newline = System.getProperty("line.separator");

    public Controller(String name) {
        super(name);
    }

    private static void createAndShowGUI() {
        Controller frame = new Controller("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        getContentPane().add(typingArea, BorderLayout.PAGE_START);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayArea.setText("");
        typingArea.setText("");
        typingArea.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {    //KEY
        if (e.getKeyCode() == 37) {         // <-
            world.move(Direction.LEFT);
        } else if ((e.getKeyCode() == 40)) {// ^
            world.move(Direction.UP);
        } else if ((e.getKeyCode() == 39)) {// ->
            world.move(Direction.RIGHT);
        } else if ((e.getKeyCode() == 38)) {// v
            world.move(Direction.DOWN);
        } else if ((e.getKeyCode() == 68)) {//d
            world.incrementPattern();
        } else if ((e.getKeyCode() == 65)) {//a
            world.decrementPattern();
        } else if ((e.getKeyCode() == 83)) {//s
            world.placePattern();
        } else if ((e.getKeyCode() == 69)) {//e
            world.incrementSpeed();
        } else if ((e.getKeyCode() == 81)) {//q
            world.decrementSpeed();
        } else if ((e.getKeyCode() == 32)) {
            //debugging purposes only "spacebar"
            world.next();
        } else if ((e.getKeyCode() == 87)) {//w
            world.pauseGrid();
        } else if ((e.getKeyCode() == 90)) {//z
            world.makeCleanGrid();
        }

            System.out.println(world);
            try {
              world.toFile();
            } catch (Exception k) {
              System.out.println(k);
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // displayInfo(e, "KEY RELEASED: ");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    // This is not needed in the end product
    @Override
    public void run() {
        createAndShowGUI();
    }
}
