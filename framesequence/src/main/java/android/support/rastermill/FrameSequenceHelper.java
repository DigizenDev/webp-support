package android.support.rastermill;

import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhanglei on 2017/9/11.
 */

public class FrameSequenceHelper {

    public static boolean isSupported(@NonNull File file) throws Exception {
        if (!file.exists()) {
            return false;
        }

        if (!file.isFile()) {
            return false;
        }

        InputStream inputStream = istreamFromFile(file);
        boolean isSupport =  isSupported(inputStream);
        closeQuietly(inputStream);
        return isSupport;
    }

    public static boolean isSupported(@NonNull InputStream inputStream) throws Exception {
        return FrameSequence.isSupport(inputStream);
    }

    private static InputStream istreamFromFile(File f) {
        try {
            return new FileInputStream(f);
        } catch (Exception ignore) {

        }
        return null;
    }

    private static void closeQuietly(InputStream input) {
        closeQuietly((Closeable) input);
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    public static FrameSequenceDrawable getDrawable(InputStream is) {
        FrameSequence fs = FrameSequence.decodeStream(is);
        return new FrameSequenceDrawable(fs);
    }
}
