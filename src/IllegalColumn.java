import java.util.List;

public class IllegalColumn extends parseInvoiceNumbers {

    @Override
    public String FromSegmentToNumbers(List<char[]> arr){
        StringBuilder ret=new StringBuilder();
        boolean isIllegal=false;

        //iterate segment by segment - each segment is spread over 3 columns
        for(int i=0;i<27;i+=3){
            boolean isValid=true;

            //check validation of each character
            for(int j=0;j<3;j++){
                for(int k=i;k<i+3;k++){
                    if(!CheckValidation(arr.get(j)[k],j,k)){
                        isValid=false;
                        isIllegal=true;
                        break;
                    }
                }
            }


            if(isValid){
                String hashDigit=FromSegmentToDigit(arr,i);
                //check if legal digit
                if(digitHashCode.containsKey(hashDigit)){
                    ret.append(digitHashCode.get(hashDigit));
                }
                else{
                    ret.append('?');
                    isIllegal=true;
                }
            }
            else {
                ret.append('?');
            }

        }
        if(isIllegal)
            ret.append(" ILLEGAL");
        return ret.toString();
    }

    /**
     *
     * @param c - character
     * @param row - row of char in array
     * @param col - column of char in array
     * @return check if character location is legal
     */
    public boolean CheckValidation(char c,int row,int col){

        boolean valid=false;
        switch (c){
            case '_':valid=(col%3==1)?true: false; break;
            case '|':valid=(row%4==0)?false:(col%3==1)?false:true;break;
            case ' ':valid=true;break;
            default: valid=false; break;
        }
        return valid;
    }
}
