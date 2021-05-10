import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderWriter {

    private String path;
    private File file;
    private Scanner reader;
    private parseInvoiceNumbers parser;

    public FileReaderWriter(String path){
        this.path=path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean readFile(){
        try{
            file=new File(path);
            reader=new Scanner(file);
            return true;
        }
        catch (FileNotFoundException e){
            return false;
        }
    }

    public boolean writeToFile(boolean withIllegal){
        if(withIllegal)
            parser=new IllegalColumn();
        else
            parser=new parseInvoiceNumbers();

        List<char[]> arr=new ArrayList<>();
        try {
            int i = 0;
            FileWriter myWriter = new FileWriter("output.txt");
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (i < 3) {
                    arr.add(data.toCharArray());
                    i++;
                } else {
                    myWriter.write(parser.FromSegmentToNumbers(arr) + "\n");
                    i = 0;
                    arr = new ArrayList<>();
                }
            }
            myWriter.close();
            return true;
        }
        catch (IOException e){
            return false;

        }

    }


}
