# Introduction #

DynNetwork is a plugin developed during GSoC 2012 to visualize and analyze dynamic data in Cytoscape 3.0. Do you want to help us bring the application up to the next level? Here are few ideas, but feel free to contribute with your own!


# IDEA 31: Visualization of Dynamic Graphs in Cytoscape #

**GOAL**: implementing the following features to improve dynamic network analysis (you can decide on which ones to focus)

**Implementation of graph metrics**
  * **Dynamic graph metrics**: out-degree, in-degree, betweeness, node centrality, etc. (directed/undirected). You will propose a list of metrics and ways that you intend to implement, or integrate from preexisting code.
  * **Dynamic visualization of metrics**: DynNetwork provides the code to map computed dynamic metrics to a dynamic update of the graphical visualization properties (node size, color, edge width, etc...). Your task will be to integrate your code with the existing interfaces to create an animated graph metrics.
  * **Running average**: currently DynNetwork provides methods to obtain a network snapshots in time through DynNetworkSnapshot. Depending on the user interest, they can be instantaneous snapshots, or snaphsots averaged over a certain time interval. You will integrate your code with this preexisting interfaces.

Look at this for a dynamic animation of visual properties (currently loaded through the xgmml):

http://code.google.com/p/dynnetwork/downloads/detail?name=dynNet_ClustersWithVizMap.xgmml&can=2&q=#makechanges

**Enhance dynamic data visualization**
  * **Timeline GUI**: select a node/edge, select a dynamic attribute of that edge, and view the attribute as a time­series graph with the current position marked. We could use for example [wikipedia.org/wiki/Sparkline sparkline charts].
  * **Fish-eye lens** - fish-­eye lens feature to explore graph details.
  * **Import from tables** - import of dynamic data directly from table sheet formats.

**Multilevel dynamic layout**
  * **Yifan Hu's Multilevel algorithm**: computation of dynamic layouts is expensive, since they are precomputed on the entire time range. You will implement and test multilevel alghoritms to improve computational time ([info](http://www.mathematica-journal.com/issue/v10i1/graph_draw.html)).

You are expected to test the usefulness of your implementation on a test database. Some suggestions (we prefer biologically oriented databases):
  * Cancer proteomics database
  * Gene expression Atlas during mouse brain development (http://www.brain-map.org/)
  * Soccer player configurations (http://www.mcfc.co.uk/the-club/mcfc-analytics/data-available)

# IDEA 32: Real-time Streaming of Dynamic Graphs in Cytoscape #

**GOAL**: implement interfaces and software architecture to provide streaming of dynamic graph events. We do not wish to provide streaming of graphical network in the common sense (i.e. streaming of data from social networks like twitter), but rather providing the ability to incrementally load data from very large databases, such as genomic databases, etc. We can imagine a server that has a graph database that contains the network topology and attributes. The server would extract pieces of the network requested by the client from the database and send them back. Different datasets may require different server implementations, but the client would remain the same. This is a highly requested feature from the Cytoscape community!

This project is independent from DynNetwork, and you will build on a preexisting application, Evolvo (a first-trial application and server to explore large databases by expansion and collapsing of a hierarchy of nodes). Your task will be:
  * evaluate the current implementation of [Evolvo](https://github.com/samadlotia/evolvo)
  * improve or redisign the application

The databases we will access are [Neo4j](http://www.neo4j.org/) based, such as [bio4j](http://bio4j.com/).


# Things you should read #

Excellent review, and everything you need to know to understand DyNetwork code:
  * [Introduction to dynamic networks](http://www.cmu.edu/joss/content/articles/volume7/deMollMcFarland/)


# To do list (warm up) #

  * you should get familiar with how Cytoscape core is implemented [here](http://wiki.cytoscape.org/Cytoscape_3/CoreDevelopment/Architecture) and [here](http://wiki.cytoscape.org/Cytoscape_3/CoreDevelopment/APIOverview)
  * get Cytoscape code from Git [here](https://github.com/cytoscape), follow the [instructions](http://wiki.cytoscape.org/Cytoscape_3/CoreDevelopment/GettingStartedGithub)
  * use Maven and Eclipse IDE to compile the code as described [here](.md)

IDEA 31
  * once you get familiar with Cytoscape, you can have a look at DynNetwork [here](http://code.google.com/p/dynnetwork/wiki/DyNetworkClassStructure)
  * get DynNetwork code from Git [here](http://code.google.com/p/dynnetwork/source/checkout)
  * as a warm up exercise, build the app and deploy it to Cytoscape

IDEA 32
  * once you get familiar with Cytoscape, you can have a look at [Evolvo](https://github.com/samadlotia/evolvo)