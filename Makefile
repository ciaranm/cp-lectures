all : tables graphs
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 1-lds
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 2-parallel-cp
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' 3-parallel-search
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' learning-outcomes
	latexmk -pdf -pdflatex='pdflatex -interaction=nonstopmode %O %S' parallel-optimisation

TABLES =

GRAPHS = colouring/gen-bigger.tex colouring/gen-bigger37.tex colouring/gen-bigger59.tex colouring/gen-simple.tex

tables : $(TABLES)

graphs : $(GRAPHS)

colouring/gen-%.tex : colouring/%.data colouring/%.gnuplot
	gnuplot colouring/$*.gnuplot

