# Index #

---




# Introduction #

---


[XGMML (the eXtensible Graph Markup and Modeling Language)](http://en.wikipedia.org/wiki/XGMML) is an XML application based on GML which is used for graph description. XGMML files are specified by the extension .xgmml.

Cytoscape, an open source bioinformatics software platform for visualizing molecular interaction networks, can load and save networks and node/edge attributes in XGMML. DynNetwork is a plugin to extends the use of XGMML to dynamical networks, i.e. networks whose node/edges and attributes change over time.

# XGMML Specification #

---


## XGMML Nodes/Edges Specification ##

An XGMML document describes a graph structure. The root element is **graph** and it can contain **node**, **edge** and **att** (attribute) elements. The graph element describes the general properties of the graph, the node element describes a node of a graph and the edge element describes an edge of a graph. Additional information for graphs, nodes and edges can be attached using the att element. The following XGMML document describes a graph with two nodes and one edge.

Below an example of smallExample.xgmml:

```
<?xml version="1.0"?>
<graph label="small example" directed="1" id="5">
    <node label="Node 1" id="1"/>
    <node label="Node 2" id="2"/>
    <edge label="Edge 1" source="1" target="2"/>
</graph>
```

Every graph and node element should contain an "id" (or in it's absence, "label" will be used instead). Every edge should contain a "target" and "source", which defines the "id" (or "label" if "id" does not exist) of the nodes to be connected. The order by which node and edge are listed is not important. Edges that connect to nodes without an explicit definition in the XGMML will be ignored.

This example is still valid:
```
<?xml version="1.0"?>
<graph label="small example" directed="1" id="5">
    <node label="Node 1" id="1"/>
    <edge label="Edge 1" source="1" target="2"/>
    <node label="Node 2" id="2"/>
</graph>
```

## XGMML Data Types Specification ##

XGMML data types (**attributes**) are stored in the value tag and defined based on the following XML data types:

  * type="boolean": Boolean values are 0 for false and 1 for True.
  * type="integer": Integer is an integer.
  * type="real": Real is a double.
  * type="string": String is a string.

Every graph. node and edge can have an unlimited number of attributes.

```
<?xml version="1.0"?>
<graph label="small example" directed="1">
  <node label="A" id="1">
    <att name="size" type="integer" value="24"/>
    <att name="val" type="real" value="4.7"/>
    <att name="isTrue" type="boolean" value="false"/>
  </node>
</graph>
```

You can specify all attributes at once, or in different points of the XGMML.

This example is still valid:
```
<?xml version="1.0"?>
<graph label="small example" directed="1">
  <node label="A" id="1">
    <att name="size" type="integer" value="24"/>
  </node>
  <node label="A" id="1">
    <att name="val" type="real" value="4.7"/>
  </node>
  <node label="A" id="1">
    <att name="isTrue" type="boolean" value="false"/>
  </node>
</graph>
```

## Nested XGMML Specification ##

Not supported.

## XGMML Graphics Specification ##

Static graphic attributes for nodes and edges can be specified with **graphics**. Supported graphics types are:

  * **Graph graphics** attributes
    * fill="...": network background color (hexadecimal)
  * **Node graphics** attributes
    * type="...": node shape ("RECTANGLE", "RECT", "BOX", "ROUND\_RECTANGLE", "ROUND\_RECT", "TRIANGLE", "PARALLELOGRAM", 'RHOMBUS", "DIAMOND", "ELLIPSE", VER\_ELLIPSE, "HOR\_ELLIPSE", "CIRCLE", "HEXAGON", "OCTAGON")
    * h="...": node height (double)
    * w="...": node width (double)
    * size="...": node size, overrides width and height (double)
    * fill="...": node fill color (hexadecimal)
    * width="...": node boder width (double)
    * outline="...": node boder color (hexadecimal)
    * transparency="...": node transparency (integer 0-255)
  * **Edge graphics** attributes
    * width="...": edge line width (double)
    * fill="...": edge line color (hexadecimal)
    * transparency="...": edge transparency (integer 0-255)

Here an example:

```
<?xml version="1.0"?>
<graph label="small example" directed="1">
   <graphics fill="#000000"/>
   <node label="A" id="1">
      <att name="size" type="integer" value="24"/>
      <graphics type="ELLIPSE" h="35.0" w="35.0" fill="#cc00ff" width="0" outline="#000000" transparency="255"/>
    </node>
    <node label="Node 2" id="2"/>
    <edge label="Edge 1" source="1" target="2">
       <graphics width="1" fill="#000000" transparency="55"/>
    </edge>
</graph>
```

Note that the order by which graphics attributes are specified does not matter.

## XGMML Layout Specification ##

Static layout attributes (position) for nodes can be specified with **layout**. Supported layout types are:

  * **Node layout** attributes
    * x="...": node X position (double)
    * y="...": node Y position (double)

Here an example:
```
<?xml version="1.0"?>
<graph label="small example" directed="1">
   <graphics fill="#000000"/>
   <node label="A" id="1">
      <att name="size" type="integer" value="24"/>
      <layout x="506.4" y="559.4"/>
    </node>
    <node label="Node 2" id="2"/>
    <edge label="Edge 1" source="1" target="2"/>
</graph>
```


# Dynamic XGMML Specification #

---


## Dynamic XGMML Time Intervals Specification ##

Dynamic XGMML are simply XGMML that contain additional time information. Time information in form of time intervals for **graph**, **node**, **edge**, and **att** can be provided by adding a "start" and "end" tags (double values). The time interval is of the form [start, end[, with start time point included and end time point excluded, except that for the maximal end time point, which is always included.

Not all elements have to bee associated with a time interval: missing "start" and "end" values are automatically defined as Double.NEGATIVE\_INFINITY or Double.POSITIVE\_INFINITY (or the minimal/maximal time value of the corresponding **graph**, **node** or **edge**).

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<graph label="small example" directed="1" start="0">
  <node label="A" id="1" start="0">
    <att name="size" type="integer" value="24" start="2" end="4"/>
    <att name="size" type="integer" value="34" start="4" />
    <att name="confirmed" type="boolean" value="true" end="5" start="1"/>
  </node>
  <node label="B" id="2" start="-1" end="2">
    <att name="size" type="integer" value="16" start="-1" end="2"/>
  </node>
  <edge label="A-B" source="1" target="2" end="5">
    <att name="weight" type="real" value="2" start="1" end="4.5"/>
  </edge>
  <node label="C" id="3" />
</graph>
```

## Dynamic XGMML Graphics Specification ##

You have two options:

  * use Vizmaps inside Cytoscape (discouraged, since was not really designed for dynamic data, is painfully slow, and accuracy cannot be guaranteed)
  * directly specify dynamic graphical attributes for graph, node and edges in the XGMML specification

Dynamic graphic attributes for nodes and edges can be specified with **graphics**.
Supported graphics types are:

  * **Graph graphics** attributes
    * fill="...": network background color (hexadecimal)
  * **Node graphics** attributes
    * type="...": node shape ("RECTANGLE", "RECT", "BOX", "ROUND\_RECTANGLE", "ROUND\_RECT", "TRIANGLE", "PARALLELOGRAM", 'RHOMBUS", "DIAMOND", "ELLIPSE", VER\_ELLIPSE, "HOR\_ELLIPSE", "CIRCLE", "HEXAGON", "OCTAGON")
    * h="...": node height (double)
    * w="...": node width (double)
    * size="...": node size, overrides width and height (double)
    * fill="...": node fill color (hexadecimal)
    * width="...": node boder width (double)
    * outline="...": node boder color (hexadecimal)
    * transparency="...": node transparency (integer 0-255)
  * **Edge graphics** attributes
    * width="...": edge line width (double)
    * fill="...": edge line color (hexadecimal)
    * transparency="...": edge transparency (integer 0-255)

Here an example:
```
<?xml version="1.0"?>
<graph label="small example" directed="1">
   <node label="A" id="1">
      <graphics size="10" fill="#cc00ff" start="0" end="10"/>
      <graphics size="15.4" fill="#ccffff"" end="20"/>
      <graphics size="50" fill="#ffffff" end="30"/>
   </node>
   <node label="B" id="2">
      <graphics size="80" fill="#cc0000" start="0" end="10"/>
   </node>
   <node label="B" id="2">
      <graphics size="80" fill="#cc0000" start="10" end="20"/>
   </node>
   <node label="B" id="2">
      <graphics size="50" fill="#ffffff" start="20" end="30"/>
   </node>
   <edge label="A-B" source="1" target="2" start="20" end="30"">
      <graphics transparency="50" start="20" end="25"/>
      <graphics transparency="255" start="25" end="30"/>
   </edge>
</graph>
```

Additional visual properties can be specified directly with the VizMap Panel in Cytoscape.

## Dynamic XGMML Layout Specification ##

There are two ways to attach dynamic behavior to nodes:

  * internally from Cytoscape, with one of the dynamic layout algorithms
  * directly from the dynamic XGMML specification

Dynamic layout attributes (position) for nodes can be specified with **layout**. Supported layout types are:

  * **Node layout** attributes
    * x="...": node X position (double)
    * y="...": node Y position (double)

Here an example of how to specify nodes positions at different times:
```
<?xml version="1.0"?>
<graph label="small example" directed="1">
   <node label="A" id="1">
      <layout x="506.4" y="559.4" start="0" end="10"/>
      <layout x="21" y="533.4" start="10" end="20"/>
      <layout x="0.4" y="500.4" start="20" end="30"/>
   </node>
</graph>
```

## Dynamic XGMML Parser Errors and Warnings ##

Several errors and warnings could occur when importing dynamic XGMML (output from the command line):

  * **ERRORS** cannot be corrected and prevents the network from loading
    * the specification of the XGMML file is invalid.
```
java.io.IOException: Could not parse XGMML file.
...
Caused by: org.xml.sax.SAXException: Fatal parsing error on line ...
```
    * the specification of the interval time is invalid.
```
XGMML Parser Error: invalid interval for ...
...
java.io.IOException: Could not parse XGMML file.
...
Caused by: java.lang.IndexOutOfBoundsException
```

  * **WARNINGS** can be partially corrected and cause the rising of a warning message.
    * minor inconsistencies of time intervals can be solved by skipping the incorrect element and rise warnings, for instance attribute values whose time interval lies outside the range of the parent:
```
XGMML Parser Warning: skipped ...
```

Warnings are currently switched off because of performance.