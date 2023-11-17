package team1project;

import java.io.File;

public class PDFBaseDecorator implements EvaluatorObserver
{
    private final PDFPrinter pdfPrinter;
   
    public PDFBaseDecorator() {       
        this.pdfPrinter = new PDFPrinter();
    }

    protected PDFPrinter getPDFManager() {
        return pdfPrinter;
    }

    public boolean update(Evaluator evaluator){
        String submissionPath = evaluator.getSubmission().getSubmissionPath();
        String studentID = evaluator.getSubmission().getStudentID();
        String savePath = submissionPath + File.separator + studentID + ".pdf";
        getPDFManager().saveAndClose(savePath);         
        return true;
    }
}
