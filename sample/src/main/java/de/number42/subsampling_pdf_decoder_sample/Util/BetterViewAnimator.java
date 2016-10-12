package de.number42.subsampling_pdf_decoder_sample.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * This class is a copy of https://github.com/JakeWharton/u2020/blob/master/src/main/java/com/jakewharton/u2020/ui/misc/BetterViewAnimator.java
 */
public class BetterViewAnimator extends ViewAnimator {
  public BetterViewAnimator(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setDisplayedChildId(int id) {
    if (getDisplayedChildId() == id) {
      return;
    }
    for (int i = 0, count = getChildCount(); i < count; i++) {
      if (getChildAt(i).getId() == id) {
        setDisplayedChild(i);
        return;
      }
    }
    String name = getResources().getResourceEntryName(id);
    throw new IllegalArgumentException("No view with ID " + name);
  }

  public int getDisplayedChildId() {
    return getChildAt(getDisplayedChild()).getId();
  }
}