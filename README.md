JThinkFreedom
=============

Think Freedom is an framework that maps behaviours (detected through any source - camera, microphone, egg) to reactions (from a computer click to... pulling a lever!).

1. System architecture
----------------------

The system consists of three main entities: Sensors, Stimuli and Reactors. Every device that is able to collect and stream data can be viewed as a Sensor. For example a webcam can stream images, a mouse can stream its pointer location and a microphone can stream sound data. The Stimuli are objects that represent behaviours. They retrieve data streamed by the Sensors at all times and decide whether a certain behaviour was detected. Such behaviours can be: red objects in images retrieved from a webcam, mouse gestures, loud noises. Finally, after a behaviour has been detected, the Reactors are called. Reactors are entities that represent a single activity like a mouse click, or the launch of an application.

![Think Freedom architecture](http://i.imgur.com/AyRo8Y1.png "Think Freedom architecture")

2. Setting up a development environment
---------------------------------------

DISCLAIMER: This guide was written in October 2014 and might be outdated. The setup instructions though might be similar, regardless of the outdated software versions.

- __Linux (Ubuntu 14.04 LTS)__
 - Install Java and Ant: `sudo apt-get install openjdk-7-* ant`
 - Install Maven: `sudo apt-get install maven`


- __Installing OpenCV 2.4.9__
 - Download OpenCV 2.4.9: [OpenCV Sourceforge link]
 - Export files
 - Install Cmake: `sudo apt-get install cmake cmake-qt-gui`
 - Go to the OpenCV directory (for example `cd opencv-2.4.9`)
 - Run `cmake-gui` (the package dependencies in the next step are not being taken into account in this guide. Google it.)
 - On "Where is the source code" and "Where to build the binaries" put the same directory (the current one)
 - Hit "Configure", then "Finish"
 - Make sure the following options are selected: [BUILD_DOCS, BUILD_PACKAGE, BUILD_PERF_TESTS, BUILD_SHARED_LIBS, BUILD_TESTS, BUILD_WITH_DEBUG_INFO, BUILD_opencv_apps, BUILD_opencv_calib3d, BUILD_opencv_contrib, BUILD_opencv_core, BUILD_opencv_features2d, BUILD_opencv_flann, BUILD_opencv_gpu, BUILD_opencv_highgui, BUILD_opencv_imgproc, BUILD_opencv_legacy, BUILD_opencv_ml, BUILD_opencv_nonfree, BUILD_opencv_objdetect, BUILD_opencv_ocl, BUILD_opencv_photo, BUILD_opencv_python, BUILD_opencv_stitching, BUILD_opencv_superres, BUILD_opencv_ts, BUILD_opencv_video, BUILD_opencv_videostab, INSTALL_C_EXAMPLES, INSTALL_PYTHON_EXAMPLES, INSTALL_TESTS, WITH_1394, WITH_CUDA, WITH_CUFFT, WITH_EIGEN, WITH_FFMPEG, WITH_GIGEAPI, WITH_GSTREAMER, WITH_GTK, WITH_JASPER, WITH_JPEG, WITH_LIBV4L, WITH_OPENCL, WITH_OPENCLAMDBLAS, WITH_OPENCLAMDFFT, WITH_OPENEXR, WITH_PNG, WITH_PVAPI, WITH_TIFF, WITH_V4L]
 - Hit "Generate" and close the Cmake GUI
 - From the terminal type `make`, then `sudo make install`


- __Installing Leap Motion SDK 2.1.5 Beta__
 - Download the Leap Motion installer: [Leap Motion]
 - Export files
 - Install the package files: `sudo dpkg -i Leap-2.1.5+22693-x64.deb` (for 64-bit machines)
 - Start the Leap Motion service `sudo service leapd start`. Open the control panel with `LeapControlPanel`
 - Download the Leap Motion SDK for Linux: [Leap Motion Developer]
 - Export files
 - The device is ready to use, and the libraries are under LeapSDK/lib


- __Integrating Leap Motion with Maven__
 - `cd LeapSDK/lib`
 - `mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file -Dfile=LeapJava.jar -DgroupId=com.leapmotion.leap -DartifactId=leapMotion -Dversion=1.0.0 -Dpackaging=jar`
 - Now we can include it in our project's pom.xml

3. Developing a sensor-stimulus module
--------------------------------------

The basic framework has been set, and now it's time to develop modules for sensors, stimuli, or both. As of right now (October 2014), Think Freedom supports webcam, leap motion and mouse as sensors. If you'd like to develop a stimulus for those sensors go ahead and implement the stimuli in their respective packages. Lets assume the most complicated scenario: implementing a sensor and its stimuli from scratch.

First of all navigate to the parent directory and compile the modules by running `mvn package install` (let's assume you're running a Linux distribution). Now the modules are installed in your local repository. After that, create a new Maven project and include the following lines in the dependencies in pom.xml dependencies.

```xml
<dependency>
    <groupId>org.scify.jthinkfreedom.skeleton</groupId>
    <artifactId>jthinkfreedom.skeleton</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
Now the tricky part. The sensor you need to implement must have some dependencies, right? For example our webcam modules rely on OpenCV, and leap motion has its own SDK (see instructions above for building it). The main point is that you need to install it to the local repository and then add it as a dependency as well. For example with leap motion, the following lines do the trick:

```xml
<dependency>
    <groupId>com.leapmotion.leap</groupId>
    <artifactId>leapMotion</artifactId>
    <version>1.0.0</version>
</dependency>
```
After the dependencies are set we need to create two packages, one for the sensors, one for the stimuli. The naming conventions are the following: Let's assume you are building a module for a sensor called Jimmy. The project name should be jthinkfreedom-jimmy and the two packages should be named something similar to `org.scify.jthinkfreedom.jimmy.sensors` and `org.scify.jthinkfreedom.jimmy.stimuli`. Then we create a java class under the sensors package `JimmySensor extends SensorAdapter` and we implement the `getData()` method via which the sensor is streaming data to the stimuli.

For the purposes of this guide, a JimmySensor can detect bad jokes. So let's create a java class `BadJokeStimulus extends StimulusAdapter` under the stimuli package and implement the method `onDataReceived()`. This method should make use of `shouldReact()` in order to determine if the joke is bad enough to call the reactors (or the police).

Last but not least, all the stimuli must be annotated with the `@StimulusAnnotation` class in order to help the GUI find them. The annotation defines under which sensor the stimulus is operating. For example the BadJokeStimulus should be annotated with `@StimulusAnnotation(sensorClass = "org.scify.jthinkfreedom.jimmy.sensors.JimmySensor")`.

Congratulations! You just created a new module.

[OpenCV Sourceforge link]:http://sourceforge.net/projects/opencvlibrary/files/opencv-unix/2.4.9/opencv-2.4.9.zip/download
[Leap Motion]:https://www.leapmotion.com/
[Leap Motion Developer]:https://developer.leapmotion.com/
