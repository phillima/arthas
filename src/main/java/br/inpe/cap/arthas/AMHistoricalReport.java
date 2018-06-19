package br.inpe.cap.arthas;

import java.util.HashMap;
import java.util.Map;

import br.inpe.cap.asniffer.MetricResult;

public class AMHistoricalReport {

	private String commit;
	private Map<String, MetricResult> metricsResult;
	
	public AMHistoricalReport(String commit) {
		this.commit = commit;
		metricsResult = new HashMap<>();
	}
	
	public AMHistoricalReport(String commit, Map<String,MetricResult> metricsResult) {
		this.commit = commit;
		metricsResult.forEach((className, metricResult) -> {
			//this.metricsResult.
		});
	}
}
