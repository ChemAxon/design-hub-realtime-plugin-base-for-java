package com.chemaxon.designhub.plugin.exampleimpl;

import com.chemaxon.designhub.plugin.settings.types.BooleanPluginSetting;
import com.chemaxon.designhub.plugin.settings.types.StringPluginSetting;
import com.chemaxon.designhub.plugin.settings.util.CustomSettings;

public class PluginSettings extends CustomSettings {
    BooleanPluginSetting s1;
    StringPluginSetting s2;

    public PluginSettings() {
        // Always keep no args constructor.
    }

    public PluginSettings(BooleanPluginSetting s1, StringPluginSetting s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public BooleanPluginSetting getS1() {
        return s1;
    }

    public StringPluginSetting getS2() {
        return s2;
    }

    public void setS1(BooleanPluginSetting s1) {
        this.s1 = s1;
    }

    public void setS2(StringPluginSetting s2) {
        this.s2 = s2;
    }
}