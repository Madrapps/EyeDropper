# EyeDropper
An android library to pick colors from any image loaded in an ImageView or anything drawn on a Custom View

<img src="/preview/preview.gif" alt="preview" title="preview" width="255" height="395" align="right" vspace="20" />

Download
-----

```gradle
repositories {
  // mavenCentral() or jcenter() works as well // Linking to mavenCentral IN_PROGRESS
  maven {
    url  "http://dl.bintray.com/madrapps/maven" 
  }
}

dependencies {
  compile 'com.github.madrapps:eyedropper:1.0.0'
}
```

Usage
-----

To pick color from any view (ImageView or Custom View) in your layout add the following to your code:

```java
final View targetView = findViewById(R.id.targerView); // Any view from which you want to pick color
new EyeDropper(targetView, new ColorSelectionListener() {
  @Override
  public void onColorSelected(@ColorInt int color) {
    // color is the color selected when you touch the targetView
    (findViewById(R.id.colorSlot)).setBackgroundColor(color);
  }
});
```

License
-----

EyeDropper by [Madrapps](http://madrapps.github.io/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
