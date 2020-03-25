# City-Weather
Simple demo application that showcases synchronising weather information from server using best practices

# App Screenshot

<table>
  <tr>
    <td>City Weather View Success</td>
    <td>City Weather View Failure</td>
  </tr>
  <tr>
    <td><img src="Screenshots/city_weather_success.png" width=230 height=480></td>
    <td><img src="Screenshots/city_weather_failure.png" width=230 height=480></td>
  </tr>
</table>
 
# Project Description
 
### Design Pattern - MVVM
This project uses the MVVM design pattern. Business logic is written in the viewmodel class, data is supplied to the viewmodel class via model class and user interface is updated in view class. Databinding is used for the ui to update automatically when the data changes in the viewmodel class. This also helps writing the unit test as only viewmodel classes has to be tested being the only section responsible for business logic.
 
### Data Binding - LiveData
Views are directly bound to data via LiveData and DataBinding that will help the UI to observe for data changes and thus update automatically when the data changes.

### Coroutines
Coroutines is used in for downloading the data in IO thread and update the UI in Main thread seamlessly

### Retrofit
Retrofit is used for writing web service client
 
### Dependency Injection - Koin
Viewmodel and RetrofitClient classes are declared as module and injected to the activity helping segragating the object definitions to the respective module
