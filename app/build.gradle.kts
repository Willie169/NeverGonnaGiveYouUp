
plugins {
    id("com.android.application")
    
}

android {
    namespace = "com.willie.nevergonnagiveyouup"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.willie.nevergonnagiveyouup"
        minSdk = 21
        targetSdk = 33
        versionCode = 10
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
    lintOptions {
        disable("all")
    }
    
}

dependencies {


    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
}
