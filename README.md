# Lunch Location Randomizer - BE
## Problem Statement
Team members often face the challenge of collectively deciding on a lunch location. This process can be time-consuming and may lead to disagreements, especially when there are varying preferences within the team. To streamline this decision-making process, we have developed a Lunch Location Randomizer application.

## Technologies Used
- ![Spring Boot](https://img.icons8.com/color/48/000000/spring-logo.png) Spring Boot
- ![Java](https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png) Java
- ![H2 Database](https://img.icons8.com/color/48/000000/database-restore.png) H2 (for database)
- Mockito / Junit (for testing)

## Description / Features
The Lunch Location Randomizer BE is a spring-boot application that provides backend services for a Lunch-Location-Randomizer application. This application works together with a React application that serves as the FE.
- <b>Randomize Selection:</b>
  - <b>EndPoint:</b> `Post` `/lunch/randomiseOptions`
  - <b>Purpose:</b> Generate a random location from a list of provided options
  - <b>Request Body:</b>
    ```js
    [{"options" : "restaruantA"},{"options" : "restaruantB"}, {"options" : "restaruantC"}]
    ```
  - <b>Response Body:</b>
    ```js
    {
    "result": "Success",
    "data": {
        "list": {
            "lunchRecordId": null,
            "optionsList": [
                {
                    "restaurantName": "Restaurant A",
                    "locationLink": null
                },
                {
                    "restaurantName": "Restaurant B",
                    "locationLink": null
                },
                {
                    "restaurantName": "Restaurant C",
                    "locationLink": null
                }
            ],
            "finalLocation": "Restaurant A",
            "createdDateTime": "14/09/2023"
        }
    },
    "errorMessage": "",
    "errorCode": ""
    }
    ```
  - <b>Get all Records:</b>
      - <b>EndPoint:</b> `Get` `/lunch/allRecords`
      - <b>Purpose:</b> Get all past lunch Records
      - <b>Request Body:</b> `null`
  - <b>Response Body:</b>
    ```js
    { "result": "Success", 
       "data": { 
        "list": [
            { "lunchRecordId": 6,
                "optionsList": [
                  {
                   "restaurantName": "Restaurant A",
                   "locationLink": null
                  },
                  {
                   "restaurantName": "Restaurant B",
                    "locationLink": null
                  },
                  {
                    "restaurantName": "Restaurant C",
                    "locationLink": null
                  }
               ],
              "finalLocation": "Restaurant A",
              "createdDateTime": "14/09/2023"
            },
            { "lunchRecordId": 7,
                 "optionsList": [
                   {
                    "restaurantName": "Restaurant F",
                    "locationLink": null
                   },
                   {
                    "restaurantName": "Restaurant E",
                    "locationLink": null
                   }
                 ],
                "finalLocation": "Restaurant E",
                "createdDateTime": "14/09/2023"
            }
        ]
      },
     "errorMessage": "",
     "errorCode": ""
    }
      ```
  - <b>Delete all records:</b>
      - <b>EndPoint:</b> `Post` `/lunch/deleteRecords`
      - <b>Purpose:</b> Delete all records
      - <b>Request Body:</b>
        ```js
         [{"recordsId" : "6"},{"recordsId" : "7"}]
        ```
      - <b>Response Body:</b>
        ```js
        { "result": "Success",
          "data": {
            "list": [
              { "lunchRecordId": 6,
                "optionsList": [
                    {
                      "restaurantName": "Restaurant A",
                      "locationLink": null
                    },
                    {
                      "restaurantName": "Restaurant B",
                      "locationLink": null
                    },
                    {
                      "restaurantName": "Restaurant C",
                      "locationLink": null
                    }
                ],
              "finalLocation": "Restaurant A",
              "createdDateTime": "14/09/2023"
            },
            { "lunchRecordId": 7,
                "optionsList": [
                   {
                     "restaurantName": "Restaurant F",
                     "locationLink": null
                   },
                   {
                     "restaurantName": "Restaurant E",
                     "locationLink": null
                   }
                ],
                "finalLocation": "Restaurant E",
                "createdDateTime": "14/09/2023"
            }
          ]
        },
        "errorMessage": "",
        "errorCode": ""
        }
        ```
## Usage
To Run the application in you local machine, follow these steps
### Prerequisites
Make sure you have the following installed:
- <b>Java 11 or higher</b>: Download and install it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or your preferred package manager.
- <b>Maven</b>: If you don't have Maven installed, download it from [Maven's official website](https://maven.apache.org/download.cgi) and follow the installation instructions.

### Installation & Setup
1. Open Command Prompt:
    - Press the Windows symbol key, type `cmd` and hit Enter to open Command Prompt.
2. Clone the Repository
    - Use teh `git clone` command to clone the Lunch Location Randomizer BE repository to your local machine:
    ```sh
    git clone <respository URl>
    ```
3. Navigate to the Project Folder
    - Change your current directory to the project folder:
    ```sh
    cd <project folder>
    ```
4. Build Application:
    - Use `mvn` to build the application using maven:
    ```sh
    mvn clean install
    ```
### Running the Application
Start the Spring-Boot application:
- Run the following command to start the application:
    ```sh
    mvn spring-boot:run
    ```
Once the development server is running, open your web browser and go to http://localhost:8080.
- Run the following command to run the unit test of the application:
    ```sh
    mvn test
    ```
## Room For Improvement / Future Features
There are many ways that this app can be improved upon! Here is a list of some ideas for future improvements:
- Features
  - Adding location link for the final location
      - Save a location link along with the final location
  - Allowing input across multiple devices
  - Add log in to tag the username to see who entered which options
  - Add creation of a link to sent to others
- Improvements
  - Code quality / re-usability
  - Custom Exception

## Author
- Name: Chester Ng
- Email: [chesternyk19@gmail.com](mailto:chesternyk19@gmail.com)
- LinkedIn Profile: [linkedin.com/in/chester-ng-b81222214](https://www.linkedin.com/in/chester-ng-b81222214)