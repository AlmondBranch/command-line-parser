grammar CmdArgs;

input     : entry (delimiter? entry)* EOF;
delimiter : SPACE+;
entry     : (REGULAR_ARG | SINGLE_QUOTED_ARG | DOUBLE_QUOTED_ARG);

SPACE             : ' ';
REGULAR_ARG       : ('\\ ' | ~('\''|'"'|' '))+;
DOUBLE_QUOTED_ARG : '"' ( '\\"' | ~('"') )* '"';

/*For some reason using ~('\'')* instead of .* causes the match to be greedy when escaped single quotes are included in the interior. This is the desired behavior*/
SINGLE_QUOTED_ARG : '\'' ~('\'')* '\'';
