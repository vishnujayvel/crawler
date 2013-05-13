/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

/**
 *
 * @author Vadivelu
 */
import static crawler.Crawler.db;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class SearchFrame extends JFrame implements ActionListener {
    JButton buttons[];
    JPanel panelSearchFrame = new JPanel();


    public SearchFrame() {}
    
    @Override
    public void actionPerformed(ActionEvent e){
        String url = e.getActionCommand();
        WebFrame aPage = new WebFrame(url);
    }
}


public class URLClass extends SearchFrame{
    
    public static DB db = new DB();
    static SearchFrame urlframe;
    
    boolean searchSQL(String PageContent , String searchTerm){
       return PageContent.contains(searchTerm);
       
    }
    
    int sizeResult(ResultSet rs){
        int size = 0;
        try{
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
        }catch(Exception e){
            System.out.println("OOPS!! "+ e.getMessage());
        }
        return size;
    }
    
    void searchDB(String searchTerm){
        try{
            Statement smt = db.conn.createStatement();
            ResultSet rs = smt.executeQuery("SELECT URL,Content from `Crawler`.`record`");
            int n = sizeResult(rs);
            panelSearchFrame.setLayout(new FlowLayout());
            buttons = new JButton[n];
            int i = 0, x = 100, y = 600, offsetX = 0, offsetY = 0;
            
            while( rs.next()){
                String PageContent = rs.getString(2);
                if( searchSQL(PageContent, searchTerm)){
                    System.out.println();
                    buttons[i] = new JButton();
                    System.out.println(buttons[i]);
                    System.out.println(offsetX+" "+offsetY);
                    buttons[i].setLocation(200, 200);
                    buttons[i].setSize(100, 30);
                    buttons[i].setPreferredSize(new Dimension(400, 30));
                    buttons[i].setText(rs.getString(1));
                    buttons[i].addActionListener(this);
                    panelSearchFrame.add(buttons[i]);
                    offsetX += 50;
                    offsetY += 50;
                    i++;
                }
            }
            add(panelSearchFrame);
            setSize(561,534);
            setResizable(true);
            setVisible(true);
            setTitle("frame");
        }catch(Exception e){
            System.out.println("OOPS!!! "+ e.getMessage());
        }
    }
    
    public static void main(String args[]){
        String searchTerm = "the";
        URLClass urlClassObject = new URLClass();
        urlClassObject.searchDB(searchTerm);
    }
    
}
