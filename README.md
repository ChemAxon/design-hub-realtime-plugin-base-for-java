# Design hub realtime plugin example implementation for Java
Please see also doc here: 
https://docs.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugins.md

## How to implement a realtime plugin for Design hub
The only thing you need to do is to implement `com.chemaxon.designhub.plugin.interfaces.RealtimePluginInterface`
Source:  [RealtimePluginInterface](../docs/RealtimePluginInterface.java)

Most simple way is to use this project and edit [PluginLogic.java](src/main/java/com/chemaxon/designhub/plugin/exampleimpl/PluginLogic.java) file.

## Chemaxon hub authentication
To be able to build this example project credentials for hub.chemaxon.com Maven repository must be configured. See documentation [here](https://docs.chemaxon.com/display/docs/public-repository.md#src-1806243-publicrepository-gradle) and example [gradle.properties](gradle.properties)

## Example
```

```

## Build and run

#### Build jar
`gradlew bootJar`

#### Run the plugin listening on port 9990
`java -jar build\libs\example-implementation-1-1.0-SNAPSHOT.jar`

or run plugin using Gradle task ```gradlew runPlugin```

#### Run tests
Run JUnit tests using ```gradlew test```

To test your plugin feel free to get insired by these curl commands:

**GET**
```
curl --location --request GET 'http://localhost:9990/'
```

**POST /update**
```
curl --location --request POST 'http://localhost:9990/update' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   	"structure" : "CN1C=NC2=C1C(=O)N(C(=O)N2C)C",
   	"pinnedStructure" : "",
   	"context" : {},
   	"settings": {
       "Enable report data": true,
       "Put some nice string here": "ChemAxon is cool",
       "Custom atom count" : 10
     }
   }
'
```

#### Design Hub configuration 
To add this plugin to Design Hub to add this line to config.json. Replace `localhost:port` with the actual address the plugin is listening on.

```"remoteServices": ["http://localhost:9990"]```
