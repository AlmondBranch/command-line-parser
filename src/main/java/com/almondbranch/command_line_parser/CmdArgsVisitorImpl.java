package com.almondbranch.command_line_parser;

import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.*;

public class CmdArgsVisitorImpl extends AbstractParseTreeVisitor<List<String>> implements CmdArgsVisitor<List<String>> {
	private List<String> _entries;	

	public List<String> visitInput(CmdArgsParser.InputContext ctx) {
 		_entries = new ArrayList<String>();
		visitChildren(ctx);
		return _entries;
	}

	public List<String> visitEntry(CmdArgsParser.EntryContext ctx) {
		_entries.add(ctx.getText());
		return null;
	}
}
