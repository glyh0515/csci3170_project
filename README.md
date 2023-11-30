# CSCI3170_project
## Group 11

<pre>
<ins>Student Name</ins>        <ins> (sID) </ins>
CHEUNG Siu Hin     (1155175864)
LAU Ching Fai      (1155195143)
LEE Yat Him        (1155176301)
</pre>

## Execute the program
### Method 1
1. Connect to CSE VPN
2. Run the following command in your source folder to execute the code

<pre>
javac Administrator.java Main.java Manager.java SalesPerson.java BaseModel.java
java -classpath ./mysql-jdbc.jar:./ Main   
</pre>

### Method 2
1. Copy the source folder and data folder to CSE Linux Server with the following command
<pre>
  scp [source_folder]/* [CSE username]@linux12.cse.cuhk.edu.hk:[destination_folder]
  scp [source_folder]/sample_data/* [CSE username]@linux12.cse.cuhk.edu.hk:[destination_folder]/sample_data
</pre>
Example
<pre>
  scp ./CSCI3170proj/* xxxx4@linux9.cse.cuhk.edu.hk:/uac/y22/xxxx4/csci3170proj
  scp ./CSCI3170proj/sample_data/* xxxx4@linux9.cse.cuhk.edu.hk:/uac/y22/xxxx4/csci3170proj/sample_data
</pre>
2. ssh to CSE Linux Server and cd to the appropriate folder
<br />
Example
<pre>
  ssh xxxx4@linux9.cse.cuhk.edu.hk
  cd ./csci3170proj
</pre>
3. Run the following command to execute the code

<pre>
  javac Administrator.java Main.java Manager.java SalesPerson.java BaseModel.java
  java -classpath ./mysql-jdbc.jar:./ Main   
</pre>
