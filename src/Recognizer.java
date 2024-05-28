import gen.SQLiteLexer;
import gen.SQLiteParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class Recognizer {
    static AtomicInteger noPassed = new AtomicInteger(0);
    static AtomicInteger noFailed = new AtomicInteger(0);
    static AtomicInteger noProcessed = new AtomicInteger(0);
    static FileWriter log;

    public static void main(String[] args) throws IOException {
        // Folder path:
//        String pathName = "C:\\Users\\omer_\\Desktop\\gensamples\\negative\\SQLite\\wordmutation\\output";
        String pathName = "C:\\Users\\omer_\\Desktop\\gensamples\\positive\\SQLite\\queries";
        log = new FileWriter("log.txt");

        try (Stream<Path> paths = Files.walk(Paths.get(pathName))) {
            paths
                    .sorted(Comparator.comparing((Path p) -> p.toFile().length()).thenComparing(Path::toString))
                    .parallel()
                    .forEachOrdered(Recognizer::parseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number passed: " + noPassed.get());
        System.out.println("Number failed: " + noFailed.get());
        log.close();
    }

    private static void parseFile(Path directory) {
        BufferedReader reader = null;
        File program = directory.toFile();

        if (program.isFile()) {  //walk also goes through dirs...
            SQLiteLexer lexer;
            SQLiteParser parser;
            try {
                String programPath = program.getAbsolutePath();
                reader = new BufferedReader(new FileReader(programPath));
                CharStream input = CharStreams.fromReader(reader);
                lexer = new SQLiteLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                parser = new SQLiteParser(tokens);
                //doing this helps the GC clear the cache
                lexer.setInterpreter(new LexerATNSimulator(lexer, lexer.getATN(), lexer.getInterpreter().decisionToDFA, new PredictionContextCache()));
                parser.setInterpreter(new ParserATNSimulator(parser, parser.getATN(), parser.getInterpreter().decisionToDFA, new PredictionContextCache()));
                // prevent large error logs
                parser.removeErrorListeners();
                CustomErrorListener listener = new CustomErrorListener();
                parser.addErrorListener(listener);
                ParseTree tree = parser.parse();
                parser.getInterpreter().clearDFA();
                lexer.getInterpreter().clearDFA();
                if (listener.getSyntaxErrors() == 0) {
                    System.out.println(program.getName() + " PASS");
                    log.write(program.getPath() + "\n");
                    noPassed.incrementAndGet();
                } else {
                    System.out.println(program.getPath() + " FAIL");
                    log.write(program.getPath() + " FAIL\n");
                    noFailed.incrementAndGet();
                }
                noProcessed.incrementAndGet();
                if (noProcessed.get() % 1000 == 0) {
                    System.out.println("Processed " + noProcessed.get() + " files.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
