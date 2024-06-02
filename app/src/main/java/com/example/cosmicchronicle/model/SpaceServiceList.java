package com.example.cosmicchronicle.model;

import java.util.List;

public class SpaceServiceList {
    private String id;
    private String name;
    private String englishName;
    private boolean isPlanet;
    private List<Moon> moons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public boolean isPlanet() {
        return isPlanet;
    }

    public void setPlanet(boolean planet) {
        isPlanet = planet;
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public void setMoons(List<Moon> moons) {
        this.moons = moons;
    }

    public double getSemimajorAxis() {
        return semimajorAxis;
    }

    public void setSemimajorAxis(double semimajorAxis) {
        this.semimajorAxis = semimajorAxis;
    }

    public double getPerihelion() {
        return perihelion;
    }

    public void setPerihelion(double perihelion) {
        this.perihelion = perihelion;
    }

    public double getAphelion() {
        return aphelion;
    }

    public void setAphelion(double aphelion) {
        this.aphelion = aphelion;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    public Volume getVol() {
        return vol;
    }

    public void setVol(Volume vol) {
        this.vol = vol;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getEscape() {
        return escape;
    }

    public void setEscape(double escape) {
        this.escape = escape;
    }

    public double getMeanRadius() {
        return meanRadius;
    }

    public void setMeanRadius(double meanRadius) {
        this.meanRadius = meanRadius;
    }

    public double getEquaRadius() {
        return equaRadius;
    }

    public void setEquaRadius(double equaRadius) {
        this.equaRadius = equaRadius;
    }

    public double getPolarRadius() {
        return polarRadius;
    }

    public void setPolarRadius(double polarRadius) {
        this.polarRadius = polarRadius;
    }

    public double getFlattening() {
        return flattening;
    }

    public void setFlattening(double flattening) {
        this.flattening = flattening;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public double getSideralOrbit() {
        return sideralOrbit;
    }

    public void setSideralOrbit(double sideralOrbit) {
        this.sideralOrbit = sideralOrbit;
    }

    public double getSideralRotation() {
        return sideralRotation;
    }

    public void setSideralRotation(double sideralRotation) {
        this.sideralRotation = sideralRotation;
    }

    public AroundPlanet getAroundPlanet() {
        return aroundPlanet;
    }

    public void setAroundPlanet(AroundPlanet aroundPlanet) {
        this.aroundPlanet = aroundPlanet;
    }

    public String getDiscoveredBy() {
        return discoveredBy;
    }

    public void setDiscoveredBy(String discoveredBy) {
        this.discoveredBy = discoveredBy;
    }

    public String getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(String discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public double getAxialTilt() {
        return axialTilt;
    }

    public void setAxialTilt(double axialTilt) {
        this.axialTilt = axialTilt;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMainAnomaly() {
        return mainAnomaly;
    }

    public void setMainAnomaly(double mainAnomaly) {
        this.mainAnomaly = mainAnomaly;
    }

    public double getArgPeriapsis() {
        return argPeriapsis;
    }

    public void setArgPeriapsis(double argPeriapsis) {
        this.argPeriapsis = argPeriapsis;
    }

    public double getLongAscNode() {
        return longAscNode;
    }

    public void setLongAscNode(double longAscNode) {
        this.longAscNode = longAscNode;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    private double semimajorAxis;
    private double perihelion;
    private double aphelion;
    private double eccentricity;
    private double inclination;
    private Mass mass;
    private Volume vol;
    private double density;
    private double gravity;
    private double escape;
    private double meanRadius;
    private double equaRadius;
    private double polarRadius;
    private double flattening;
    private String dimension;
    private double sideralOrbit;
    private double sideralRotation;
    private AroundPlanet aroundPlanet;
    private String discoveredBy;
    private String discoveryDate;
    private String alternativeName;
    private double axialTilt;
    private double avgTemp;
    private double mainAnomaly;
    private double argPeriapsis;
    private double longAscNode;
    private String bodyType;
    private String rel;
}

class Moon {
    private String moon;
    private String rel;

}

class Mass {
    private double massValue;
    private int massExponent;

    public double getMassValue() {
        return massValue;
    }

    public void setMassValue(double massValue) {
        this.massValue = massValue;
    }

    public int getMassExponent() {
        return massExponent;
    }

    public void setMassExponent(int massExponent) {
        this.massExponent = massExponent;
    }
}

class Volume {
    private double volValue;
    private int volExponent;

    public double getVolValue() {
        return volValue;
    }

    public void setVolValue(double volValue) {
        this.volValue = volValue;
    }

    public int getVolExponent() {
        return volExponent;
    }

    public void setVolExponent(int volExponent) {
        this.volExponent = volExponent;
    }
}

class AroundPlanet {
    private String planet;
    private String rel;

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
