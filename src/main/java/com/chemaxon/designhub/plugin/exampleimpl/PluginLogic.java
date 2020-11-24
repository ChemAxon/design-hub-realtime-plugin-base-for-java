package com.chemaxon.designhub.plugin.exampleimpl;

import chemaxon.calculations.ElementalAnalyser;
import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;
import com.chemaxon.designhub.plugin.interfaces.RealtimePluginInterface;
import com.chemaxon.designhub.plugin.interfaces.ResultSet;
import com.chemaxon.designhub.plugin.settings.types.BooleanPluginSetting;
import com.chemaxon.designhub.plugin.settings.types.NumberPluginSetting;
import com.chemaxon.designhub.plugin.settings.types.StringPluginSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

@Component
public class PluginLogic implements RealtimePluginInterface<PluginSettings> {

    final PluginSettings myDefaultSettings = new PluginSettings(
            new BooleanPluginSetting("Enable report data", true),
            new StringPluginSetting("Put some nice string here", ""),
            new NumberPluginSetting("Custom atom count", 4));

    ElementalAnalyser elementalAnalyser = new ElementalAnalyser();

    final Logger logger = LoggerFactory.getLogger(PluginLogic.class);

    public ResultSet getResultSet(String structure, String pinnedStructure, PluginSettings settings, Object context) {
        logger.debug("We just received some data!");
        try {
            MolImporter mi = new MolImporter(new ByteArrayInputStream(structure.getBytes()));
            Molecule mol = null;
            mol = mi.read();

            elementalAnalyser.setMolecule(mol);

            double exactMass = elementalAnalyser.exactMass();
            double mass = elementalAnalyser.mass();
            int massPrecision = elementalAnalyser.massPrecision();
            int atomCount1 = elementalAnalyser.atomCount(8); // oxygen atom count
            int atomCount2 = elementalAnalyser.atomCount(8, 0); // non-isotope oxygen count
            int atomCount3 = elementalAnalyser.atomCount(8, 16); // oxygen isotope count with massno=16
            String formula = elementalAnalyser.formula();
            String isotopeFormula = elementalAnalyser.isotopeFormula();
            String composition = elementalAnalyser.composition(2); // precision=2
            String isotopeComposition = elementalAnalyser.isotopeComposition(2); // precision=2

            // now use the results...

            ClientData clientData = new ClientData();

            clientData.setAtomCount1(atomCount1);
            clientData.setAtomCount2(atomCount2);
            clientData.setAtomCount3(atomCount3);

            clientData.setFormula(formula);
            clientData.setIsotopeFormula(isotopeFormula);
            clientData.setComposition(composition);
            clientData.setIsotopeComposition(isotopeComposition);

            // using settings
            int customAtomCountSetting = settings.getAtomCount().getValue().intValue();
            clientData.setCustomAtomCount(elementalAnalyser.atomCount(customAtomCountSetting));
            clientData.setSettingOfCustomAtomCount(customAtomCountSetting);

            if (settings.getReportEnabled().getValue()) {
                HashMap<String, Object> reportData = new HashMap<>();
                reportData.put("Oxygen atom count", atomCount1);
                reportData.put("Non-isotope oxygen count", atomCount2);
                reportData.put("Some boolean data", true);
                reportData.put("Some string data", "Report data!");
                return new ResultSet(clientData, reportData);
            }
            //Return null in the case this plugin should not provide any report data
            return new ResultSet(clientData, null);

        } catch (Exception e) {
            logger.error("There was an error processing this calculation structure: {}", structure);
            logger.error(e.getMessage());
            return new ResultSet(null, null);
        }
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
        return "example-java-plugin-name";
    }

    @Override
    public String getTemplate() {
        // For more details see docs: https://docs.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugin-templates.md
        return "<div>\n" +
                "  <p>Oxygen atom count: {{client.atomCount1}}</p>\n" +
                "  <p>Non-isotope oxygen count: {{client.atomCount2}}</p>\n" +
                "  <p>Oxygen isotope count with massno=16: {{client.atomCount3}}</p>\n" +
                "  <p>Custom set ({{client.settingOfCustomAtomCount}}) atom count: {{client.customAtomCount}}</p>\n" +
                "  <p>Formula: {{client.formula}}</p>\n" +
                "  <p>Isotope formula: {{client.isotopeFormula}}</p>\n" +
                "  <p>Composition: {{client.composition}}</p>\n" +
                "  <p>Isotope composition: {{client.isotopeComposition}}</p>\n" +
                "</div>";
    }
}
