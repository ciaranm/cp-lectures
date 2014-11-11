# vim: set et ft=gnuplot sw=4 :

set terminal tikz color size 4in,2.6in font ",8"
set output "colouring/gen-simple.tex"
set xlabel "Number of Colours"
set ylabel "Runtime (ms)" offset -5
set key top right

set border 0
set ytics nomirror border scale 0 offset -3
set xtics (1, 5, 10) nomirror border rangelimited
set grid ytics

set boxwidth 0.5
set style fill solid
set xrange [0:11]
plot "colouring/simple.data" using 1:(($2+0.001)) with boxes ti "Sample Graph"
