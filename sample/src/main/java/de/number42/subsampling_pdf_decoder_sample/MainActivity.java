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

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

  @BindView(R.id.pager) VerticalViewPager pager;
  @BindView(R.id.pages) TextView pages;
  private PDFPagerAdapter pagerAdapter;
  protected Integer currentPosition = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    pager.setOnPageChangeListener(this);
    setPDF();
  }

  private void setPDF() {
    pagerAdapter = new PDFPagerAdapter(this, Utils.getFileFromAssets(this, "Kanban.pdf"));
    pager.setAdapter(pagerAdapter);
    updatePageCounter();
  }


  //listener functions
  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {
    this.currentPosition = position;
    updatePageCounter();
  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  /**
   * updates the page counter
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
