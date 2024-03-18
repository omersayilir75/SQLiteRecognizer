import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.Recognizer;

public class CustomErrorListener extends BaseErrorListener {
    private int syntaxErrors = 0;
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e)  {
        // we only care if a syntax error occurs or not
        syntaxErrors++;
    }

    public int getSyntaxErrors(){
        return this.syntaxErrors;
    }
}


