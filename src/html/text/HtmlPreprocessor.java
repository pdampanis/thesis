package html.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Panagiotis Dampanis AM: 070095
 */
public class HtmlPreprocessor {

    public String getBodyFromHtmlPage(String page) {
        Document doc;
        String body = null;
        try {
            doc = Jsoup.connect(page).get();
            body = doc.body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    public void toFile(String filename, String content) {
        try {
            File file = new File(filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}