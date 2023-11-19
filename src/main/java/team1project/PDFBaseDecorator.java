package team1project;

import java.io.File;

/**
 * The {@code PDFBaseDecorator} class is an implementation of the {@code EvaluatorObserver} interface
 * that provides basic functionality for creating and saving PDF documents using a {@code PDFPrinter}.
 * It serves as a base class for more specialized decorators.
 */

public class PDFBaseDecorator implements EvaluatorObserver
{
    private final PDFPrinter pdfPrinter;
   
    /**
     * Constructs a new {@code PDFBaseDecorator} with a default {@code PDFPrinter}.
     */
    public PDFBaseDecorator() {       
        this.pdfPrinter = new PDFPrinter();
    }

    
    /**
     * Gets the underlying {@code PDFPrinter} instance.
     *
     * @return The {@code PDFPrinter} instance.
     */
    protected PDFPrinter getPDFManager() {
        return pdfPrinter;
    }

    /**
     * Updates the PDF document based on the given evaluator's submission information.
     *
     * @param evaluator The evaluator containing submission information.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    public boolean update(Evaluator evaluator){
        String submissionPath = evaluator.getSubmission().getSubmissionPath();
        String studentID = evaluator.getSubmission().getStudentID();
        String savePath = submissionPath + File.separator + studentID + ".pdf";
        getPDFManager().saveAndClose(savePath);         
        return true;
    }
}
