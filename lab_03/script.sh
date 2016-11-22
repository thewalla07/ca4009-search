URL=http://136.206.115.117:8080
TREC=6
SIMF=BM25
KVAL=1
BVAL=0.9
DIR="/IRModelGenerator/TrecBatchQueryExecuterServlet?treccode=$TREC&simf=$SIMF&k=$KVAL&b=$BVAL"
echo "$URL$DIR"
NEWDIR=$(wget --no-http-keep-alive -q -O- "$URL$DIR" | grep href | sed 's_<a href="\([^"]*\)".*_\1_')
echo "$URL$NEWDIR"
wget --no-http-keep-alive -q -O- "$URL$NEWDIR" >> output.txt
