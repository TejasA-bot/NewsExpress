# NewsExpress

A news app that brings you the latest headlines.

## Features

* Fetches news articles using the News API.
* Shows news from various categories.
* Supports Google login using tokenized authentication.

## Technologies Used

* Android
* Java 
* News API
* Google Sign-In

## Installation (Optional)

**To run this project, you'll need:**

1. **API Key:** Obtain a News API key from [https://newsapi.org](https://newsapi.org).
2. **Android Studio:** Download and install Android Studio from [https://developer.android.com/studio](https://developer.android.com/studio).

# Build With

* News API - Used to fetch news data
* Picasso - Used for image loading and caching

## How to implement Google Sign-In and token-based authentication in an Android project

Step 1: Setting Up Google Sign-In 

   1. Set Up a Project in the Google Developers Console:

* Go to the Google API Console.
* Create a new project.
* Enable the "Google Sign-In" API.
* Configure the OAuth consent screen.
* Generate OAuth 2.0 Client IDs and add the SHA-1 fingerprint from your Android Studio project.

Step 2: Integrate Google Sign-In in Your Android Project
* Add Dependencies:
* Add the following dependencies in your build.gradle (app-level) file:

```
implementation 'com.google.android.gms:play-services-auth:20.2.0'
```

# Note

* Make sure to add the required SHA-1 fingerprint to the Google API Console.
* Refer to the [Google Sign-In documentation](https://developers.google.com/identity/sign-in/web/sign-in) for more details.


