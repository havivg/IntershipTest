import java.util.Scanner;

public class main {


    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Your txt File Path");
        FileReaderWriter fileReaderWriter=new FileReaderWriter(input.nextLine());

        while(true) {
            while (!fileReaderWriter.readFile()) {
                System.out.println("Your File Path Invalid, Please Try Again");
                fileReaderWriter.setPath(input.nextLine());
            }

            System.out.println("For Parser Without Illegal Column Enter 1, Otherwise Enter 2");
            int type = Integer.parseInt(input.nextLine());
            while (true) {
                if (type == 1 || type == 2) break;
                System.out.println("Your Input Invalid, Try Again");
                type = Integer.parseInt(input.nextLine());
            }

            if (fileReaderWriter.writeToFile(type == 2))
                System.out.println("Your output file saved successfully");
            else
                System.out.println("There is problem with your file");

            System.out.println("Do you want to exit? Y/N");
            if(input.nextLine().toLowerCase().equals("y"))
                break;
            else{
                System.out.println("Please Enter Your txt File Path");
                fileReaderWriter.setPath(input.nextLine());
            }
        }
        input.close();

    }
}
