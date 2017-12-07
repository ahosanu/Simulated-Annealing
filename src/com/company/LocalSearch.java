package com.company;

import java.util.Random;

public class LocalSearch {
	public static State greedy_HCS() {

		State current = State.get_random_state();

		while (true) {
			System.out.println(current + "\t" +
							current.objecive());
			
			if (current.goal_test())
				break;

			State bestSuccessor = current.get_best_successor();

			if (bestSuccessor.objecive() < current.objecive()) {
				current = bestSuccessor;
			} else
				break;
		}

		return current;
	}
	
	public State random_restart_HCS()
	{
		State soln = null;
		while(true){
			soln = greedy_HCS();
			if(soln.goal_test())
				return soln;
		}
	}

	public static State FCHCS() {

		State current = State.get_random_state();
		State init = null;
		while (true) {
			System.out.println(current + "\t" +
					current.objecive());
			init = current;
			if (current.goal_test())
				break;
			//current = current.neishbor();
			for(int i=0; i < 100; i++) {
				State bestSuccessor = current.neighbor();
				if (bestSuccessor.objecive() < current.objecive()) {
					current = bestSuccessor;
					break;
				}
			}
			if (init.equals(current))
				break;

		}

		return current;
	}

	public static State simulated_annealing() {

		State currentState = State.get_random_state();

		double T = 10000000;
		double alpha = 0.999;
		Random random = new Random();
		double epsilon = .00001;

		while (T >= epsilon) {

			System.out.println(currentState.objecive());

			if (currentState.goal_test())
				break;

			// System.out.println(currentState + " obj = " +
			// currentObjectiveVal);

			// System.out.println(currentObjectiveVal);

			State neighbor = currentState.neighbor();

			int currVal = currentState.objecive();

			int neighborObj = neighbor.objecive();

			int delE = neighborObj - currVal;

			if (delE < 0)
				currentState = neighbor;
			else {

				double p = Math.exp(-delE / T);

				double randomVal = random.nextDouble();
				if (randomVal <= p)
					currentState = neighbor;
			}

			T = alpha * T;
		}

		return currentState;
	}
}
