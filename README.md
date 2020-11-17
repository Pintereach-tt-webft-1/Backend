#Logout
##GET 
###http://unit4-bw.herokuapp.com/logout

#Create new user
##POST 
###http://unit4-bw.herokuapp.com/createnewuser
####Pass in:
```json
{
    "username": username,
    "password": password,
    "primaryemail": primaryemail
}
```


#Get current user info
##GET 
###http://unit4-bw.herokuapp.com/users/getuserinfo

#Get all articles
####Note: This is currently only available to admins, not all users. I'll change that later 
today but the api will be down for a bit while I'm making the change
##GET
###http://unit4-bw.herokuapp.com/userarticles/userarticles

#Delete article
##DELETE
###http://unit4-bw.herokuapp.com/userarticles/userarticle/{userarticleid}

#Update article
##PUT
###http://unit4-bw.herokuapp.com/userarticles/userarticle/{userarticleid}
####Pass in
```json
{
    "userarticleid": userarticleid,
    "articletitle": articletitle,
    "category": category,
    "priority": priority,
    "user": {}
}
```
####Note: The user object can be empty, it just needs to be there

#Add article
##POST
###http://unit4-bw.herokuapp.com/userarticles/user/article
####Pass in
```json
{
    "userarticleid": userarticleid,
    "articletitle": articletitle,
    "category": category,
    "priority": priority,
    "user": {}
}
```
####Note: The user object can be empty, it just needs to be there

# java-springfoundation

For an example of accessing this application with a JavaScript Front End see the repository (https://github.com/LambdaSchool/java-js-front-end.git)
