package com.almondbranch.command_line_parser;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

public class CmdArgsReaderTests {
	@Test
	public void Separates_Two_Simple_Arguments() throws Exception {
		List<String> args = CmdArgsReader.read("arg1 arg2");
		assertThat(args, is(Arrays.asList("arg1", "arg2")));
	}
}


