package com.github.almondbranch.command_line_parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

public class CmdArgsReader {
    public static String[] read(String cmdLineArgs) throws Exception, RecognitionException {
	String trimmedValue = cmdLineArgs.trim();
	if (trimmedValue == "")
		return new String[0];

        CmdArgsLexer lexer = new CmdArgsLexer(CharStreams.fromString(trimmedValue));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CmdArgsParser parser = new CmdArgsParser(tokens);

	//Just return null for now if the input couldn't be parsed. This should be reconsidered later...
	ParseTree parseTree = null;	
	try { parseTree = parser.input(); }
	catch (Exception ex) { return null; }

	CmdArgsVisitorImpl visitor = new CmdArgsVisitorImpl();
        return visitor.visit(parseTree).stream().toArray(String[]::new);
    }
}
