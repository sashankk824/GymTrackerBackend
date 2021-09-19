# GymTrackerBackend

Contains the code of the Spring Backend used to communicate with the Postgres DB and React Frontend. The Spring application defines the model classes and variables for 
gym member and machine data objects and exposes an API through RESTControllers to return the list of all gym members, list of all lifting records, a particular gym member, latest exercise details for a gym member,
and authentication of the passed-in username and password from the frontend against the gym member data in the database as well as the ability to add gym members or lifting records by 
mapping GET and POST reqeusts. (Located in the src/main/java/com/sashank/gymtracker)
