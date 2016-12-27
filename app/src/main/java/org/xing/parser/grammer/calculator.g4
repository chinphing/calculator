/*
BSD License

Copyright (c) 2013, Tom Everett
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. Neither the name of Tom Everett nor the names of its contributors
   may be used to endorse or promote products derived from this software
   without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

grammar calculator;

expression
   : multiplyingExpression ((PLUS | MINUS) multiplyingExpression)*
   ;

multiplyingExpression
   : powExpression ((TIMES | DIV) powExpression)*
   ;

powExpression
   : atom (POW atom)*
   | chinaPowExpression
   ;
   
chinaPowExpression 
   : atom ( DE  ((atom CIFANG) | PINGFANG | LIFANG | KAIFANG))* 
   ;

atom
   : number
   | number FRAC number 
   | LPAREN expression RPAREN
   | KUOHAO expression KUOHAO
   | func
   ;

func
   : funcname atom
   | number funcnameEx atom
   ;

funcname
   : COS
   | TAN
   | SIN
   | ACOS
   | ATAN
   | ASIN
   | LOG
   | LN
   | LG
   | GENHAO
   | DUISHU
   ;
   
funcnameEx
   : DUISHU
   | GENHAO
   ;

number
   : MINUS? DIGIT + (POINT DIGIT +)?
   ;

COS
   : 'cos' | '余弦'
   ;


SIN
   : 'sin' | '正弦'
   ;


TAN
   : 'tan' | '正切'
   ;


ACOS
   : 'acos' | '反余弦'
   ;


ASIN
   : 'asin' | '反正弦'
   ;


ATAN
   : 'atan' | '反正切'
   ;


LN
   : 'ln'
   ;


LOG
   : 'log'
   ;
   
LG
   : 'lg'
   ;
   
DUISHU
   : '对数'
   ;
   
GENHAO
   : '根号'
   ;
   
KUOHAO
   : '|' | '括号'
   ;

LPAREN
   : '('
   ;


RPAREN
   : ')'
   ;


PLUS
   : '+' | '加'('上')?
   ;


MINUS
   : '-' | '负' | '减'('去')?
   ;


TIMES
   : '*' | '×' | '乘'('以')?
   ;


DIV
   : '/' | '÷' | '除'('以')?
   ;
   
FRAC
   : '分之'
   ;

PINGFANG
   : '平方'
   ;
   
LIFANG
   : '立方'
   ;

CIFANG
   : '次方'
   ;
   
KAIFANG
   : '开方'
   ;

DE
   : '的'
   ;

POINT
   : '.' | '点'
   ;

E
   : 'e' | 'E'
   ;


POW
   : '^'
   ;


LETTER
   : ('a' .. 'z') | ('A' .. 'Z')
   ;


DIGIT
   : ('0' .. '9') | [零一二三四五六七八九十百千万亿]
   ;


WS
   : [ \r\n\t] + -> channel (HIDDEN)
   ;
