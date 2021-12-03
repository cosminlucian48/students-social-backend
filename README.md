# students-social-backend
At the moment you can:
<ul>
  <li>post a user (with a UserDetailsDto body json) and it will it will do 2 inserts in the h2 db:
  <ul>
    <li>1 in the user schema (email, password)</li>
    <li>1 in the user_details schema (firstName, lastName, university, registrationDate, userid (FK))</li>
  </ul>
  </li>
  
UserDetailsDto:
{
<ul>
    <li>"email":"test@gmail.com",</li>
    <li>"password":"123456",</li>
    <li>"firstName":"Andrei",</li>
    <li>"lastName":"Popescu",</li>
    <li>"university":"Universitate",</li>
    <li>"registrationDate": "2012-04-23T18:25:43.511Z"</li>
</ul>
}
  <li>
  do get request to print all elements in the users schema
  </li>
  <li>
    create a valid jwt (localhost:8080/authenticate with a json body of username:admin and password:admin) that you can use for 10 hours
  </li>

</ul>
  
