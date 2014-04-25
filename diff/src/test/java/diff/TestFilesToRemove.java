package diff;

import org.junit.Test;

import diff.parameters.PluginParameters;


public class TestFilesToRemove {

	@Test
	public void filesToRemoveShouldContainMissingFilesInNewFolder() {
		// The file A.txt is not present in new folder, so it should be part of files to remove
		PluginParameters parameters = new PluginParametersBuilder()
                .setOldFolder("src/test/resources/old")
                .setNewFolder("src/test/resources/new")
                .createPluginParameters();
		
		FolderParser folderParser = new FolderParser();
		folderParser.setup(parameters);
		
		assertThat(parameters.getFilesToRemove(), contains("A.txt"));
	}
	
}
