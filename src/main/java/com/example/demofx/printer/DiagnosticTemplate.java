package com.example.demofx.printer;

import com.example.demofx.model.DiagnosticModel;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.tagging.StandardRoles;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BaseDirection;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;


public class DiagnosticTemplate {
    private String FILE;
    private PdfWriter writer;

    public static LanguageProcessor al = new ArabicLigaturizer();


    public Paragraph initParagraph(String str, PdfFont f) {
        return new Paragraph(al.process(str))
                .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
    }

    public DiagnosticTemplate(String file, DiagnosticModel diagnosticModel) {
        try {
            this.FILE = file;
            writer = new PdfWriter(this.FILE);
            //writer = new PdfWriter("C:\\Users\\ZedYacine\\IdeaProjects\\demofx\\diagonstics\\test.pdf");
            PdfFont f = PdfFontFactory.createFont("C:\\Windows\\Fonts\\arial.ttf", PdfEncodings.IDENTITY_H);
            PdfFont f1 = PdfFontFactory.createFont("C:\\Users\\ZedYacine\\IdeaProjects\\demofx\\src\\main\\java\\com\\example\\demofx\\printer\\Janna LT Bold.ttf", PdfEncodings.IDENTITY_H);

            PdfDocument pdfDoc = new PdfDocument(writer);
            try (Document document = new Document(pdfDoc)) {
                ImageData data = ImageDataFactory.create("C:\\Users\\ZedYacine\\IdeaProjects\\demofx\\src\\main\\resources\\com\\example\\demofx\\images\\logopurple.png");
                Image img = new Image(data);
                img.setFixedPosition(50, 720);
                img.setHeight(70);
                img.setWidth(150);
                document.add(img);
                Paragraph title = new Paragraph(al.process("الملف الطبي "))
                        .setFont(f1)
                        .setFontSize(40f)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                title.getAccessibilityProperties().setRole(StandardRoles.H1);
                document.add(title);
                Paragraph patientN = new Paragraph(al.process(diagnosticModel.getDrFullName()+"إسم المريض :"))
                        .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                Paragraph patientB = new Paragraph(al.process("تاريخ الميلاد :"))
                        .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                Paragraph patientP = new Paragraph(al.process("رقم الهاتف: :"))
                        .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                Paragraph patientS = new Paragraph(al.process("الحالة المرضية :"))
                        .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);

                document.add(patientN);
                document.add(patientB);
                document.add(patientP);
                document.add(patientS);

                Paragraph titleM = new Paragraph(al.process("التشخيص الطبي: "))
                        .setFont(f1)
                        .setFontSize(20f)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                titleM.getAccessibilityProperties().setRole(StandardRoles.H1);
                document.add(titleM);
                Paragraph paraM = new Paragraph(al.process(diagnosticModel.getMedicladiagnostic()))
                        .setFont(f).setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                document.add(paraM);

                Paragraph titleP = new Paragraph(al.process("التشخيص النفسي: "))
                        .setFont(f1)
                        .setFontSize(20f)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                titleP.getAccessibilityProperties().setRole(StandardRoles.H1);
                Paragraph paraP = initParagraph(diagnosticModel.getPsychologydiagnostic(), f);
                document.add(titleP);
                document.add(paraP);

                Paragraph titleI = new Paragraph(al.process("دينامكية اللقاء: "))
                        .setFont(f1)
                        .setFontSize(20f)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                Paragraph paraI = initParagraph(diagnosticModel.getInterviewdynamics(), f);
                document.add(titleI);
                document.add(paraI);

                Paragraph titleC = new Paragraph(al.process("الحوصلة: "))
                        .setFont(f1)
                        .setFontSize(20f)
                        .setBaseDirection(BaseDirection.RIGHT_TO_LEFT).setTextAlignment(TextAlignment.RIGHT);
                document.add(titleC);
                Paragraph paraC = initParagraph(diagnosticModel.getConclusion(), f);
                document.add(paraC);
                document.close();
            }
            System.out.println("Pdf created go to " + file);
        } catch (Exception e) {
            System.out.println(e+" "+e.getCause());
        }

    }
}
