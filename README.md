# EyeDropper
An android library to pick colors from any image loaded in an ImageView or anything drawn on a Custom View

USAGE
-----

To pick color from any view (ImageView or Custom View) in your layout add the following to your gradle dependency.

```groovy
compile 'com.github.madrapps:eyedropper:1.0.0'
```

In your code, use it as below:

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

LICENCE
-----

EyeDropper by [Madrapps](http://madrapps.github.io/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
