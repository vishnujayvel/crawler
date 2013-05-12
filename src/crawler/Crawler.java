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
 
 

class PageProcess implements Runnable{
    String URL;
    static int count = 1;
    
    PageProcess(String s){
        URL = s;
    }
    
    @Override
    public void run(){
        try{
        processPage();
        }catch(SQLException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    void processPage() throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from record where URL = '"+URL+"'";
		ResultSet rs = db.runSql(sql);
		if(rs.next()){
 
		}else{
			//store the URL to database to avoid parsing again
			sql = "INSERT INTO  `Crawler`.`record` " + "(`URL`,`Content`) VALUES " + "(?,?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        Document doc = Jsoup.connect("http://www.udel.edu/").timeout(0).get();
                        Element content = doc.select("html").first();
			stmt.setString(1,URL);
                        stmt.setString(2,content.html());
			stmt.execute();
                        if(content.html().length()>0){
                            String txt = content.html();
                            GuiDFrame.progresswindow.setText(txt);
                        }
                       // else
                            
                        System.out.print("hello");
			//get useful information
			
                        //System.out.println(doc.text());
                        //Document document = Jsoup.connect("http://www.qualcomm.com/innovation").get();
                        //if( count == 1){
                        //System.out.println(content.html()); 
                        //count++;
                        //}
// ...
			if(doc.text().contains("the")){
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("udel.edu")){
                                    Thread t = new Thread(new PageProcess(link.attr("abs:href")));
                                    t.start();
                                    try{
                                    //t.sleep(100);
                                    }catch(Exception e){
                                        System.out.println(e);
                                    }
                                }
			}
		}
	}
}
public class Crawler{
	public static DB db = new DB();
 
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
                Thread t = new Thread(new PageProcess("http://www.udel.edu/"));
		t.start();
	}	
}