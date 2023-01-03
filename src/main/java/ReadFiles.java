import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class ReadFiles {
    Map<String, List<File>> files;

    public static final Set<String> VIDEO_EXTINSION = new HashSet<String>(Arrays.asList("webm", "mkv", "flv", "vob", "ogv", "rrc", "gifv", "mng", "mov", "avi", "qt", "wmv", "yuv", "rm", "asf", "amv", "mp4", "m4p", "m4v", "mpg", "mp2", "mpeg", "mpe", "mpv", "m4v", "svi", "3gp", "3g2", "mxf", "roq", "nsv", "flv", "f4v", "f4p", "f4a", "f4b", "mod"));
    public static final Set<String> AUDIO_EXTINSION = new HashSet<String>(Arrays.asList("wav", "bwf", "raw", "aiff", "flac", "pac", "tta", "wv", "ast", "aac", "mp3", "amr", "s3m", "act", "au", "dct", "dss", "gsm", "mmf", "mpc", "ogg", "oga", "opus", "ra", "sln", "mka", "vox"));
    public static final Set<String> SUBTILE_EXTINSION = new HashSet<String>(Arrays.asList("aqt", "gsub", "jss", "sub", "ttxt", "pjs", "psb", "rt", "smi", "slt", "ssf", "srt", "ssa", "ass", "usf", "idx", "vtt"));

    public ReadFiles(Path path) {
        files = new HashMap<String, List<File>>();
        append(path);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        files.forEach((name, list) -> {
            sb.append(name + "[");
            list.forEach(f -> {
                if (fileType(f) == 3)
                    sb.append("Video,");
                if (fileType(f) == 2)
                    sb.append("Audio,");
                if (fileType(f) == 1)
                    sb.append("Subtitle,");
            });
            sb.append("]\n");
        });
        return sb.toString();
    }

    static Integer fileType(File file) {
        if (file.isFile()) {
            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (VIDEO_EXTINSION.contains(extension))
                return 3;
            if (AUDIO_EXTINSION.contains(extension))
                return 2;
            if (SUBTILE_EXTINSION.contains(extension))
                return 1;
        }
        return 0;
    }

    public void append(Path path) {
        Arrays.stream(path.toFile().listFiles()).forEach(f -> {
            if (f.isFile()) {
                if(fileType(f)>0) {
                    String fileName = f.getName().substring(0, f.getName().lastIndexOf("."));
                    if (!files.containsKey(fileName))
                        files.put(fileName, new ArrayList<>());
                    files.get(fileName).add(f);
                    Collections.sort(files.get(fileName), new Comparator<File>() {
                        @Override
                        public int compare(File o1, File o2) {
                            return fileType(o2)-fileType(o1);
                        }
                    });
                }
            }
        });
    }
}
