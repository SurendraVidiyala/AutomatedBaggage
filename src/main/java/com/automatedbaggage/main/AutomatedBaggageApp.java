package com.automatedbaggage.main;

import java.util.List;
import java.util.Map;

import com.automatedbaggage.data.Bag;
import com.automatedbaggage.data.ConveyorNode;
import com.automatedbaggage.data.FlightDeparture;
import com.automatedbaggage.domain.ConveyorImplementationService;
import com.automatedbaggage.resourceHandler.BagHandler;
import com.automatedbaggage.resourceHandler.ConveyorGraphHandler;
import com.automatedbaggage.resourceHandler.FlightDepartureHandler;
import com.automatedbaggage.resources.Util;

public class AutomatedBaggageApp {

	public static void main(String[] args) {

		ConveyorImplementationService conveyorGraph = null;
		Map<String, FlightDeparture> flightIdToDepartureMap = null;
		Map<String, Bag> bagIdToBagMap = null;

		ConveyorGraphHandler conveyorGraphHandler = new ConveyorGraphHandler();
		try {
			conveyorGraphHandler.process();
			conveyorGraph = conveyorGraphHandler.getConveyorGraph();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		FlightDepartureHandler flightDepartureHandler = new FlightDepartureHandler();
		try {
			flightDepartureHandler.process();
			flightIdToDepartureMap = flightDepartureHandler.getFlightIdToDepartureMap();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		BagHandler bagHandler = new BagHandler();
		try {
			bagHandler.process();
			bagIdToBagMap = bagHandler.getBagIdToBagMap();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		StringBuffer output = new StringBuffer();

		for (Map.Entry<String, Bag> entry : bagIdToBagMap.entrySet()) {
			Bag bag = entry.getValue();
			String bagId = bag.getId();
			String flightId = bag.getFlightId();
			Util sourceGate = bag.getEntryPoint();

			output.append(bagId + " ");

			Util departureGate = null;
			if (flightId.equals("ARRIVAL")) {
				departureGate = sourceGate.BAGGAGE_CLAIM;
			} else {
				departureGate = flightIdToDepartureMap.get(flightId).getDepartureGate();
			}

			ConveyorNode sourceNode = new ConveyorNode(sourceGate, sourceGate.getValue());
			ConveyorNode targetNode = new ConveyorNode(departureGate, departureGate.getValue());
			List<ConveyorNode> shortestPath = conveyorGraph.findShortestPath(sourceNode, targetNode);

			if (!shortestPath.isEmpty()) {
				output.append(sourceGate.getValue() + " ");
				ConveyorNode prevNode = shortestPath.get(0);
				output.append(prevNode.getNodeId().getValue() + " ");

				for (int i = 1; i < shortestPath.size(); i++) {
					ConveyorNode current = shortestPath.get(i);
					prevNode = current;
					output.append(current.getNodeId().getValue() + " ");
				}
				output.append(": " + prevNode.getMinDistance());
				output.append(System.lineSeparator());
			} else {
				output.append("PATH_NOT_EXISTS");
				output.append(System.lineSeparator());
			}
		}

		System.out.println(output.toString());
	}
}
