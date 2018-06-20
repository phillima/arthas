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

import br.inpe.cap.asniffer.ASniffer;

public class ArthasStudy implements Study {

	private static ASniffer aSniffer = null;
	
	public static void main(String[] args) {
		aSniffer = new ASniffer();
		new RepoDriller().start(new ArthasStudy());
	}
	
	@Override
	public void execute() {
		
		DeveloperVisitor devVisitor = new DeveloperVisitor();
		new RepositoryMining()
		    .in(GitRepository.singleProject("C:\\Users\\phyll\\Documents\\eclipse-workspace\\EsfingeMetaDATA"))
			.through(Commits.all())
			.process(devVisitor, new CSVFile("C:\\Users\\phyll\\Documents\\eclipse-workspace\\MiningRes\\res.csv"))
			.mine();
		System.out.println("FINISHED");
	}
}
