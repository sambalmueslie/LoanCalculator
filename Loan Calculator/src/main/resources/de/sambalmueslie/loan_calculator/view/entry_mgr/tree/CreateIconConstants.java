/**
 *
 */
package de.sambalmueslie.loan_calculator.view.entry_mgr.tree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper to create the constants from the files in the img directory.
 *
 * @author sambalmueslie 2015
 */
public class CreateIconConstants {

	/**
	 * Main.
	 * 
	 * @param args
	 *            the args
	 * @throws IOException
	 *             on error
	 */
	public static void main(final String[] args) throws IOException {
		final String directory = "./src/main/resources/de/sambalmueslie/loan_calculator/view/entry_mgr/tree";
		final List<File> imageFiles = Files.list(new File(directory).toPath()).map(p -> p.toFile()).filter(f -> f.getName().endsWith("gif"))
				.collect(Collectors.toList());
		for (final File img : imageFiles) {
			System.out.println("");
			System.out.println("/** the image name. */");
			final String fileName = img.getName();
			final String constantName = "ICON_" + fileName.substring(0, fileName.lastIndexOf(".")).toUpperCase();
			System.out.println("public static final String " + constantName + " = \"" + fileName + "\";");
		}
	}
}
