# Simple Spring Boot API

This is a simple set-up for a Spring Boot API, including a PATCH functionality as well as a custom error page.

## PATCH support

The PatchMapping that supports several patch request with just one endpoint and no extra "patchDTO" needed, can be found
in the ProfessorController class.

### Why?

This way we get a true "PATCH" operation, so that the end-user doesn't need to send all the fields of the object when
they only need to update just one of these fields.  
It also means we don't have to write separate logic (or DTO's) for the different fields on a certain class.

### How?

The code below accepts the request, including the "patch" operations.
See this link for more info on those: https://jsonpatch.com/

```java
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
@ResponseStatus(HttpStatus.OK)
public ProfessorDTO patchProfessor(@PathVariable String id,@RequestBody JsonPatch patch)throws JsonPatchException,JsonProcessingException{
        return professorService.patchProfessor(id,patch);
        }
```

Note: this endpoint consumes "application/json-patch+json" so we need to set this in our request.  
In Postman for instance, you can do this by adding a header -> `Content-Type: application/json-patch+json`

Note: you also need to add the following dependency to your pom/xml

```xml

<dependency>
    <groupId>com.github.java-json-tools</groupId>
    <artifactId>json-patch</artifactId>
    <version>1.13</version>
</dependency>
```

Here is the code in the ProfessorService that handles the patching:

```java
    public ProfessorDTO patchProfessor(String id,JsonPatch patch)throws JsonPatchException,JsonProcessingException{
        Professor professorToPatch=professorRepository.getProfessorById(id);
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(professorToPatch,JsonNode.class));
        return toDto(professorRepository.updateProfessor(objectMapper.treeToValue(patched,ProfessorDTO.class)));
        }
```

In short, this is what it does:

1. First we search for the item (in this case Professor) we want to change
2. Create a new instance of the ObjectMapper class (from the Jackson library)
3. The following line does a few things:
    1. We convert the Professor to a JsonNode type (using the objectmapper)
    2. We then apply the patch operations to this JsonNode
    3. Finally this returns a JsonNode object, that is patched with the new values
4. Finally:
    1. We convert this JsonPatch object back to a class from our application (in this case the ProfessorDTO we need to
       update the Professor)
        2. We call the updateProfessor method with this new DTO to actually update the values in our repository.  
           (so far this was only patched in the JsonNode object, but not yet in the actual repo)
        3. We finally convert the Professor that is returned from the update method back into a ProfessorDTO, as this is
           what we'll pass back to the controller.  
           (this step can be more efficient as we're now converting a lot :) )

## Custom error page

This example also uses a custom error page for your REST errors.

### Set-up

To make this work with Spring Boot, we add the following dependency to the pom file:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

This allows us to use html rendering in our Spring application.

To have a custom error page for all possible errors, create a file called `error.html` in the following folder:
`resources > templates`

In this file, we can add HTML, as well as a link to a separate .css file. (I went for the quick and dirty solution of
inline styles in this example).  
You can access the error status code, type, message and path using variables, for instance `${error}`

Add this line to the application.properties file to override the default white label
page: `server.error.whitelabel.enabled=false`  
Spring Boot will now look for custom error pages first (see part below) and finally settle on the default error page.

### Custom pages per error

We can also add custom pages for certain error status codes, eg: 404.
To do this, we need to add a html file with the error code as name (eg: `404.html`) in the following folder (create if
not yet there): `resources > public > error`

*PS: this repo is intended for my fellow Java students, and is in no way a "best practice" guide for experienced devs.*