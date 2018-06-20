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

import br.inpe.cap.arthas.utils.FileUtils;

public class DeveloperVisitor implements CommitVisitor{
	
	private Map<String,String> sourceCode;
	
	public Map<String, String> getSourceCode() {
		return sourceCode;
	}

	public DeveloperVisitor() {
		
	}
	
	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		sourceCode = new HashMap<>();
		for(Modification m : commit.getModifications()) {
			/*writer.write(
					commit.getAuthor().getName(),
					commit.getCommitter().getName()
			);*/
			sourceCode.put(m.getFileName(), m.getSourceCode());
		}
		FileUtils.createJavaFiles(commit.getHash(), sourceCode, 
				"C:\\Users\\phyll\\Documents\\eclipse-workspace\\MiningRes");
	}
	@Override
	public String name() {
		return "developers";
	}
}


