package html.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class HtmlPreprocessor {

    public String getBodyFromHtmlPage(String page) {
        Document doc;
        String content = null;
        try {
            doc = Jsoup.connect(page).get();
            Elements title = doc.select("#sTitle");
            StringBuilder sb = new StringBuilder();
            for (Element element : title) {
                sb.append(element.text() + ".\n");
            }
            Elements elements = doc.select("#spBody");
            for (Element element : elements) {
                sb.append(element.text());
            }
            content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public void toFile(String fileName, String content) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("greek_texts/" + fileName + ".txt"));
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}