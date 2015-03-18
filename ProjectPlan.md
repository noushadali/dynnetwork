# Index #

---




# Introduction #

---


I would like to bring my contribution to #26 (visualization of dynamic graphs). Not only it would be a desirable feature to integrate time evolution of networks in Cytoscape, but is also a challenging and interesting programming task. Dynamic graphs can be distinguished into graphs for which the dynamics in time is well defined by the dataset (and thus stored as a parameter) and graphs were the dynamics changes because of data streaming and user interactions, which is not predefined and is generally not necessary to store the time evolution for each node. While this project is concerned with the first case, I'll consider implementations that can be extended to the second case.

Because of the nice modular organization of Cytoscape 3.0, this project is well suited for the implementation of interfaces and classes into a separate application. The project is split up into three phases:
  * loading of dynamical data information from XGMML (specify a XGMML format to store dynamical data, implement the XML parser, and store dynamical information into CyNetwork)
  * interactive visualization of dynamical data by selecting a time, or a time range (CyTimeline).
  * analysis of a test case dynamical database

# TimeLine #

---


| **Time** | **Week** | **Deliverable** | **Check** | **Description** |
|:---------|:---------|:----------------|:----------|:----------------|
| Before | 0 |  | OK | In a the warming up phase I'll familiarize with Cytoscape code, and develop a fist basic version of the plugin. |
| **05/21** |  | **D0** | **OK** | **Deliver D0: first DynNetwork plugin release v1.0** |
| 05/21 - 05/28 | 1 |  | OK | Implementing Interval Tree |
| 05/28 - 06/04 | 2 |  | OK | Implementing Event-Based classes |
| 06/04 - 06/11 | 3 |  | OK | Debugging |
| 06/11 - 06/17 | 4 |  | OK | Debugging |
| 06/17 - 06/25 | 5 |  | - | Visit to Allen Brain Institute (pause) |
| 06/25 - 07/02 | 6 |  | OK | Debugging |
| 07/02 - 07/09 | 7 |  | OK | Wrap up D1, documentation and make a detailed working plan for the second term. |
| **12/09** |  | **D1** | **OK** | **Deliver D1: import and display dynamic graphs in Cytoscape 3.0** |
| 07/09 - 07/16 | 8 |  | OK | Implement Interval Tree based visualization |
| 07/16 - 07/23 | 9 |  | OK | Provide support for Vizmap. |
| 07/23 - 07/30 | 10 |  | OK | Debugging. |
| 07/30 - 08/06 | 11 |  | OK | Dynamic Layout algorithm. |
| 08/06 - 08/13 | 12 |  | OK | Debugging. |
| 08/13 - 08/20 | 13 |  | OK | Wrap up D2, documentation. |
| **08/20** |  | **D2** | OK | **Deliver D2: visualization and analysis of dynamic graphs in Cytoscape 3.0** |

## Deliverable D0 ##

  * XGMML Parser (nodes, metanodes, edges, attributes):        <font color='green'><b>OK</b></font>
  * Dynamical Attributes DynAttribute:                        <font color='green'><b>OK</b></font>
  * Time Intervals DynInterval:                               <font color='green'><b>OK</b></font>
  * Temporary data structure DynData:                         <font color='green'><b>OK</b></font>
  * Dynamic Visualization DynNetworkViewTask:                 <font color='green'><b>OK</b></font>


## Deliverable D1 ##

  * Convert application to bundle:                             <font color='green'><b>OK</b></font>
  * Integrated Visualization Panel:                            <font color='green'><b>OK</b></font>
  * Dynamic interval Tree dynIntervalTree:                     <font color='green'><b>OK</b></font>
  * Implement event based data structure:                      <font color='green'><b>OK</b></font>
  * Add option to select visualization time resolution:        <font color='green'><b>OK</b></font>
  * Improve visualization performance:                         <font color='green'><b>OK</b></font>
  * Added documentation:                                       <font color='green'><b>OK</b></font>

## Deliverable D2 ##

  * Implement dynamic visualization framework:                             <font color='green'><b>OK</b></font>
  * Dynamic force based layout algorithm: <font color='green'><b>OK</b></font>
  * Extend force based layout algorithm to weighted graphs: <font color='green'><b>OK</b></font>
  * Provide support for dynamic Vizmap:                             <font color='green'><b>OK</b></font>
  * Added documentation:                                       <font color='green'><b>OK</b></font>
  * Test case:                                       <font color='green'><b>OK</b></font>
  * Video:                                       <font color='green'><b>OK</b></font>

# Time slots available for chat/meetings #

---


| **Sabina** | Zurich |  |  |  |  |  |  |  |  |  | 09 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |  |  |  | 21 | 22 | 23 |
|:-----------|:-------|:-|:-|:-|:-|:-|:-|:-|:-|:-|:---|:---|:---|:---|:---|:---|:---|:---|:---|:-|:-|:-|:---|:---|:---|
| **Jason**  | Toronto |  | 19 | 20 | 21 |  |  |  |  |  |  |  |  |  |  |  |  | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
| **John**   | [Moyhu (AU)](http://maps.google.com.au/maps/myplaces?hl=en&ll=-36.578045,146.378567&spn=0.227731,0.198784&gl=au&ctz=-600&t=m&z=12) | 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |  |  |  |  |  |  | 06 | 07 |