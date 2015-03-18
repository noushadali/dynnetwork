[![](http://n.ethz.ch/student/sabpfist/GSoC/images/rss.png)](http://code.google.com/feeds/p/dynnetwork/downloads/basic)

![http://n.ethz.ch/student/sabpfist/GSoC/images/Screenshot.png](http://n.ethz.ch/student/sabpfist/GSoC/images/Screenshot.png)

### What is dynnetwork? ###

DynNetwork is a [OSGi bundle](http://en.wikipedia.org/wiki/OSGi) to provide support for importing, visualizing, and analysing dynamical networks in [Cytoscape 3.0](http://www.cytoscape.org/documentation_cy3_dev.html). Dynamical networks are imported in form of XGMML files, and the dynamics of nodes, edges, attributes can be visualized at the desired time points. To improve visualization, the nodes can be animated with a dynamic force layout algorithm.

### What is the purpose of dynnetwork? ###

Analysis of dynamical information (gene expression, protein interactions, etc...) is become of increasing interest for the scientific community, and the development of open source tools to provide the ability of analyzing such data sets is highly required. Cytoscape offers an ideal and already well developed framework into which network structured data can be accessed, visualized, and analyzed. This is a first step toward implementing a full support for dynamical graphs in Cytoscape.


### What is special about dynnetwork? ###

DynNetwork provides extensions for classes in order to store, retrieve and visualize dynamical information. It is designed on an event based implementation called Interval Trees, which provides faster performances than time snapshot based implementations.

### How can I use dynnetwork? ###

  * Download [Cytoscape 3.0](http://www.cytoscape.org/download.html)
  * Install DynNetwork from the [AppStore](http://apps.cytoscape.org/apps/dynnetwork) or directly under _Apps/App Manager_
  * Dynamic XGMML examples: [examples](http://code.google.com/p/dynnetwork/downloads/list)

Alternative:
download the latest build of [Cytoscape](http://code.cytoscape.org/jenkins/job/cytoscape-3-gui-distribution/lastBuild/org.cytoscape.distribution$cytoscape/) and [DynNetwork](http://code.google.com/p/dynnetwork/source/browse/DynNetwork/target/dyn-1.4.0-beta.jar) (save it under CytoscapeConfiguration/3/apps/installed in your home directory)

### Documentation ###

User documentation:

  * [Project Description](http://code.google.com/p/dynnetwork/wiki/ProjectSummary)
  * [Documentation Index](http://code.google.com/p/dynnetwork/wiki/Index)
  * [User manual](http://code.google.com/p/dynnetwork/wiki/DynNetworkHelp)
  * [Dynamic XGMML documentation](http://code.google.com/p/dynnetwork/wiki/DynamicXGMML)

Developer documentation:

  * [API documentation](http://dynnetwork.googlecode.com/git/DynNetwork/doc/index.html)
  * [Understand the class structure](http://code.google.com/p/dynnetwork/wiki/DyNetworkClassStructure)

### I found a bug! ###

Please report the bug in the [Issues](http://code.google.com/p/dynnetwork/issues/list) tracking section.

### Who are the developers? ###

This plugin has been developed in the context of the **Google Summer of Code 2012** by **Sabina Sara Pfister** and **Google Summer of Code 2013** by **Jimmy Mahesh Morzaria** under the supervision of Jason Montojo. If you are interested in the development, just get in touch with us!


### Links ###

<a href='http://www.youtube.com/watch?feature=player_embedded&v=R6RkMQpOmDs' target='_blank'><img src='http://img.youtube.com/vi/R6RkMQpOmDs/0.jpg' width='425' height=344 /></a>

### Contact ###

Sabina Sara Pfister, (dynamic visualization), xanibas(at)gmail.com

Jimmy Mahesh Morzaria, (dynamic graph metrics), morzaria.jimmy(at)gmail.com