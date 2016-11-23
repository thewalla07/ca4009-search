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
splot filename using 1:2:3 with points palette pointsize 3 pointtype 7
pause -1
