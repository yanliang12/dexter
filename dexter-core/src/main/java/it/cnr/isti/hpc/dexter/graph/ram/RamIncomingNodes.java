/**
 *  Copyright 2012 Diego Ceccarelli
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package it.cnr.isti.hpc.dexter.graph.ram;

import it.cnr.isti.hpc.dexter.graph.IncomingNodes;
import it.cnr.isti.hpc.dexter.graph.NodeStar;
import it.cnr.isti.hpc.dexter.graph.NodesWriter;
import it.cnr.isti.hpc.dexter.util.DexterParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RamIncomingNodes allows to keep the incoming nodes for each node in a graph
 * directly in main memory.
 * 
 * @author Diego Ceccarelli, diego.ceccarelli@isti.cnr.it created on 05/lug/2012
 */
public class RamIncomingNodes extends RamNodes implements IncomingNodes,
		NodesWriter {

	private static RamIncomingNodes instance = null;

	private static final Logger logger = LoggerFactory
			.getLogger(RamIncomingNodes.class);

	private static DexterParams params = DexterParams.getInstance();

	private RamIncomingNodes() {

		super(params.getGraph("entity-entity", NodeStar.Direction.IN));
	}

	public static RamIncomingNodes getInstance() {
		if (instance == null) {
			logger.info("Loading ram incoming nodes");
			instance = new RamIncomingNodes();
		}
		return instance;
	}

	// @Override
	public int[] getIncoming(int id) {
		return getNeighbours(id);
	}

}
