package team1project;

/**
 * The CleanCodeChecker class provides a simple utility for checking the cleanliness of code.
 * It evaluates code based on various criteria such as indentation, syntax, method length,
 * naming conventions, and comments. The overall code cleanliness score is calculated,
 * and specific issues are counted for each criterion.
 */
public class CleanCodeChecker {

    /**
     * Checks the cleanliness of the given code and returns a cleanliness score.
     *
     * @param code The code to be evaluated for cleanliness.
     * @return The cleanliness score, ranging from 0 to 5.
     */
    public static double checkCode(String code) {

        double score = 5;

        // Check indentation
        score -= 0.5 * countIndentationIssues(code);

        // Check syntax 
        score -= 0.5 * countSyntaxIssues(code);

        // Check method length
        score -= 0.25 * countLongMethods(code);

        // Check naming
        score += 0.25 * countCamelCaseNames(code);

        // Check comments
        score += checkCommentScore(code);

        // Ensure the score is within the valid range [0, 5]
        return Math.min(5, score);
    }

    /**
     * Counts the number of lines with incorrect indentation in the given code.
     *
     * @param code The code to be evaluated.
     * @return The number of lines with incorrect indentation.
     */
    private static int countIndentationIssues(String code) {
        int issues = 0;
        String[] lines = code.split("\n");

        for(int i=0; i<lines.length; i++) {
          String line = lines[i];
          if(!line.matches("\t{0,1}\\s{0,2}")) {
            issues++;  
          }
      }

      return issues; 
    }

    /**
     * Counts the number of syntax issues in the given code.
     *
     * @param code The code to be evaluated.
     * @return The number of syntax issues found.
     */
    private static int countSyntaxIssues(String code) {
        int issues = 0;
        if(code.matches(".*\\}[\\n\\r\\s]*\\{.*")) {
            issues++;
        }
        return issues;
    }

    /**
     * Counts the number of methods with excessive length in the given code.
     *
     * @param code The code to be evaluated.
     * @return The number of methods with excessive length.
     */
    private static int countLongMethods(String code) {
        int count = 0;
        String[] methods = code.split("}");

        for(String method : methods) {
            if(method.split("\n").length > 20) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of words with incorrect camel case naming in the given code.
     *
     * @param code The code to be evaluated.
     * @return The number of words with incorrect camel case naming.
     */
    private static int countCamelCaseNames(String code) {
        int count = 0;
        String[] words = code.split("\\W+");

        for(String word : words) {
            if(word.matches("[a-z]+([A-Z][a-z]+)*")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of lines with comments in the given code.
     *
     * @param code The code to be evaluated.
     * @return The number of lines with comments.
     */
    private static int countCommentLines(String code) {
        int commentCount = 0;
    
        String[] lines = code.split("\n");
        for(String line : lines) {
            if(line.trim().startsWith("//") || line.trim().startsWith("/*")) {
                    commentCount++; 
            }
        }
    
        return commentCount;
    }

    /**
     * Checks the overall comment score of the given code.
     * Awards 0.5 points for every 10 lines with comments.
     *
     * @param code The code to be evaluated.
     * @return The overall comment score.
     */
    private static double checkCommentScore(String code) {
        double score = 0;
    
        // Award 0.5 points for every 10 lines with comments
        int commentLines = countCommentLines(code); 
        score += 0.5 * (commentLines / 10);

        return score;
    }
}
