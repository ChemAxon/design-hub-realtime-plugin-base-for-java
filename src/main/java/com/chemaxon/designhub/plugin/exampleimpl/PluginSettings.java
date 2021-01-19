package com.chemaxon.designhub.plugin.exampleimpl;

import com.chemaxon.designhub.plugin.settings.types.BooleanPluginSetting;
import com.chemaxon.designhub.plugin.settings.types.NumberPluginSetting;
import com.chemaxon.designhub.plugin.settings.types.StringPluginSetting;
import com.chemaxon.designhub.plugin.settings.util.CustomSettings;

public class PluginSettings implements CustomSettings {

    private BooleanPluginSetting reportEnabled;
    private StringPluginSetting niceString;
    private NumberPluginSetting atomCount;

    public PluginSettings() {
        // Always keep no args constructor.
    }

    public PluginSettings(BooleanPluginSetting reportEnabled, StringPluginSetting niceString, NumberPluginSetting atomCount) {
        this.reportEnabled = reportEnabled;
        this.niceString = niceString;
        this.atomCount = atomCount;
    }

    public BooleanPluginSetting getReportEnabled() {
        return reportEnabled;
    }

    public void setReportEnabled(BooleanPluginSetting reportEnabled) {
        this.reportEnabled = reportEnabled;
    }

    public StringPluginSetting getNiceString() {
        return niceString;
    }

    public void setNiceString(StringPluginSetting niceString) {
        this.niceString = niceString;
    }

    public NumberPluginSetting getAtomCount() {
        return atomCount;
    }

    public void setAtomCount(NumberPluginSetting atomCount) {
        this.atomCount = atomCount;
    }
}