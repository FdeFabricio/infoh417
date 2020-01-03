#!/bin/bash
# brew install gnu-time

# Experiment 1
# run for each inputStream {1...4} all input files, 5 times each and purging memory in-between
javac -sourcepath src src/Experiment1.java -d bin
for ((stream_type=1; stream_type<=4; stream_type++)); do
    for ((i=0; i<5; i++)); do
        sudo purge
        for filename in input/*.csv; do
            time=$( { gtime -f '%E' java -cp bin Experiment1 $filename $stream_type 1>/dev/null } 2>&1 )
            echo $filename,$stream_type,$time
        done
    done
done

