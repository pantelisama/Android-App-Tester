

---

## Instructions

Test script name : ItemCreationTest.kt
Test script location : \android-test-assignment\TheSurgeonsTodoList\app\src\androidTest\java\com\touchsurgery\thesurgeonstodolist\ItemCreationTest.kt

## Test Description : 

1.	Add Item 1 & Item 2
      Test adding Item 1 with priority 1.
      Test adding Item 2 with priority 6.
      Verify that Item 1 appears first, followed by Item 2.
2.	Delete Item 1
      Test deleting Item 1 from the list.
      Verify that Item 1 (priority 1) is no longer visible.
3.	Delete All Items
      Test deleting both Item 1 and Item 2.
      Verify that the list is empty and no items are displayed.
4.	Sort by Name
      Open the Settings menu and select Sort list by name.
      Verify that items are sorted alphabetically in the list.
5.	Verify Deletion in Settings
      Test deleting Item 1 and Item 2.
      Verify that after returning from Settings, deleted items do not reappear.

## Dependencies : 

implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"

    // Dagger Dependency Injection
    implementation 'com.google.dagger:dagger:2.19'
    kapt 'com.google.dagger:dagger-compiler:2.19'
    kapt 'com.google.dagger:dagger-android-processor:2.15'

    // Android Support Libraries
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

    // JUnit for Unit Testing
    testImplementation 'junit:junit:4.13.2'

    // AndroidX Testing & Espresso
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.5.1'
    androidTestImplementation 'androidx.test.espresso:espresso-intents:3.5.1'
    androidTestImplementation 'androidx.test.espresso:espresso-accessibility:3.5.1'


    // Espresso & Dagger Testing
    androidTestImplementation 'androidx.test.espresso:espresso-idling-resource:3.5.1'
    kaptAndroidTest 'com.google.dagger:dagger-compiler:2.19'

## Emulator :

Properties
avd.ini.displayname              Pixel 3 API 27
avd.ini.encoding                 UTF-8
AvdId                            Pixel_3_API_27
disk.dataPartition.size          6442450944
fastboot.chosenSnapshotFile      
fastboot.forceChosenSnapshotBoot no
fastboot.forceColdBoot           yes
fastboot.forceFastBoot           no
hw.accelerometer                 yes
hw.arc                           false
hw.audioInput                    yes
hw.battery                       yes
hw.camera.back                   virtualscene
hw.camera.front                  emulated
hw.cpu.ncore                     2
hw.device.hash2                  MD5:8a60718609e0741c7c0cc225f49c5590
hw.device.manufacturer           Google
hw.device.name                   pixel_3
hw.dPad                          no
hw.gps                           yes
hw.gpu.enabled                   yes
hw.gpu.mode                      auto
hw.initialOrientation            portrait
hw.keyboard                      yes
hw.lcd.density                   440
hw.lcd.height                    2160
hw.lcd.width                     1080
hw.mainKeys                      no
hw.ramSize                       4096
hw.sdCard                        yes
hw.sensors.orientation           yes
hw.sensors.proximity             yes
hw.trackBall                     no
image.androidVersion.api         27
image.sysdir.1                   system-images\android-27\google_apis_playstore\x86\
PlayStore.enabled                true
runtime.network.latency          none
runtime.network.speed            full
showDeviceFrame                  yes
skin.dynamic                     yes
tag.display                      Google Play
tag.displaynames                 Google Play
tag.id                           google_apis_playstore
tag.ids                          google_apis_playstore
vm.heapSize                      256


