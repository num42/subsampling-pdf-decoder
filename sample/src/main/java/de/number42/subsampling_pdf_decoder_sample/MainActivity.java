package de.number42.subsampling_pdf_decoder_sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import de.number42.subsampling_pdf_decoder_sample.Util.Utils;
import de.number42.subsampling_pdf_decoder_sample.pager.PDFPagerAdapter;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * This class simply shows a pdf file within the activity
 */
public class MainActivity extends AppCompatActivity {

  /**
   * name of the pdf in assets folder
   */
  private final String pdfName = "LoremIpsum.pdf";

  /**
   * A {@VerticalViewPager} to scroll vertical within the pdf
   */
  VerticalViewPager pager;

  /**
   * Shows the page count
   */
  TextView pages;

  /**
   * The animator to switch between views.
   */
  ViewAnimator animator;

  /**
   * The {@PDFPagerAdapter} implemented in this example
   */
  private PDFPagerAdapter pagerAdapter;

  /**
   * The current position within the pdf
   */
  protected Integer currentPosition = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    pager = findViewById(R.id.pager);
    pages = findViewById(R.id.pages);
    animator = findViewById(R.id.animator);

    findViewById(R.id.btnLoadExtern).setOnClickListener(view -> {
      Toast toast = Toast.makeText(MainActivity.this, "Implement an intent to show the pdf externally", Toast.LENGTH_SHORT);
      toast.show();
    });


    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      animator.setDisplayedChild(1);
    } else {
      setPDF();
    }

    pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
      @Override
      public void onPageSelected(int index) {
        MainActivity.this.onPageSelected(index);
      }
    });

  }


  /**
   * Callback method for the {@link #onPageSelected(int)} to handle setting the
   * page number in the activity
   * @param position the current position in the pager
   */
  private void onPageSelected(int position) {
    this.currentPosition = position;
    updatePageCounter();
  }

  /**
   * Sets the pdf file to the {@PDFPagerAdapter} and then sets the adapter to the
   * {@VerticalViewPager}
   */
  private void setPDF() {
    animator.setDisplayedChild(0);
    pagerAdapter = new PDFPagerAdapter(this, Utils.getFileFromAssets(this, pdfName));
    pager.setAdapter(pagerAdapter);
    updatePageCounter();
  }


  /**
   * updates the page counter while scrolling through the pdf
   */
  public void updatePageCounter() {
    pages.setText(currentPosition + 1 + "/" + pagerAdapter.getCount());
    pages.clearAnimation();
    AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.0f);
    animation1.setDuration(1000);
    animation1.setStartOffset(4000);
    animation1.setFillAfter(true);
    pages.startAnimation(animation1);
  }
}
