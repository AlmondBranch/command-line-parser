package com.github.almondbranch.command_line_parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

public class CmdArgsReader {
    public static List<String> read(String cmdLineArgs) throws Exception {
        CmdArgsLexer lexer = new CmdArgsLexer(CharStreams.fromString(cmdLineArgs));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CmdArgsParser parser = new CmdArgsParser(tokens);
        ParseTree parseTree = parser.input();

	CmdArgsVisitorImpl visitor = new CmdArgsVisitorImpl();
	return visitor.visit(parseTree);
    }
}
