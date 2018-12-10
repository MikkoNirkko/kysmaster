package com.mikkonirkko.kysmaster.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import com.mikkonirkko.kysmaster.model.Result;

@Service
public class ResultService {
	public List<Result> pickTen(List<Result> results) { //To sort and select the 10 best highscores.
		List<Result> top10 = new ArrayList<Result>(10);
		if (results.size() > 2) {
			Collections.sort(results, new Comparator<Result>() {
				@Override
				public int compare(Result result2, Result result1) {
					return Double.compare(result1.getScore(), result2.getScore());
				}
			});
		}
		if (results.size() > 1 && results.size() < 10) {
			for (int i = 0; i < results.size(); i++) {
				top10.add(results.get(i));
			}
		} else {
			for (int i = 0; i < 10; i++) {
				top10.add(results.get(i));
			}
		}

		return top10;
	}

	public List<Result> order(List<Result> results) {//Only sorting for lists shorter than 10 items.
		if (results.size() > 1) {
			Collections.sort(results, new Comparator<Result>() {
				@Override
				public int compare(Result result2, Result result1) {
					return Double.compare(result1.getScore(), result2.getScore());
				}
			});
		}

		return results;
	}
}
