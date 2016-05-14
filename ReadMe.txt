Hardware-Java
==============
Windows : 
- Copy hardwarex.dll/.so from x86 folder if you use Java 32 bit or x64 folder if you use Java 64 bit (or download the ones from Hardware-MATLAB) and put it in this project folder (rename hardwarex.so to libhardwarex.so for Linux).
- From Eclipse, use File\Import\Existing Projects into Workspace.
- Check e.g. RazorAHRS.java file for usage information.

Tested on Windows 8.1 64 bit using Java 1.7 64 bit, Ubuntu 15.10 32 bit using Java 1.7 32 bit.

Hardware support : 
- Maestro : Pololu Mini Maestro 6, 18, 24 servo controllers.
- NMEADevice : GPS, Furuno WS200 weather station, AIS Receiver dAISy.
- RazorAHRS : SparkFun 9DOF Razor IMU.
- SSC-32 : Lynxmotion SSC-32, SSC-32u servo controllers.

See also https://github.com/ENSTABretagneRobotics/Android, https://github.com/ENSTABretagneRobotics/Hardware-CPP, https://github.com/ENSTABretagneRobotics/Hardware-MATLAB.

Other dependencies (in case of a problem or to create a new project) :
- Download JNA (Java Native Access) on https://maven.java.net/content/repositories/releases/net/java/dev/jna/jna/4.2.2/jna-4.2.2.jar and put it in the lib folder.
- In the Eclipse project Properties\Java Build Path\Libraries, click on Add JARs and choose jna-4.2.2.jar.
- Use Oracle JDK, not OpenJDK.
- From the command line : put jna-4.2.2.jar, hardwarex.dll/libhardwarex.so, the .txt configuration files, the .java and the .class in the same folder or use the -classpath option...
