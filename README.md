## AndroidX Compatible

## MyPLaces

An android library which contains the google's place autocomplete API implementation, along with some UI and customization options.

# Installation

In your project level build.gradle file:

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Then in your app level build.gradle file:

```
android {
	dataBinding {
	   enabled = true
	}
}

dependencies {
	implementation 'com.github.SachinRupani:MyPlaces:1.0.1-alpha'
}

```

# Implementation

Now to use the library in your app:

```
MyPlaces.with("AIzaXXXXXXXXXXXXXXXXXXXXXX") //Your Google API key
            //.setTextTypeFace(Typeface.createFromAsset(assets, "Ubuntu-Medium.ttf")) //
            //.setLocationIcon(R.mipmap.ic_launcher) //For changing the default location icon
            //.setNearbyLocation(lat = 28.7041, lng = 77.1025) //For getting results according to a specific lat lng
            //.setCountry("in") //For country specific results, refer to country codes
            //.setBackgroundListItem(android.R.color.transparent) //For changing the default list item background resource
            //.animateList(false) //true by default
            .startSearch(this)
```

Then, in your onActivityResult method of your activity:

```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && requestCode==MyPlaces.REQUEST_CODE_PLACE_SEARCH){
            val predictionResult=data?.extras?.getSerializable(MyPlaces.PREDICTION_RESULT) as PredictionModel?
            Log.d("Prediction",""+predictionResult?.placeId+" - "+predictionResult?.placeAddress)
        }
}
```

Thats it, Happy Coding