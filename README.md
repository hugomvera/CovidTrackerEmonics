# CovidTrackerEmonics

  


## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#Demonstration)
4. [Use-Cases](#use-case)
5. [Collaboration](#collaboration)
6. [FAQs](#faqs)
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


## Use-Cases
**From Welcome Screen either (a) or (b)**
* (a) Click Login to go to log-in page.
* (b) Click SignUp to go to sign-up page.

**From log-in page:**
* Provide existing user credentials, and click Login.

**From sign-up page:**
* Provide new user credentials, and click Register.

**On Successful Log-In: Covid Tracker Chart Page Displayed**
* Click OR for State.
* Line Chart or Bar Chart for Charts.
* Death for Data Type.
* Click Select Date. Click on pencil to type-in date range or select the range from calendar.
* Click Save on the calendar.
* Click and select the bar chart or line chart again.

**Once chart display has populated data**
* Click on Convert To CSV to go to the CSV Display Page.
* Look at the CSV version of the chart data.

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
2. **What Was The Most Difficult?**

Many Api/Frameworks had to be used in conjunction:
* Room DataBase
* RetroFit for Rest
* Graphing Utilities

## Features We Would've Liked To Add
* Email csv button feature to send a csv copy of the data selected to a user's email.

