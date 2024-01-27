# Parallel-assignment01

As far as a brief summary of my approach goes, the program first initializes
an array of threads that contains space for a total of 8. The program
then initializes the threads and starts them one by one. These threads
all then complete their tasks, finding the number of primes in their
assigned range. This range is specified such that each thread has
an equal amount of numbers to run the prime checking algorithm.
The program then joins all the threads to makesure that they stop running before proceeding. 
After this, the program will thenprint a text file containing the outputs: execution time, 
total number of primes found,sum of all primes found, 
and the top ten maximum primes from lowest to highest.

To run this program, be sure that the latest JDK
is installed. open Command Prompt and change directory to where
the Java file is located. Afterwards, type in "javac pa1.java". This
compiles the Java file. To run the compiled file, type in "java pa1". This
will execute the program. An output file will then be printed in the same directory.