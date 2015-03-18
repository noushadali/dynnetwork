# Index #

---




# Introduction #

DynNetwork brings the import, visualization, and analysis of dynamical networks to Cytoscape. Based on the very light weight event-based implementation, it makes a very exciting tool to animate dynamic graphical data.

# Installation #

---


DynNetwork is a OSGi bundle application of the revamped Cytoscape 3.0.

  * Download [Cytoscape 3.0/3.1 beta](http://code.cytoscape.org/jenkins/job/Cytoscape%203%20GUI%20Distribution/lastBuild/org.cytoscape.distribution$cytoscape/)
  * Install DynNetwork from [AppStore](http://apps.cytoscape.org/apps/dynnetwork)
  * ... or install/update the application in Cytoscape with App -> AppManager -> Install Apps -> DynNetwork v.1.1.0.beta
  * ...or download the latest build [dyn-vx.x.x.jar](http://code.google.com/p/dynnetwork/source/browse/DynNetwork/target/dyn-1.3.0-beta.jar) and save it under CytoscapeConfiguration/3/apps/installed in your home directory
  * Dynamic XGMML examples: [examples](http://code.google.com/p/dynnetwork/downloads/list)

# Create Dynamic Networks #

---


Currently we do not support custom generation of XGMML files, you have to do it yourself. An easy way is to export 3 dimensional matrices to XGMML is with the help of a [Matlab script](http://dynnetwork.googlecode.com/files/matrix2xgmml.m).

# Import Dynamic Networks #

---


To import dynamic networks from XGMML files:

  * Select _File_ > _Import_ > _Dynamic Network_ > _XGMML File..._
  * Select xgmml file to import

For more information on the dynamic XGMML Specification, see [here](http://code.google.com/p/dynnetwork/wiki/DynamicXGMML).

# Dynamic Network GUI #

---


The dynamic visualization of networks can be controlled from the **Dynamic Network** Tab in the **Control Panel**. The panel provides several components to control the visualization of dynamic data:

| **Component** | **Description** |
|:--------------|:----------------|
| Dynamic Visualization Slider | Drag the slider to choose a time point. |
| <<Play Button | Animate backwards in time. |
| Stop Button | Stop animation. |
| Play>> Button | Animate forward in time. |
| Record Button | Switch on/off recording of PNG image sequences in the form: fileName\_timeStamp\_time\_simulationTime.svg |
| Time resolution | Controls the time resolution of the slider (1 over x time steps). |
| Time smoothness | Controls the time . Longer time means smoother transitions, but also a more heavy computational load. |
| VizMap Range | When creating VizMap from the Editor, the range of values can be remapped to the whole dynamical range. |
| Node/edge visibility | Option currently not available. |
| Metrics | Gives information on the number of currently displayed nodes and edges over the total. |


Additional operations are available from the menu bar

| **Menu Item** | **Description** |
|:--------------|:----------------|
| Select>Select all visible nodes | Select all nodes currently visible. |
| Select>Select all visible edges | Select all edges currently visible. |

![http://n.ethz.ch/student/sabpfist/GSoC/images/GUI.png](http://n.ethz.ch/student/sabpfist/GSoC/images/GUI.png)


# Can I use standard Cytoscape functions? #

---


The general answer is **NO**.

Dynamic networks require a very specific software architecture, and many functions in Cytoscape are not designed to be able to deal with that. This means that you should uniquely make use of the functionalities provided by DynNetwork itself. Assume that everything that is not described in the documentation, does not work.

# Dynamic Layout #

---


The dynamic layout algorithms use the events (changes in nodes and edges) to trigger a change in the node positions. Because of the implementation strategy, the position of nodes can be moved during the animation of the graph, without disturbing the final layout.

To load a dynamic layout on an imported dynamic network:

  * Select _Layout_ > _Dynamic Layouts_
  * Several dynamic layout algorithms have been implemented.

| **Name** | **Options** | **Description** |
|:---------|:------------|:----------------|
| Kamada Kawai DynLayout |  | Move nodes to optimal positions according to the Kamada Kawai algorithm when a node or edge event is triggered. |
|  | Distance edge attribute¹ | Compute node distances with the given edge attribute (the distance is set to 1 for each edge if none attribute is selected). It is currently limited to positive edge distances. If you have infinite distances, we suggest to set them slightly above the maximal distance in the graph. |
|  | Maximum iterations | Maximum number of iterations allowed. The number of iterations is dynamically adjusted so that short events have fewer iterations than longer events. |
|  | Past events | Number of past events to consider (to prevent jumps). |
|  | Future events² | Number of future events to consider (to prevent jumps). |
|  | Allow node exchange | Allows node position to be exchanged (avoid local minima). |
|  | Allow autoscaling | Divide timeslices by graph diameter. |
| Perfuse DynLayout |  | Adaptation of Cytoscape Perfuse Layout to dynamic networks. The layout is based on spring and N-body repulsive forces. |
|  | Maximum iterations | Maximum number of iterations allowed. The number of iterations is dynamically adjusted so that short events have fewer iterations than longer events. |
|  | Past events | Number of past events to consider (to prevent jumps). |
|  | Future events² | Number of future events to consider (to prevent jumps). |
| Remove DynLayout |  | Remove current dynamic layout from the visualization. |

¹ The Dijkstra's algorithm takes a distance measure between nodes, and not a weight!

² Consideration of future events can be used to further smooth the graph visualization.

# Export Dynamic Networks #

---


## Export PNG Image Sequences ##

It is possible to export the dynamic network visualization to a sequence of png image files:

  * Select the desired setting for your network visualization (time settings, smoothing settings, initial position, zoom, VizMap, etc...)
  * The total number of frames is given by: n = 25 x timeSmoothness/1000 x (startTime-endTime) x timeResolution
  * Select _Record_ in the _Dynamic Network_ panel and type the name of the files (file extension is not necessary). The button will turn red.
  * Select a _Play_ button or drag the time slider. Any movement you do now will automatically dump PNG image files.
  * Select _Record_ again to stop recording. The button will turn gray.

## Encode PNG Image Sequences into a video ##

For **Linux/Mac users**:

  * Install _ImageMagik_ converter, and _ffmpeg_
  * Create a text file named _png2mp4.sh_ in the folder where the PNG images are and insert:
```
#!/usr/bin/env bash

## iterate through png files
n=0
for f in ./*.png; do \

  ## resize images
  convert $f -depth 8 -resize 1280x720 -background white -gravity center -extent 1280x720 $f

  ## rename files in a sequence as "img000000xx.png"
  cp -v "$f" "$(printf "img%05d" $n).png"

  n=$(($n+1))
done

## encode video to mp4/H264
echo 'encode images...'
ffmpeg -r 25 -i img%05d.png -s 1280x720 -vcodec libx264 -preset veryslow -crf 15 -b 1024k -an -f mp4 movie.mp4
```
> with encoding options:
    * "%05d" inserts 5 zeros when converting image name into a sequence, use more if you need
    * r is the desired frame rate
    * b is the desired bitrate
    * crf is the compression quality (choose 0 for lossless compression)

  * Change file permission to be able to execute the script
  * Encode video by running the bash script in the terminal (or double click on the file):
```
./png2mp4.sh
```

For **Windows users**:

Install [VirtualDub](http://virtualdub.sourceforge.net/). It's an excellent software that allows you to import png image sequences and encode avi movies. More information [here](http://www.virtualdub.org/).