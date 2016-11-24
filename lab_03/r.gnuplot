set xlabel "K"
set xrange [0:2]
set xtics 0,.2,2
set ylabel "B"
set yrange [0:1]
set ytics 0,.1,1
set zlabel "Map"
set zrange [0:1]
set ztics 0,.05,1

set autoscale
set key off

set view map
set size ratio .9

set object 1 rect from graph 0, graph 0 to graph 1, graph 1 back
set object 1 rect fc rgb "black" fillstyle solid 1.0

set terminal pngcairo size 1800,1800 enhanced font 'Verdana,20'
set output 'temp.png'

splot filename using 1:2:3 with points pointtype 5 pointsize 18 palette linewidth 50
