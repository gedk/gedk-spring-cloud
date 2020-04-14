# shellcheck disable=SC2006
aa=`netstat -anp|grep 9001|grep -i list|awk '{print $7}'|awk -F / '{print $1}'`
echo $aa
kill -9 $aa
nohup java -jar xxx.jar >> nohup.out &
