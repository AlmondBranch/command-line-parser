grammar CmdArgs;

input : entry (SPACE* entry)* SPACE*;
entry : (REGULAR_ARG | SINGLE_QUOTED_ARG | DOUBLE_QUOTED_ARG);

SPACE             : ' ';
REGULAR_ARG       : ('\\ ' | ~('\''|'"'|' '))+;
DOUBLE_QUOTED_ARG : '"' ( '\\"' | ~('"') )* '"';
SINGLE_QUOTED_ARG : '\'' ( '\\\'' | ~('\'') )* '\'';

