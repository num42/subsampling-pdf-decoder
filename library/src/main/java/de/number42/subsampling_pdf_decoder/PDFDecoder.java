/*
 * Copyright (C) 2016 Number42
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.number42.subsampling_pdf_decoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.davemorrissey.labs.subscaleview.decoder.ImageDecoder;
import java.io.File;

/**
 * Decodes and renders a {@link PdfRenderer.Page} into a {@link Bitmap}
 */
public class PDFDecoder implements ImageDecoder {

  /**
   * defines the initial scale of the picture
   */
  private float scale;

  /**
   * the current pdf site
   */
  private int position;

  /**
   * the pdf file to render
   */
  private File file;

  /**
   * basic constructor for PDFDecoder.
   *
   * @param scale the scale to get from {@link Point} to Pixel
   */
  public PDFDecoder(int position, File file, float scale) {
    this.file = file;
    this.scale = scale;
    this.position = position;
  }

  /**
   * Creates a {@link Bitmap}in the correct size and renders the {@link PdfRenderer.Page} into it.
   *
   * @param context not used
   * @param uri not used
   * @return a bitmap, containing the pdf page
   * @throws Exception, if rendering fails
   */
  @Override public Bitmap decode(Context context, Uri uri) throws Exception {
    ParcelFileDescriptor descriptor =
        ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);

    PdfRenderer renderer = new PdfRenderer(descriptor);
    final PdfRenderer.Page page = renderer.openPage(position);

    Bitmap bitmap = Bitmap.createBitmap((int) (page.getWidth() * scale + 0.5),
        (int) (page.getHeight() * scale + 0.5f), Bitmap.Config.ARGB_8888);

    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

    page.close();
    renderer.close();
    descriptor.close();

    return bitmap;
  }
}
