## Description
1. This application takes a CSV file loaded into src/main/java/readwrite
2. Next, it reformats the fields
3. Last, it writes to the specified output file with a date 

If the csv line does not contain all the fields, the line is dropped from the output.

This was developed on a windows machine. 

##To Run 
Make sure you have Maven 3 and Java 11 installed

#####Windows systems: 
0. Load your file to convert into readwrite as sample.csv
1. In a command window, navigate to root
2. run 'mvn clean package'
3. run 'java -jar target/readwritepractice-1.0-SNAPSHOT.jar'

Input file should be a csv with headers as follows:

Timestamp,Address,ZIP,FullName,FooDuration,BarDuration,TotalDuration,Notes

---

#####Next Steps:
Write a script to run with a input to a file path

A more robust system to check inputs

Updating the error handling 

Gracefully handle an account that does not contain all the fields

Add logging to replace the System.out

Update date output to match input pattern

Update the Person object to use types, rather than Strings for everything

Write integration tests

Update tests to be more specific and clear (especially the integration test)
