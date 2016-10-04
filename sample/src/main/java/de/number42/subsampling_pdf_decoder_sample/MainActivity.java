package de.number42.subsampling_pdf_decoder_sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.number42.subsampling_pdf_decoder_sample.Util.Utils;
import de.number42.subsampling_pdf_decoder_sample.pager.PDFPagerAdapter;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * This class simply shows a pdf file within the activity
 */
public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

  /**
   * A {@VerticalViewPager} to scroll vertical within the pdf
   */
  @BindView(R.id.pager) VerticalViewPager pager;

  /**
   * Shows the page count
   */
  @BindView(R.id.pages) TextView pages;

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
    pager.setOnPageChangeListener(this);
    setPDF();
  }

  /**
   * Sets the pdf file to the {@PDFPagerAdapter} and then sets the adapter to the
   * {@VerticalViewPager}
   */
  private void setPDF() {
    pagerAdapter = new PDFPagerAdapter(this, Utils.getFileFromAssets(this, "Kanban.pdf"));
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
