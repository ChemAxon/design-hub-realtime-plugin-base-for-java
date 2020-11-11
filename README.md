# Design hub realtime plugin example implementation for Java
Please see also doc here: 
https://d2.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugins.md

## How to implement a realtime plugin for Design hub
The only thing you need to do is to implement `com.chemaxon.designhub.plugin.interfaces.RealtimePluginInterface`
Source:  [RealtimePluginInterface](../docs/RealtimePluginInterface.java)

The best way is to use this project and edit [PluginLogic.java](src/main/java/com/chemaxon/designhub/plugin/exampleimpl/PluginLogic.java) file.

## Example
```
@Component
public class PluginLogic implements RealtimePluginInterface<PluginSettings> {

    final PluginSettings myDefaultSettings = new PluginSettings(
            new BooleanPluginSetting("Example boolean plugin setting", true),
            new StringPluginSetting("Example string plugin setting", "some default string")
    );

    final Logger logger = LoggerFactory.getLogger(PluginLogic.class);

    public Object getClientData(String structure, String pinnedStructure, PluginSettings settings, Object context) {

        logger.debug("We just received some data!");

        // todo Remove lines 28 - 32 and insert you client data generating code here
        ExampleClientData example = new ExampleClientData();
        example.setAttributeA("Hello");
        example.setAttributeB(123456L);
        example.setAttributeC(settings.getS1().getValue());
        example.setAttributeD(settings.getS2().getValue());
        return example;
    }

    // return null in the case this plugin should not provide any report data
    public Map<String, String> getReportData(String structure, String pinnedStructure, PluginSettings settings, Object context) {
        // todo Remove lines 40 - 41 and insert you report data generating code here as key/value map
        HashMap<String, String> report = new HashMap<>();
        report.put("AttributeA", "1234");
        report.put("AttributeB", "Some nice text");
        return report;
        //return null;
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
        // For more details see docs: https://d2.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugin-templates.md
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
