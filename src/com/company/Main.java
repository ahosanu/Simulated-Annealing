package com.company;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		State.n = 5;
		State end = LocalSearch.simulated_annealing();

		System.out.println("End");
		System.out.println(end);
		System.out.println(end.goal_test());
	}
}
