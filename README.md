# Pintereach API Documentation
#### Base URL: http://unit4-bw.herokuapp.com

## Table of Contents

- [Pintereach API Documentation](#pintereach-API-Documentation) 
    - [User related endpoints](#user-related-endpoints)
        - [`POST /login` - Authenticate user](#post-login---Authenticate-user)
        - [`GET /logout` - Logout ](#get-logout---logout)
        - [`POST /createnewuser` - Create new user](#post-createnewuser---create-new-user)
        - [`GET /getuserinfo` - Get user info](#get-getuserinfo---get-user-info)
        - [`GET /users/users` - Get all users](#get-usersusers---get-all-users)
        - [`GET /users/user/name/{username}` - Get user by username](#get-usersusernameusername---get-user-by-username)
    - [Article related endpoints](#article-related-endpoints)
        - [`GET /userarticles/userarticles` - Get all articles](#get-userarticlesuserarticles---get-all-articles)
        - [`GET /userarticles/userarticle/{userarticleid}` - Get article by id](#get-userarticlesuserarticleuserarticleid---get-article-by-id)
        - [`POST /userarticles/user/article` - Add an article](#post-userarticlesuserarticle---add-an-article)
        - [`PUT /userarticles/userarticle/{userarticleid}` - Edit an article](#put-userarticlesuserarticleuserarticleid---edit-an-article)
        - [`DELETE /userarticles/userarticle/{userarticleid}` - Delete an article](#delete-userarticlesuserarticleuserarticleid---delete-an-article)
    - [Front End set up JavaScript](#front-end-set-up-javascript)



## User related endpoints

### `POST /login` - Authenticate user

Authenticates the user and returns an authentication token.

**Request URL**

```
http://unit4-bw.herokuapp.com/login?grant_type=password&username=${username}&password=${password}
```

**Request Headers**

```
Authorization: `Basic ${btoa("lambda-client:lambda-secret")}`,
"Content-Type": "application/x-www-form-urlencoded"
```

### `GET /logout` - Logout 
#### Available to all users

Log out current user

### `POST /createnewuser` - Create new user
#### Available to all

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
#### Available to all users

Get current user's info

### `GET /users/users` - Get all users
#### Available to admins only

Retrieve a list of all users

**Note:** This is only available to admins, not all users. I will not be changing this one.

### `GET /users/user/name/{username}` - Get user by username
#### Available to admin only

Retrieves a user by the username passed into the endpoint url

## Article related endpoints

### `GET /userarticles/userarticles` - Get all articles
#### Available to all users

Retrieve a list of all articles
**Note:** This is currently only available to admins, not all users. I'll change that later today but the api will be down for a bit while I'm making the change

### `GET /userarticles/userarticle/{userarticleid}` - Get article by id
#### Available to admin only

Retrieve an article by the userarticleid passed into the endpoint url

### `POST /userarticles/user/article` - Add an article
#### Available to all users

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

### `PUT /userarticles/userarticle/{userarticleid}` - Edit an article
#### Available to all users

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

### `DELETE /userarticles/userarticle/{userarticleid}` - Delete an article
#### Available to all users

Delete an article by passing its ID into the endpoint url

## Front End set up JavaScript

**Note:** the link below has templates for axiosWithAuth, ProtectedRoute, and Login.

#### For an example of accessing this application with a JavaScript Front End see the repository (https://github.com/LambdaSchool/java-js-front-end.git)
