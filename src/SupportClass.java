import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SupportClass {
    public static void main(String[] args) {
        StringBuilder logs = new StringBuilder();

        String[] directories = {"src", "res", "savegames", "temp", "main", "test", "drawables", "vectors", "icons"};
        String[] files = {"Main", "Utils"};

        for (String i: directories) {
            File newDirectory = new File("");
            if (i.equals("src") | i.equals("res") | i.equals("savegames") | i.equals("temp")) {
                newDirectory = new File("Games/" + i);
            } else if (i.equals("main") | i.equals("test")){
                newDirectory = new File("Games/src/" + i);
            } else {
                newDirectory = new File("Games/res/" + i);
            }
            if (newDirectory.exists()) {
                newDirectory.delete();
                newDirectory.mkdir();
                logs.append("Обновлен каталог " + i + "\n");
            } else {
                newDirectory.mkdir();
                logs.append("Добавлен каталог " + i + "\n");
            }
        }

        for (String i: files) {
            File newfile = new File("Games/src/main/"+i+".java");
            try{
                if (newfile.createNewFile()){
                    logs.append("Добавлен файл " +i + ".java в каталог src/main \n");
                }
            } catch (IOException ex){
                logs.append(ex.getMessage() + "\n");
            }
        }

        File temp = new File("Games/temp/temp.txt");
        try{
            if (temp.createNewFile()){
                logs.append("Добавлен файл temp.txt в каталог temp \n");
            }
        } catch (IOException ex){
            logs.append(ex.getMessage() + "\n");
        }

        try (FileWriter writer = new FileWriter(temp, false)) {
            writer.write(logs.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
