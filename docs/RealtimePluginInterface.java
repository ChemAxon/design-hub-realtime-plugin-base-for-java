package com.chemaxon.designhub.plugin.interfaces;

import com.chemaxon.designhub.plugin.settings.util.CustomSettings;
import lombok.NonNull;

import java.util.Map;

public interface RealtimePluginInterface<T extends CustomSettings> {
    /**
     * Implement code that does plugin calculation and return calculated data. Data are accessible in JavaScript code
     * provided by getTemplate method.
     *
     * @param structure       MRV formatted structure as string.
     * @param pinnedStructure MRV formatted pinned structure as string. Can be null if no pinned structure is provided.
     * @param settings        If provided by getSetting these are actual values of your setting that are set up
     *                        in Design Hub.
     * @param context         The rest of calculation request provided by Design Hub containing raw data.
     * @return Object that is send to Design Hub and is available for JavaScript code in template given by getTemplate.
     */
    Object getClientData(String structure, String pinnedStructure, T settings, Object context);

    /**
     * Return null if no report (export) data is needed.
     * Implement code that does plugin calculation and return map of key/value that will be copied into reports
     * (exports) of Design Hub.
     *
     * @param structure       MRV formatted structure as string.
     * @param pinnedStructure MRV formatted pinned structure as string. Can be null if no pinned structure is provided.
     * @param settings        If provided by getSetting these are actual values of your setting that are set up in
     *                        Design Hub.
     * @param context         The rest of calculation request provided by Design Hub containing raw data.
     * @return Map of key/value to be copied to reports (exports).
     */
    Map<String, String> getReportData(String structure, String pinnedStructure, T settings, Object context);

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
     * Please see https://d2.chemaxon.com/display/docs/design-hub-developer-guide-real-time-plugin-templates.md
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
