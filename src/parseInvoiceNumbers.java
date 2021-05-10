import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class parseInvoiceNumbers {

    protected HashMap<String,String> digitHashCode;

    public parseInvoiceNumbers(){
        digitHashCode=new HashMap<>();
        InitMap();
    }


    /**
     * @param arr - list of chars array - each chars array represent line in txt file
     * @return - string of the invoice number
     */
    public String FromSegmentToNumbers(List<char[]> arr){
        StringBuilder ret=new StringBuilder();
        for(int i=0;i<27;i+=3){
            String hashDigit=FromSegmentToDigit(arr,i);
            ret.append(digitHashCode.get(hashDigit));
        }
        return ret.toString();
    }

    /**
     *
     * @param arr - list of chars array - each chars array represent line in txt file
     * @param i - column that some digit started
     * @return - hash string that represent that digit
     * assumes valid input
     */
    public String FromSegmentToDigit(List<char[]> arr,int i){
        boolean[] booleans=new boolean[7];
        booleans[0]=arr.get(0)[i+1]=='_';
        booleans[1]=arr.get(1)[i]=='|';
        booleans[2]=arr.get(1)[i+1]=='_';
        booleans[3]=arr.get(1)[i+2]=='|';
        booleans[4]=arr.get(2)[i]=='|';
        booleans[5]=arr.get(2)[i+1]=='_';
        booleans[6]=arr.get(2)[i+2]=='|';
        String str=CreateString(booleans);
        return str;

    }


    /**
     *
     * initialization of the map - hashString to digit that hashString represent
     */
    public void InitMap(){
        boolean[] array=new boolean[7];
        Arrays.fill(array,true);
        digitHashCode.put(CreateString(array),"8");
        array[2]=false;
        digitHashCode.put(CreateString(array),"0");
        array[2]=true;
        array[1]=false;
        array[4]=false;
        digitHashCode.put(CreateString(array),"3");
        array[5]=false;
        array[2]=false;
        digitHashCode.put(CreateString(array),"7");
        array[1]=true;
        array[2]=true;
        array[5]=true;
        digitHashCode.put(CreateString(array),"9");
        array[3]=false;
        digitHashCode.put(CreateString(array),"5");
        array[4]=true;
        digitHashCode.put(CreateString(array),"6");
        array[6]=false;
        array[1]=false;
        array[3]=true;
        digitHashCode.put(CreateString(array),"2");
        array[0]=false;
        array[4]=false;
        array[5]=false;
        array[6]=true;
        array[1]=true;
        digitHashCode.put(CreateString(array),"4");
        array[1]=false;
        array[2]=false;
        digitHashCode.put(CreateString(array),"1");
    }


    /**
     *
     * @param digitRepresent - boolean array that represent digit
     * @return representation of the boolean array as a hash string
     */
    public String CreateString(boolean[] digitRepresent){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<digitRepresent.length;i++){
            if(digitRepresent[i]){
                str.append("1#");
            }
            else{
                str.append("0#");
            }
        }
        return str.toString();
    }


}
