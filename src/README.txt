Simion Bogdan - Alexandru, 323CD

	Programul ce vine la un loc cu acest readme este implementarea mea la jocul
MMO RPG League of OOP, fără interfață grafică, el făcând doar logica din spate.
	Pentru acest lucru am avut nevoie de mai multe clase și pachete în care
să-mi organizez codul. Menționez faptul că am explicat în javadoc-urile aferente
funcționalitatea fiecărui program. Drept urmare, voi explica doar implementarea
și mersul metodei main.
	Astfel, citesc din fișierul de test variabilele ce îmi trebuie pentru ca
programul să funcționeze în modul așteptat, iar apoi am creat o listă de
jucători cu toate caracteristicile lor. Partea cea mai importantă a codului din
clasa Main este acea în care „se joacă” League of OOP: în fiecare rundă, dacă
2 jucători se întâlnesc pe același pătrățel pe hartă, atunci ei își vor folosi
reciproc abilitățile împotriva celuilalt. Înainte de a face această verificare,
am aplicat vătămarea pentru abilitățile cu efect pe mai multe runde, dacă a
existat într-o rundă anterioară.
	În interiorul metodei action se află implementarea cea mai importantă din
cod, astfel încât ea apelează 3 metode, diferite în funcție de tipul
jucătorului. Mai departe, am implementat double-dispatch folosind design
pattern-ul Visitor pentru a obține modificatorii de rasă. Astfel, am salvat
într-un vector modificatorii, apelând metoda accept specifică fiecărui jucător
(A se specifica că fiecare tip de jucător are aferentă câte o clasă, ce
moștenește clasa pentru tipul general de jucător - Hero). Metoda accept este
implementată în fiecare dintre clasele specifice fiecărui tip de jucător, ce
apelează metoda visit implementată în clasa Append. Aceste 2 metode sunt
specifice interfețelor Modificator și, respectiv ModificatorVisitor.
	După ce se termină toate rundele, dacă există un jucător care are viața mai
mică sau egală cu zero, atunci el va fi declarat mort. Dacă nu, atunci i se va
afișa statistica în fișierul de ieșire.
	De asemenea, vreau să menționez câteva lucruri la final. Pe Vmchecker îmi
arată că am -20 de puncte pe erorile de pe checkstyle, în ciuda faptului că eu
am mai puțin de 30 de erori pe checkstyle, iar pe local îmi arată că am 0 puncte
pe aceste erori.