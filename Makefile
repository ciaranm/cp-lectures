all : tables graphs
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 1-lds
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 2-parallel-cp
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 3-parallel-search
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' learning-outcomes
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' parallel-optimisation

TABLES =

GRAPHS = colouring/gen-bigger.tex

tables : $(TABLES)

graphs : $(GRAPHS)

colouring/gen-bigger.tex : colouring/bigger.data colouring/bigger.gnuplot
	gnuplot colouring/bigger.gnuplot

