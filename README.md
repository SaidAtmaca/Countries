# Countries

This is an app which you can get information and add your favourites the countries.
You can download app and install from **[here.](https://github.com/SaidAtmaca/Countries/raw/master/apks/contries-14-11-2022_v2.apk)** 

## Tech Stack

- Developed with **Kotlin.**
- Used **Coil Library** as SVG Image loader.
- Used **Navigation Component** for flow.
- Used **Retrofit** for getting data from api.


## The Implementation
I start with creating navigation_graph. I implemented necessary libraries.

<p align="left" style="padding: 10px">
<img src="https://user-images.githubusercontent.com/54797582/201536018-0592611d-6fac-4368-97e7-3c9c95eac89b.png" width="480">
</p>

Then created **navigation map.**

![7](https://user-images.githubusercontent.com/54797582/201536148-2f98a338-7704-4eed-86be-66863e0dff07.png)

After create algorithm , I desinged main screen. I used **bottom navigation menu** in main screen. The app has three different fragment. Home, Saved and Detail fragment.
In home fragment, there is a recyclerview and toolbar. I get country data from API and list on Recyclerview in home fragment also in saved fragment.


<table><tr>
<td> 
  <p align="center" style="padding: 10px">
    <img alt="Forwarding" src="https://user-images.githubusercontent.com/54797582/201540322-484826fb-14a5-40b1-9964-b053c109a8b0.png" width="400">
    <br>
    <em style="color: grey">Home</em>
  </p> 
</td>
<td> 
  <p align="center">
    <img alt="Routing" src="https://user-images.githubusercontent.com/54797582/201540326-ada67530-bc6d-4b0d-a746-e9e4efe414ce.png" width="400">
    <br>
    <em style="color: grey">Saved</em>
  </p> 
</td>
</tr></table>

Then I designed Detail Screen. I get country code, name, wikiDataId, flagImageUri. Images which i get over api are in svg format.
I load svg images with **coil library**. I set a listener on "for more information button" to go to wikiData page.

<p align="left" style="padding: 10px">
<img src="https://user-images.githubusercontent.com/54797582/201540534-aa67e52a-4478-42dc-8b15-a980c3d8d869.png" width="520">
</p>

<table><tr>
<td> 
  <p align="center" style="padding: 10px">
    <img src="https://user-images.githubusercontent.com/54797582/201541505-cfde1aee-c738-4194-9aaf-0ab85bcc87dc.png" width="400">
    <br>
    <em style="color: grey">Loading</em>
  </p> 
</td>
<td> 
  <p align="center">
    <img src="https://user-images.githubusercontent.com/54797582/201541513-2136e430-9c11-4f2a-9dc6-2b5d03a32444.png" width="400">
    <br>
    <em style="color: grey">Loaded</em>
  </p> 
</td>
</tr></table>

<table align="center"><tr>
<td> 
  <p align="center" style="padding: 10px">
    <img src="https://user-images.githubusercontent.com/54797582/201541886-ccfda60f-3397-4e93-909a-21dfdf86efdd.png" width="400">
    <br>
    <em style="color: grey">Wiki Data Page</em>
  </p> 
</td>
</tr></table>

> I have included more detailed explanations in the code.

## For Development

- Open this repository with Android Studio
- You need to add **Rapid API Key.** You can get Api Key from [here.](https://rapidapi.com/)




