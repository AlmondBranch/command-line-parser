package com.github.almondbranch.command_line_parser;

import java.util.*;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.*;

public class CmdArgsVisitorImpl extends AbstractParseTreeVisitor<List<String>> implements CmdArgsVisitor<List<String>> {
	private List<String> _matches;
	private List<String> _currentMatchPieces;

	private final List<Character> QUOTE_CHARS = Arrays.asList(new Character[] { '\'', '"' });

	public List<String> visitInput(CmdArgsParser.InputContext ctx) {
 		_matches = new ArrayList<String>();
		_currentMatchPieces = new ArrayList<String>();

		visitChildren(ctx);

		if (_currentMatchPieces.size() > 0)
			_matches.add(String.join("", _currentMatchPieces));

		return _matches;
	}

	public List<String> visitDelimiter(CmdArgsParser.DelimiterContext ctx) {
		_matches.add(String.join("", _currentMatchPieces));
		_currentMatchPieces.clear();
		return null;
	}

	public List<String> visitEntry(CmdArgsParser.EntryContext ctx) {
		String entryText = ctx.getText();
		if (QUOTE_CHARS.contains(entryText.charAt(0)))
			entryText = entryText.substring(1, entryText.length() - 1);
		else
			entryText = entryText.replaceAll("\\\\ ", " ");
		
		_currentMatchPieces.add(entryText);
		return null;
	}
}
