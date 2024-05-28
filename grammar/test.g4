grammar test;

r0 : 'MODULE' 'TestOberon0;' 'VAR' r31 'a:' 'ARRAY' '10' 'OF' 'INTEGER;' 'PROCEDURE' 'perm(k:' 'INTEGER);' r11 'x:' 'INTEGER;' 'BEGIN' 'IF' 'k' '=' r66 r14 r32 'WriteInt(a[i],' r65 'i+1' r2 'WriteLn;' 'ELSE' 'perm(k-1);' r14 'k-1' 'DO' r41 'perm(k-1);' r41 r49 'perm;' 'PROCEDURE' 'Permutations*;' 'BEGIN' 'OpenInput;' r80 r16 '~eot()' 'DO' 'ReadInt(a[n]);' r80 'n+1' r2 'perm(n)' 'END' 'Permutations;' 'PROCEDURE' 'MagicSquares*;' '(*magic' 'square' 'of' 'order' '3,' '5,' '7,' '...' '*)' r51 'x,' 'nx,' 'nsq,' r31 'M:' r74 'OF' r74 r47 'nsq' ':=' 'n*n;' r5 '0;' r1 'n' 'DIV' '2;' r3 'n-1;' r71 'nsq' 'DO' 'nx' ':=' 'n' '+' 'x;' r3 '(j-1)' r79 r58 r33 r71 'nx' 'DO' r1 '(i+1)' r79 r3 '(j+1)' 'MOD' 'n;' r58 ':=' 'x' r62 r14 r34 r22 r32 'WriteInt(M[i][j],' '6);' r18 r1 'i+1;' 'WriteLn' r12 'MagicSquares;' 'PROCEDURE' 'PrimeNumbers*;' r50 'm,' 'x,' 'inc,' 'lim,' 'sqr:' 'INTEGER;' 'prim:' 'BOOLEAN;' 'p:' 'ARRAY' '400' r10 'v:' 'ARRAY' '20' r24 'OpenInput;' 'ReadInt(n);' r5 '1;' 'inc' r67 'lim' r15 'sqr' r67 r52 r1 '3;' r60 r34 'REPEAT' r5 'x' '+' 'inc;' 'inc' ':=' '6' '-' 'inc;' 'IF' 'sqr' '<=' r72 '(*sqr' '=' 'p[lim]^2*)' 'v[lim]' ':=' 'sqr;' 'lim' ':=' 'lim' '+' '1;' 'sqr' ':=' 'p[lim]*p[lim]' r2 r23 '2;' r68 'TRUE;' 'WHILE' 'prim' '&' '(k' '<' 'lim)' 'DO' r23 'k+1;' 'IF' 'v[k]' '<' r72 'v[k]' ':=' 'v[k]' '+' 'p[k]' r2 r68 'x' '#' 'v[k]' 'END' 'UNTIL' 'prim;' 'p[i]' ':=' 'x;' 'WriteInt(x,' r65 'i+1;' r73 '=' r77 'WriteLn;' r9 r76 r9 'm+1' r62 r73 '>' r66 'WriteLn' r8 'PrimeNumbers;' 'PROCEDURE' 'Fractions*;' '(*' 'Tabulate' 'fractions' '1/n*)' 'CONST' 'Base' '=' '10;' r75 '32;' r51 'm,' 'r,' r31 'd:' r46 '(*digits*)' 'x:' r46 '(*index*)' 'BEGIN' r28 r1 '2;' r60 r32 r22 'i' 'DO' 'x[j]' ':=' '0;' r18 r52 r6 r61 'x[r]' '=' '0' 'DO' 'x[r]' ':=' 'm;' r6 'Base*r;' 'd[m]' r56 'i;' r54 'i;' r9 'm+1' r2 'WriteInt(i,' '5);' 'WriteChar(9);' 'WriteChar(46);' r22 'x[r]' r44 'WriteChar(32);' '(*blank*)' 'WHILE' r20 'm' r44 'WriteLn;' r49 'Fractions;' 'PROCEDURE' 'Powers*;' 'CONST' r75 '32;' 'M' '=' '11;' '(*M' '~' 'N*log2*)' r50 'n,' 'exp:' 'INTEGER;' 'c,' 'r,' 't:' 'INTEGER;' 'd:' 'ARRAY' 'M' r10 'f:' r29 r47 'd[0]' r15 'k' r15 r69 r61 'exp' '<' r34 '(*compute' 'd' '=' '2^exp*)' r35 '0;' '(*carry*)' r14 'k' 'DO' 't' ':=' '2*d[i]' '+' 'c;' 'IF' 't' '<' r77 r78 't;' r35 r76 r78 't' '-' '10;' r35 '1' r2 r48 'IF' 'c' '=' '1' 'THEN' 'd[k]' r15 r23 'k+1' r2 '(*write' 'd*)' r1 'M;' r59 'k' r64 'WriteChar(32)' '(*blank*)' r2 r59 '0' r64 'WriteChar(d[i]' '+' '48)' r2 'WriteInt(exp,' 'M);' '(*compute' 'f' '=' '2^-exp*)' 'WriteChar(9);;' 'WriteChar(46);' r6 '0;' r1 '1;' r7 'exp' 'DO' r6 '10*r' '+' 'f[i];' 'f[i]' r56 '2;' r54 '2;' 'WriteChar(f[i]' r30 r48 'f[exp]' ':=' '5;' 'WriteChar(53);' '(*5*)' 'WriteLn;' r69 'exp' '+' '1' r12 'Powers;' 'END' 'TestOberon0.';
r1 : 'i' ':=';
r10 : 'OF' 'INTEGER;';
r11 : 'VAR' 'i,';
r12 : 'END' r8;
r13 : '0;' r7;
r14 : r1 r13;
r15 : ':=' '1;';
r16 : '0;' 'WHILE';
r17 : r3 'j+1';
r18 : r17 r2;
r19 : r1 'i+1';
r2 : 'END' ';';
r20 : 'j' '<';
r21 : r16 r20;
r22 : r3 r21;
r23 : 'k' ':=';
r24 : r10 'BEGIN';
r25 : 'OpenInput;' 'IF';
r26 : r25 '~eot()';
r27 : r26 'THEN';
r28 : r27 'ReadInt(n);';
r29 : 'ARRAY' 'N';
r3 : 'j' ':=';
r30 : '+' '48);';
r31 : 'n:' 'INTEGER;';
r32 : 'n' 'DO';
r33 : ':=' 'x;';
r34 : 'n' 'DO';
r35 : 'c' ':=';
r36 : 'a[k-1]' r33;
r37 : 'a[k-1];' r36;
r38 : ':=' r37;
r39 : 'a[i]' r38;
r4 : 'WHILE' 'i';
r40 : 'a[i];' r39;
r41 : r5 r40;
r42 : 'WriteChar(d[j]' r30;
r43 : r42 r18;
r44 : 'DO' r43;
r45 : r29 'OF';
r46 : r45 'INTEGER;';
r47 : r24 r28;
r48 : r19 r2;
r49 : r19 r12;
r5 : 'x' ':=';
r50 : r11 'k,';
r51 : r11 'j,';
r52 : r9 '0;';
r53 : r6 'r';
r54 : r53 'MOD';
r55 : ':=' 'r';
r56 : r55 'DIV';
r57 : r5 'x+1;';
r58 : r57 'M[i][j]';
r59 : r4 '>';
r6 : 'r' ':=';
r60 : r4 '<=';
r61 : '1;' 'WHILE';
r62 : 'END' r2;
r63 : r1 'i-1;';
r64 : 'DO' r63;
r65 : '5);' r1;
r66 : '0' 'THEN';
r67 : ':=' '4;';
r68 : 'prim' ':=';
r69 : 'exp' ':=';
r7 : r4 '<';
r70 : 'x' '<';
r71 : 'WHILE' r70;
r72 : 'x' 'THEN';
r73 : 'IF' 'm';
r74 : 'ARRAY' '13';
r75 : 'N' '=';
r76 : '0' 'ELSE';
r77 : '10' 'THEN';
r78 : 'd[i]' ':=';
r79 : 'MOD' 'n;';
r8 : 'END' 'END';
r80 : 'n' ':=';
r9 : 'm' ':=';


Whitespace
		:   [ \t]+
		-> skip
		;
Newline
		:   [ \n]+
		-> skip
		;