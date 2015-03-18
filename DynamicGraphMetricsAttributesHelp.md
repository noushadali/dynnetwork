Computing graph metrics using DynNetwork and viewing plots.

# How to compute network centralities using DynNetwork? #

Once you have successfully installed DynNetwork in Cytoscape 3, you may import a dynamic network from an XGMML/CSV file. Sample files can be found [here](https://code.google.com/p/dynnetwork/downloads/list).

To compute the network centralities click on Apps -> Dynamic Graph Metrics.

This will initiate the computation of the above mentioned network centralities. Since the computation of network centralities is expensive this may take time depending on the size of the network. Once the centralities are computated, columns for each centrality will be added to the Node Table and you should be able to see their values for a particular node by selecting the node. As you move the time slider of the DynNetwork these values will get updated depending on their value at that time.

1. Apps -> Dynamic Graph Metrics

<img src='https://github.com/Jimmy-Morzaria/DynNetwork/blob/master/DynNetworkScreenshots/DynNetwork1.png?raw=true' alt='some_text'>

You would also find a new tab in the Controls Panel of Cytoscape for Dynamic Graph Metrics which would have a node and an edge attributes table.<br>
<br>
2. Graph Metrics tab in Controls Panel<br>
<br>
<img src='https://github.com/Jimmy-Morzaria/DynNetwork/blob/master/DynNetworkScreenshots/DynNetwork2.png?raw=true' alt='some_text'>

To plot charts of centrality values against time, you may select one or more node/edge attributes from the table, then select the nodes/edges for which you want the selected attributes to be plotted and click on the Plot Selected Attributes button.<br>
<br>
3. Selecting and plotting attributes<br>
<br>
<img src='https://github.com/Jimmy-Morzaria/DynNetwork/blob/master/DynNetworkScreenshots/DynNetwork3.png?raw=true' alt='some_text'>

This would give you a chart in the Results Panel of Cytoscape in separate tab.<br>
<br>
4. Chart in Results Panel<br>
<br>
<img src='https://github.com/Jimmy-Morzaria/DynNetwork/blob/master/DynNetworkScreenshots/DynNetwork4.png?raw=true' alt='some_text'>

You may save the chart as jpg/png/svg file, save the chart data in a text file and enlarge the chart using the buttons provided for each one of them. If you need more help or definitions of the centralities used in computation, there is also a help button which you could use.<br>
<br>
<br>
<h1>Expected file format for CSV import</h1>

If you would like to import dynamic networks from a csv file, you may now do so provided your file is in the following format. For some this may be a lot easier than importing a dynamic network from XGMML as it is easy to write export scripts from other applications or modify spreadsheet data.  The underlying concept is that of an arc-list format (as opposed to a matrix format) with separate sections for node and arc records. The entries for each record are <b>comma-delimited</b>, and are order-insensitive because they are defined by column headings rather than inline tags. However, most of the attributes are optional and can be omitted, and the order of the columns is not important. The required attributes are some kind of unique identifying tag, and time coordinates for the event.<br>
<br>
<b>The first item in the row must be "NodeId" </b>, as this is how the parser finds the row. Also, the data must contain a column for NodeId, as this is the nodes' unique identifier and is used to reference "to" and "from" nodes in the arc data. The rest of the column headings may be in any order. This part of the csv file constitutes the node records/table. You may want to refer to the graphic attributes supported by Cytoscape.<br>
<br>
<table><thead><th> <b>NodeId</b> </th><th> <b>Label</b> </th><th> <b>Happiness</b> </th><th> <b>type</b> </th><th> <b>fill</b> </th><th> <b>transparency</b> </th><th> <b>borderWidth</b> </th><th> <b>height</b> </th><th> <b>width</b> </th><th> <b>size</b> </th><th> <b>StartTime</b> </th><th> <b>EndTime</b> </th></thead><tbody></tbody></table>



1,node_1,2.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
2,node_2,3.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
3,node_3,4.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
4,node_4,5.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
5,node_5,6.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
6,node_6,7.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
7,node_7,8.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
8,node_8,9.2,ELLIPSE,#410038,255,1,18,18,18.4618,0,2<br>
1,node_1,2.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
2,node_2,3.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
3,node_3,4.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
4,node_4,5.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
5,node_5,6.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
6,node_6,7.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
7,node_7,8.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,4<br>
8,node_8,9.1,ELLIPSE,#410038,255,1,21,21,21.4624,2,3<br>
8,node_8,3.1,ELLIPSE,#410038,255,1,21,21,21.4624,3,3.1<br>
8,node_8,7.1,ELLIPSE,#410038,255,1,21,21,21.4624,3.1,4<br>

After the node records should be a row of column headings for the arc records. <b> This line must begin with "FromId" </b>, as this is how the parser knows that the end of the node records has been reached. The rest of the entries for the arc column headings can be in any order.<br>
<br>
<table><thead><th> <b>FromId</b> </th><th> <b>ToId</b> </th><th> <b>weight</b> </th><th> <b>width</b> </th><th> <b>fill</b> </th><th> <b>transparency</b> </th><th> <b>StartTime</b></th><th> <b>EndTime</b> </th></thead><tbody></tbody></table>



1,2,10,1,#410038,255,0,2<br>
2,3,11,1,#410038,255,0,2<br>
3,4,12,1,#410038,255,0,2<br>
4,5,13,1,#410038,255,0,2<br>
5,6,14,1,#410038,255,0,2<br>
6,7,15,1,#410038,255,0,2<br>
7,8,16,1,#410038,255,0,2<br>
1,3,8,1,#410038,255,2,4<br>
2,4,9,1,#410038,255,2,4<br>
3,5,10,1,#410038,255,2,4<br>
4,6,11,1,#410038,255,2,4<br>
5,7,12,1,#410038,255,2,4<br>
6,8,13,1,#410038,255,2,4<br>
7,1,14,1,#410038,255,2,4<br>
8,2,15,1,#410038,255,2,4<br>
1,2,16,1,#410038,255,2,4<br>