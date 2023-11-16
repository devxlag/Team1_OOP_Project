package team1project;



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
        getPDFManager().saveAndClose();         
        return true;
    }
}
