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

package org.cytoscape.dyn.internal.view.layout.algorithm.dynamic;

import java.util.Set;

import org.cytoscape.dyn.internal.view.layout.DynLayout;
import org.cytoscape.dyn.internal.view.model.DynNetworkView;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.layout.AbstractLayoutTask;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;

/**
 * <code> DynForceLayoutAlgorithmTask </code> is responsible for the generation of force-based network
 * dynamics by associating to each nodes in the network appropriate intervals of node positions,
 * which are stored in {@link DynLayout}.
 * 
 * @author Sabina Sara Pfister
 *
 * @param <T>
 */
public class DynForceLayoutAlgorithmTask<T> extends AbstractLayoutTask 
{
    
	private final DynLayout<T> layout;
	private final CyNetworkView view;
	private final DynNetworkView<T> dynView;
	
	private final double currentTime;
	
	/**
	 * <code> DynForceLayoutAlgorithmTask </code> constructor.
	 * @param name
	 * @param layout
	 * @param nodesToLayOut
	 * @param layoutAttribute
	 * @param undo
	 * @param currentTime
	 * @param timeMin
	 * @param timeMax
	 * @param timeStep
	 */
    public DynForceLayoutAlgorithmTask(
                    final String name,
                    final DynLayout<T> layout,
                    final DynNetworkView<T> dynView,
                    final Set<View<CyNode>> nodesToLayOut, 
                    final String layoutAttribute,
                    final UndoSupport undo,
                    final double currentTime)
    {
            super(name, layout.getNetworkView(), nodesToLayOut, layoutAttribute, undo);
            this.layout = layout;
            this.view = layout.getNetworkView();
            this.dynView = dynView;
            this.currentTime = currentTime;
    }

	@Override
	protected void doLayout(TaskMonitor taskMonitor)
	{	
		if (networkView!=null && dynView!=null)
		{
			layout.initNodePositions(currentTime);
			view.fitContent();
			view.updateView();
		}
	}

}