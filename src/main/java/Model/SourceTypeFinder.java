package Model;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SourceTypeFinder {
    private static final Set<String> VIDEO_EXTINSION = new HashSet<String>(Arrays.asList("webm", "mkv", "flv", "vob", "ogv", "rrc", "gifv", "mng", "mov", "avi", "qt", "wmv", "yuv", "rm", "asf", "amv", "mp4", "m4p", "m4v", "mpg", "mp2", "mpeg", "mpe", "mpv", "m4v", "svi", "3gp", "3g2", "mxf", "roq", "nsv", "flv", "f4v", "f4p", "f4a", "f4b", "mod"));
    private static final Set<String> AUDIO_EXTINSION = new HashSet<String>(Arrays.asList("wav", "bwf", "raw", "aiff", "flac", "pac", "tta", "wv", "ast", "aac", "mp3", "amr", "s3m", "act", "au", "dct", "dss", "gsm", "mmf", "mpc", "ogg", "oga", "opus", "ra", "sln", "mka", "vox"));
    private static final Set<String> SUBTILE_EXTINSION = new HashSet<String>(Arrays.asList("aqt", "gsub", "jss", "sub", "ttxt", "pjs", "psb", "rt", "smi", "slt", "ssf", "srt", "ssa", "ass", "usf", "idx", "vtt"));

    static SourceType findType(File file) throws IllegalArgumentException {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (VIDEO_EXTINSION.contains(extension))
            return SourceType.VIDEO;
        if (AUDIO_EXTINSION.contains(extension))
            return SourceType.AUDIO;
        if (SUBTILE_EXTINSION.contains(extension))
            return SourceType.SUBTITLE;
        throw new IllegalArgumentException("Can't recognize ." + extension + " file type!!");
    }
}
