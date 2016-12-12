/* ===========================================================================
 * Copyright (C) 2016 CapsicoHealth Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

grammar TildaSQL2;


main
 : expr EOF
 ;
 
expr
 : literal
 | column
 | unary_operator expr
 | expr concat='||' expr
 | expr arithmetic_op_mul expr
 | expr arithmetic_op_add expr
 | expr comparators1=( '<' | '<=' | '>' | '>=' ) expr
 | expr comparators2=( '=' | '==' | '!=' | '<>' ) expr
 | expr like=bin_like expr
 | expr K_AND expr
 | expr K_OR expr
 | func=func_name '(' (expr ( ',' expr )*)? ')'
 | '(' expr ')'
 | expr isnull=isnull_op
 | expr between=between_op expr K_AND expr
 | expr K_NOT? K_IN '(' expr ( ',' expr )* ')'
 | K_CASE ( K_WHEN expr K_THEN expr )+ ( K_ELSE expr )? K_END                   
;

unary_operator
 : '-'
 | '+'
 | K_NOT
;

func_name
 : K_LEN
 | K_DAYS_BETWEEN
 ;

bin_like: (K_NOT)? K_LIKE;
arithmetic_op_add: '+' | K_MINUS;
arithmetic_op_mul: '*' | K_DIV;
isnull_op: K_IS K_NOT? K_NULL (K_OR K_EMPTY)?;
between_op: K_NOT? K_BETWEEN;

literal
 : numeric_literal     # ValueNumericLiteral
 | timestamp_literal   # ValueTimestampLiteral
 | string_literal      # ValueStringLiteral
 | bind_parameter      # ValueBindParam
 ; 

numeric_literal
 : NUMERIC_LITERAL
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )?
 ;


timestamp_literal // ISO: '2011-12-03T10:15:30+01:00'.
 : TIMESTAMP_LITERAL
 | CURRENT_TIMESTAMP
 | TIMESTAMP_YESTERDAY LAST?
 | TIMESTAMP_TODAY LAST?
 | TIMESTAMP_TOMORROW LAST?
 ;

TIMESTAMP_LITERAL        : '\'' YEAR_LITERAL '-' MONTH_LITERAL '-' DAY_LITERAL ('T' HOUR_LITERAL_24 ':' MINUTE_LITERAL (':' SECOND_LITERAL (PLUS_MINUS HOUR_LITERAL_12 ':' MINUTE_LITERAL)?)?)? '\'' ;
CURRENT_TIMESTAMP        : C U R R E N T '_' T I M E S T A M P;
TIMESTAMP_YESTERDAY      : T I M E S T A M P '_' Y E S T E R D A Y;
TIMESTAMP_TODAY          : T I M E S T A M P '_' T O D A Y;
TIMESTAMP_TOMORROW       : T I M E S T A M P '_' T O M O R R O W;
LAST: L A S T;


string_literal
 : STRING_LITERAL
 ;
 
STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;
 
bind_parameter
 : BIND_PARAMETER
 ;
 
BIND_PARAMETER
 : '?{' IDENTIFIER '}'
 ;

column
 : ( IDENTIFIER '.' )? IDENTIFIER
 ;


K_AND : A N D;
K_BETWEEN : B E T W E E N;
K_IN : I N;
K_IS : I S;
K_LIKE : L I K E;
K_NOT : N O T;
K_NULL : N U L L;
K_EMPTY : E M P T Y;
K_OR : O R;
K_REGEXP : R E G E X P;
K_LT: '<';
K_LTE: '<=';
K_GT: '>';
K_GTE: '>=';
K_EQ: '=' '='?;
K_NEQ: '<>' | '!=';
K_DIV: '/';
K_MINUS: '-';
K_LEN: L E N;
K_DAYS_BETWEEN : D A Y S B E T W E E N;
K_CASE : C A S E;
K_WHEN : W H E N;
K_THEN : T H E N;
K_ELSE : E L S E;
K_END : E N D;


IDENTIFIER
 : [a-zA-Z_] [a-zA-Z_0-9]* 
 ;

 
PLUS_MINUS
 : '+'
 | '-'
 ;
 
YEAR_LITERAL // 1800-2199
// : ([0-9])|('2'[0-1]) DIGIT DIGIT
 : DIGIT DIGIT DIGIT DIGIT
 ;
 
MONTH_LITERAL
// : ('0'[1-9])|('1'[0-2])
 : DIGIT DIGIT
 ;
 
DAY_LITERAL
// : ('0'[1-9])|([1-2][0-9])|('3'[0-1]) 
 : DIGIT DIGIT
 ;
 
HOUR_LITERAL_24
// : ([0-1][0-9]) | ('2'[0-3])
 : DIGIT DIGIT
 ;
 
MINUTE_LITERAL
// : ([0-5][0-9])
 : DIGIT DIGIT
 ;
 
SECOND_LITERAL
// : ([0-5][0-9])
 : DIGIT DIGIT
 ;
 
HOUR_LITERAL_12
// : ('0'[0-9])|('1'[0-2])
 : DIGIT DIGIT
 ;

SPACES
 : [ \u000B\t\r\n] -> channel(HIDDEN)
 ;

UNEXPECTED_CHAR
 : .
 ;


fragment DIGIT : [0-9];
fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];