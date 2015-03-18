# Index #

---




# Goals #

---


Goals of this project are:
  * Provide a specification for importing dynamic data in the XGMML format
  * Provide an event-based implementation of dynamic network storage and visualization, for future extension toward fully event driven graphs
  * Fast visualization through implementation of interval trees
  * Dynamic visualization through dynamic Vizmap
  * Implementation of a dynamic layout algorithm to better visualize dynamically changing data


# Open issues #

---


  * hiding/showing of nodes or edges without consistency check (Cytoscape API request)
  * control Z buffer to prevent image flickering in video (Cytoscape API request)
  * removal of nodes from the Dynamic Network and intervals from the Interval Tree

# Feature wishlist #

---


  * interface to specify dynamic VizMaps
  * timeline visualization for attributes
  * dynamic graph metrics
  * dynamic clustering algorithms
  * shortest path algorithm with support for negative edges ([Bellman–Ford algorithm](http://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm))
  * multiscale layout algorithm ([Yifan Hu's Multilevel algorithm](http://www.mathematica-journal.com/issue/v10i1/graph_draw.html))