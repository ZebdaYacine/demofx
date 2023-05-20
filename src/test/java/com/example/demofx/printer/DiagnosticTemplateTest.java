package com.example.demofx.printer;

import com.example.demofx.model.DiagnosticModel;
import org.junit.jupiter.api.Test;

class DiagnosticTemplateTest {
    DiagnosticModel diagnosticModelCurrent = new DiagnosticModel();

    final String str= "من بيئة فقيرة في قرية \"تاخمارت\" الصغيرة بتيارت، بدأت قصة هذا الشاب\n" +
            "لتحقيق حلمه بتخصص الطب، وكان له ذلك";
    public DiagnosticTemplateTest() {
        diagnosticModelCurrent.setConclusion(str);
        diagnosticModelCurrent.setMedicladiagnostic(str);
        diagnosticModelCurrent.setPsychologydiagnostic(str);
        new DiagnosticTemplate("C:\\Users\\ZedYacine\\IdeaProjects\\demofx\\diagonstics\\test.pdf",
                diagnosticModelCurrent);
    }

    @Test
    void name() {
        new DiagnosticTemplateTest();
    }
}