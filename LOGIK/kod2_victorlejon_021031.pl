

man(gustaf-vi-adolf).
man(carl-johan).
man(prins-bertil).
man(fredrik-ix-av-danmark).
man(sigvard).
man(prins-gustaf-adolf).
man(john-ambler).
man(johann-georg-av-hohenzollern).
man(niclas-silfverschiold).
man(tord-magnuson).
man(carl-xvi-gustaf).
man(prins-daniel).
man(prins-oscar).
man(prins-carl-philip).
man(prins-alexander).
man(prins-gabriel).
man(christopher-o-neill).
man(prins-nicolas).
man(prins-julian).

woman(margareta-av-storbritannien).
woman(louise-f-mountbatten).
woman(lilian-f-davies).
woman(prinsessan-ingrid).
woman(sibylla-av-sachsen-coburg-gotha).
woman(prinsessan-margaretha).
woman(prinsessan-birgitta).
woman(prinsessan-desiree).
woman(prinsessan-christina).
woman(silvia-f-sommerlath).
woman(kronprinsessan-victoria).
woman(prinsessan-estelle).
woman(prinsessan-sofia).
woman(prinsessan-madeleine).
woman(prinsessan-leonore).
woman(prinsessan-adrienne).

husband(gustaf-vi-adolf, margareta-av-storbritannien).
husband(gustaf-vi-adolf, louise-f-mountbatten).
husband(prins-gustaf-adolf, sibylla-av-sachsen-coburg-gotha).
husband(fredrik-ix-av-danmark, prinsessan-ingrid).
husband(prins-bertil, lilian-f-davies).
husband(john-ambler, prinsessan-margaretha).
husband(johann-georg-av-hohenzollern, prinsessan-birgitta).
husband(niclas-silfverschiold, prinsessan-desiree).
husband(tord-magnuson, prinsessan-christina).
husband(carl-xvi-gustaf, silvia-f-sommerlath).
husband(prins-daniel, kronprinsessan-victoria).
husband(prins-carl-philip, prinsessan-sofia).
husband(christopher-o-neill, prinsessan-madeleine).

wife(margareta-av-storbritannien, gustaf-vi-adolf).
wife(louise-f-mountbatten, gustaf-vi-adolf).
wife(sibylla-av-sachsen-coburg-gotha, prins-gustaf-adolf).
wife(prinsessan-ingrid, fredrik-ix-av-danmark).
wife(lilian-f-davies, prins-bertil).
wife(prinsessan-margaretha, john-ambler).
wife(prinsessan-birgitta, johann-georg-av-hohenzollern).
wife(prinsessan-desiree, niclas-silfverschiold).
wife(prinsessan-christina, tord-magnuson).
wife(silvia-f-sommerlath, carl-xvi-gustaf).
wife(kronprinsessan-victoria, prins-daniel).
wife(prinsessan-sofia, prins-carl-philip).
wife(prinsessan-madeleine, christopher-o-neill).

father(gustaf-vi-adolf, prins-gustaf-adolf).
father(gustaf-vi-adolf, sigvard).
father(gustaf-vi-adolf, prinsessan-ingrid).
father(gustaf-vi-adolf, prins-bertil).
father(gustaf-vi-adolf, carl-johan).
father(prins-gustaf-adolf, prinsessan-margaretha).
father(prins-gustaf-adolf, prinsessan-birgitta).
father(prins-gustaf-adolf, prinsessan-desiree).
father(prins-gustaf-adolf, prinsessan-christina).
father(prins-gustaf-adolf, carl-xvi-gustaf).
father(carl-xvi-gustaf, kronprinsessan-victoria).
father(carl-xvi-gustaf, prinsessan-madeleine).
father(carl-xvi-gustaf, prins-carl-philip).
father(prins-daniel, prinsessan-estelle).
father(prins-daniel, prins-oscar).
father(prins-carl-philip, prins-alexander).
father(prins-carl-philip, prins-gabriel).
father(prins-carl-philip, prins-julian).
father(christopher-o-neill, prinsessan-leonore).
father(christopher-o-neill, prins-nicolas).
father(christopher-o-neill, prinsessan-adrienne).

mother(margareta-av-storbritannien, prins-gustaf-adolf).
mother(margareta-av-storbritannien, sigvard).
mother(margareta-av-storbritannien, prinsessan-ingrid).
mother(margareta-av-storbritannien, prins-bertil).
mother(margareta-av-storbritannien, carl-johan).
mother(sibylla-av-sachsen-coburg-gotha, prinsessan-margaretha).
mother(sibylla-av-sachsen-coburg-gotha, prinsessan-birgitta).
mother(sibylla-av-sachsen-coburg-gotha, prinsessan-desiree).
mother(sibylla-av-sachsen-coburg-gotha, prinsessan-christina).
mother(sibylla-av-sachsen-coburg-gotha, carl-xvi-gustaf).
mother(silvia-f-sommerlath, kronprinsessan-victoria).
mother(silvia-f-sommerlath, prinsessan-madeleine).
mother(silvia-f-sommerlath, prins-carl-philip).
mother(kronprinsessan-victoria, prinsessan-estelle).
mother(kronprinsessan-victoria, prins-oscar).
mother(prinsessan-sofia, prins-alexander).
mother(prinsessan-sofia, prins-gabriel).
mother(prinsessan-sofia, prins-julian).
mother(prinsessan-madeleine, prinsessan-leonore).
mother(prinsessan-madeleine, prins-nicolas).
mother(prinsessan-madeleine, prinsessan-adrienne).

%% BEGINNING OF YOUR CODE

married(X, Y):- husband(X, Y);
            wife(X, Y).

father_in_law(X, Y):- married(Y, Z),
            father(X, Z).

mother_in_law(X, Y):- married(Y, Z),
            mother(X, Z).

parent(X, Y):- father(X, Y);
            mother(X, Y).

son(X, Y):- parent(Y, X),
            man(X).

daughter(X, Y):- parent(Y, X),
            woman(X).

grandfather(X, Y):- parent(Z, Y),
            father(X, Z).

grandmother(X, Y):- parent(Z, Y),
            mother(X, Z).

brother(X, Y):- sibling(X, Y),
            man(X).

sister(X, Y):- sibling(X, Y),
            woman(X).

sibling(X, Y):- setof((X,Y), P^(parent(P,X),parent(P,Y), \+X=Y), Brothers),
            member((X,Y), Brothers).

uncle(X, Y):- brother(X, Z),
            parent(Z, Y).

uncle(X, Y):- married(X, Z),
            sister(Z, A),
            parent(A, Y).

aunt(X, Y):- sister(X, Z),
            parent(Z, Y).

aunt(X, Y):- married(X, Z),
            brother(Z, A),
            parent(A, Y).

cousin(X, Y):- setof((X, Y), Z^((uncle(Z, X); aunt(Z, X)), parent(Z, Y)), Cousins),
            member((X, Y), Cousins).


brother_in_law(X, Y):- husband(X, Z),
			sibling(Z, Y).

brother_in_law(X, Y):- brother(X, Z),
			married(Z, Y).

brother_in_law(X, Y):- husband(X, Z),
			sibling(Z, A),
			married(A, Y).

sister_in_law(X, Y):- wife(X, Z),
			sibling(Z, Y).
			
sister_in_law(X, Y):- sister(X, Z),
			married(Z, Y).

sister_in_law(X, Y):- wife(X, Z),
            sibling(Z, A),
            married(Y, A).

nephew(X, Y):- (uncle(Y, X); aunt(Y, X)),
            man(X).

niece(X, Y):- (uncle(Y, X); aunt(Y, X)),
            woman(X).

ancestor(X, Y):- parent(X, Y).
ancestor(X, Y):- parent(X, Z), ancestor(Z, Y).

%% END OF YOUR CODE



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TESTS %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%                                                                  %%
%%           DO NOT MODIFY THE FILE FROM HERE TO THE END            %%
%%                                                                  %%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
ptest(P, A):-
	write('Exists '), write(P), write('/'), write(A), write('?'),
	(
	 current_predicate(P/A), !, write(' [YES]'), nl;
	 write(' [PREDICATE MISSING]'), nl
	).

stest(Term1, A, Term2):-
	current_predicate(Term1/A),
	write('Correct sex in '), write(Term1), write('/'), write(A),
	write('?'),
	(
	 functor(F1, Term1, 2),
	 call(F1),
	 arg(1, F1, A1),
	 functor(F2, Term2, 1),
	 arg(1, F2, A1),
	 \+ call(F2), write(' [NO]'), nl;
	 write(' [YES]'), nl
	).
stest(_, _, _).

test_([[P, A]|Ps]):-
	ptest(P, A),
	test_(Ps).
test_([[P, A, S]|Ps]):-
	A == 2,
	ptest(P, A),
	stest(P, A, S),
	test_(Ps).
test_([]).	

test_case(P, 1, Argument, ExpectedSet):-
	current_predicate(P/2),
	(write('Test case: '), write(P),
	 write('('), write(Argument), write(', X)'),
	 functor(F1, P, 2),
	 arg(1, F1, Argument),
	 findall(X, (call(F1), arg(2, F1, X)), Xs),
	 list_to_set2(Xs, Ys),
	 sort(Ys, Ys1), sort(ExpectedSet, ExpectedSet1),
	 Ys1 = ExpectedSet1, write(' [OK]'), nl;
	 write(' [INCORRECT]'), nl).
test_case(P, 2, Argument, ExpectedSet):-
	current_predicate(P/2),
	(write('Test case: '), write(P),
	 write('(X, '), write(Argument), write(')'),
	 functor(F1, P, 2),
	 arg(2, F1, Argument),
	 findall(X, (call(F1), arg(1, F1, X)), Xs),
	 list_to_set2(Xs, Ys),
	 sort(Ys, Ys1), sort(ExpectedSet, ExpectedSet1),
	 Ys1 = ExpectedSet1, write(' [OK]'), nl;
	 write(' [INCORRECT]'), nl).
test_case(_, _, _, _).

test_cases([[P, N, A, R]|Cs]):-
	test_case(P, N, A, R),
	test_cases(Cs).
test_cases([]).
	
test:-
	test_([[man, 1], [woman, 1], [husband, 2, man], [wife, 2, woman], [father, 2, man], [mother, 2, woman], [married, 2], [father_in_law, 2, man], [mother_in_law, 2, woman], [parent, 2], [son, 2, man], [daughter, 2, woman], [grandfather, 2, man], [grandmother, 2, woman], [brother, 2, man], [sister, 2, woman], [uncle, 2, man], [aunt, 2, woman], [cousin, 2], [brother_in_law, 2, man], [sister_in_law, 2, woman], [nephew, 2, man], [niece, 2, woman], [ancestor, 2]]),
	test_cases([
		    [uncle, 1, prins-daniel, [prins-alexander, prins-gabriel, prins-julian, prins-nicolas, prinsessan-leonore, prinsessan-adrienne]],
		    [uncle, 2, prins-alexander, [prins-daniel, christopher-o-neill]],
		    [cousin, 2, prins-alexander, [prinsessan-estelle, prins-oscar, prinsessan-leonore, prins-nicolas, prinsessan-adrienne]],
		    [son, 2, gustaf-vi-adolf, [prins-gustaf-adolf, sigvard, prins-bertil, carl-johan]],
		    [father_in_law, 1, gustaf-vi-adolf, [sibylla-av-sachsen-coburg-gotha, fredrik-ix-av-danmark, lilian-f-davies]],
		    [mother_in_law, 1, silvia-f-sommerlath, [prins-daniel, christopher-o-neill, prinsessan-sofia]],
		    [parent, 2, prins-bertil, [margareta-av-storbritannien, gustaf-vi-adolf]],
		    [grandfather, 1, carl-xvi-gustaf, [prinsessan-estelle, prins-oscar, prinsessan-leonore, prins-nicolas, prins-alexander, prins-gabriel, prins-julian, prinsessan-adrienne]],
		    [grandmother, 1, silvia-f-sommerlath, [prinsessan-estelle, prins-oscar, prinsessan-leonore, prins-nicolas, prins-alexander, prins-gabriel, prins-julian, prinsessan-adrienne]],
		    [brother, 1, prins-bertil, [prins-gustaf-adolf, sigvard, prinsessan-ingrid, carl-johan]],
		    [sister, 2, carl-xvi-gustaf, [prinsessan-margaretha, prinsessan-birgitta, prinsessan-desiree, prinsessan-christina]],
		    [aunt, 2, prins-carl-philip, [prinsessan-margaretha, prinsessan-birgitta, prinsessan-desiree, prinsessan-christina]],
		    [brother_in_law, 1, prins-daniel, [prinsessan-madeleine, prins-carl-philip, christopher-o-neill, prinsessan-sofia]],
		    [brother_in_law, 2, prins-daniel, [prins-carl-philip, christopher-o-neill]],
		    [sister_in_law, 2, prins-daniel, [prinsessan-madeleine, prinsessan-sofia]],
		    [sister_in_law, 1, silvia-f-sommerlath, [prinsessan-margaretha, prinsessan-birgitta, prinsessan-desiree, prinsessan-christina, john-ambler, johann-georg-av-hohenzollern, niclas-silfverschiold, tord-magnuson]],
		    [nephew, 2, prinsessan-sofia, [prins-oscar, prins-nicolas]],
		    [niece, 2, prinsessan-sofia, [prinsessan-estelle, prinsessan-leonore, prinsessan-adrienne]],
		    [ancestor, 2, prins-oscar, [kronprinsessan-victoria, prins-daniel, margareta-av-storbritannien, sibylla-av-sachsen-coburg-gotha, silvia-f-sommerlath, gustaf-vi-adolf, prins-gustaf-adolf, carl-xvi-gustaf]]
		   ]), !.

list_to_set2([], Acc, Acc).
list_to_set2([X|Xs], Acc, Set):-
	member(X, Acc),
	list_to_set2(Xs, Acc, Set), !;
	list_to_set2(Xs, [X|Acc], Set).
list_to_set2([X|Xs], Set):-
	list_to_set2(Xs, [X], Set).
list_to_set2([], []).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%% END OF TESTS %%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
