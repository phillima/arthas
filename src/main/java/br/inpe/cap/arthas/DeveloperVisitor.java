package br.inpe.cap.arthas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.repodriller.domain.Commit;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

import br.inpe.cap.asniffer.AMReport;
import br.inpe.cap.asniffer.ASniffer;

public class DeveloperVisitor implements CommitVisitor{
	
	private Map<String,Map<String,String>> sources;
	private Map<String,List<AMReport>> metrics;
	private ASniffer aSniffer;
	
	public DeveloperVisitor() {
		sources = new LinkedHashMap<>();
		metrics = new LinkedHashMap<>();
		aSniffer = new ASniffer();
	}
	
	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		Map<String,String> sourceFilesPerCommit = new LinkedHashMap<>();
		List<AMReport> reports = new ArrayList<>();
		for(Modification m : commit.getModifications()) {
			writer.write(
					commit.getHash(),
					commit.getAuthor().getName(),
					commit.getCommitter().getName(),
					m.getType()
			);
			try {
				reports = aSniffer.run("/Users/phillima/Desktop/Arthas/", "/Users/phillima/Desktop/Arthas/", true);
				//sourceFilesPerCommit.put(m.getFileName(),m.getSourceCode());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		sources.put(commit.getMsg(), sourceFilesPerCommit);
		metrics.put(commit.getMsg(), reports);
	}
	
	@Override
	public String name() {
		return "developers";
	}
	
	public Map<String, Map<String, String>> getSources() {
		return sources;
	}
	
}


