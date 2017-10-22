## Dokumentácia k Bluemix servisom:
App ID - https://console.bluemix.net/docs/services/appid/existing.html#adding-app-id-to-an-existing-app
Cloudant NoSQL DB - https://console.bluemix.net/docs/services/Cloudant/getting-started.html#getting-started-with-cloudant

## Basic Starter
Basic IBM Cloud Mobile Starter in Java

[![](https://img.shields.io/badge/bluemix-powered-blue.svg)](https://bluemix.net)
[![](https://img.shields.io/badge/platform-android-lightgrey.svg?style=flat)](https://developer.android.com/index.html)

### Table of Contents
* [Summary](#summary)
* [Requirements](#requirements)
* [Configuration](#configuration)
* [Run](#run)
* [License](#license)

### Summary

The Basic IBM Cloud Mobile Starter is an empty project that has integration points for each of the IBM Cloud Mobile services.

### Requirements

* A [Bluemix](http://bluemix.net) Account
* [Android Studio](https://developer.android.com/studio/index.html) and [Gradle](https://gradle.org/gradle-download/)

### Configuration

* Open the project in Android Studio and perform a Gradle Sync.
* There is no configuration in the Basic starter if there is no Mobile Foundation Service. Incase Mobile Foundation is added then follow the below pre steps.

##### Steps (IBM Cloud Mobile Foundation (Optional)):

* Goto the project folder and find a shell script by name **mfpregisterapp.sh** and ensure that you have execute and write permissions in order to run the script.
* run **mfpregisterapp.sh**. This should set up the Mobile Foundation prereqs for running the starter

### Run

Click **Run** to start the app in Android Studio.

<img src="README_Images/basic.png" alt="Basic App Screenshot" width="250px"/>

The application has built in integration points for the Mobile Analytics, Push Notifications and Mobile Foundation services.

### License
This package contains code licensed under the Apache License, Version 2.0 (the "License"). You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 and may also view the License in the LICENSE file within this package.
"# navigation-for-autists" 
