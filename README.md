# Pintereach API Documentation

## Endpoints

#### Base URL: http://unit4-bw.herokuapp.com

### `GET /logout` - Logout 

Log out current user

### `POST /createnewuser` - Create new user

Creates new user

**Request body**

```json
{
    "username": username,
    "password": password,
    "primaryemail": primaryemail
}
```

### `GET /getuserinfo` - Get user info

Get current user's info

### `GET /userarticles/userarticles` - Get all articles

Retrieve a list of all articles
**Note:** This is currently only available to admins, not all users. I'll change that later today but the api will be down for a bit while I'm making the change

### `DELETE /userarticles/userarticle/{userarticleid}` - Delete an article

Delete an article by passing its ID into the endpoint url

### `PUT /userarticles/userarticle/{userarticleid}` - Edit an article

Edit an article by passing its ID into the endpoint url

**Request Body**

```json
{
    "userarticleid": userarticleid,
    "articletitle": articletitle,
    "category": category,
    "priority": priority,
    "user": {}
}
```
**Note:** The user object can be empty, it just needs to be there

### `POST /userarticles/user/article` - Add an article

**Request Body**

```json
{
    "userarticleid": userarticleid,
    "articletitle": articletitle,
    "category": category,
    "priority": priority,
    "user": {}
}
```
**Note:** The user object can be empty, it just needs to be there

# java-springfoundation

*Note:* the link below has templates for axiosWithAuth, ProtectedRoute, and Login.

#### For an example of accessing this application with a JavaScript Front End see the repository (https://github.com/LambdaSchool/java-js-front-end.git)
