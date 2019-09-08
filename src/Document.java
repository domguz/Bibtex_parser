import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    private DocumentType type;

    public Map<FieldType, String> getDocumentFields() {
        return documentFields;
    }

    public void setDocumentFields(Map<FieldType, String> documentFields) {
        this.documentFields = documentFields;
    }

    private Map<FieldType, String> documentFields = new LinkedHashMap<>();
    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType document) {
        this.type = document;
    }
    private static final FieldType [][] required={
            {FieldType.author,FieldType.title, FieldType.journal,FieldType.year},
            {FieldType.author,FieldType.editor,FieldType.title, FieldType.publisher,FieldType.year},
            {FieldType.title},
            {FieldType.author,FieldType.editor,FieldType.title, FieldType.chapter,FieldType.pages, FieldType.publisher,FieldType.year},
            {FieldType.author,FieldType.title, FieldType.booktitle, FieldType.publisher,FieldType.year},
            {FieldType.author,FieldType.title, FieldType.booktitle,FieldType.year},
            {FieldType.author,FieldType.editor,FieldType.title, FieldType.chapter,FieldType.pages, FieldType.publisher,FieldType.year},
            {FieldType.title},
            {FieldType.author,FieldType.title, FieldType.school,FieldType.year},
            {},
            {FieldType.author,FieldType.title, FieldType.school,FieldType.year},
            {FieldType.title,FieldType.year},
            {FieldType.author,FieldType.title, FieldType.institution,FieldType.year},
            {FieldType.author,FieldType.title, FieldType.note}
    };

    public static FieldType[][] getRequired() {
        return required;
    }

    public static FieldType[][] getOptional() {
        return optional;
    }

    private static final FieldType [][] optional={
            {FieldType.volume,FieldType.number,FieldType.pages,FieldType.month, FieldType.note},
            {FieldType.volume,FieldType.number, FieldType.series,FieldType.address, FieldType.edition,FieldType.month, FieldType.note},
            {FieldType.author, FieldType.howpublished,FieldType.address,FieldType.month,FieldType.year, FieldType.note},
            {FieldType.editor,FieldType.volume,FieldType.number, FieldType.series,FieldType.pages,FieldType.address,FieldType.month, FieldType.organization, FieldType.publisher, FieldType.note},
            {FieldType.volume,FieldType.number,FieldType.series,FieldType.type,FieldType.address, FieldType.edition,FieldType.month, FieldType.note},
            {FieldType.editor,FieldType.volume,FieldType.number, FieldType.series,FieldType.type, FieldType.chapter,FieldType.pages,FieldType.address, FieldType.edition,FieldType.month, FieldType.note},
            {FieldType.editor,FieldType.volume,FieldType.number, FieldType.series,FieldType.pages,FieldType.address,FieldType.month, FieldType.organization, FieldType.publisher, FieldType.note},
            {FieldType.author, FieldType.organization,FieldType.address, FieldType.edition,FieldType.month,FieldType.year, FieldType.note},
            {FieldType.type,FieldType.address,FieldType.month, FieldType.note},
            {FieldType.author,FieldType.title, FieldType.howpublished,FieldType.month,FieldType.year, FieldType.note},
            {FieldType.type,FieldType.address,FieldType.month, FieldType.note},
            {FieldType.editor,FieldType.volume,FieldType.number, FieldType.series,FieldType.address, FieldType.publisher, FieldType.note,FieldType.month, FieldType.organization},
            {FieldType.type,FieldType.number,FieldType.address,FieldType.month, FieldType.note},
            {FieldType.month,FieldType.year}
    };

    public void parseRecord(String record){
        Matcher m;
        for(FieldType r:required[this.type.ordinal()]){
            m=Pattern.compile(r.toString()+" = \"[^\",]*\"").matcher(record);
            if(m.find()){
                String fieldValue = m.group();
                fieldValue=fieldValue.replaceAll("[a-zA-Z]* = ","");
                fieldValue=fieldValue.replaceAll("\"","");
                this.documentFields.put(r,fieldValue);
            }
            else System.out.println("brak");
        }
        for(FieldType o:optional[this.type.ordinal()]){
            m=Pattern.compile(o.toString()+" = \"[^\",]*\"").matcher(record);
            if(m.find()){
                String fieldValue = m.group();
                fieldValue=fieldValue.replaceAll("[a-zA-Z]* = ","");
                fieldValue=fieldValue.replaceAll("\"","");
                this.documentFields.put(o,fieldValue);
            }
        }
        //System.out.println(this.getType());
        documentFields.keySet().iterator().forEachRemaining(key-> System.out.println(key+"="+ documentFields.get(key)));
    }




}