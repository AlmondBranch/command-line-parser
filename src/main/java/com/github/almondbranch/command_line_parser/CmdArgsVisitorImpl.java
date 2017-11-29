package com.github.almondbranch.command_line_parser;

import java.util.*;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.*;

public class CmdArgsVisitorImpl extends AbstractParseTreeVisitor<List<String>> implements CmdArgsVisitor<List<String>> {
	private List<String> _entries;	
	private final List<Character> QUOTE_CHARS = Arrays.asList(new Character[] { '\'', '"' });

	public List<String> visitInput(CmdArgsParser.InputContext ctx) {
 		_entries = new ArrayList<String>();
		visitChildren(ctx);
		return _entries;
	}

	public List<String> visitEntry(CmdArgsParser.EntryContext ctx) {
		String entryText = ctx.getText();
		if (QUOTE_CHARS.contains(entryText.charAt(0)))
			entryText = entryText.substring(1, entryText.length() - 1);
		else
			entryText = entryText.replaceAll("\\\\ ", " ");
		
		_entries.add(entryText);
		return null;
	}
}
