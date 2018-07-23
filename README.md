Overview
-------
This library adds two pdf decoder classes for the [subsampling-scale-image-view].

<img src="https://raw.githubusercontent.com/num42/subsampling-pdf-decoder/master/example.gif" width="250" />

Usage
-------

The classes provided in this library need version 21 (Lollipop) or above.

The following code renders a single pdf page into a interactive view:

```
SubsamplingScaleImageView imageView = new SubsamplingScaleImageView(context);

// the smaller this number, the smaller the chance to get an "outOfMemoryException"
// still, values lower than 100 really do affect the quality of the pdf picture
imageView.setMinimumTileDpi(120);

// sets the PDFDecoder and PDFRegionDecoder for the imageView
// to render the first page of the pdf with a max. zoom level of 8
imageView.setBitmapDecoderFactory(() -> new PDFDecoder(1, file, 8));
imageView.setRegionDecoderFactory(() -> new PDFRegionDecoder(1, file, 8));

ImageSource source = ImageSource.uri(file.getAbsolutePath());

imageView.setImage(source);
```

For a more complex example see the sample application. There we use a [VerticalViewPager] and a PagerAdapter.
Within the PagerAdapter we use the subsampling-scale-image-view and this's decoder classes.


Download
-------
```
repositories {
    jcenter()
}

dependencies {
    compile 'de.number42:subsampling-pdf-decoder:0.1.0@aar'
}
```

License
-------

    Copyright 2016 Number42

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[subsampling-scale-image-view]: https://github.com/davemorrissey/subsampling-scale-image-view
[VerticalViewPager]: https://github.com/castorflex/VerticalViewPager
