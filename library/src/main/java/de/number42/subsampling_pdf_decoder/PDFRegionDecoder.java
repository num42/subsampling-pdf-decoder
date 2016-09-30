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
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import java.io.File;
import java.io.IOException;

/**
 * Decodes and renders a given rect out of a {@link PdfRenderer.Page} into a {@link Bitmap}
 */

public class PDFRegionDecoder implements ImageRegionDecoder{
  /**
   * the page that will be rendered to a bitmap.
   */
  private PdfRenderer renderer;

  /**
   * defines the initial scale of the picture
   */
  private float scale;

  /**
   * the current page position in the pdf
   */
  private int position;

  /**
   * the pdf page
   */
  private PdfRenderer.Page page;

  /**
   * the file descriptor
   */
  private ParcelFileDescriptor descriptor;

  /**
   * the pdf file
   */
  private File file;

  /**
   * basic constructor for PDFDecoder.
   * @Param position:the current position in the pdf
   * @Param file: the pdf-file
   * @param scale: the scale factor
   */
  public PDFRegionDecoder(int position , File file,  float scale) {
    this.file = file;
    this.scale = scale;
    this.position = position;
  }

  /**
   * Initializes the region decoder. This method initializes
   * @param context not used here
   * @param uri not used here (file is already loaded)
   * @return the rescaled point
   * @throws Exception
   */
  @Override public Point init(Context context, Uri uri) throws Exception {

    this.descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
    this.renderer = new PdfRenderer(descriptor);
    page = renderer.openPage(position);

    return new Point((int) ((page.getWidth() * scale + 0.5f)),
        (int) ((page.getHeight() * scale + 0.5f)));
  }

  /**
   * Creates a {@link Bitmap} in the correct size and renders the region defined by rect of the
   * {@link PdfRenderer.Page} into it.
   *
   * @param rect the rect of the {@link PdfRenderer.Page} to be rendered to the bitmap
   * @param sampleSize the sample size
   * @return a bitmap containing the rendered rect of the page
   */
  @Override public Bitmap decodeRegion(Rect rect, int sampleSize) {

    int bitmapWidth = rect.width() / sampleSize;
    int bitmapHeight = rect.height() / sampleSize;

    Bitmap bitmap = Bitmap.createBitmap(bitmapWidth , bitmapHeight,
        Bitmap.Config.ARGB_8888);

    Matrix matrix = new Matrix();
    matrix.setScale(scale/sampleSize,scale/sampleSize);
    matrix.postTranslate(-rect.left/sampleSize, -rect.top/sampleSize);
    page.render(bitmap, null, matrix, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

    return bitmap;
  }

  @Override public boolean isReady() {
    return true;
  }

  /**
   * close everything
   */
  @Override public void recycle() {
    page.close();
    renderer.close();
    try {
      descriptor.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
