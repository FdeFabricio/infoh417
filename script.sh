#!/bin/bash
# brew install gnu-time

# sudo apt-get update && sudo apt-get -y install openjdk-8-jdk
# git clone git@github.com:FdeFabricio/infoh417.git
# wget -O imdb.tgz https://davinci.ulb.ac.be/index.php/s/R7Jef6switwL32M/download
# tar -xvzf imdb.tgz -C infoh417/input/
# rm -f infoh417/input/schematext.sql
# cd infoh417
# mkdir bin
mv input/movie_info_idx.csv input/quarantine/movie_info_idx.csv
mv input/aka_name.csv input/quarantine/aka_name.csv
mv input/movie_companies.csv input/quarantine/movie_companies.csv
mv input/title.csv input/quarantine/title.csv
mv input/person_info.csv input/quarantine/person_info.csv
mv input/movie_info.csv input/quarantine/movie_info.csv
mv input/cast_info.csv input/quarantine/cast_info.csv
mv input/char_name.csv input/quarantine/char_name.csv
mv input/comp_cast_type.csv input/quarantine/comp_cast_type.csv
mv input/kind_type.csv input/quarantine/kind_type.csv
mv input/role_type.csv input/quarantine/role_type.csv
mv input/link_type.csv input/quarantine/link_type.csv
mv input/ input/quarantine/


purge() {
  sync && sudo sh -c 'echo 3 >/proc/sys/vm/drop_caches'
}

# we had to increase the memory size for stream 4 input/name.csv b = 1000

# Experiment 1
# run for each inputStream {1...4} all input files, 5 times each and purging memory in-between
javac -sourcepath src src/Experiment1.java -d bin
f_exp1="output/experiment1.txt"
date >> $f_exp1
for filename in `ls -Sr input/*.csv`; do
    echo -ne $filename"\t\t"
    for b in {10,1000,10000,100000,1000000}; do
        for ((stream_type=3; stream_type<=4; stream_type++)); do
            for ((i=0; i<3; i++)); do
                purge
                time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment1 $filename $stream_type $b 1>/dev/null) 2>&1`
                echo $filename,$stream_type,$time_result,$b >> $f_exp1
                echo -ne "#"
            done
        done
    done
    echo
done


# Experiment 2
# run for each inputStream {1...4} all input files, 5 times each and purging memory in-between
javac -sourcepath src src/Experiment2.java -d bin
f_exp2="output/experiment2_1.txt"
date > $f_exp2
for filename in `ls -Sr input/*.csv`; do
    echo -ne $filename"\t"
    for ((stream_type=1; stream_type<=4; stream_type++)); do
        for j in {30,3000,30000,300000}; do
            for ((i=0; i<10; i++)); do
                if [ "$stream_type" -lt 3 ]
                then
                    purge
                    time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment2 $filename $stream_type $j 1>/dev/null) 2>&1`
                    echo $filename,$stream_type,$j,$time_result >> $f_exp2
                    echo -ne "#"
                else
                    for b in {10,10000,100000}; do
                        purge
                        time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment2 $filename $stream_type $j $b 1>/dev/null) 2>&1`
                        echo $filename,$stream_type"_"$b,$j,$time_result >> $f_exp2
                        echo -ne "#"
                    done
                fi
            done
        done
    done
    echo
done

# Experiment 3
javac -sourcepath src src/Experiment3.java -d bin
output="output/output3.txt"
f_exp3="output/experiment3.txt"
echo input_stream,output_stream,filename,k,time >> $f_exp3

# input_stream = 2
for out_stream in {1,2,3,4}; do
    echo out_tream $out_stream
    if [ "$stream_type" -lt 3 ] # not buffered
    then
        for file in {input/name.csv,input/company_name.csv}; do # large and small files
            echo    file $file
            for ((i=0; i<2; i++)); do # iterations
                # k=2 files
                purge
                time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 0 $file $file 1>/dev/null) 2>&1`
                echo 2,$out_stream,$file,2,$time_result >> f_exp3
                echo      k 2

                # k=5 files
                purge
                time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 0 $file $file $file $file $file 1>/dev/null) 2>&1`
                echo 2,$out_stream,$file,5,$time_result >> f_exp3
                echo      k 5

                # k=10 files
                purge
                time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 0 $file $file $file $file $file $file $file $file $file $file 1>/dev/null) 2>&1`
                echo 2,$out_stream,$file,10,$time_result >> f_exp3
                echo      k 10
            done
        done
    else
        for b in {10,10000,100000}; do
            echo    b $b
            for file in {input/name.csv,input/company_name.csv}; do # large and small files
                echo       file $file
                for ((i=0; i<2; i++)); do # iterations
                    # k=2 files
                    purge
                    time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 $b $file $file 1>/dev/null) 2>&1`
                    echo 2,$out_stream"_"$b,$file,2,$time_result >> f_exp3
                    echo         k 2

                    # k=5 files
                    purge
                    time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 $b $file $file $file $file $file 1>/dev/null) 2>&1`
                    echo 2,$out_stream"_"$b,$file,5,$time_result >> f_exp3
                    echo         k 5

                    # k=10 files
                    purge
                    time_result=`(/usr/bin/time -f '%E' java -Xms1g -Xmx15g -cp bin Experiment3 $output 2 $out_stream 0 $b $file $file $file $file $file $file $file $file $file $file 1>/dev/null) 2>&1`
                    echo 2,$out_stream"_"$b,$file,10,$time_result >> f_exp3
                    echo         k 10
                done
            done
        done
    fi
done
java -cp bin Experiment3 output/output.txt 1 1 0 0 input/comp_cast_type.csv input/kind_type.csv