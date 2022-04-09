# Map_Location android app

## Description
This software is mostly concerned with Android rooms and Google Map Api.
The essential prerequisites for the application are as follows:

>-can enter information about a city into the database (details: city name, country name, city latitude, city longitude).

>-Can insert sample data from the retrofit API into the database.

>-has the ability to update a city's information in the database.

>-has the ability to remove a city's information from the database.

>-has the ability to erase all city data from the database.

>-can see the location of the city Using a map to locate a city's details

## Used resources

>    IDE :Android Studio
>    
>    Programing Language : Kotlin
>    
>    Icons : https://iconarchive.com/
>    
>    api Host :https://www.npoint.io/
>    
>    Sample Data : https://simplemaps.com/data/world-cities

## demo 


![Layouts](https://user-images.githubusercontent.com/73293868/162576945-a1b4d8f4-bf1f-41d8-b182-05e780b5dc1d.png)





https://user-images.githubusercontent.com/73293868/162577717-7fa35e3c-11fe-4d1a-afbf-ef3b90030adc.mp4




## package hierarchy


```bash
Root [com.atarious.map_location]
│   
├───Activities
│       MainActivity.kt
│       
├───Adapters
│       CitiesAdapter.kt
│       
├───api
│       retrofit_Config.kt
│       
├───Data
│   ├───dao
│   │       CityDao.kt
│   │       
│   ├───database
│   │       LocalDatabase.kt
│   │       
│   ├───Repositories
│   │       CityRepository.kt
│   │       
│   ├───tables
│   │       City.kt
│   │       
│   └───viewModels
│           CityViewModel.kt
│           
├───fragments
│   ├───dialogFragments
│   │       addCityFragment.kt
│   │       delete_city_dialog_fragment.kt
│   │       UpdateCityDialogFragment.kt
│   │       
│   ├───home
│   │       HomeFragment.kt
│   │       
│   ├───List
│   │       ListFragment.kt
│   │       
│   └───map
│           MapsFragment.kt
│           
└───model
        Cities.kt
```


## NavGraph

```mermaid
graph LR
A[Home Fragment]  --> B[List Fragment]
B --> C[add City Dialog Fragmnet]
B --> E[Map fragmnet]
B --> F[update City Dialog Fragment]
B --> G[delete city Dialog Fragment]
E --> B
