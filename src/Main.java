import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main{

    public static void indexFile(String fileName) throws IOException, ClassNotFoundException {

        File f = new File(fileName+".out");

        if(f.exists())
        {
            System.out.println("ok"); //просто для проверки, что это работает :)

            FileInputStream fis = new FileInputStream(fileName + ".out");

            ObjectInputStream oin = new ObjectInputStream(fis);

            Map<Character, Integer> characters = (Map<Character, Integer>) oin.readObject();

            int total=0;

            for (int v : characters.values()) total += v;

            for (var i : characters.entrySet()){
                System.out.println(i.getKey() + " - " + (((double)i.getValue())/total)*100);
            }
        }
        else
        {
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

            FileOutputStream fos = new FileOutputStream(fileName+".out");

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(characters);

            oos.flush();

            oos.close();
        }

    }

    public static void indexFolder(String folder) throws IOException, ClassNotFoundException {
        File dir = new File(folder);
        String[] files = dir.list();

        for (String s : files){
            indexFile(folder+"/"+s);
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String userInput;
        userInput = sc.nextLine();
        indexFile(userInput);
        userInput = sc.nextLine(); // /run/media/Nikolay/46E1-BAEE/ArchLinuxVM/Logs
        indexFolder(userInput);
    }
}