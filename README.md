# Popcorn App

----
## About
This is a search engine for movies, composed of 4 screens (Splash, Search, Movies list results, Movie details).

In this project I have used several design patterns and components that will be described below.

## Validations
* The app will show error messages if the user types an empty string
* Also, will notify the user if there is no movie found with that title
* An alert will appear if there is no internet connection.

## Manifest
* Used permissions for internet
* All activities are blocked in portrait mode and screen is blocked at keyboard showing up or hiding.
* Custom App class is used
* Network security config inserted because of rendering problems in Picasso on devices with Android 8.0 and newer.

## Project setup
* Project is using AndroidX libraries and approach.
* It can be compiled and built in Android Studio 3.4 or newer.

## Architectural patterns:
* I have used an MVVM + Clean Arhitecture approach: the app is split into modules (packages), one for each screen
* I have used Android Jetpack components to achieve this purpose - LiveData, ViewModel, Databinding.

## Design patterns
* I have used the singleton pattern for Network Engine
* Dependency injection (all dependencies are injected from fragment to viewModel, all modules are decoupled, complying to Clean Arhitecture)
* Reactive pattern - LiveData (mutable objects), Double way databinding, Observable pattern, Binding Adapters
* Factory pattern - for viewModel creation
* Command pattern - for communication between viewModels and fragments

## Structure
The code is split into packages with the following structure:

* Access layer -  which holds the networking client and interfaces
* Activities - which holds all sub-packages with each activity / fragment / adapter / viewModel / tasksRepository
* Components - which hold re-usable components such as commands
* Extensions - which holds different Utils classes, dialogs, binding adapters (for images)
* Infrastructure - which holds factories (in this case ViewModelFactory) and App custom class
* Models - which holds, in this case just API models (request and response)
* Workers - different workers that can be re-used (in this case just SearchMovie worker) 

## Communication between layers
* Activities communicate with Fragments through interface listeners
* All data is passed from activity to fragment through fragment constructor, for decoupling purposes.
* In fragment I instantiate the viewModel with ViewModelFactory. Then the fragment communicates with viewModel through liveData commands.
* ViewModels communicate with xmls through dataBinding (2 way communication (e.g. text = "@={}")

## Dependency injection
* In fragment are instantiated all workers and dataModels. Then they injected in tasksRepository or in viewModel directly. If there is a tasks repository, then this is injected in viewModel.
* ViewModels are injected into xml through dataBinding.

## Unit testing
* All code is very modular and decoupled, in order to be tested.
* ViewModels, tasksRepositories and dataModels do not contain any Android element, in order to be unit-tested.
* In this combination of architectures (MVVM + Clean) I perform unit tests on viewModels, tasks repositories and dataModels (where applicable).
* In this case I have performed unit tests just on SearchMovie module, for illustration purposes, which contains also the api call (8 unit tests on viewModel and 2 on tasksRepository). Tests on other modules would be the same.
* I am using Mockito / JUnit.

## Gradle. Libraries
* I set the project on min sdk 21, with target on 28.
* I have two build types (debug, release), having the same data.
* I hold some fields to use in the project with BuildConfig (apiKey etc)
* I have enabled dataBinding.
* I have enabled Java 8 for lambdas.
* I use recyclerView for lists, viewModel and liveData (as stated above), GSON for json serialization / deserialzation in conjuction with Retrofit, Retrofit for networks calls, Picasso for images.
* I like to keep all versions visible here, not centralized, to see when updates are available.

## UI / UX
* I have used especially ConstraintLayout, which gives flexibilty and avoid nested layouts and is also producing high quality UI.
* I have used a sliding animation between activities.
* I have used a dynamic approach on search textField in the searchMovie activity. E.g. when you empty the search edit container, the keyboard disspears and the "Find the movie" appears again (which is custom, is not an EditText hint).
* I have used percentages, in order for the screens to adapt to as many resolutions as possible.
* I have exported the svg assets to all 5 / 6 resolutions specific on Android.

## Other observations
* I tried as much as possbile to use compact dimensions for all layouts, saved in dimens.xml file. There are cases where the UI is very custom thus I put the dimensions directly in the xml files.
* I separated the string to strings.file.
* I did not implement pagination (I know that the API returns more pages, if applicable). At this moment, I am loading just the first page. This would have taken more time to implement.
* I have used regions in the whole project, for a better code visibility and organization.
* I have used dataBinding also in recyclerViews. Also, I have used in adapters the Diff.Util implementation, for optimisation and speed on the recyclerView.
* I have used Parcelable instead of Serializable, to transfer objects from one activity to other, since Serializable uses reflection (being part of Java libraries) and costs more memory than Parcelable, which is Android specific method.
* The carousel in the Search Screen has just static images.

## Development timeframe
* The app took approx 22 hours to develop (the commits show the starting point and the ending point of the app, with every stage of development).
* Unit tests took approx 1.5 hours
* Documentation and videos took approx 1.5 hours

## Kotlin
* Update on 7th of November 2019
* Created branch KotlinVersion where I have migrated MovieDetails scene to Kotlin (3 classes 100% Kotlin) in approx 2 hours
* Created branch JavaVersion with all classes 100% Java.
* Merged into master the KotlinVersion.