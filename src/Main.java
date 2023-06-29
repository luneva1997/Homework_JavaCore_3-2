import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> files = Arrays.asList("Games/savegames/first",
                "Games/savegames/second", "Games/savegames/third");

        GameProgress first = new GameProgress(100, 1, 1, 0);
        GameProgress second = new GameProgress(80, 3, 2, 10.0);
        GameProgress third = new GameProgress(50, 3, 3, 20.5);

        saveGame("Games/savegames/first", first);
        saveGame("Games/savegames/second", second);
        saveGame("Games/savegames/third", third);

        zipFiles("Games/savegames/zip.zip", files);

        for (String i: files){
            File f = new File(i);
            f.delete();
        }

    }

    public static void saveGame(String path, GameProgress game){
        try (FileOutputStream writer = new FileOutputStream(path);
        ObjectOutputStream writerO = new ObjectOutputStream(writer)){
            writerO.writeObject(game);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> filesForZip) {
        try (ZipOutputStream zipper = new ZipOutputStream(new FileOutputStream(path));) {
            for (int i = 0; i < filesForZip.size(); i++) {
                FileInputStream fis = new FileInputStream(filesForZip.get(i));
                ZipEntry entry = new ZipEntry("save" + i);
                zipper.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zipper.write(buffer);
                zipper.closeEntry();
                fis.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}