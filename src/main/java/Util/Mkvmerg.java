package Util;

import Model.SourceTrack;
import Model.SourceType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Mkvmerg {
    static Path mkvMergPath = Paths.get("C:\\Program Files (x86)\\MKVToolNix\\mkvmerge.exe");

    private static String runWithCommands(List<String> commands) {
        commands.add(0, mkvMergPath.toString());
        ProcessBuilder builder = new ProcessBuilder(commands);
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
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    public static List<SourceTrack> readTracks(File file) {
        List<String> command = new ArrayList<String>();
        command.add("-J");
        command.add(file.getAbsolutePath());
        List<SourceTrack> sourceTracks = new ArrayList<>();
        JsonObject root = JsonParser.parseString(runWithCommands(command)).getAsJsonObject();
        JsonArray tracks = root.get("tracks").getAsJsonArray();
        tracks.forEach(t -> {
            try {
                String name = "";
                if (t.getAsJsonObject().get("properties").getAsJsonObject().has("track_name"))
                    name = t.getAsJsonObject().get("properties").getAsJsonObject().get("track_name").getAsString();
                String language = "und";
                if (t.getAsJsonObject().get("properties").getAsJsonObject().has("language"))
                    language = t.getAsJsonObject().get("properties").getAsJsonObject().get("language").getAsString();
                sourceTracks.add(
                        new SourceTrack(t.getAsJsonObject().get("id").getAsInt(),
                                SourceType.valueOf(t.getAsJsonObject().get("type").getAsString().toUpperCase()),
                                t.getAsJsonObject().get("codec").getAsString(),
                                language,
                                name
                        ));
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        return sourceTracks;
    }
}
