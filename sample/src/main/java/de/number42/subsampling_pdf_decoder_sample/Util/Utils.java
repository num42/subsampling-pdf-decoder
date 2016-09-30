package de.number42.subsampling_pdf_decoder_sample.Util;

import android.app.Activity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lindner Stefan on 30.09.16.
 */

public class Utils {

  public static File getFileFromAssets(Activity activity, String filename){
    File f = new File(activity.getCacheDir()+"/Kanban.pdf");
    if(!f.exists()) try{
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
    return f;
  }
}
