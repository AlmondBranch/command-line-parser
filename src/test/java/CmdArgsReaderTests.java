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

	@Test
	public void Reads_Space_As_Part_Of_Double_Quoted_Argument() throws Exception {
		List<String> args = CmdArgsReader.read("\"arg1 arg2\"");
		assertThat(args, is(Arrays.asList("\"arg1 arg2\"")));
	}

	@Test
	public void Reads_Space_As_Part_Of_Single_Quoted_Argument() throws Exception {
		List<String> args = CmdArgsReader.read("'arg1 arg2'");
		assertThat(args, is(Arrays.asList("'arg1 arg2'")));
	}

        @Test
	public void Reads_Space_As_Part_Of_Argument_When_It_Is_Escaped_With_A_Backslash() throws Exception {
		List<String> args = CmdArgsReader.read("arg1\\ arg2");
		assertThat(args, is(Arrays.asList("arg1\\ arg2")));
	}

        @Test
	public void Reads_Single_Quote_Inside_Double_Quoted_Argument() throws Exception {
		List<String> args = CmdArgsReader.read("\"'arg\"");
		assertThat(args, is(Arrays.asList("\"'arg\"")));
	}
 
        @Test
	public void Reads_Double_Quote_Inside_Single_Quoted_Argument() throws Exception {
		List<String> args = CmdArgsReader.read("'\"arg'");
		assertThat(args, is(Arrays.asList("'\"arg'")));
	}
}

