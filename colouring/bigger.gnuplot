# vim: set et ft=gnuplot sw=4 :

set terminal tikz color size 4in,2.6in font ",8"
set output "colouring/gen-bigger.tex"
set xlabel "Number of Colours"
set ylabel "Runtime (s)"
set key top right

set border 0
set ytics nomirror border scale 0
set xtics (1, 5, 10, 15, 20, 25, 30) nomirror border rangelimited
set grid ytics

set boxwidth 0.5
set style fill solid
set xrange [0:31]
plot "colouring/bigger.data" using 1:(($2+0.1) / 1000) with boxes ti "G(30, 0.7)"
