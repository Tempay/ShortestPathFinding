import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tempa on 12/22/2016.
 */
public class BriteReader {

/*    public static void main(String[] args) {

        BriteReader briteReader = new BriteReader();
        try {
            List<Link> links = briteReader.loadTopo("../123.brite");
            System.out.println(links.size());
        } catch (IOException ioe) {
            System.out.println("File Not Found");
        }
    }
*/
    public List<Link> loadTopo(String topoFilePath) throws IOException {

        List<String> topoFile = readTopoFile(topoFilePath);
        int numberOfNodes = parseNumber(topoFile.get(3));
        int numberOfLinks = parseNumber(topoFile.get(numberOfNodes + 6));
        return parseLink(topoFile, numberOfNodes + 7, numberOfLinks);
    }

    private List<String> readTopoFile(String topoFilePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(topoFilePath);
        return Files.readAllLines(path);
    }

    private int parseNumber(String line) {
        String regEX = "[^0-9]";
        Pattern pattern = Pattern.compile(regEX);
        Matcher m = pattern.matcher(line);
        int number = Integer.parseInt(m.replaceAll("").trim());
        return number;
    }

    private List<Link> parseLink(List<String> topoFileContents, int startLine, int linkNumber) {
        int index = 0;
        List<Link> linkList = new ArrayList<>(linkNumber);
        while (index < linkNumber) {
            String linkLine = topoFileContents.get(startLine + index);
            String[] parsedLinkLine = linkLine.split("\t");
            Link link = new Link(Integer.parseInt(parsedLinkLine[1]), Integer.parseInt(parsedLinkLine[2]),
                    Double.parseDouble(parsedLinkLine[3]));
            linkList.add(link);
            index++;
        }
        return linkList;
    }
}
