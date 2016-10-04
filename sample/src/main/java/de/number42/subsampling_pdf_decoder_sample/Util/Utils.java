package de.number42.subsampling_pdf_decoder_sample.Util;

import android.app.Activity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple utils class.
 */
public class Utils {

  /**
   * Writes the pdf from the assets of this project ot the activities cache and returns the file
   * handle.
   *
   * @param activity the activity
   * @param filename the filename in the assets
   * @return the pdf file in the cache
   */
  public static File getFileFromAssets(Activity activity, String filename) {
    File f = new File(activity.getCacheDir() + "/Kanban.pdf");
    if (!f.exists()) {
      try {
        InputStream is = activity.getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(buffer);
        fos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return f;
  }
}
