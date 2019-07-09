# Popcorn App

----
## About
This is search engine for movies, composed of 4 screens (Splash, Search, Movies list results, Movie details).

In this project I have used several design patterns and components that will be described below.

## Manifest
* Used permissions for internet
* All activities are blocked in portrait mode and screen is blocked at keyboard showing up or hiding.
* Custom App class is used
* Network security config inserted because of rendering problems in Picasso.

## Project setup
* Project is using AndroidX libraries and approach.
* It can be compiled and built in Android Studio 3.4 or newer.

## Architectural patterns:
* I have used an MVVM + Clean Arhitecture approach: the app is split into modules (packages), one for each screen
* I have used Android Jetpack components to accomplish achieve this purpose - LiveData, ViewModel, Databinding.

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
