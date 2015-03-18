# Index #

---




# Introduction #

---


**DynNetwork** is an OSGi bundle for Cytoscape 3.0 consisting of several classes that extend the capabilities of the original Cytoscape classes to support the import, visualization, and layout of dynamic networks. Here I briefly review the most important points of my implementation strategy.

# General Code Structure #

---


The implementation of **DynNetwork** is divided into three sections (_Figure 1_).
  * <font color='green'>Dynamic Network Classes</font>**: dynamic XGMML import into DynNetwork class, which stores dynamical information and uses CyNetwork to represent the current network state.
  ***<font color='blue'>Dynamic Network View Classes</font>**: visualization of the network by the creation of DynNetworkView, which provides the methods to access and update the current CyNetworkView.
  ***<font color='orange'>Dynamic VizMap Classes</font>**: dynamic visualization of graphical attributes with smoothing, stored in DynVizMap.
  ***<font color='magenta'>Dynamic Network Layout Classes</font>**: dynamic layout algorithm, where optimal node position for different time points are stored in DynLayout.
  ***<font color='black'>Dynamic Network User Interface Classes</font>**: interface between the user and the program, such as action menus and a JPanel to control time selection and other options.**

![http://n.ethz.ch/student/sabpfist/GSoC/images/Diagram.png](http://n.ethz.ch/student/sabpfist/GSoC/images/Diagram.png)

_Figure 1 - Simplified class structure of DynNetwork_

## Dynamic Network Classes ##

Loading of dynamic networks from XGMML specifications happens in several stages (_Figure 2_):
  * XGMML import is handled by **XGMMLReader**, **XGMMLParser**, and **DynHandlerXGMML**.
  * The construction of the dynamic network is directed by **DynNetworkFactory**, which is responsible for constructing the dynamic network (**CyNetwork** network, and its associated dynamic network **DynNetwork**).
  * The mapping between a **CyNetwork** and it's corresponding **DynNetwork** is stored in the **DynNetworkManager**.
  * **DynNetwork** holdes dynamic information in form of Hashmaps that map each element or attribute in CyNetwork to a **DynAttribute**. Dynamic events are represented by time intervals **DynInterval**, which store a value, a time of start, and an end time. A list of the time intervals for a each element is stored in **DynAttribute**.
  * Because linearly searching of intervals for a given time through the list of intervals in each **DynAttribute** would be prohibitive, **DynIntervals** are additionally stored in a special structures referred as **DynIntervalTree**, which provides fast searching over the data structure.
  * To minimize the maintenance time of Interval Trees, the data is partitioned in six separated Interval Trees: graphs, nodes, edges, graph attributes, node attributes, and edge attributes.

![http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramG.png](http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramG.png)

_Figure 2 - Simplified class structure to store the network and it's dynamic data_

## Dynamic Network View Classes ##

After the loading of the network structure is completed, the generation of the network visualization starts (_Figure 3_):

  * The construction of the dynamic network is directed by **DynNetworkViewFactory**, which is responsible for constructing the dynamic network view (**CyNetworkView** network, and its associated dynamic network view **DynNetworkView**).
  * The mapping between a **CyNetworkView** and it's corresponding **DynNetworkView** is stored in the **DynNetworkViewManager**.

![http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramB.png](http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramB.png)

_Figure 3 - Simplified class structure to store the network view and it's dynamic data_

## Dynamic VizMap Classes ##

It is possible to attach a dynamic visualization of graphical attributes with the use of **DynVizMap** (_Figure 4_):

  * **DynVizMapTask** is responsible for generating dynamic layouts **DynVizMap**.
  * Each **DynVizMap** is associated with a network within the **DynVizMapManager**.
  * **DynVizMap** stores information in from of interval trees, where each **DynInterval** contains the graphical attributes of a graph, node, or edge in a time range.
  * This implementation dissociate the visual mappings from the view, making easy to attach different dynamic visual mappings on the same view.

![http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramO.png](http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramO.png)

_Figure 4 - Simplified class structure to store the network VizMap and it's dynamic data_

## Dynamic Network Layout Classes ##

Additionally, **DynNetworkView** can be animated by specifying a dynamical layout of the nodes, that is the node positions in time (_Figure 5_):

  * **DynLayoutAlgorithmTask** is responsible for generating dynamic layouts **DynLayout**.
  * Each **DynLayout** is associated with a network within the **DynLayoutManager**.
  * **DynLayout** stores information in from of interval trees, where each **DynInterval** contains either the X or Y position of a node in a time range. **DynInterval** can be associated either with a X or Y node position **DynAttribute**, and controls the movement of nodes.
  * This implementation dissociate the view from the layout, making easy to test different layouts on the same view, or the custom design of layout algorithms.

![http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramV.png](http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramV.png)

_Figure 5 - Simplified class structure to store the network layout and it's dynamic data_

## Dynamic Network User Interface Classes ##

DynNetwork can be accessed by the user in three ways (_Figure 6_):

  * **MenuActionLoadXGGML** triggers the execution of **DynNetowrkFactory** and **DynNetworkViewFactory**.
  * **DynCytoPanel** provides the user interface to control the network visualization.
  * Dynamic networks can be animated through the loading of dynamic layouts **DynLayout**.

![http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramW.png](http://n.ethz.ch/student/sabpfist/GSoC/images/DiagramW.png)

_Figure 6 - Simplified class structure for the user interface_

## Dynamic Network Time Snapshot Slice ##

The class **DynNetworkSnapshot** provides an interface to filter a network based on a time range. This interface is currently used by the layout visualization algorithms to get network representation in time slices between events. This class would be also useful as an interface for already implemented clustering algorithms.

## API Description ##

DynNetwork provides several interfaces to the major classes. However, currently none of the interfaces is exposed. See [API documentation](http://dynnetwork.googlecode.com/git/DynNetwork/doc/index.html) for more details.

# Design Patterns #

---


Visualization of dynamic networks is an highly computationally intensive task, and therefore I was forced to choose implementation strategies that minimize the number of read and write operations needed when displaying and updating the network. I describe in detail the strategies below:

## Event Based Class Structure ##

An ideal design for dynamic graphs are event-based architectures:

  * Events report changes in the network structure or network visualization, **Sources** classes produce events, and **Sink** classes react to events. For each source, they may be several registered Sinks.
  * **DynHandlerXGMML** and **LoadDynNetwork** are examples of Sources.
  * **DynNetworkFactory** and **DynNetworkViewFactory** are examples of Sinks.

Another type of events carries information whether there have been structural modification in the current network, for instance upon changing the visualization time. Those events are returned upon searching an **DynIntervalTree**. If we search for changes, the returned events contain additional information in their **isOn** parameter:
  * **on value**: the time interval has been turned on in respect to the previous search (on value are used to store the actual value of the interval)
  * **off value**: the time interval has been turned off in respect to the previous search (off events can store default values without the need of specifying other intervals)

## Interval Tree Based Network Representation ##

How are event best represented in a dynamic network? A compelling representation is achieved through interval trees:

  * Events are stored as time intervals **DynInterval**. An interval corresponds to a value that exists for a given period of time. the value may correspond to the existence of a node, or the value taken by an attribute.
  * Events are triggered when the selected time overlaps with their time range.
  * Searching for overlapping intervals is best achieved by sorting the intervals into balanced red and black trees, which guaratuee a minimal number of comparison operations.
  * An implementation of the **DynIntervalTree** O(log n) with insertion and deletion time was implemented according to Cormen, Thomas H.; Leiserson, Charles E.; Rivest, Ronald L.; Stein, Clifford (2001), Introduction to Algorithms (2nd ed.), MIT Press and McGraw-Hill. The implementation was extended to be able to deal with multiple equivalent intervals.

## Interval Tree Based Visualization ##

Two approaches can be used to visualize dynamic networks:

  * **Representations** describe the network visualization at a given time point in it's entirety (state and position of every element). This strategy comes from the idea of slicing the dynamic network into snapshots, but comes at high computational costs, since it requires iterating over all elements at each time step.
  * **Transformations** describe changes in the network, rather than it's current properties (changes of states and position of each element). Obviously this approach is much better suited since it's sparse and event-driven.

I extended the event-based approach to the dynamic visualization as well. Node positions are stored in a **DynIntervalTree**, which notifies the network when positional properties change. For each network view **DynNetworkView**, custom layout algorithms **DynLayout** can be designed and applied to the current network visualization.

## Blocking Queue Visualization Update ##

**DynCytoPanel** controls the visualization by starting separate Threads responsible for the update of the visualization. Since the decision of which part of the network to update is taken by identifying only changing properties of the network, it is of uttermost importance that Threads are executed in order of their instantiation. Therefore, Threads are required at run time to lock to a **BlockingQueue**, which guarantees their sequential execution.

The locks take considerable amount of computational time, however this avoids the processor to be cluttered with newly generated Threads (and waiting Threads are cancelled by their successors).

# Representation of dynamic networks #

---


A dynamic graph is a graph in which nodes represent actuators, and edges represent (directed or undirected) relationships between the actuators. In contrast to static graphs, the collection of nodes and edges are subject to discrete changes, such as additions, deletions, or value changes over time.

Thinking of dynamic networks in snaphots in time may be intuitive, but it's not especially suited to understanding the graph dynamic in time, for instance it is not able to capture clusters of highly connected nodes through time. For example, in the network represented in Figure 7, a static series of snapshots show us that node C and B never share a connection. However, a representation through time clearly shows a path in time leading from C to B.

![http://n.ethz.ch/student/sabpfist/GSoC/images/longitudinalNetworks.png](http://n.ethz.ch/student/sabpfist/GSoC/images/longitudinalNetworks.png)

_Figure 7 - Static versus dynamic network representations_

Two classes are used to represent dynamic data:

  * **DynNetwork Class**: Stores dynamic network information, and provides the methods to access the data.
  * **DynNetworkSnapshot Class**: Provides methods to access dynamic network in form of snaphots in time. Importantly, snapshots can be taken for any desired time range and can be thus used to obtain a moving average representation of the network. The moving average enable us to take in the consideration also the time dimension. If we take snapshots between changes in the network we obtain a description the original data.