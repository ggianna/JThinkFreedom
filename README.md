JThinkFreedom
=============

Think Freedom is an framework that maps behaviours (detected through any source - camera, microphone, egg) to reactions (from a computer click to... pulling a lever!).

Setting up a development environment
------------------------------------

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


[OpenCV Sourceforge link]:http://sourceforge.net/projects/opencvlibrary/files/opencv-unix/2.4.9/opencv-2.4.9.zip/download
[Leap Motion]:https://www.leapmotion.com/
[Leap Motion Developer]:https://developer.leapmotion.com/
