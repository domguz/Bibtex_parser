import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {


    private List<String> records = new ArrayList<>();

    public List<String> getRecords() {
        return records;
    }

    public void addRecords(List<String> records) {
        this.records = records;
    }



    public static String readFileAsString(String fileName)throws Exception
    {
        String data = new String(Files.readAllBytes(Paths.get(fileName)));
        data = data.replaceAll("\\r\\n|\\r|\\n", " ");
        return data;
    }

    public List<String> findMatches(String file) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("@[A-Z]*\\{[^}]*\\}")
                .matcher(file);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches;
    }

    public void parseRecord(List<String> records,String record){
        Document element=new Document();
        Matcher m = Pattern.compile("@[a-zA-Z]*").matcher(record);
        if(m.find()){
            String k = m.group();
            k=k.replaceAll("@","");
            element.setType(DocumentType.valueOf(k));
            element.parseRecord(record);
            System.out.println("-----------------------------------------------------------------");
        }


    }
}


