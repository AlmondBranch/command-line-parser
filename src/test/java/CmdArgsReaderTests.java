package com.github.almondbranch.command_line_parser;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

public class CmdArgsReaderTests {
	@Test
	public void Recognizes_Two_Simple_Arguments_Separated_By_A_Single_Space() throws Exception {
		String[] args = CmdArgsReader.read("arg1 arg2");
		assertThat(args, is(new String[] { "arg1", "arg2" }));
	}

	@Test
	public void Recognizes_Two_Simple_Arguments_Separated_By_A_Two_Spaces() throws Exception {
		String[] args = CmdArgsReader.read("arg1  arg2");
		assertThat(args, is(new String[] { "arg1", "arg2" }));
	}

	@Test
	public void Reads_Space_As_Part_Of_Double_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("\"arg1 arg2\"");
		assertThat(args, is(new String[] { "arg1 arg2" }));
	}

	@Test
	public void Reads_Space_As_Part_Of_Single_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("'arg1 arg2'");
		assertThat(args, is(new String[] { "arg1 arg2" }));
	}

        @Test
	public void Reads_Space_As_Part_Of_Argument_When_It_Is_Escaped_With_A_Backslash() throws Exception {
		String[] args = CmdArgsReader.read("arg1\\ arg2");
		assertThat(args, is(new String[] { "arg1 arg2" }));
	}

        @Test
	public void Reads_Single_Quote_Inside_Double_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("\"'arg\"");
		assertThat(args, is(new String[] { "'arg" }));
	}
 
        @Test
	public void Reads_Double_Quote_Inside_Single_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("'\"arg'");
		assertThat(args, is(new String[] { "\"arg" }));
	}

	@Test
	public void Reads_Escaped_Double_Quote_Inside_Double_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("\"\\\"arg\"");
		assertThat(args, is(new String[] { "\"arg" }));
	}

	@Test
	public void Leaves_Escaped_Space_Inside_Double_Quoted_Argument_Alone() throws Exception {
		String[] args = CmdArgsReader.read("\"part1\\ part2\"");
		assertThat(args, is(new String[] { "part1\\ part2" }));
	}

	@Test
	public void Escaped_Single_Quote_Is_Not_Allowed_In_Singled_Quoted_Argument() throws Exception {
		String[] args = CmdArgsReader.read("'\\'arg'");
		assertThat(args, is(nullValue()));
	}

	@Test
	public void Double_Quoted_Scope_Inside_Argument_Is_Allowed() throws Exception {
		String[] args = CmdArgsReader.read("prefix\"double_quoted_scope\"");
		assertThat(args, is(new String[] { "prefixdouble_quoted_scope" }));
	}

	@Test
	public void Single_Quoted_Scope_Inside_Argument_Is_Allowed() throws Exception {
		String[] args = CmdArgsReader.read("prefix'single_quoted_scope'");
		assertThat(args, is(new String[] { "prefixsingle_quoted_scope" }));
	}

	@Test
	public void One_Double_Quote_Inside_Argument_Is_Not_Matched() throws Exception {
		String[] args = CmdArgsReader.read("ar\"g");
		assertThat(args, is(nullValue()));
	}

	@Test
	public void One_Single_Quote_Inside_Argument_Is_Not_Matched() throws Exception {
		String[] args = CmdArgsReader.read("ar'g");
		assertThat(args, is(nullValue()));
	}

	@Test
	public void Empty_String_Returns_An_Empty_List() throws Exception {
		String[] args = CmdArgsReader.read("");
		assertEquals(0, args.length);
	}

        @Test
        public void Test_That_Constructor_Works_To_Make_Code_Coverage_Tool_Happy() {
		CmdArgsReader cmdArgsReader = new CmdArgsReader();
        }
}


