MobileDeviceKeyboard



Configure (Eclipse): (Not required if using command line maven)

1. Install Java 8 JDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2. Install Eclipse Mars. (Java EE version was used, but not required) -> http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars1

3. Set Eclipse JDK to Java 8 path.

  - Window -> Preferences -> Java -> Installed JREs -> Add -> (Standard VM) Next -> Click Directory and navigate to Java 8 JDK installation -> Finish

  - Make sure the Java 8 JDK is selected, then press OK.



Compile: (Command line with maven optional https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

1. Find Project Explorer tab

2. Right click on Autocomplete -> Run As -> Maven Install

  - Once the Autocomplete JAR is compiled, it can be imported and used in other Java projects for other autocomplete prediction applications.

3. Right click on MobileDevice -> Run As -> Maven Install

  - With a little more configuration, this step would create a runnable jar.



Run in Eclipse:

1. Find Project Explorer tab

2. Right click on MobileDevice -> Run As -> Java Application

3. Results are in the console.


Run JUnit tests:
1. Find Project Explorer tab

2. Right click on Autocomplete -> Run As -> Maven test