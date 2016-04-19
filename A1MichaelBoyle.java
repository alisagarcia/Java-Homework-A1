/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*git version*/
package a1michaelboyle;

import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 *
 * @author JMB
 */
public class A1MichaelBoyle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String eventText; //variable to store the raw event text
        
        //get the 3rd face-off in the game
        eventText = getNthEventByType(3, "FAC", "http://www.nhl.com/scores/htmlreports/20132014/PL020026.HTM");
        System.out.println(eventText);

//        //get the 5th face-off in the game
//        eventText = getNthEventByType(5, "FAC", "http://www.nhl.com/scores/htmlreports/20132014/PL020026.HTM");
//        System.out.println(eventText);
        //Task 2
        int beginIndex = (eventText.indexOf("\">") + 2);
        int endIndex = eventText.indexOf("</td>");
        String eventTextNoHTML = eventText.substring(beginIndex, endIndex);
        System.out.println("The value of eventTextNoHTML is: " + eventTextNoHTML);
        
        //Task 3 
         char[] eventTextNoHTMLCharArray = eventTextNoHTML.toCharArray();
        System.out.println("The length of the array eventTextNoHTMLCharArray is: " + eventTextNoHTMLCharArray.length);
        System.out.println("The second character in eventTextNoHTMLCharArray is: " + eventTextNoHTMLCharArray[1]);
        //TODO: your logic here
                        
    }
    
    /**
     * 
     * @param n
     * @param eventType
     * @param urlText
     * @return 
     * 
     * This code has been modified from 
     * http://stackoverflow.com/questions/238547/how-do-you-programmatically-download-a-webpage-in-java
     * 
     */
    public static String getNthEventByType(int n, String eventType, String urlText){
        
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line = "not found"; //initialize result line to "not found"
        int eventCount = 0;
       
        try {
            url = new URL(urlText);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while (eventCount < n && (line = br.readLine()) != null) {
                if(line.contains(eventType)) {                    
                    //we know the next line is the face-off details
                    line = br.readLine();
                    eventCount += 1;
                }
            }
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return line;
    }
}

