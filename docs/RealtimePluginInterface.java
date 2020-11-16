package com.chemaxon.designhub.plugin.interfaces;

import com.chemaxon.designhub.plugin.settings.util.CustomSettings;
import lombok.NonNull;

public interface RealtimePluginInterface<T extends CustomSettings> {
    /**
     * Implement code that does plugin calculation and returns calculated data. Client data is accessible in JavaScript code
     * provided by getTemplate method. Report data can be are added into a report based in report property setting in Design Hub.
     *
     * @param structure       MRV formatted structure as string.
     * @param pinnedStructure MRV formatted pinned structure as string. Can be null if no pinned structure is provided.
     * @param settings        If provided by getSetting these are actual values of your setting that are set up
     *                        in Design Hub.
     * @param context         The rest of calculation request provided by Design Hub containing raw data.
     * @return Results set containing two parts: Client data is an object that is send to Design Hub and is available for JavaScript code in template given by getTemplate.
     * Second - report data is a Map of key/value to be copied to reports (exports). If not report data is needed this field can be null.
     */
    ResultSet getResultSet(String structure, String pinnedStructure, T settings, Object context);

    /**
     * @return Null in no settings are required or return an instance of class extending
     * com.chemaxon.designhub.plugin.settings.util.CustomSettings with fields
     * chosen from com.chemaxon.designhub.plugin.settings.types.BooleanPluginSetting,
     * com.chemaxon.designhub.plugin.settings.types.EnumPluginSetting,
     * com.chemaxon.designhub.plugin.settings.types.NumberPluginSetting,
     * com.chemaxon.designhub.plugin.settings.types.StringPluginSetting containing labels (name of setting visible
     * to the user) and default values. These settings will be visible in Design Hub and can be used to configure
     * this plugin from Design Hub.
     */
    default T getSettings() {
        return null;
    }

    /**
     * Not supported at this moment. Will be used to separate multiple plugin implementations in the same running
     * Spring application.
     *
     * @return an empty string.
     */
    default @NonNull String getPath() {
        return "";
    }

    /**
     * @return Name of this plugin. Will be visible to user in Design Hub.
     */
    @NonNull String getLabel();

    /**
     * @return Name of the plugin used to store plugin setup in Design Hub. Not visible to the user.
     */
    @NonNull String getName();

    /**
     * @return Piece of JavaScript code used to display data in Design Hub.
     * Please see https://docs.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugin-templates.md
     */
    @NonNull String getTemplate();

    /**
     * @return Array of strings with domains in Design Hub to limit for whose domains shall this plugin be used.
     * Value {"*"} used by default means use plugin in all domains.
     */
    default @NonNull String[] getDomains() {
        return new String[]{"*"};
    }
}
