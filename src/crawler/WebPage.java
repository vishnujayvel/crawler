/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.awt.*;
import java.applet.*;
import javax.swing.*;

/**
 *
 * @author Vadivelu
 */

class WebFrame extends JFrame{
    JEditorPane editorPane = new JEditorPane();
    JPanel frame = new JPanel();
    String url;
    
    WebFrame(String url){
        this.url = url;
        displayPage();
    }
    
    void displayPage(){
        try{
            if( url == null){
                System.out.println("Couldn't find the URL");
            }else{
                editorPane.setPage(url);
            }
            JScrollPane editorScroll = new JScrollPane(editorPane);
            editorScroll.setPreferredSize(new Dimension(500, 500));
            add(editorScroll);
            setSize(541,534);
            setResizable(true);
            setVisible(true);
            setTitle("Web Page");
            
        }catch(Exception e){
            System.out.println("OOPS!! "+ e.getMessage());
        }
    }
}


public class WebPage extends JFrame {
    public static void main(String args[]){
        //WebFrame f = new WebFrame("https://www.google.com");        
    }
}
