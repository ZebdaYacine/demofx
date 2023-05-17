package com.example.demofx.printer;

import com.example.demofx.model.DiagnosticModel;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;


public class DiagnosticTemplate {
    private  String FILE ;
    private PdfWriter writer;
    private AreaBreak aB ;

    public DiagnosticTemplate(String file, DiagnosticModel diagnosticModel) {
        try{
            this.FILE = file;
            writer = new PdfWriter(this.FILE);
            PdfDocument pdfDoc = new PdfDocument(writer);
            try (Document document = new Document(pdfDoc)) {
                PdfFont arabicFont = PdfFontFactory.createFont(FontProgramFactory.createFont(
                        "C:\\Windows\\Fonts\\arial.ttf"), PdfEncodings.IDENTITY_H);
                Paragraph paraM = new Paragraph(diagnosticModel.getMedicladiagnostic()).setFont(arabicFont);
        /*    Paragraph paraP = new Paragraph (diagnosticModel.getPsychologydiagnostic());
            paraP.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
            Paragraph paraI = new Paragraph (diagnosticModel.getInterviewdynamics());
            paraI.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);
            Paragraph paraC = new Paragraph (diagnosticModel.getConclusion());
            paraC.setBaseDirection(BaseDirection.RIGHT_TO_LEFT);*/

                document.add(paraM);
    /*        document.add(paraP);
            document.add(paraI);
            document.add(paraC);*/
                document.close();
            }
            System.out.println("Pdf created go to "+file);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
