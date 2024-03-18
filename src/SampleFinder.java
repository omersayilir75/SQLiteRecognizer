import gen.SQLiteLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SampleFinder {
    static HashSet<TokenTypePair> poisonedPairs = new HashSet<>();
    static Hashtable<Integer, String> tokenInstances = new Hashtable<>();
    static AtomicInteger noChecked = new AtomicInteger(0);
    static Hashtable<Integer, TokenNeighbours> tokenNeighboursHashtable = new Hashtable<>();
    static HashSet<String> pickedFiles = new HashSet<>();
    static FileWriter log;
    static FileWriter csv;

    public static void main(String[] args) throws IOException {
        log = new FileWriter("log_old.txt");
        csv = new FileWriter("details.csv");

        // populate tokenNeighboursHashtable
        PPCalculator.calculatePoisonedPairs(poisonedPairs, tokenInstances, tokenNeighboursHashtable);

        String pathName = "C:\\Users\\omer_\\Desktop\\gensamples\\positive\\bcgensamples";
        try (Stream<Path> paths = Files.walk(Paths.get(pathName))) {
            paths.filter(p -> p.toFile().isFile())
                    .sorted(Comparator.comparing((Path p) -> p.toFile().length()).thenComparing(Path::toString))
                    .forEachOrdered(SampleFinder::checkFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String file : pickedFiles) {
            log.write(file + '\n');
        }

        log.close();
        csv.close();
    }

    private static void checkFile(Path directory) {

        BufferedReader reader = null;
        File program = directory.toFile();

        if (program.isFile()) {  //walk also goes through dirs...
            try {
                Hashtable<Integer, TokenNeighbours> tokenNeighboursCurrentFile = new Hashtable<>();
                String programPath = program.getAbsolutePath();
                reader = new BufferedReader(new FileReader(programPath));
                CharStream input = CharStreams.fromReader(reader);
                SQLiteLexer lexer = new SQLiteLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                tokens.fill();

                // go through tokens in tokenstream
                for (int i = 0; i < tokens.size(); i++) {
                    Token token = tokens.get(i);
                    Token prevToken = i > 0 ? tokens.get(i - 1) : null;
                    Token nextToken = i < tokens.size() - 1 ? tokens.get(i + 1) : null;

                    int tokenType = token.getType();

                    // populate hashtable for current file
                    if (tokenType != -1) {
                        int prevType = prevToken != null ? prevToken.getType() : -2; // -1 reserved for EOF
                        int nextType = nextToken != null ? nextToken.getType() : -2;

                        TokenNeighbours neighbours = tokenNeighboursCurrentFile.getOrDefault(tokenType, null);

                        if (neighbours == null) neighbours = new TokenNeighbours();
                        if (prevType != -2 && prevType != -1) neighbours.precede.add(prevType);
                        if (nextType != -2 && nextType != -1) neighbours.follow.add(nextType);
                        tokenNeighboursCurrentFile.put(tokenType, neighbours);
                    }
                }
                // check and update tokenNeighboursCurrentFile
                for (int i : tokenNeighboursCurrentFile.keySet()) {
                    TokenNeighbours neighbours = tokenNeighboursCurrentFile.getOrDefault(i, null);
                    if (neighbours != null) {
                        TokenNeighbours allNeighboursOfTokenType = tokenNeighboursHashtable.getOrDefault(i, null);
                        if (allNeighboursOfTokenType != null) {

                            boolean removedPreceding = false;
                            boolean removedFollowing = false;
                            List<Integer> tokensRemovedPrecede = new LinkedList<>();
                            List<Integer> tokensRemovedFollow = new LinkedList<>();


                            for (int j : neighbours.precede) {
                                removedPreceding = allNeighboursOfTokenType.precede.remove(j);
                                if (removedPreceding) {
                                    tokensRemovedPrecede.add(j);
                                }
                            }
                            for (int j : neighbours.follow) {
                                removedFollowing = allNeighboursOfTokenType.follow.remove(j);
                                if (removedFollowing) {
                                    tokensRemovedFollow.add(j);
                                }
                            }


                            if (!tokensRemovedPrecede.isEmpty() || !tokensRemovedFollow.isEmpty()) {
                                csv.write(program.getPath() + ';' + i + ';' + tokensRemovedPrecede + ';' + tokensRemovedFollow + '\n');
                            }

                            if (removedFollowing || removedPreceding)
                                pickedFiles.add(programPath); // only add to log if something changed

                            if (allNeighboursOfTokenType.follow.isEmpty() && allNeighboursOfTokenType.precede.isEmpty()) {
                                tokenNeighboursHashtable.remove(i); // remove if empty
                            }

                        }
                    }

                }
                noChecked.incrementAndGet();
                if (noChecked.get() % 1000 == 0) {
                    System.out.println("Checked " + noChecked.get() + " files.");
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
