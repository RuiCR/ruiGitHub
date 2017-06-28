package edu.fjnu.cr.domain;

public class Dictionary {
    private Integer dictId;

    private Integer dictType;

    private String dictTypeName;

    private String dictName;

    private Integer dictParent;

    private String dictParents;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public Integer getDictType() {
        return dictType;
    }

    public void setDictType(Integer dictType) {
        this.dictType = dictType;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName == null ? null : dictTypeName.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public Integer getDictParent() {
        return dictParent;
    }

    public void setDictParent(Integer dictParent) {
        this.dictParent = dictParent;
    }

    public String getDictParents() {
        return dictParents;
    }

    public void setDictParents(String dictParents) {
        this.dictParents = dictParents == null ? null : dictParents.trim();
    }
}