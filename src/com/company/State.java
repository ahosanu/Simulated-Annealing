package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class State {
	static int n;
	int[] Q;

	public State() {
		// super();
		Q = new int[n];
	}

	public State(int[] news) {
		// super();
		Q = news;
	}

	public int objecive() {
		int f = 0;

		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (Q[i] == Q[j] || Math.abs(Q[i] - Q[j]) == Math.abs(i - j))
					f++;
			}
		}

		return f;
	}

	public ArrayList<State> get_successors() {
		ArrayList<State> successors = new ArrayList<>();

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j)
				if (j != Q[i]) {
					State s = new State();

					for (int k = 0; k < n; ++k)
						s.Q[k] = Q[k];

					s.Q[i] = j;

					successors.add(s);
				}
		}

		return successors;
	}


	public State neighbor() {
			Random random = new Random();
			int i = random.nextInt(n);
			State s = new State();

			for (int k = 0; k < n; ++k)
				s.Q[k] = Q[k];

			s.Q[i] = random.nextInt(n);

		return s;
	}

	public State get_best_successor() {
		int bestVal = Integer.MAX_VALUE;
		State best = new State();

		ArrayList<State> successors = get_successors();

		for (State s : successors) {
			if (bestVal > s.objecive()) {
				bestVal = s.objecive();
				best = s;
			}
		}

		return best;
	}

	public boolean goal_test() {
		if (objecive() == 0)
			return true;

		return false;

		// return objective_function() == 0;
	}

	public static State get_random_state() {
		State s = new State();
		Random random = new Random();

		for (int i = 0; i < n; ++i)
			s.Q[i] = random.nextInt(n);

		return s;
	}

	public String toString() {
		String s = "[ ";

		for (int i = 0; i < n; ++i)
			s += (Q[i] + " ");

		s += " ]";

		return s;

		// return Arrays.toString(Q);
	}
}
