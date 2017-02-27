Project 1: Virtual Disk Unit
Author: Enrique A. Marrero Torres
Created for the class ICOM4035-070

I. What does this program do?

This Program creates a Virtual Disk Unit using Java. It uses Random Access Files or RAF to do so. They are stored in the
physical disk after execution of the program, and can be accessed any time.

Inside this project you will find the following packages:
-diskUtilities: Contains important utilities such as VirtualDiskBlock, which creates the blocks to be used by the Virtual Disk Unit.
-ExceptionHandlers: Contains constructors used in the diskUtilities package.
-testers: Contains DiskUnitTester0, which creates 5 Virtual Disk Units and DiskUnitTester1, which reads the contents of the created units.

As a default option for the DiskUnitTester0, it will only read the disk contents of disk1. It is easily modifiable by going to line 12 of the DiskUnitTester1 class and changing from "disk1" to "disk#", where # is a number in the mutually inclusive range of 1-5.

II. How to run this program

a. Using Eclipse

1. Select File from the top toolbar, then Import.
2. Click General, then Projects from Folder or Archive.
3. Select the DiskUnitTester0.java class contained inside the "testers" package and right-click it. Choose "Run as", then click "Java Application".*
4. Select the DiskUnitTester1.java class contained inside the same folder as DiskUnitTester0.java class and follow the same procedure as step 3.**

*Please note that you can only run this class once or else it will throw an ExistingDiskException, as "disk1" is already present. If you wish to test again, delete the files disk1 to disk5 using either the package explorer within eclipse or your system's default file explorer (these files will be in the root folder of the project).
**If you haven't run the DiskUnitTester0 class, this will result in a NonExistingDiskException, as the disk files are not present. Please run DiskUnitTester0 before running this class.

b. Command prompt or terminal (command-line console)
1. On your command-line interface, navigate to the root directory of the project using the command cd.
2. Make sure the bin folder is in the project's root directory. You can either create it using "mkdir bin" or wait for the javac command to create it.
3. Use the following commands to compile every package contained in this project:
	javac -d bin -sourcepath src src\diskUtilities\*.java
	javac -d bin -sourcepath src src\ExceptionHandlers\*.java
	javac -d bin -sourcepath src src\testers\*.java 
4. Run the following command to create the disks for testing
	java -classpath bin testers.DiskUnitTester0
5. Verify that the disks have been created correctly by using the commands "ls" in unix-based operating systems and Mac OSX or "dir" in Windows.
6. Once that you've verified the existence of the disks, run the following command for testing the disk contents
	java -classpath bin testers.DiskUnitTester1
