# TUIO SIMULATOR
This repository is a fork of [https://github.com/mkalten/TUIO11_Simulator](https://github.com/mkalten/TUIO11_Simulator) and contains the following main changes:
* Dependencies of this project are now managed by Maven (see [pom.xml](./pom.xml)).
* The software is now using Spring Framework for easy configurable interchangeability of implementation classes during runtime. 
* It is now possible to send TUIO messages via WebSocket instead of UDP by configuring `tuiosim.useWebsocket=true` in `application.properties`.

Copyright (c) 2005-2016 Martin Kaltenbrunner <martin@tuio.org>
This software is part of reacTIVision, an open source fiducial
tracking and multi-touch framework based on computer vision. 

http://reactivision.sourceforge.net/

## Introduction:
This Simulator can be used by application developers for the 
design of tangible user interfaces based on the TUIO framework,
without the actual need of a real table/camera setup in an
earlier project phase.

The TuioSimulator simulates a virtual table with tangibles.
It tracks the ID, position and orientation of virtual objects
on a virtual table surface. This data is sent via a network 
connection to any client application. It implements the TUIO protocol,
which is based on Open sound control. See the included PDF file for
more information on this protocol.

Additionally the simulator provides a multi-touch finger tracking
simulation, which is also included in the TUIO specification.

## Application Start:
This application is based on the Java programming language.
It needs a Java Runtime Environment version 1.8 or later 
installed on your system in order to function properly.
See: http://www.oracle.com/technetwork/java/javase/downloads/

You can start the application with `mvn spring-boot:run`.  
Consider having Maven 3.8+ installed.

The default TUIO host is 127.0.0.1 (localhost)
The default TUIO port is 3333.
You can change it in [application.properties](src/main/resources/application.properties).

## Handling:
* Dragging objects onto the white table surface will make them visible
  for the "virtual sensor". An activated object is marked with a green frame.
  The corresponding object ID number is written onto the object surface.

* Shift-Dragging an object is equivalent to lifting an object from the surface.

* Right-Dragging an object rotates it.
  The black bar within the object indicates its current rotation angle. 

* Shift-Right-Dragging is changing the face of volumetric objects.
  Volumetric objects (e.g. cubes) are marked with a dot in their centre.

* Simple mouse-dragging on the table creates cursor events

* Press SHIFT while dragging to create a sticky cursor 

* Continue to manipulate any sticky cursor by moving its gray area 

* Remove sticky cursors by SHIFT clicking in its gray area 

* Press CTRL while dragging to add a cursor to a group

* Moving any cursor of a group will move the other group members

* Remove cursors from a group by CTRL clicking in its dark gray area 

* Choosing "Reset" from the "File" menu, resets the Simulator to its original state. 

* Enabling "Verbose" in the "File" menu, will print TUIO events to the console. 

## Session Configuration:
* see resources/config.xml for an example
* generic object types:
  name, shape, colour, description
* the current object list:
  type, faces, position, angle

## References:
This application uses the JavaOSC OpenSound Control library.
See [http://www.illposed.com/software/javaosc.html](http://www.illposed.com/software/javaosc.html)
for more information and the source code.
