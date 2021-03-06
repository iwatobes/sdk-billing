package sdk;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVService {


    public static void write(String filepath,List<Entry> list)throws IOException,
    CsvDataTypeMismatchException,
    CsvRequiredFieldEmptyException {

        try 
            
         {
            Writer writer = Files.newBufferedWriter(Paths.get(filepath),StandardOpenOption.CREATE
            ,StandardOpenOption.APPEND);

            BeanToCsv bc = new BeanToCsv<Entry>();
            ColumnPositionMappingStrategy mappingStrategy = 
            		new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Entry.class);
            String[] columns = new String[]{"appName","type"};
            mappingStrategy.setColumnMapping(columns);
            CSVWriter csvWriter = new CSVWriter(writer, ',');
            bc.write(mappingStrategy,csvWriter,list);
/*             CSVWriter cwriter = new CSVWriter(writer, ',');
            String[] entries = "first#second#third".split("#");
            cwriter.writeNext(entries); */
            
            writer.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    
}