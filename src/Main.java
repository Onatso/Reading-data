import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main{

    public static void indexFile(String fileName) throws IOException {

        Map<Character, Integer> characters = new HashMap<>();
        int totalCharacters=0;

        String text = Files.readString(Paths.get(fileName));

        for (char c : text.toCharArray()){
            characters.put(c, characters.getOrDefault(c, 0)+1);
            totalCharacters++;
        }

        for (var i : characters.entrySet()){
            System.out.println(i.getKey() + " - " + (((double)i.getValue())/totalCharacters)*100);
        }
    }

    public static void indexFolder(String folder) throws IOException{
        File dir = new File(folder);
        String[] files = dir.list();

        for (String s : files){
            indexFile(folder+"/"+s);
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        indexFile(userInput);
        userInput = sc.nextLine(); // /run/media/Nikolay/46E1-BAEE/ArchLinuxVM/Logs
        indexFolder(userInput);
    }
}