# command-line-parser

Resolves the issue of calling a main(String[] args) method directly using an unparsed string containing the unparsed arguments. This avoids the need to call the command line version of an executable in a separate process and instead call the main method directly from your code.

For example, suppose that you wanted to run "antlr4 TestGrammer.g4 -o visitor". Since antlr4 is written in Java you would hope that from your Java code you could just reference the JAR and call the main method directly. Unfortunately the signature of the main method is main(String[] args) so you can't directly pass it "TestGrammar.g4 -o visitor".

This project will convert your arguments string into a collection that should be the same as what the command line would give you. You only need to call CmdArgsReader.Read("TestGrammar.g4 -o visitor") and you will get back a collection of ["TestGrammar.g4", "-o", "visitor"]. You can then pass this to the main method and not have to worry about calling out to the command line.

Testing on the parser has been relatively thorough and handles a number of parsing cases beyond simply splitting on spaces. It handles espaced spaces, double quoted regions with escaped values inside, and single quoted regions (it seems by design these are meant not to support escaped values).
