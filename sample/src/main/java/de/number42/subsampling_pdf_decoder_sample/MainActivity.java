package de.number42.subsampling_pdf_decoder_sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.number42.subsampling_pdf_decoder_sample.Util.Utils;
import de.number42.subsampling_pdf_decoder_sample.pager.PDFPagerAdapter;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * This class simply shows a pdf file within the activity
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

  /**
   * name of the pdf in assets folder
   */
  private final String pdfName = "LoremIpsum.pdf";

  /**
   * A {@VerticalViewPager} to scroll vertical within the pdf
   */
  @BindView(R.id.pager) VerticalViewPager pager;

  /**
   * Shows the page count
   */
  @BindView(R.id.pages) TextView pages;

  /**
   * The animator to switch between views.
   */
  @BindView(R.id.animator) ViewAnimator animator;

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
    ButterKnife.bind(this);

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      pager.setOnPageChangeListener(this);
      animator.setDisplayedChild(1);
    } else {
      setPDF();
    }
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

  //listener functions
  @Override public void onPageScrolled(int position, float positionOffset,
      int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {
    this.currentPosition = position;
    updatePageCounter();
  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  /**
   * Shows a toast with further instructions.
   *
   * @param view view parameter of the button click
   */
  @OnClick(R.id.btnLoadExtern) public void loadExtern(View view) {
    Toast toast = Toast.makeText(this, "Implement an intent to show the pdf externally", Toast.LENGTH_SHORT);
    toast.show();
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
