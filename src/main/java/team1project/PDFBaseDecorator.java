package team1project;



public class PDFBaseDecorator implements EvaluatorObserver
{
   
    private final EvaluatorObserver parent;
    private final PDFManager PDFManager;

    public PDFBaseDecorator(EvaluatorObserver parent, PDFManager pdfManager) {
        this.parent = parent;
        this.PDFManager = pdfManager;
    }

    protected EvaluatorObserver getParent() {
        return parent;
    }

    protected PDFManager getPDFManager() {
        return PDFManager;
    }


    public void update(Submission submission){

        getParent().update(submission);


    }


}
