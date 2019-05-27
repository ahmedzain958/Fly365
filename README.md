# Fly365

Fly365 is an application that displays one-way, round-trip and multi-city flights which enables traveller to know his flight itineraries 
and filter them by airways of the flight, airports that he/she willgo during his/her itinerary or stops (1stop/2stops/3stops/..) as he/she
needs.

Traveller can specify the number of travlerrs he/she want to book.



## Languages
Kotlin ; application is totally written in kotlin

## Libraries

Koin: koin is the latest dependency injection libary and it is recommended than dagger2.

Rxjava: app calls all network apis using rxjava calls, though no more rxjava usage in the app which makes the code with lower prerformance.

Clean Architecture: app contains a domain, data and presentation layers to isolate the logic of the application from the activity.

Retrofit and Okhttp: for calling network apis



## Accomplished:
- The application starts with displaying flight search form that matching the one on our website (https://nz.fly365.com/)
- Search form should have all fields as on our website and allow same core functionality
- Only main flow functionality is require, Which are: search form page , search result page
- We don't require specific UI implementation, Even though we expecting UI to be user friendly
- At least, Basic flight details should be displayed, this includes Airline name, origin airport, Destination Airport, Time, Price, Flight number.
- We expect you to handle all non 200 API response cases, this includes validation errors, 500 errors, etc

## Remaining:

- After search, User should be able to filter search results by Airline, Airport, Flight stops
- Unit tested code
