#Objectionable Content API

The Solution ingests a CSV File and builds a Dictionary of offensive words as the spring application is started up. 
The same dictionary is used to flag the offensive words whenever the Api receives a REST Request to verify the comment
for offensive words.

#Assumptions

1. The Solution assumes that the Dictionary of offensive words is provided in the CSV Format.
2. The Solution assumes that the CSV File is present in the current folder.

#Building the Solution
The Solution relies on Maven to build the Solution and packages a pom.xml as part of the project/zip file.
Run the following in order to build the Solution.

mvn clean install

Needless to say that the maven should be installed and present on the path.

#Input
The Solution assumes that the input is passed in the following text format:
Your comment is bullshit.

#Running the Solution
The Solution can be run as:
java -jar ./target/objectionable-content-api-0.0.1-SNAPSHOT.jar

#Output
{
"status": "SUCCESS",
"message": "No bad words found"
}