package silkclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;


public class FileManager {


    private static Gson gson = new Gson();

    private static File ROOT_DIR = new File("SilkClient");
    private static File MODS_DIR = new File(ROOT_DIR, "Mods");

    public static void init() {

        if(!ROOT_DIR.exists()) { ROOT_DIR.mkdirs(); }
        if(!MODS_DIR.exists()) { MODS_DIR.mkdirs(); }

    }

    public static Gson getGson() {
        return gson;
    }

    public static File getModsDirectory() {
        return MODS_DIR;
    }

    public static File getRootDirectory() {
        return ROOT_DIR;
    }

    public static boolean writeJsonToFile(File file, Object obj) {

        try {
            if(!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(gson.toJson(obj).getBytes());
            outputStream.flush();
            outputStream.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T extends Object> T readFromJson(File file, Class<T> c) {
        try {

            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            inputStreamReader.close();
            inputStream.close();

            return gson.fromJson(builder.toString(), c);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String readFromJson(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            builder.append(line);
        }

        br.close();
        reader.close();
        inputStream.close();

        return builder.toString();
    }


}
