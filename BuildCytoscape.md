# Index #

---




# Introduction #

---


To run DynNetwork you can download the latest build from [here](http://code.cytoscape.org/jenkins/job/Cytoscape%203%20GUI%20Distribution/lastSuccessfulBuild/org.cytoscape.distribution$cytoscape/) and make sure you have Java SUN 6 JDK installed and used as default by typing in the command line:
```
java -version
```

If however you wish to build Cytoscape 3.0 yourself from the repositories, follow this instruction list. For more detailed information see **Links**.

# Prerequisites #

---


  * Install Java SUN 6 JDK.
  * Install svn according to your system instructions.
  * Install Maven according to your system instructions.
  * Install Eclipse IDE SDK according to your system instructions.

# Set up Maven Project #

---


## Checkout Cytoscape Repository ##

  * Checkout the repository through public access (no account required; read-only) in your directory of choice
```
svn co http://chianti.ucsd.edu/svn/core3/cy3 cy3
```
  * The checkout will stop when it defaults to grenache. You can work around this issue by editing the svn:externals definition
```
cd cy3
svn propedit svn:externals .
```
  * Then replace the definition with this:
```
parent http://chianti.ucsd.edu/svn/core3/parent/trunk
api http://chianti.ucsd.edu/svn/core3/api/trunk
impl http://chianti.ucsd.edu/svn/core3/impl/trunk
support http://chianti.ucsd.edu/svn/core3/support/trunk
gui-distribution http://chianti.ucsd.edu/svn/core3/gui-distribution/trunk
headless-distribution http://chianti.ucsd.edu/svn/core3/headless-distribution/trunk
samples http://chianti.ucsd.edu/svn/core3/samples/trunk
```
  * Pull all projects with:
```
svn up
```
  * Everytime you wish to update the repository type:
```
svn update
```

## Maven Build ##

  * Create Maven project from bundles and POM files
```
cd cy3/gui-distribution
mvn install
```
  * Everytime you wish to update the repository with _svn update_, build the project with:
```
mvn clean install -DskipTests
```

## Run Cytoscape ##

  * Run Cytoscape 3
```
cd cy3/gui-distribution/assembly/target/cytoscape 
sh cytoscape.sh (Mac or Linux) or cytoscape.bat (Windows)
```

# Set up Eclipse Project #

---


## Import project in Eclipse ##

  * Install Maven plugin in Eclipse: m2eclipse plugin (Maven 2.0 plugin for Eclipse)
  * _Import_ > _Maven_ > _Existing Maven project_
  * Right-click Cytoscape project folder (cy3/core/gui-distribution/trunk/assembly) and _OK_
  * Right-click on bundles with errors, and select _Build path_ > _New Source Folder..._ (maybe not all errors will disappear, but that's not a problem)

## Maven Build from Eclipse ##

You can also build the project directly from Eclipse as following:
  * _Run As_ → _Maven Build_
  * In _Name_ type the run configuration name, such as "cytoscape"
  * In _Base directory_ select your project with _Browse Workspace..._ (mine is called "cy3")
  * Type "clean install" in the _Goals_ text box, and eventually click _Skip Test_ to seed up the building
  * Click _Run_ button

## Create Application in Eclipse ##

  * _New_ → _Java Project_
  * Enter project name. This can be anything. In this example, we use Cy3SimpleApp as a sample app name.
  * Add cytoscape-app-api jar file as a library (or add directly the different bundles)
  * Right-click src folder and create a class file HelloWorldApp. Use org.cytoscape.sample as its package name
  * Create Manifest file. It's just a text file named MANIFEST.MF. The only entry you need to write in this file is the following (Be sure to include a return at the end of the last line or it may be excluded upon export!):
```
Manifest-Version: 1.0
Cytoscape-App: org.cytoscape.sample.HelloWorldApp
```
  * Write App main class
  * _File_ > _Export_
  * Select JAR File and click _Next_
  * Type app jar file name and click _Next_
  * Leave all settings as-is and click _Next_
  * Select _Use existing manifest_ from workspace and specify the one you've just created
  * Click _Finish_. Make sure the app jar file exists in the directory you specified above.

## Tests Application ##

  * Run Cytoscape from command-line
  * Select _Apps_ > _App Manager_
  * Select _Import Local App_ tab
  * Click Select button and select the app jar file you've created

## Create Bundle in Eclipse ##

  * Select _File_ → _New_ → _Other..._
  * Select _Maven_ → _Maven Project_ and click _Next_
  * Click _Next_ again
  * Make sure you have Cytoscape 3 Remote Archetype Catalog in your setting file. If not, press _Configure..._ and add the following remote catalog (and press _Ok_ and _Apply_ at the end):

http://code.cytoscape.org/nexus/service/local/repositories/snapshots/content/archetype-catalog.xml

  * Select _Cytoscape Remote Catalog_ and check _Include snapshot archetypes_. Then select _cyaction-app_ and click _Next_
  * Enter required information and click _Finish_
  * Correct Java compiler setting and add correct version of JRE (1.6)
  * Run Maven to make sure you can build the generated project in Eclipse
```
mvn clean install
```
  * Create java project with packages in "src/main/java" folders
  * Add the necessary dependencies to other bundles in the "pom.xml"
```
	<dependencies>
		<dependency>
			<groupId>org.cytoscape</groupId>
			<artifactId>presentation-api</artifactId>
			<version>[3.0.0-alpha7,4.0)</version>
		</dependency>
                ...
```
  * Build Maven project by typing in the parent folder
```
mvn clean install
```

## Tests Bundle ##

  * Try the simple app. You just need to copy app jar file (in the target directory) to framework/deploy directory in your Cytoscape application folder and run Cytoscape.

## Modifying a Cytoscape Bundle ##

  * Modify source code in Eclipse
  * Build your modified bundle
```
cd cy3/api/<bundle name> or cy3/impl/<bundle name>
mvn install -DskipTests
cd cy3/gui-distribution
mvn clean install -DskipTests
```
  * Run Cytoscape as previously described

## Run in debug mode ##

  * Start Cytoscape in debug mode:
```
cd cy3/gui-distribution/assembly/target/cytoscape 
sh cytoscape.sh debug (Mac or Linux) or cytoscape.bat debug (Windows)
```

  * To run Cytoscape in debug mode from Eclipse, create Maven remote debug configuration as following:
  * _Run_ > _Debug Configurations..._
  * _Remote Java Application_
  * _Name_: cytoscape\_debug
  * _Project_: your project name
  * _Local host_: localhost
  * _Port_: 12345 (the port number was listed when you started Cytoscape in debug mode)
  * Click _Run_ button

## Refresh cache ##

  * Delete all the content in the folder _framwork/data_

# Cytoscape profiling #

---


  * Install VisualVM (http://visualvm.java.net/)
  * Add this line to framework/etc/custom.properties to enable instrumentation:
```
org.osgi.framework.bootdelegation=org.netbeans.lib.profiler.*
```
  * Run Cytoscape and VisualVM

# Links #

---


Original instructions were taken from:

  * http://wiki.cytoscape.org/Cytoscape_3/CoreDevelopment/GettingStarted
  * http://wiki.cytoscape.org/Cytoscape_3/AppDeveloper/SettingUpAnIDE/Eclipse
  * http://opentutorials.cgl.ucsf.edu/index.php/Tutorial:Creating_a_Simple_Cytoscape_3_App#Generation_of_the_App_Jar_File
  * http://opentutorials.cgl.ucsf.edu/index.php/Tutorial:Creating_an_OSGi_Bundle_Cytoscape_3_App
  * http://opentutorials.cgl.ucsf.edu/index.php/Tutorial:Create_a_Bundle_App_Using_IDE