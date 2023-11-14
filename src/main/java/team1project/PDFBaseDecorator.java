package team1project;



public class PDFBaseDecorator implements EvaluatorObserver
{
    private final PDFManager PDFManager;
   
    public PDFBaseDecorator( PDFManager pdfManager) {       
        this.PDFManager = pdfManager;
    }

    protected PDFManager getPDFManager() {
        return PDFManager;
    }

    public boolean update(Submission submission){
        getPDFManager().saveAndClose();         
        return true;
    }
}
