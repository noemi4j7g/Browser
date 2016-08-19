package main_tester;

import dom.DOMException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import main.MainDocument;

/**
 *
 * @author arquitectura de software II-2015
 */
public class MainTesterBrowser {

    private static final String MESSAGE_SUCCESSFUL = "\033[32m Successful.\033[30m\n";
    private static final String MESSAGE_FAILED = "\033[31m Failed the test\033[30m";
    private static final String PATH_DIRECTORY = "src/docs_tester/";
    private final StringBuilder report;

    public MainTesterBrowser() {
        report = new StringBuilder();
    }

    public void testBrowser(String pathDir) {
        if (pathDir != null && !pathDir.equals("")) {
            File dir = new File(pathDir);
            if (dir.exists()) {
                List<File> files = Arrays.asList(dir.listFiles());
                String pathFile, nameFile;
                int numValidFiles = 0, numInvalidFiles = 0;
                report.append(String.format("   %-25s\t%-25s\t%s\n", "Name of file", "State", "Message of error"));
                for (File file : files) {
                    pathFile = file.getAbsolutePath();
                    nameFile = file.getName();
                    try {
                        readFile(pathFile);
                        numValidFiles += 1;
                        report.append(String.format("\033[32m\u2713 \033[30m%-25s\t%-25s", nameFile, MESSAGE_SUCCESSFUL));
                    } catch (DOMException domException) {
                        numInvalidFiles += 1;
                        report.append(String.format("\033[31mX \033[30m%-25s\t%-25s\t%s\n", nameFile, MESSAGE_FAILED, domException));
                    } catch (IOException ioException) {
                        report.append(String.format("\033[31mX \033[30m%-25s\t%-25s\t%s\n", nameFile, MESSAGE_FAILED, ioException));
                    }
                }
                printResult(files.size(), numValidFiles, numInvalidFiles);
            }
        } else {
            throw new ExceptionInInitializerError("The path is invalid");
        }
    }

    private boolean readFile(String pathFile) throws IOException {
        boolean valid = true;
        try {
            boolean externalFile = true;
            MainDocument mainDocment = new MainDocument(pathFile, externalFile);
            mainDocment.getContentDocument();
        } catch (IOException ioException) {
            valid = false;
            throw ioException;
        } catch (DOMException domException) {
            valid = false;
            throw domException;
        }
        return valid;
    }

    private void printResult(int numFiles, int numValidFiles, int numInvalidFiles) {
        String reportTotalTest = String.format("Total tested files: %d", numFiles);
        String reportValidFiles = String.format("Valid files: %d", numValidFiles);
        String reportInvalidFiles = String.format("Invalid files: %d", numInvalidFiles);
        report.insert(0, String.format("%-15s, %15s, %15s\n", reportTotalTest, reportValidFiles, reportInvalidFiles));
        System.out.println(report.toString());
    }

    public static void main(String[] args) {
        MainTesterBrowser mainTester = new MainTesterBrowser();
        mainTester.testBrowser(PATH_DIRECTORY);
    }

}
