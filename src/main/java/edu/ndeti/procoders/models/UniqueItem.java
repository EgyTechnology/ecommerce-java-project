package edu.ndeti.procoders.models;

public abstract class UniqueItem {
    protected String identifier = null;

    public void setIdentifier(String identifier) {
        if (this.identifier == null)
            this.identifier = identifier;
    }

    public String getIdentifier() {
        if (identifier == null)
            return "";

        return identifier;
    }
}
