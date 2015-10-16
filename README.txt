MobileDeviceKeyboard

How to run jar:
1. Install Java 8 JRE http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
2. Double click on mobile-device.jar to run.

How to compile (Eclipse): (Command line with maven optional https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
First configure Eclipse:
1. Install Java 8 JDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Install Eclipse Mars. (Java EE version was used, but not required) -> http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars1
3. Set Eclipse JDK to Java 8 path.
  - Window -> Preferences -> Java -> Installed JREs -> Add -> (Standard VM) Next -> Click Directory and navigate to Java 8 JDK installation -> Finish
  - Make sure the Java 8 JDK is selected, then press OK.

Compile:
1. Find Project Explorer tab
2. Right click on Autocomplete -> Run As -> Maven Install
  - Once the Autocomplete JAR is compiled, it can be imported and used in other Java projects for other autocomplete prediction applications.
3. Right click on MobileDevice -> Run As -> Maven Install
  - After this step, the mobile-device.jar will be generated in the target directory.

Run in Eclipse:
1. Find Project Explorer tab
2. Right click on MobileDevice -> Run As -> Java Application
