command-line-parser
===================

[![Build Status](https://travis-ci.org/AlmondBranch/command-line-parser.svg?branch=master)](https://travis-ci.org/AlmondBranch/command-line-parser)
[![Coverage Status](https://coveralls.io/repos/github/AlmondBranch/command-line-parser/badge.svg?branch=master)](https://coveralls.io/github/AlmondBranch/command-line-parser?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.almondbranch/command-line-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.almondbranch/command-line-parser)

Parses a String containing command line arguments into a collection of the individual formatted arguments that should be passed to an executables main method. Without this package there isn't a clear and easy way to access the parser that a command line would use to break up the command line arguments into the final array which a program's main method takes as an argument.

```java
//The old way
public void CallExe()
{
  Runtime rt = Runtime.getRuntime();
  Process pr = rt.exec("exeName arg1 arg2 arg3");

  //Potentially more code here to handle errors, getting the process's return value if it has one, etc
  //...
}

//The new way using the command-line-parser package
public void CallExe()
{
  //TO DO - Make sure to reference the JAR file for the exe from your build script as a dependency

  String[] args = CmdArgsReader.read("arg1 arg2 arg3");
  int returnValue = exeMainClass.main(args);
}
```

## Parser Details
The unit tests serve as a good documentation for the cases that this parser covers. These tests were derived by seeing how the bash shell parses and formats the arguments that it sends to a program. It is thought that the current parser either exactly or closely matches the behavior that a shell or command prompt would have.

## Example Usage
The [lein-antlr plugin](https://github.com/AlmondBranch/lein-antlr) uses this code to directly call a main method with a single string containing all command line arguments rather than resorting to calling the program in a separate process.
