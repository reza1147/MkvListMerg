import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("F:\\The Witcher Blood Origin");
//        Path start = Paths.get("C:\\Users\\rezat\\Downloads\\Video");
        ReadFiles rf = new ReadFiles(start);
//        rf.append(second);
        System.out.println(rf);
        File outputDir = new File(start + "\\output");
        if (!outputDir.exists())
            outputDir.mkdir();
        rf.files.forEach((name, list) -> {
            if (list.size() > 1) {
                List<String> command = new ArrayList<String>();
                String mkvmerge = "C:\\Program Files (x86)\\MKVToolNix\\mkvmerge.exe";
                command.add(mkvmerge);
                command.add("--ui-language");
                command.add("en");
                command.add("--output");
                command.add(outputDir.getAbsolutePath() + "\\" + name + ".mkv");
                boolean first=true;
                for (File file : list) {
                    switch (ReadFiles.fileType(file)) {
                        case 3:
                            if(first){
                                command.add("--track-name");
                                command.add("0:");
                                first=false;
                            }
                            break;
                        case 2:
                            command.add("--language");
                            command.add("0:fa");
                            command.add("--track-name");
                            command.add("0:RanginKamaanSokhan");
                            command.add("--track-name");
                            command.add("1:RanginKamaanSokhan");
                            break;
                        case 1:
                            command.add("--language");
                            command.add("0:fa");
                            break;
                    }
                    command.add(file.getAbsolutePath());
                }
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.redirectErrorStream(true);
                Process process = null;
                try {
                    process = builder.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while (true) {
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(line);
                }
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Muxin "+ name +" Ended!!..\n\n");
            }
        });

    }
}
