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
set object 1 rect from graph 0, graph 0 to graph 1, graph 1 back
set object 1 rect fc rgb "black" fillstyle solid 1.0
splot filename using 1:2:3 with points palette pointsize 1 pointtype 5 linewidth 30
pause -1
