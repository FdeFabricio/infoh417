purge() {
  sync && sudo sh -c 'echo 3 >/proc/sys/vm/drop_caches'
}

experiment4() {
    javac -sourcepath src src/Experiment4.java -d bin
    output="output/output4.txt"
    f_exp4="output/experiment4.txt"
    echo filename,k,M,d,time >> $f_exp4

    for filename in `ls -Sr input/*.csv`; do
        echo -ne $filename"\t"
        for k in {1,2}; do
            for M in {100,1000,10000}; do
                for d in {4,10,25}; do
                    for ((i=0; i<1; i++)); do
                        purge
                        time_result=`(/usr/bin/time -f '%E' java -Xms8g -Xmx15g -cp bin Experiment4 $filename $output $k $M $d 1>/dev/null) 2>&1`
                        echo $filename,$k,$M,$d,$time_result >> $f_exp4
                        echo -ne "#"
                    done
                done
            done
        done
        echo
    done
}

experiment4