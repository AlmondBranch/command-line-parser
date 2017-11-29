package com.github.almondbranch.command_line_parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.*;

public class CmdArgsReader {
    public static List<String> read(String cmdLineArgs) throws Exception, RecognitionException {
        CmdArgsLexer lexer = new CmdArgsLexer(CharStreams.fromString(cmdLineArgs.trim()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CmdArgsParser parser = new CmdArgsParser(tokens);

	//Make sure to collect all errors encountered while creating the parse tree
	List<String> syntaxExceptions = new ArrayList<String>();
	parser.addErrorListener(new TrackSyntaxErrorListener(syntaxExceptions));

	//This might be an Antlr bug, but sometimes when notifying error listeners an exception
	//is thrown so watch out for that...
	ParseTree parseTree = null;	
	try { parseTree = parser.input(); }
	catch (Exception ex) { syntaxExceptions.add(ex.getMessage()); }

	if (syntaxExceptions.size() > 0)
	{
		//A returned null indicates that the parse failed. Eventually the error information should be returned to the caller for it to see.
		return null;
	}
	else
	{
		CmdArgsVisitorImpl visitor = new CmdArgsVisitorImpl();
		return visitor.visit(parseTree);
	}
    }

    private static class TrackSyntaxErrorListener extends BaseErrorListener {
	private final List<String> _syntaxExceptions;

	public TrackSyntaxErrorListener(List<String> syntaxExceptions)
	{
		_syntaxExceptions = syntaxExceptions;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
							int line, int charPositionInLine,
							String msg, RecognitionException e)
	{
		_syntaxExceptions.add("line " + line + ":" + charPositionInLine + " " + msg);
	}
    }
}
