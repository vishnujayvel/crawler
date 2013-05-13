/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Vadivelu
 */
public class URLS extends JFrame implements ActionListener {
    JButton buttons[];
    public URLS(int n){
        buttons = new JButton[n];
    }
    public void actionPerformed(ActionEvent e){
        
    }
    public void init() {
        // TODO start asynchronous download of heavy resources
    }
    // TODO overwrite start(), stop() and destroy() methods
}
