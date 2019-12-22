** Candidate interview task **
Please, enhance simple Java application fulfilling the following criteria...
Use any Java libraries as dependencies you like:

1. Read JSON configuration file with measurement definitions and deserialize them into Java bean objects.

2. Every definition contains information about:
- initial value - starting value of measurement
- increment - how the value is changed
- interval - for how long the measurement should be generated

3. Generate values for these measurements based on information in JSON file:
- Output format will be CSV file.
- Every measurement from JSON file will have its own output CSV file named "%appName%"-"%measurementId%".csv, e.g. Skynet-perf.int001.csv.
- Output values should be generated for every 10 seconds starting from now
- Initialize measurements with value from initialValue param, every next value should be incremented by increment.
- Output values should be generated for period of time defined by interval in seconds, e.g. if interval is 60, there will be 7 values in output CSV file with 10 seconds difference.
- Information in CSV file will be in this format "date", "measurement value","appName","measurementId".
- Date format will be dd-MM-yyyy HH:mm:ss.
- Output of CSV file will look like this:

"01-02-2018 10:12:45",15,"Skynet","perf.int001"
"01-02-2018 10:12:55",20,"Skynet","perf.int001"
"01-02-2018 10:13:05",25,"Skynet","perf.int001"
"01-02-2018 10:13:15",30,"Skynet","perf.int001"

Hints:
1. Use any Java IDE (IntelliJ, Eclipse, NetBeans, ...) you like and import the source code as the 'Apache Maven' project to the IDE.
2. Check all Java classes source files and try to implement all 'TODO' sections.
3. Optionally implement Java TEST classes.
4. The source code must be compilable and all tests must pass.
5. After the manual running of 'com.mavenir.interview.measurement.app.Main' class with 2 parameters
(output folder and comma-separated list of measurement codes), required CSV files must be created.
