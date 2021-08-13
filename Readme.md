MudahChat App

1. This is Simple Chat Application developed by Kotlin.
2. PreLoad Chat Messages Saved in assets folder(chat.json)
3. Using Retrofit Web Service for post message API.("https://reqres.in/api/users").
4. All Messages stored in local Room Database.
5. bind all data using Databinding


Configuration Setup : 

1. Install Android Studio 4.2+

2. Required Android Sdk Minimum SDK version 23 and Target SDK version 30+

3. signingConfigs {
        config {
            keyAlias 'kay0'
            keyPassword '123456'
            storePassword '123456'
            storeFile rootProject.file('keystore.jks')
        }
    }
