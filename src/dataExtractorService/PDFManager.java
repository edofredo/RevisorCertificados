/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataExtractorService;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author CRA
 */
public class PDFManager {
    
     private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public final double MM_TO_UNITS = 1/(10*2.54f)*72;
    
    public PDFManager() {

    }

    public String toText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);
        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0
        
        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        Text = pdfStripper.getText(pdDoc);
        return Text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PDDocument getPdDoc() {
        return pdDoc;
    }

    public String getTextUsingPositionsUsingPdf(String pdfLocation, int pageNumber, double x, double y, double width,
                double height) throws IOException {
            String extractedText = "";
            // PDDocument Creates an empty PDF document. You need to add at least
            // one page for the document to be valid.
            // Using load method we can load a PDF document
            PDDocument document = null;
            PDPage page = null;
            try {
                if (pdfLocation.endsWith(".pdf")) {
                    document = PDDocument.load(new File(pdfLocation));
                    int getDocumentPageCount = document.getNumberOfPages();
                    System.out.println(getDocumentPageCount);

                    // Get specific page. THe parameter is pageindex which starts with // 0. If we need to
                    // access the first page then // the pageIdex is 0 PDPage
                    if (getDocumentPageCount > 0) {
                        page = document.getPage(pageNumber + 1);
                    } else if (getDocumentPageCount == 0) {
                        page = document.getPage(0);
                    }
                    // To create a rectangle by passing the x axis, y axis, width and height 
                    Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
                    String regionName = "region1";

                    // Strip the text from PDF using PDFTextStripper Area with the
                    // help of Rectangle and named need to given for the rectangle
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    stripper.addRegion(regionName, rect);
                    stripper.extractRegions(page);
                    System.out.println("Region is " + stripper.getTextForRegion("region1"));
                    extractedText = stripper.getTextForRegion("region1");
                } else {
                    System.out.println("No data return");
                }
            } catch (IOException e) {
                System.out.println("The file  not found" + "");
            } finally {
                document.close();
            }
            // Return the extracted text and this can be used for assertion
            return extractedText;
        }
    
    
}
