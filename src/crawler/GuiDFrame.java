/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiDFrame extends JFrame implements ActionListener
{
    JTextArea urlbox;
    static String crawlurl = "http://www.udel.edu/";
     JButton button1;
     JButton button2;
     static JLabel progresswindow;
    //static GuiDFrame f;
     
   public GuiDFrame()
   {
     getContentPane().setLayout(null);
     setupGUI();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   void setupGUI()
   {
     	urlbox = new JTextArea();
	urlbox.setLocation(6,5);
	urlbox.setSize(525,25);
	urlbox.setText("");
	urlbox.setRows(5);
	urlbox.setColumns(5);
	urlbox.setToolTipText("Enter the url here");
	getContentPane().add(urlbox);

	button1 = new JButton();
	button1.setLocation(42,37);
	button1.setSize(100,27);
	button1.setText("crawl!");
	button1.setToolTipText("click here to crawl");
	getContentPane().add(button1);
         button1.addActionListener(this);

	button2 = new JButton();
	button2.setLocation(261,38);
	button2.setSize(93,27);
	button2.setText("stop");
	getContentPane().add(button2);

	progresswindow = new JLabel();
	progresswindow.setLocation(6,117);
	progresswindow.setSize(527,281);
	progresswindow.setText("");
	getContentPane().add(progresswindow);

	setTitle("frame");
	setSize(561,534);
	setVisible(true);
	setResizable(true);
	
	
   }
    public static void main( String args[] )
   {
    new GuiDFrame();
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        crawlurl = urlbox.getText();
        new Crawler();
    }
}  
