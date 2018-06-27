Overview
-------
This library adds two pdf decoder classes for the [subsampling-scale-image-view].

![subsampling-pdf-decoder example](https://raw.githubusercontent.com/num42/subsampling-pdf-decoder/master/example.gif =250x)

Getting started
-------

The classes provided in this library need Version 21 (Lollipop) or above.

```
repositories {
    jcenter()
}

dependencies {
    compile 'de.number42:subsampling-pdf-decoder:0.1.0@aar'
}
```

For further instructions on how to use it, see the sample application. There we use a [VerticalViewPager] and a PagerAdapter.
Within the PagerAdapter we use the subsampling-scale-image-view and this's decoder classes.


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
