# Design hub realtime plugin example implementation for Java
Please see also doc here: 
https://docs.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugins.md

## How to implement a realtime plugin for Design hub
The only thing you need to do is to implement `com.chemaxon.designhub.plugin.interfaces.RealtimePluginInterface`
Source:  [RealtimePluginInterface](../docs/RealtimePluginInterface.java)

The best way is to use this project and edit [PluginLogic.java](src/main/java/com/chemaxon/designhub/plugin/exampleimpl/PluginLogic.java) file.

## Chemaxon hub authentication
To be able to build this example project hub.chemaxon.com setup as Maven repository must be provided. See documentation [here](https://docs.chemaxon.com/display/docs/public-repository.md#src-1806243-publicrepository-gradle) and example [gradle.properties](gradle.properties)

## Example
```
@Component
public class PluginLogic implements RealtimePluginInterface<PluginSettings> {

    final PluginSettings myDefaultSettings = new PluginSettings(
            new BooleanPluginSetting("Example boolean plugin setting", true),
            new StringPluginSetting("Example string plugin setting", "some default string")
    );

    final Logger logger = LoggerFactory.getLogger(PluginLogic.class);

    public ResultSet getResultSet(String structure, String pinnedStructure, PluginSettings settings, Object context) {

        logger.debug("We just received some data!");

        //Remove lines 28 - 32 and insert you client data generating code here
        ExampleClientData clientData = new ExampleClientData();
        clientData.setAttributeA("Hello");
        clientData.setAttributeB(123456L);
        clientData.setAttributeC(settings.getS1().getValue());
        clientData.setAttributeD(settings.getS2().getValue());

        //Return null in the case this plugin should not provide any report data
        //Remove lines 37 - 38 and insert you report data generating code here as key/value map
        HashMap<String, String> reportData = new HashMap<>();
        reportData.put("AttributeA", "1234");
        reportData.put("AttributeB", "Some nice text");

        // no report: new ResultSet(clientData,  null);
        return new ResultSet(clientData, reportData);
    }

    @Override
    public PluginSettings getSettings() {
        return myDefaultSettings;
    }

    @Override
    public String getLabel() {
        return "Example plugin label";
    }

    @Override
    public String getName() {
        return "example-plugin-name";
    }

    @Override
    public String getTemplate() {
        // For more details see docs: https://docs.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugin-templates.md
        return "<div>" +
                "<p>{{client.attributeA}}</p>" +
                "<p>{{client.attributeB}}</p>" +
                "<p>{{client.attributeC}}</p>" +
                "<p>{{client.attributeD}}</p>" +
                "</div>";
    }
}

```

## Build and run

#### Build jar
`gradlew bootJar`

#### Run the plugin listening on port 9990
`java -jar build\libs\example-implementation-1-1.0-SNAPSHOT.jar`

#### Add this to config.json for Design hub
`"remoteServices": ["http://localhost:9990"]`
