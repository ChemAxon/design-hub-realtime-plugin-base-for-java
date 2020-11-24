package com.chemaxon.designhub.plugin.exampleimpl;

class ClientData {
    double exactMass;
    double mass;
    int massPrecision;
    int atomCount1;
    int atomCount2;
    int atomCount3;
    String formula;
    String isotopeFormula;
    String composition;
    String isotopeComposition;
    int customAtomCount;
    int settingOfCustomAtomCount;

    public int getSettingOfCustomAtomCount() {
        return settingOfCustomAtomCount;
    }

    public void setSettingOfCustomAtomCount(int settingOfCustomAtomCount) {
        this.settingOfCustomAtomCount = settingOfCustomAtomCount;
    }

    public int getCustomAtomCount() {
        return customAtomCount;
    }

    public void setCustomAtomCount(int customAtomCount) {
        this.customAtomCount = customAtomCount;
    }

    public double getExactMass() {
        return exactMass;
    }

    public void setExactMass(double exactMass) {
        this.exactMass = exactMass;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getMassPrecision() {
        return massPrecision;
    }

    public void setMassPrecision(int massPrecision) {
        this.massPrecision = massPrecision;
    }

    public int getAtomCount1() {
        return atomCount1;
    }

    public void setAtomCount1(int atomCount1) {
        this.atomCount1 = atomCount1;
    }

    public int getAtomCount2() {
        return atomCount2;
    }

    public void setAtomCount2(int atomCount2) {
        this.atomCount2 = atomCount2;
    }

    public int getAtomCount3() {
        return atomCount3;
    }

    public void setAtomCount3(int atomCount3) {
        this.atomCount3 = atomCount3;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getIsotopeFormula() {
        return isotopeFormula;
    }

    public void setIsotopeFormula(String isotopeFormula) {
        this.isotopeFormula = isotopeFormula;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getIsotopeComposition() {
        return isotopeComposition;
    }

    public void setIsotopeComposition(String isotopeComposition) {
        this.isotopeComposition = isotopeComposition;
    }
}
