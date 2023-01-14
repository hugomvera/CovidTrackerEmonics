# CovidTrackerEmonics

  


## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Collaboration](#collaboration)
5. [FAQs](#faqs)
### General Info
***

Data Visualization project.
Fetches data using RetroFit and stores in a Room Database.
ModelView is used to create the retrofit data.
FireBase is used to authenticate users.
The data involves deaths, negative, positive test results, Queries are perform based on date ranges and state selected. 
The queried data can be graphed in xy plane with lines connecting them or they can be put in a bar graph. 
The data is fetched from https://api.covidtracking.com/ where a Json is captured and parse Using Retrofit. 


### RoomDataBase
![RoomDataBasePicture](RoomDataBase.png)
This is our Database table with data populated. This was viewed using App Inspector. 


## Technologies
***
A list of technologies used within the project:
* [MP Android Chart](https://com.github.PhilJay:MPAndroidChart): Version 2.2.4 
* [View Model](https://androidx.lifecycle:lifecycle-viewmodel-ktx): Version 2.5.1
* [Room](https://androidx.room): Version 2.3
* [RetroFit](https://com.squareup.retrofit2:retrofit):Version 2.7.1 

### Demo

####  Login SingUp 



 <img src="https://github.com/hugomvera/CovidTrackerEmonics/blob/hugoChart1andChart2PassingData/LoginSingUpScreen1.png?raw=true" width="300" height="600"  >

#### Data Visualization





<div class="row">
  <div class="column">
   <img src="https://github.com/hugomvera/CovidTrackerEmonics/blob/hugoChart1andChart2PassingData/barGraph1.png?raw=true" width="300" height="600"  >
  </div>
  <div class="column">
   <img src="https://github.com/hugomvera/CovidTrackerEmonics/blob/hugoChart1andChart2PassingData/lineGraph1.png?raw=true" width="300" height="600" >
  </div>
  <div class="column">

  </div>
</div>




## Collaboration

#### Amik
#### Hugo 
#### Nibedita
#### Komal


### Contribute
> Email one of the Collaborators. 
> Tell us what you want to improve.
> Ask for pull/merge request.
## FAQs
***
A list of frequently asked questions
1. **Where Was the Data Fetched?**

   https://api.covidtracking.com/
2. **Where Was The Most Difficult?**

Many Api/Frameworks had to be used in conjunction:
* Room DataBse
* RetroFit for Rest
* Graphing Utilities

