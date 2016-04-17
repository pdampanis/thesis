/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author pdampanis
 */
public class PdfPreprocessor {

    public void pdfToText(File file) {
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        try {
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            int totalPages = pdDoc.getPages().getCount();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(totalPages);
            String parsedText = pdfStripper.getText(pdDoc);
            pdDoc.close();
            try {
                String fileName = parsedText.substring(0, 10);
                BufferedWriter out = new BufferedWriter(new FileWriter("greek_texts/" + fileName + ".txt"));
                out.write(parsedText); 
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
