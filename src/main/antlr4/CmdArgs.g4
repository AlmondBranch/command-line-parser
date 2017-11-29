grammar CmdArgs;

input     : entry (delimiter entry)* ;
delimiter : SPACE+;
entry     : (REGULAR_ARG | SINGLE_QUOTED_ARG | DOUBLE_QUOTED_ARG);

SPACE             : ' ';
REGULAR_ARG       : ('\\ ' | ~('\''|'"'|' '))+;
DOUBLE_QUOTED_ARG : '"' ( '\\"' | ~('"') )* '"';
SINGLE_QUOTED_ARG : '\'' .* '\'';

