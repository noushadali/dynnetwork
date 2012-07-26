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

package org.cytoscape.dyn.internal.view.layout;

import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

import org.cytoscape.view.model.CyNetworkView;

/**
 * <code> DynLayoutManagerImpl </code> implements the interface
 * {@link DynLayoutManager} for managing multiple {@link DynLayout}s.
 * 
 * @author Sabina Sara Pfister
 *
 * @param <T>
 */
public final class DynLayoutManagerImpl implements DynLayoutManager
{
	private final Map<CyNetworkView, DynLayout> dynLayoutMap;
	
	/**
	 * <code> DynLayoutManagerImpl </code> constructor.
	 */
	public DynLayoutManagerImpl()
	{
		this.dynLayoutMap = new WeakHashMap<CyNetworkView, DynLayout>();
	}

	@Override
	public void addDynLayout(DynLayout dynLayout)
	{
		this.dynLayoutMap.put(dynLayout.getNetworkView(), dynLayout);
	}

	@Override
	public DynLayout getDynLayout(CyNetworkView view)
	{
		return dynLayoutMap.get(view);
	}
	
	@Override
	public void removeDynLayout(CyNetworkView view)
	{
		if (dynLayoutMap.containsKey(view))
			dynLayoutMap.remove(view);
	}
	
	@Override
	public Collection<DynLayout> getDynNetworks() 
	{
		return dynLayoutMap.values();
	}
	
}

