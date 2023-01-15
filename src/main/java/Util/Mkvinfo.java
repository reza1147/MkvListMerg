package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Mkvinfo {
    static Path mkvInfoPath = Paths.get("C:\\Program Files (x86)\\MKVToolNix\\mkvinfo.exe");
    public static String readInfo(File file) {
        List<String> command = new ArrayList<String>();
        command.add(mkvInfoPath.toString());
        command.add("--ui-language");
        command.add("en");
        command.add(file.getAbsolutePath());
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line+"\n");
        }
        return sb.toString();
    }
}
