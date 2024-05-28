# SQLiteRecognizer

This repository contains the code for a recognizer and a negative sample generator for the language SQLite.




Both tools rely on an ANTLR parser, the parser found in  `src/gen` was generated with the SQLite grammar found in the files `grammar/SQLiteLexer.g4` and `grammar/SQLiteParser.g4`.

The negative sample generator is based on the word mutation method described by Raselimo, Taljaard and Fischer in their paper _Breaking parsers: mutation-based generation of programs with guaranteed syntax errors_ [[1]](#1).


<a id="1">[1]</a>
Moeketsi Raselimo, Jan Taljaard, and Bernd Fischer. 2019. Breaking parsers: mutation-based generation of programs with guaranteed syntax errors. In Proceedings of the 12th ACM SIGPLAN International Conference on Software Language Engineering (SLE 2019). Association for Computing Machinery, New York, NY, USA, 83–87. https://doi.org/10.1145/3357766.3359542
