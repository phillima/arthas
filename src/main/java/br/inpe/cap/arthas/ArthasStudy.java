package br.inpe.cap.arthas;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import br.inpe.cap.arthas.utils.FileUtils;
import br.inpe.cap.asniffer.AMReport;
import br.inpe.cap.asniffer.ASniffer;
import br.inpe.cap.asniffer.MetricResult;

public class ArthasStudy implements Study {

	private static ASniffer aSniffer;
	
	public static void main(String[] args) {
		aSniffer = new ASniffer();
		new RepoDriller().start(new ArthasStudy());
	}
	
	@Override
	public void execute() {
		
		Map<String,Integer> acValues = new LinkedHashMap<>();
		int acValue = 0;
		
		DeveloperVisitor devVisitor = new DeveloperVisitor();
		new RepositoryMining()
		    .in(GitRepository.allProjectsIn("/Users/phillima/Documents/Arthas-Tool/projects/"))
			.through(Commits.all())
			.process(devVisitor, new CSVFile("/Users/phillima/Desktop/arthasstudy.csv"))
			.mine();
		System.out.println("FINISHED");
		
		Map<String, Map<String,String>> sources = devVisitor.getSources();
		
		sources.forEach((commit, source)->{
			FileUtils.createJavaFiles(source, "/Users/phillima/Desktop/Arthas/"+commit);
		});
		
		devVisitor.getSources();
		
		/*List<AMReport> reports;
		try {
			reports = aSniffer.run("/Users/phillima/Desktop/Arthas/", "/Users/phillima/Desktop/Arthas/", false);
			for (AMReport amReport : reports) {

				for (MetricResult metric : amReport.all()) {
					acValue+=metric.getClassMetric("AC");
				}
				acValues.put(amReport.getProjectName(),acValue);
			}
			acValues.forEach((commit,metric)->{
				System.out.println("Commit: " + commit + " has AC=" + metric);
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
}
