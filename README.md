<b>Settings</b>

https://github.com/deniotokiari/training-epam-2016/blob/master/ide_settings.jar



<b>TODO's</b>

https://github.com/Anna-Kohanova/HotelRoomsReservation/commit/eb92c9d145a6972715d09da94d88694b36847e72

https://github.com/PaulHost/youTubeCastomPlayer/commit/61977a72a36acb72d86c4904074bb9920fdcffec

https://github.com/Andrei1993ak/Finance-Manager-ver3/commit/188759a82cfd7fccc0e06ba991b17f6db5a61a6c

https://github.com/SShopik/Onliner/commit/cfae14580308fd3aade0bce4e19e7b2aef621365

https://github.com/egorikftp/Fitness-Cats/commit/bca5f10159b39318f3f2537750537658d5cf8667


<b>Important</b>: 

Classes will be held on Mondays and Fridays.

Also, 21.11.2016 is most probably that class will be held in room 40-16. TBC

Stats => https://deniotokiari.github.io/training-epam-2016/

Patterns => http://citforum.ru/SE/project/pattern/

SOLID => https://ru.wikipedia.org/wiki/SOLID

KISS => https://ru.wikipedia.org/wiki/KISS_(%D0%BF%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF)

YAGNI => https://ru.wikipedia.org/wiki/YAGNI

DRY => https://ru.wikipedia.org/wiki/Don%E2%80%99t_repeat_yourself

ACID

CRUD

DB => https://habrahabr.ru/post/281226/

Singleton, **Adapter**, Builder, Fabric, State, Strategy, **VeiewHolder**

IMPORTANT:

1. On Friday all need to comes at 17:50 to Makei/EPAM Systems door.
2. All of you should provide access to add commentaries in your repositories for everyone or for teachers[IstiN,alexdzeshko,deniotokiari,ilya-shknaj]
3. Checklist before commit

* [ ] check members name(m* or without m), use s* when static
* [ ] check accessors (public, private, protected, default, static)
* [ ] check { } in if, for, etc...
* [ ] fragment creation need to be with parameters in Bundle, and set like setArguments
* [ ] check use this.* or do not use this.* when get access to the members
* [ ] use @Override if extends some method of class or interface
* [ ] text_tag - do not use "_" in variable, members, packages
* [ ] textTag - incorrect package name(do not use UPPERCASE)
* [ ] hardcoded strings need to be in Resources
* [ ] check getActivity for NULL if use in fragment except onActivityCreated and onCreateView
* [ ] check all __________ underlined by Android Studio code
* [ ] move extras keys to constants

<b>Base project requirements:</b>

1. 5+ screens
2. Multi-threading
3. Data processing (parse json/xml)
4. Own implementation of http client, image loader
5. Data caching (SQLite, files, memory)
6. Backend

Note: Readme in yours repository should contain clear project's description.

=============================================================================

HW 31.10.2016

Implement your own DBHelper and write tests for CRUD operations
* Fix tests and create pull request with fix

HW 28.10.16

https://github.com/nostra13/Android-Universal-Image-Loader/wiki/Task-flow
Resolve todos in imageloader (file cache, lifo queue, check if loading started)

HW 26.10.2016

1. Resolve TODO's in Malevich in scope of your ImageLoader
2.* universal loader that will download whatever and ImageLoader will be example of that
Examples: Glide, Picasso, UniversalImageLoader, Fresko and etc.



HW 24.10.2016

1. Implement swipe-to-dissmiss or drag-n-drop in recycler view.

HW 21.10.2016

1. Load and parse json over org.json.JSONObject
2. Load and parse json over GSON library
3. Convert some field to another format. For instance 'Wed Oct 19 13:31:38 +0000 2016' to 'Yesterday 13:31'

For parsing you can use any response from your final project backend.
If backend return data only in xml format, you can parse xml, but without any external libraries.

PS. Parsing should work in background thread. In sample, which i demonstrate you yesterday, we did that in UI thread.

HW 19.10.2016

1. Write own AsyncTask that will have the same API as android.AsyncTask, but will support screen rotation.
    If we already downloaded data, we just need to reuse it, if activity was destroyed we need to destoy cache data.

2*. Start implementation of own ImageLoader.



HW 17.10.2016

1. Create 4 flavors (for example 2 variants of enviroments and 2 variants of countries)
2. rename apk over gradle task to app_name+flavor+version (example trainingAplicationProductionRelease_1.0.0.apk)
3. define variables in gradle and use it in Java code


</hr>
HW 14.10.2016

1. Create and deploy backend module
2. Get configuration for your app

HW 12.10.2016

1. Http protocol basics
2. Java collections (List+Map+Set) + SparseArrays
3. Create AppEngine module


HW 10.10.2016

1. Implement some object.
2. Write units test for that object
3. Mock part/fully behaviour of that object. (mockito, robolectric)
4. Write instrumentationTests (expresso, robotium )
5. Implement button which visibility depends of private field in activity. Mock that private object.

visibility conditions

<code>
if (BuildConfig.DEBUG )  {
    return true;
}
</code>

=============================================================================


HW 07.10.2016[deadline for all tasks 07.10.2016]

1. create reusable styles and custom them in prev HW
2. create custom attributes for different buttons
3. add screenshots of prev HW to readme.md as images[you can upload everything to git]
4. Add left/bottom icon support for IcButton class.
5. Draw9Patch, create splash screen with bg_splash.9.png

Links:
Checklist - https://github.com/IstiN/android_training_2014
Diagrams - http://www.planttext.com/planttext
Draw9patch - https://habrahabr.ru/post/113623/

HW 05.10.2016[deadline for all tasks 07.10.2016]

1. https://goo.gl/photos/H5CtzrZ18pNZwZe26, https://goo.gl/photos/Ds5osKmE1BvkP8QLA [launchMode, flags]
2. Create [static] layouts for social networks(vk.com/linked.in/fb.com/etc.)
3. Send project urls with HW and questions as "issues" to this repository!!!
4. Create samples with Percent layouts, and others

Links:
https://medium.freecodecamp.com/android-development-best-practices-83c94b027fd3#.si0lrmrgd

HW 03.10.2016

1. Save instance state example
2. Start activity Intent flags
3. Try ApiDemos with monitor. Where is the issues perfomance.
4. Bind service
5. Foreground service, service in separate process

Links:
https://www.dropbox.com/s/ax92q35mgtjv9k4/backstack.pdf?dl=0
https://www.dropbox.com/s/kqe9zbbqrsqekxv/complete_android_fragment_lifecycle.png?dl=0

HW 28.09.2016

1. What is mipmap?
2. What we need to add to build.gradle to support vector icons in old android versions?
3. Play with Android Monitor
4. How to create ApiDemos app
5. Review samples

Links:
https://romannurik.github.io/AndroidAssetStudio/
https://material.google.com/
https://developer.android.com/studio/profile/android-monitor.html?utm_source=android-studio


Для гостевого Wi-fi нужен список вида, FirstName_LastName. Добавить в https://goo.gl/Ksm3IQ
Гостевой Wi-fi делается максимум на месяц. Через месяц надо делать повторно.

HW 26.09.2016

1. Create project (github || bitbucket), soursetree
2. Push Android Project to master in repo (.gitignore)
3. Create 'feature_1', 'feature_n' branches
4. Push all branches to remote
5. Emulate conflict in a few files
6. Merge conflict to master
7. Chery Pick
8. Rebase
9. Stash

istin2007@gmail.com
topic: HW: Traning 2016
Body: Firstname Lastname - url to github/bitbucket
