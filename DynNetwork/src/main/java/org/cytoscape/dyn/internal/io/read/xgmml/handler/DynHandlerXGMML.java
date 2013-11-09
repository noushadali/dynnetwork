/*
 * DynNetwork plugin for Cytoscape 3.0 (http://www.cytoscape.org/).
 * Copyright (C) 2012 Sabina Sara Pfister
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.cytoscape.dyn.internal.io.read.xgmml.handler;

import java.util.Stack;

import org.cytoscape.dyn.internal.io.read.xgmml.ParseDynState;
import org.cytoscape.dyn.internal.io.read.xgmml.XGMMLDynParser;
import org.cytoscape.dyn.internal.layout.model.DynLayoutFactory;
import org.cytoscape.dyn.internal.model.DynNetwork;
import org.cytoscape.dyn.internal.model.DynNetworkFactory;
import org.cytoscape.dyn.internal.view.model.DynNetworkViewFactory;
import org.cytoscape.dyn.internal.vizmapper.model.DynVizMapFactory;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.xml.sax.Attributes;

/**
 * <code> DynHandlerXGMML </code> implements the interface {@link DynHandler} and
 * handles events generated by the XGMML parser {@link XGMMLDynParser} and generates
 * graph event accordingly.
 * 
 * @author Sabina Sara Pfister
 *
 * @param <T>
 */
public final class DynHandlerXGMML<T> extends AbstractXGMMLSource<T> implements DynHandler 
{
	private final Stack<OrphanEdge<T>> orphanEdgeList;
	
	private DynNetwork<T> currentNetwork;
	private CyNode currentNode;
	private CyEdge currentEdge;
	
	private String directed;
	private String id;
	private String label;
	private String name;
	private String source;
	private String target;
	private String type;
	private String value;
	private String start;
	private String end;
	
	private String h;
	private String w;
	private String size;
	private String x;
	private String y;
	private String fill;
	private String labelfill;
	private String labelsize;
	private String width;
	private String outline;
	private String sourcearrowshape;
	private String targetarrowshape;
	private String transparency;
	
	private int ID = 0;
	
	/**
	 * <code> DynHandlerXGMML </code> constructor.
	 * @param networkSink
	 * @param viewSink
	 * @param layoutSink
	 */
	public DynHandlerXGMML(DynNetworkFactory<T> networkSink, DynNetworkViewFactory<T> viewSink, DynLayoutFactory<T> layoutSink, DynVizMapFactory<T> vizMapSink)
	{
		orphanEdgeList = new Stack<OrphanEdge<T>>();
		this.networkSink = networkSink;
		this.viewSink = viewSink;
		this.layoutSink = layoutSink;
		this.vizMapSink = vizMapSink;
	}

	@Override
	public void handleStart(Attributes atts, ParseDynState current)
	{
		switch(current)
		{
		case NONE:
			break;
			
		case GRAPH:
			id = atts.getValue("id");
			label = atts.getValue("label");
			start = atts.getValue("start");
			end = atts.getValue("end");
			directed = atts.getValue("directed");
			label = label==null?"dynamic network ("+(ID++)+")":label;
			id = id==null?label:id;
			directed = directed==null?"1":directed;
			currentNetwork = this.addGraph(id, label, start, end, directed);
			break;
			
		case NODE:
			id = atts.getValue("id");
			label = atts.getValue("label");
			start = atts.getValue("start");
			end = atts.getValue("end");
			id = id==null?label:id;
			currentNode = this.addNode(currentNetwork, id, label, start, end);
			break;
			
		case EDGE:
			id = atts.getValue("id");
			label = atts.getValue("label");
			source = atts.getValue("source");
			target = atts.getValue("target");
			start = atts.getValue("start");
			end = atts.getValue("end");
			label = label==null?source+"-"+target:label;
			id = id==null?label:id;
			currentEdge = this.addEdge(currentNetwork, id, label, source, target, start, end);
			if (currentEdge==null)
				orphanEdgeList.push(new OrphanEdge<T>(currentNetwork, id, label, source, target, start, end));
			break;
			
		case NET_ATT:
			name = checkGraphAttributeName(atts.getValue("name"));
			value = atts.getValue("value");
			type = atts.getValue("type");
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentNetwork!= null && name!=null && value!=null && type!=null)
				this.addGraphAttribute(currentNetwork, name, value, type, start, end);
			break;
			
		case NODE_ATT:
			name = checkNodeAttributeName(atts.getValue("name"));
			value = atts.getValue("value");
			type = atts.getValue("type");
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentNode!=null && name!=null && value!=null && type!=null)
				this.addNodeAttribute(currentNetwork, currentNode, name, value, type, start, end);
			break;
			
		case EDGE_ATT:
			name = checkEdgeAttributeName(atts.getValue("name"));
			value = atts.getValue("value");
			type = atts.getValue("type");
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentEdge!=null && name!=null && value!=null && type!=null)
				this.addEdgeAttribute(currentNetwork, currentEdge, name, value, type, start, end);
			else
				orphanEdgeList.peek().addAttribute(currentNetwork, name, value, type, start, end);
			break;
			
		case NET_GRAPHICS:
			fill = atts.getValue("fill");
			this.addGraphGraphics(currentNetwork, fill, start, end);
			break;
			
		case NODE_GRAPHICS:
			type = atts.getValue("type");
			h = atts.getValue("height");
			w = atts.getValue("width");
			size = atts.getValue("size");
			fill = atts.getValue("fill");
			labelfill = atts.getValue("labelfill");
			labelsize = atts.getValue("labelsize");
			width = atts.getValue("borderwidth");
			outline = atts.getValue("bordercolor");
			transparency = atts.getValue("transparency");
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentNode!=null)
				this.addNodeGraphics(currentNetwork, currentNode, type, h, w, size, fill, labelfill, labelsize, width, outline, transparency, start, end);
			break;
			
		case NODE_DYNAMICS:
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentNode!=null)
				this.addNodeDynamics(currentNetwork, currentNode, x, y, start, end);
			break;
			
		case EDGE_GRAPHICS:
			width = atts.getValue("width");
			fill = atts.getValue("fill");
			sourcearrowshape = atts.getValue("sourcearrowshape");
			targetarrowshape = atts.getValue("targetarrowshape");
			transparency = atts.getValue("transparency");
			start = atts.getValue("start");
			end = atts.getValue("end");
			if (currentEdge!=null)
				this.addEdgeGraphics(currentNetwork, currentEdge, width, fill, sourcearrowshape, targetarrowshape, transparency, start, end);
			else
				orphanEdgeList.peek().addGraphics(currentNetwork, width, fill, sourcearrowshape, targetarrowshape, transparency, start, end);
			break;
			
		}
	}

	@Override
	public void handleEnd(Attributes atts, ParseDynState current)
	{
		switch(current)
		{
		case GRAPH:
			while (!orphanEdgeList.isEmpty())
				orphanEdgeList.pop().add(this);
			finalize(currentNetwork);
			break;
		}
	}

	@Override
	protected CyEdge addEdge(DynNetwork<T> currentNetwork, String id, String label, String source, String target, String start, String end)
	{
		return networkSink.addedEdge(currentNetwork, id, label, source, target, start, end);
	}

	@Override
	protected void addEdgeAttribute(DynNetwork<T> network, CyEdge currentEdge, String name, String value, String Type, String start, String end)
	{
		networkSink.addedEdgeAttribute(network, currentEdge, name, value, Type, start, end);
	}
	
	@Override
	protected void addEdgeGraphics(DynNetwork<T> network, CyEdge currentEdge, String width, String fill,  String sourcearrowshape, String targetarrowshape, String transparency, String start, String end)
	{
		vizMapSink.addedEdgeGraphics(network, currentEdge, width, fill, sourcearrowshape, targetarrowshape, transparency, start, end);
	}
	
	private String checkGraphAttributeName(String name)
	{
		if (name.equals("name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'name' is reserved and cannot be uded.");
		}
		if (name.equals("shared name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'shared name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'shared name' is reserved and cannot be uded.");
		}
		if (name.equals("__Annotations"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag '__Annotations' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag '__Annotations' is reserved and cannot be uded.");
		}
		
		return name;
	}
	
	private String checkNodeAttributeName(String name)
	{
		if (name.equals("name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'name' is reserved and cannot be uded.");
		}
		if (name.equals("shared name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'shared name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'shared name' is reserved and cannot be uded.");
		}
		
		return name;
	}
	
	private String checkEdgeAttributeName(String name)
	{
		if (name.equals("name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'name' is reserved and cannot be uded.");
		}
		if (name.equals("shared name"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'shared name' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'shared name' is reserved and cannot be uded.");
		}
		if (name.equals("interaction"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'interaction' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'interaction' is reserved and cannot be uded.");
		}
		if (name.equals("shared interaction"))
		{
			System.out.println("\nXGMML Parser Error: Reserved attribute name: the tag 'shared interaction' is reserved and cannot be uded.");
			throw new IllegalArgumentException("Invalid attribute name: the tag 'shared interaction' is reserved and cannot be uded.");
		}
		
		return name;
	}

}
