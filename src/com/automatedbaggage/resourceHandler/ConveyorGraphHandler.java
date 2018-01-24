package com.automatedbaggage.resourceHandler;

import com.automatedbaggage.data.ConveyorNode;
import com.automatedbaggage.domain.ConveyorImplementationService;
import com.automatedbaggage.resources.Util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConveyorGraphHandler implements InputHandler {

	private ConveyorImplementationService conveyorGraph;

	private Map<Util, ConveyorNode> gateNodeMap = new HashMap<>();

	@Override
	public void process() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("input.txt").getFile());

		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			boolean startGraphSection = false;
			boolean endGraphSection = false;

			while (scanner.hasNextLine() && !endGraphSection) {
				String line = scanner.nextLine();

				if (line.trim().equals("")) {
					continue;
				}

				if (line.startsWith("# Section:")) {
					if (!line.endsWith("Conveyor System")) {
						startGraphSection = false;
						continue;
					} else if (line.endsWith("Conveyor System")) {
						startGraphSection = true;
						conveyorGraph = new ConveyorImplementationService();
						continue;
					} else if (startGraphSection && !line.endsWith("Conveyor System")) {
						endGraphSection = true;
					}
				}

				if (startGraphSection && !endGraphSection) {
					String tokens[] = line.split(" ");
					if (tokens.length != 3) {
						throw new IOException("BAD INPUT FORMAT...");
					}
					String from = tokens[0];
					String to = tokens[1];
					int cost = Integer.parseInt(tokens[2]);

					Util fromGate = Util.getGate(from);
					Util toGate = Util.getGate(to);

					if (fromGate == null || toGate == null) {
						throw new IOException("INVALID GATE FOUND...");
					}

					conveyorGraph.addLink(createNode(fromGate, gateNodeMap), createNode(toGate, gateNodeMap), cost);
					conveyorGraph.addLink(createNode(toGate, gateNodeMap), createNode(fromGate, gateNodeMap), cost);
				}

			}
		} finally {
			scanner.close();
		}
	}

	public ConveyorImplementationService getConveyorGraph() {
		return conveyorGraph;
	}

	public void setConveyorGraph(ConveyorImplementationService conveyorGraph) {
		this.conveyorGraph = conveyorGraph;
	}

	private ConveyorNode createNode(Util gate, Map<Util, ConveyorNode> nodeMap) {
		if (nodeMap.containsKey(gate)) {
			return nodeMap.get(gate);
		}
		ConveyorNode conveyorNode = new ConveyorNode(gate, gate.getValue());
		nodeMap.put(gate, conveyorNode);
		return conveyorNode;
	}
}
